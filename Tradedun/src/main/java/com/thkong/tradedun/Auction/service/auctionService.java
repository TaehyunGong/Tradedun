package com.thkong.tradedun.Auction.service;

import java.io.IOException;

import com.thkong.tradedun.Auction.vo.Characters;

public interface auctionService {

	public Characters avatarSeachList(String server, String character) throws IOException;
	
}
