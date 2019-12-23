package com.thkong.tradedun.Contact.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Common.FileLib;
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
	
	@Override
	public List<CodeTB> selectContactCodeList() {
		return dao.selectContactCodeList();
	}

	@Override
	public boolean sendContact(Contact contact, File file, InputStream inputStream) throws IOException {
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
			
			System.out.println("확장자 : " + file.getName());
			
			//문의와 첨부파일이 정상적으로 DB에 insert 되면 true
			if(result != 0 || attachResult != 0) 
				isCheck = true;
			
		}else {
			//문의가 정상적으로 DB에 insert 되면 true
			if(result != 0)
				isCheck = true;
		}
		
		return isCheck;
	}

}
