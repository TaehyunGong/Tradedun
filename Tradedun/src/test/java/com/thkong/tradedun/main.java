package com.thkong.tradedun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.Avatar;
import com.thkong.tradedun.Auction.vo.AvatarMastar;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		SqlSession session = context.getBean("sqlSession", SqlSession.class);
		
		//944b9aab492c15a8474f96947ceeb9e4 : R_4_3

		AvatarMastar am = new AvatarMastar();
		am.setJobId("944b9aab492c15a8474f96947ceeb9e4");
		am.setCategoryCode("R_4_3");
		
		List<Avatar> list = session.selectList("selectAvatarSet", am);
		System.out.println(list);
	}
}