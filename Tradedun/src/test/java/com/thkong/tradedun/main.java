package com.thkong.tradedun;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.AvatarMastar;
import com.thkong.tradedun.Common.DnfApiLib;

public class main {
	
	public static void main(String[] args) throws IOException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
		SqlSession session = context.getBean("sqlSession", SqlSession.class);
		
		List<AvatarMastar> avatarList = session.selectList("selectRareAvatarList");
		System.out.println(avatarList);
	}
}