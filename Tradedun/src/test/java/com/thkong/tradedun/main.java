package com.thkong.tradedun;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		ApplicationContext sessionContext = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		SqlSessionTemplate session = sessionContext.getBean("sqlSession", SqlSessionTemplate.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		AuctionCharacterDetail detail = dnfapi.charactersAvatar("bakal", "43555446f17e37fcf3eb546ee312d0f8");
		System.out.println(detail);
	}
}