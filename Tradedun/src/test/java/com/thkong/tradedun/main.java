 package com.thkong.tradedun;

import javax.mail.Message;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Common.MailLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	public void process() throws Exception {
		MailLib mail = context.getBean("MailLib", MailLib.class);
		
		Message msg = mail.getInstance()
						.setFrom("tony950620@gmail.com")
						.setRecipient("tony950620@naver.com")
						.setSubject("제목")
						.setContent("내용")
						.build();
		
		mail.send(msg);
		
		System.out.println("성공?");
	}
	
	public static void main(String[] args) throws Exception {
		new main().process();
	}
}
