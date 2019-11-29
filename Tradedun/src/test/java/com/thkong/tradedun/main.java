package com.thkong.tradedun;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.ItemDetail;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {
	
	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
	SqlSession session = context.getBean("sqlSession", SqlSession.class);
	
	
	public void process() throws IOException {
		//엠블렘 map 리스트
		List<ItemDetail> emblems = session.selectList("selectItemDetailList");
		for(ItemDetail list : emblems) {
			System.out.println(list.getItemName());
		}
	}
	
	public static void main(String[] args) throws IOException {
		new main().process();
	}
}
