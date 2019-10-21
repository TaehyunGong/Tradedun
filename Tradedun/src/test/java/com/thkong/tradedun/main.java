package com.thkong.tradedun;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Common.httpConnection;

public class main {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/SpringConfig/config/root-context.xml");
		ObjectMapper mapper = context.getBean("mapper", ObjectMapper.class);
		httpConnection conn = httpConnection.getInstance();
		String restkey = "";
		
		String name = conn.URLencoder("체원");
		
		String result = conn.HttpGetConnection("https://api.neople.co.kr/df/servers/bakal/characters?"
				+ "characterName=" + name + "&wordType=full&apikey="+restkey).toString();
		System.out.println(result);
		
		Characters list = mapper.readValue(result, Characters.class);
		System.out.println(list);
	}
}
