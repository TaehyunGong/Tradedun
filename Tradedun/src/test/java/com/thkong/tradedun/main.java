package com.thkong.tradedun;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.AuctionSalesBoardDetail;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	
	public void process() throws IOException {
		AuctionSalesBoardDetail detail = session.selectOne("selectAuctionSalesBoardDetail", "1");
		System.out.println(detail);
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}