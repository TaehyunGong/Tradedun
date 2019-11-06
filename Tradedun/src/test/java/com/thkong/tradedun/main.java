package com.thkong.tradedun;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Common.DnfApiLib;
import com.thkong.tradedun.Common.httpConnection;

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
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		DnfApiLib dnfapi = context.getBean("dnfapi", DnfApiLib.class);
		httpConnection conn = context.getBean("conn", httpConnection.class);
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		
		String jobId = "41f1cdc2ff58bb5fdc287be0db2a8df3";
		String showroom = "\n" + 
				" 머리\n" + 
				"	\n" + 
				" 블루 인페르노 실버 롱헤어\n" + 
				"\n" + 
				" 모자\n" + 
				"	\n" + 
				" 던파스쿨의 리본 핀[C타입]\n" + 
				"\n" + 
				" 얼굴\n" + 
				"	\n" + 
				" 사막의 수호자 바스테트의 레드 이어링과 눈화장[B타입]\n" + 
				"\n" + 
				" 목가슴\n" + 
				"	\n" + 
				" 해변의연인 목걸이[B타입]\n" + 
				"\n" + 
				" 상의\n" + 
				"	\n" + 
				" 플레이아데스 프릴 슈트\n" + 
				"\n" + 
				" 허리\n" + 
				"	\n" + 
				" 해변의연인 플라워벨트[D타입]\n" + 
				"\n" + 
				" 하의\n" + 
				"	\n" + 
				" 만우절 화이트 팬츠\n" + 
				"\n" + 
				" 신발\n" + 
				"	\n" + 
				" 사막의 수호자 바스테트의 블루 주얼리 스트랩 슈즈[A타입] (연회색)\n" + 
				"\n" + 
				" 피부\n" + 
				"	\n" + 
				" NPC 도플갱어 루드밀라의 잿빛 피부[C타입]";
		
		main main = new main();
		
		main.charCoordiPrice(showroom);
	}
}