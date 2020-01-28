 package com.thkong.tradedun;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Common.DnfApiException;

public class main {
	
//	ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//	ApplicationContext context1 = new GenericXmlApplicationContext("/SpringConfig/config/database-context.xml");
//	DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//	ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
//	SqlSession session = context1.getBean("sqlSession", SqlSession.class);
	
	public void process() throws DnfApiException {
		int logCount = 16;
		int row = 1;
		ArrayList<Integer> pageNumbers = new ArrayList<Integer>();
		
		double max = Math.ceil(logCount / (row*10));
		if(max > 0) {
			
			//조회건수로 최대 페이지 갯수
			int total = (int)Math.ceil(logCount / 10.0);
			System.out.println("total : " + total);
			
			if(total > 5) {
				for(int i = total-5; i <= total; i++) {
					pageNumbers.add(i);
				}
			}else {
				for(int i=1; i <= total; i++) {
					pageNumbers.add(i);
				}
			}
			
		}else {
			//유효하지 않는페이지라면 1번만 보여줌
			pageNumbers.add(1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new main().process();
	}
}
