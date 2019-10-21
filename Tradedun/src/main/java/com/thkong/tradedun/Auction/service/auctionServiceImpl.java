package com.thkong.tradedun.Auction.service;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.Common.httpConnection;

@Transactional
@Service
public class auctionServiceImpl implements auctionService {
	
	@Value("#{props['dnf.RestKey']}")
	private String dnfRestKey;
	
	@Autowired
	private ObjectMapper mapper;
	
	private httpConnection conn = httpConnection.getInstance();

	@Override
	public Characters avatarSeachList(String server, String character) throws IOException {
		
		character = conn.URLencoder(character);
		
		String result = conn.HttpGetConnection("https://api.neople.co.kr/df/servers/bakal/characters?"
				+ "characterName=" + character + "&wordType=full&apikey="+dnfRestKey).toString();
		
		Characters list = mapper.readValue(result, Characters.class);
		return list;
	}
	
}
