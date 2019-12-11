 package com.thkong.tradedun;

import java.io.IOException;

import com.nhncorp.lucy.security.xss.XssPreventer;

public class main {
	
//	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
//	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	
	public void process() throws IOException {
		String dirty = "\"><script>alert('한글');</script>";
	    String clean = XssPreventer.escape(dirty);
	    
	    System.out.println(clean);
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}
