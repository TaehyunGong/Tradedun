 package com.thkong.tradedun;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	public void process() throws IOException {
		int result = session.selectOne("selectContactNo");
		
		System.out.println("결과 : " + result);
		
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}
