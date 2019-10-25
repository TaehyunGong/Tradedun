package com.thkong.tradedun.Auction.service;

import java.io.IOException;

import com.thkong.tradedun.Auction.vo.Characters;

public interface auctionService {

	public String charSeachList(String server, String character, String number) throws IOException;
	
	public String charAvatarSeach(String server, String character, String number, String kind) throws IOException;
}
