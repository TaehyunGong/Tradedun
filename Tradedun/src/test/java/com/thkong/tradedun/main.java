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
		
		List<String> existCheckList = new ArrayList<String>();
		List<Map<String, Object>> job = new ArrayList<Map<String, Object>>();
		
		for(AvatarMastar mst : avatarList) {
			Map<String, Object> nameAndValue = new HashMap<String, Object>();
			nameAndValue.put("categoryCode", mst.getCategoryName());
			nameAndValue.put("categoryName", mst.getCategoryCode());
			
			if(existCheckList.contains(mst.getJobName())) {
				int index = existCheckList.indexOf(mst.getJobName());
				List<Object> listObj = (List<Object>) job.get(index).get("avatarList");
				listObj.add(nameAndValue);
			}else {
				existCheckList.add(mst.getJobName());
				
				List<Object> setList = new ArrayList<Object>();
				setList.add(nameAndValue);
				
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("avatarList", setList);
				obj.put("jobId", mst.getJobId());
				obj.put("jobName", mst.getJobName());
				
				job.add(obj);
			}
		}
		
		String json = mapper.writeValueAsString(job);
		System.out.println(json);
		
	}
}