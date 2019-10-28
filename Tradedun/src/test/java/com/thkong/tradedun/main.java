package com.thkong.tradedun;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		String head = "모자 아바타";
		System.out.println(head.split(" ")[0]);
		
	}
}