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

import com.thkong.tradedun.Auction.vo.AuctionCharacterDetail;
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
	public String charSeachList(String server, String character, String number) throws IOException {
		
		character = conn.URLencoder(character);
		
		String result = conn.HttpGetConnection("https://api.neople.co.kr/df/servers/bakal/characters?"
				+ "characterName=" + character + "&wordType=full&apikey="+dnfRestKey).toString();
		
		List<Character> list = mapper.readValue(result, Characters.class).getRows();
		
		Template template = velocityEngine.getTemplate("AuctionCharacterSelectForm.vm");
        
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("list", list);
		velocityContext.put("server", server);
		velocityContext.put("number", number);

		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);
		
		return stringWriter.toString();
	}

	@Override
	public String charAvatarSeach(String server, String character, String number) throws IOException {
		character = conn.URLencoder(character);
		
		String url = "https://api.neople.co.kr/df/servers/"+server+"/characters/"+character+"/equip/avatar?apikey="+dnfRestKey;
		String result = conn.HttpGetConnection(url).toString();
		
		System.out.println("url : " + url);
		System.out.println(result);
		
		AuctionCharacterDetail detail = mapper.readValue(result, AuctionCharacterDetail.class);
		System.out.println(detail.toString());
		
		return detail.toString();
	}
	
}
