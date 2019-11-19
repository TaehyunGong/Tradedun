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
import com.thkong.tradedun.Auction.vo.JobGrow;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/*.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		SqlSession session = context.getBean("sqlSession", SqlSession.class);
		
		//944b9aab492c15a8474f96947ceeb9e4 : R_4_3
		
		List<AvatarMastar> avatarList = session.selectList("selectRareAvatarList");
		List<String> existCheckList = new ArrayList<String>();
		
		// cascading select를 위해 DB에서 가져온 레압리스트를 json으로 변경
		List<Map<String, Object>> job = new ArrayList<Map<String, Object>>();
		for(AvatarMastar mst : avatarList) {
			Map<String, Object> nameAndValue = new HashMap<String, Object>();
			nameAndValue.put("categoryName", mst.getCategoryName());
			nameAndValue.put("categoryCode", mst.getCategoryCode());
			
			//DB에서 가져온 직군이 리스트에 없으면 해당 직군명으로 map을 생성, 있으면  해당 직군의 레압을 리스트로 넣어줌. 걍 출력 값 보면 알거야..
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
		
		//-----------------------------------------------------

		List<JobGrow> jobGrow = session.selectList("selectJobGrowList");
		Map<String, List<JobGrow>> jobGrowMapList = new HashMap<String, List<JobGrow>>();
		for(JobGrow jg : jobGrow) {
			JobGrow growTemp = new JobGrow();
			growTemp.setJobGrowId(jg.getJobGrowId());
			growTemp.setJobGrowName(jg.getJobGrowName());
			
			//jobGrowMapList에 해당 jobId키가 존재한다면 jobGrow를 넣어준다.
			if(jobGrowMapList.containsKey(jg.getJobId())){
				jobGrowMapList.get(jg.getJobId()).add(growTemp);
			}else {
				//해당 jobId가 없다면 리스트를 만들어준다.
				List<JobGrow> jobList = new ArrayList<JobGrow>();
				jobList.add(growTemp);
				
				jobGrowMapList.put(jg.getJobId(), jobList);
			}
		}
		
		for(Map<String, Object> map : job) {
			if(jobGrowMapList.containsKey(map.get("jobId"))) {
				map.put("jobGrowList", jobGrowMapList.get(map.get("jobId")));
			}
		}
		
		String result = mapper.writeValueAsString(job);
		System.out.println(result);
	}
}