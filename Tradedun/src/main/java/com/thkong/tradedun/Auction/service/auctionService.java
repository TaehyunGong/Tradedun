package com.thkong.tradedun.Auction.service;

import java.io.IOException;

import com.thkong.tradedun.Auction.vo.Characters;

public interface auctionService {

	public String avatarSeachList(String server, String character) throws IOException;
	
}
