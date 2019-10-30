package com.thkong.tradedun;

import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		VelocityEngine velocityEngine = context.getBean("velocityEngine", VelocityEngine.class);
		
		Template template = velocityEngine.getTemplate("AuctionCharacterNoAvatar.vm");
		System.out.println(template);
	}
}