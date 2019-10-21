package com.thkong.tradedun;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Common.httpConnection;
import com.thkong.tradedun.User.vo.KakaoInfoDetail;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		httpConnection conn = httpConnection.getInstance();
		String restkey = "ZhdFccSqmmddf4TjPySS0cAUiszhcwGJ";
		
		String name = conn.URLencoder("체원");
		
		String result = conn.HttpGetConnection("https://api.neople.co.kr/df/servers/bakal/characters?"
				+ "characterName=" + name + "&apikey="+restkey).toString();
		System.out.println(result);
		
		KakaoInfoDetail kakao = mapper.readValue(result, KakaoInfoDetail.class);
		
	}
}
