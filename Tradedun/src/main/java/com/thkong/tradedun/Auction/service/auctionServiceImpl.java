package com.thkong.tradedun.Auction.service;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void avatarSeachList(String server, String character) throws IOException {
		String apiurl = "https://api.neople.co.kr/df/items/" + "/shop?apikey=7gW7GbmqkpcFLERS0FT8S9RIK5O1257V";
		conn.HttpGetConnection(apiurl).toString();
	}
	
}
