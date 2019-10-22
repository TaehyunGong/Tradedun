package com.thkong.tradedun.Auction.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.Character;
import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Common.httpConnection;

@Transactional
@Service
public class auctionServiceImpl implements auctionService {
	
	@Value("#{props['dnf.RestKey']}")
	private String dnfRestKey;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
	VelocityEngine velocityEngine;
	
	private httpConnection conn = httpConnection.getInstance();

	@Override
	public String avatarSeachList(String server, String character) throws IOException {
		
		character = conn.URLencoder(character);
		
		String result = conn.HttpGetConnection("https://api.neople.co.kr/df/servers/bakal/characters?"
				+ "characterName=" + character + "&wordType=full&apikey="+dnfRestKey).toString();
		
		List<Character> list = mapper.readValue(result, Characters.class).getRows();
		
		Template template = velocityEngine.getTemplate("AuctionCharacterSelectForm.vm");
        
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("list", list); 

		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}
	
}
