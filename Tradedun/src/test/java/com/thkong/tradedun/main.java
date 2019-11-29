package com.thkong.tradedun;

import java.io.IOException;

public class main {
	
//	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
//	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	
	public void process() throws IOException {
		
		String test = "머리	골드 셀레스티얼 양갈래 롱헤어 (흑록)";
		
		if(test.indexOf('(') != -1) {
			int start = test.indexOf('(');
			
			test = test.substring(0, start);
		}
		
		System.out.println(test);
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}
