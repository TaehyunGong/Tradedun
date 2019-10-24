package com.thkong.tradedun;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Common.DnfApiLib;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
		
		dnfapi.characters();
	}
}
