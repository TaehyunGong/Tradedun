 package com.thkong.tradedun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.Auction;
import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Common.DnfApiException;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//	ApplicationContext context1 = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
//	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	VelocityEngine velocityEngine = context.getBean("velocityEngine", VelocityEngine.class);
	
	public void process() throws DnfApiException {
		List<Auctions> avatarList = new ArrayList<Auctions>();
		
		try {
			Auctions auctions = dnfapi.auctionItemName("");
			
			System.out.println(auctions);
			
			for(Auction auction : auctions.getRows()) {}
			
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new main().process();
	}
}
