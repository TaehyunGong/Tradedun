package com.thkong.tradedun.Common;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.thkong.tradedun.Auction.vo.Character;
import com.thkong.tradedun.Auction.vo.Characters;

public class DnfApiLib {
	
	private ObjectMapper mapper;
	private httpConnection conn;
	private String dnfRestKey;
	
	public DnfApiLib(ObjectMapper mapper, httpConnection conn, String dnfRestKey) {
		this.mapper = mapper;
		this.conn = conn;
		this.dnfRestKey = dnfRestKey;
	}
	
	public void characters() throws IOException {
		System.out.println(mapper.toString());
		System.out.println(conn.toString());
		System.out.println(dnfRestKey);
	}
}
