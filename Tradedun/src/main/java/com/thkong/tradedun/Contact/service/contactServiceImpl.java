package com.thkong.tradedun.Contact.service;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Common.FileLib;
import com.thkong.tradedun.Common.MailLib;
import com.thkong.tradedun.Common.vo.Attach;
import com.thkong.tradedun.Contact.dao.contactDao;
import com.thkong.tradedun.Contact.vo.Contact;

@Transactional
@Service
public class contactServiceImpl implements contactService{

	@Autowired
	contactDao dao;
	
	@Autowired
	private FileLib fileLib;
	
	@Autowired 
	VelocityEngine velocityEngine;
	
	@Autowired
	MailLib mailLib;
	
	@Override
	public List<CodeTB> selectContactCodeList() {
		return dao.selectContactCodeList();
	}

	@Override
	public boolean sendContact(Contact contact, File file, InputStream inputStream) throws Exception {
		boolean isCheck = false;

		//Contact 테이블의 마지막 row의 contactNo + 1를 가져온다
		int contactNo = dao.selectContactNo();
		contact.setContactNo(contactNo);
		
		int result = dao.insertContact(contact);
		
		//첨부파일이 있다면 파일을 저장하고 DB에 첨부파일 정보를 넣는다.
		if(file != null) {
			int attachNo = dao.selectAttachNo();
			String fileName= fileLib.uploadFile("/upAttach/", file, inputStream);
			Attach attach = new Attach();
			
			attach.setAttachNo(attachNo);
			attach.setTargetNo(contactNo);
			attach.setKindCode(contact.getContactKind());
			attach.setAttachOrigin(file.getName());
			attach.setAttachExtenstion(fileLib.fileExtenstion(file.getName()));
			attach.setAttachName(fileName);
			
			int attachResult = dao.insertAttach(attach);
			
			//문의와 첨부파일이 정상적으로 DB에 insert 되면 true
			if(result != 0 || attachResult != 0) 
				isCheck = true;
			
		}else {
			//문의가 정상적으로 DB에 insert 되면 true
			if(result != 0)
				isCheck = true;
		}
		
		//DB에 정상적으로 삽입했을때 메일을 발송한다.
		if(isCheck) {
			String contents = renderTemplate(null, "ContactConfirm.vm");
			
			Message msg = mailLib.getInstance()
							.setFrom("tony950620@gmail.com", "TradeDun")
							.setRecipient(contact.getFromEmail())
							.setSubject("[TradeDun]_문의 해주셔서 감사합니다!")
							.setContent(contents)
							.build();
			
			mailLib.send(msg);
		}
		
		return isCheck;
	}
	
	/**
	 * @description 템플릿 context를 받고 랜더링 후 Template 객체로 반환한다.
	 * @createDate 2019. 11. 1.
	 * @param context
	 * @param templateName
	 * @return
	 */
	public String renderTemplate(Map<String, Object> contextValiable, String templateName) {
		VelocityContext velocityContext = new VelocityContext();
		
		if(contextValiable != null) {
			//map의 키+값 의갯수 만큼 context에 삽입
			for( Map.Entry<String, Object> elem : contextValiable.entrySet() ){
				velocityContext.put(elem.getKey(), elem.getValue());
			}
		}
		
		Template template = velocityEngine.getTemplate(templateName);
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}

}
