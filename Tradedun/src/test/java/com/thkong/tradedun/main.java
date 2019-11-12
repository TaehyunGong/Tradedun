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

import com.thkong.tradedun.Auction.vo.AvatarMastar;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		SqlSession session = context.getBean("sqlSession", SqlSession.class);
		
		List<AvatarMastar> avatarList = session.selectList("selectRareAvatarList");
		
		Map<String, List<String>> job = new HashMap<String, List<String>>();
		for(AvatarMastar mst : avatarList) {
			if(job.containsKey(mst.getJobName())) {
				job.get(mst.getJobName()).add(mst.getCategoryName());
			}else {
				List<String> setList = new ArrayList<String>();
				setList.add(mst.getCategoryName());
				job.put(mst.getJobName(), setList);
			}
		}
		
		String json = mapper.writeValueAsString(job);
		System.out.println(json);
		
	}
}