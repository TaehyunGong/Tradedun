package com.thkong.tradedun;


import java.io.IOException;
import java.lang.reflect.Type;

import com.thkong.tradedun.Auction.vo.ItemDetail;

public class main {

	public <T> T getSessionAttribute(Object obj, String key, Type type){
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		ApplicationContext sessionContext = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		SqlSessionTemplate session = sessionContext.getBean("sqlSession", SqlSessionTemplate.class);
		
		ItemDetail detail = new ItemDetail();
		System.out.println("출력");
	}
}