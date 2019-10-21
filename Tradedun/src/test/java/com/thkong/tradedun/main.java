package com.thkong.tradedun;

import java.io.IOException;
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
		ObjectMapper mapper = context.getBean("ObjectMapper", ObjectMapper.class);
		httpConnection conn = httpConnection.getInstance();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "KakaoAK 3e7639d881eedc5e22f2f22722d541bc");
		map.put("target_id_type=", "user_id");
		map.put("target_id=", "1192936782");
		
		String result = conn.HttpPostConnection("https://kapi.kakao.com/v2/user/me", map).toString();
		System.out.println(result);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		KakaoInfoDetail kakao = mapper.readValue(result, KakaoInfoDetail.class);
		
	}
}
