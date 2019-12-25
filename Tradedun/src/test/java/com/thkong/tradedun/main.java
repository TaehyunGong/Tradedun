 package com.thkong.tradedun;

import java.io.StringWriter;
import java.util.Map;

import javax.mail.Message;

import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Common.MailLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	VelocityEngine velocityEngine = context.getBean("velocityEngine", VelocityEngine.class);
	
	public void process() throws Exception {
		MailLib mail = context.getBean("MailLib", MailLib.class);
		
		String contents = renderTemplate(null, "");
		
		Message msg = mail.getInstance()
						.setFrom("tony950620@gmail.com")
						.setRecipient("tony950620@naver.com")
						.setSubject("벨로시티")
						.setContent(contents)
						.build();
		
		mail.send(msg);
		
		System.out.println("성공?");
	}
	
	public String renderTemplate(Map<String, Object> contextValiable, String templateName) {
		VelocityContext velocityContext = new VelocityContext();
		
		//map의 키+값 의갯수 만큼 context에 삽입
		for( Map.Entry<String, Object> elem : contextValiable.entrySet() ){
			velocityContext.put(elem.getKey(), elem.getValue());
        }
		
		Template template = velocityEngine.getTemplate(templateName);
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}
	
	public static void main(String[] args) throws Exception {
		new main().process();
	}
}
