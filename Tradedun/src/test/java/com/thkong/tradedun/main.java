package com.thkong.tradedun;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class main {

	//아바타의 부위별 id, 변동될 일이 없기 때문에 고정적으로 박아준다.
	static List<String> parts = Arrays.asList(
			new String[]{"모자", "머리", "얼굴", "상의", "하의", "신발", "목가슴", "허리", "스킨"});
	
	public void charCoordiPrice(String coordi){
		
		for(String s : coordi.split("\\n")){
			String part = s.replaceAll(" ", ""); 
			if (!(part.length() <= 1) && !parts.contains(part)){
				System.out.println(s.trim());
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		
//		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
//		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
//		httpConnection conn = context.getBean("conn", httpConnection.class);
//		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
	}
}