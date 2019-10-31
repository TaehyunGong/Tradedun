package com.thkong.tradedun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		ApplicationContext sessionContext = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		SqlSessionTemplate session = sessionContext.getBean("sqlSession", SqlSessionTemplate.class);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2352);
		list.add(6346);
		list.add(11312);
		
		for(Integer num : list) {
			System.out.println(num + " : " + list.indexOf(num));
		}
		
	}
}