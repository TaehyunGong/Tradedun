package com.thkong.tradedun.Auction.service;

import java.io.IOException;

import com.thkong.tradedun.Auction.vo.Characters;
import com.thkong.tradedun.User.vo.User;

public interface auctionService {

	public String charSeachList(String server, String character, String number) throws IOException;
	
	public String charAvatarSeach(String server, String character, String number, String kind) throws IOException;
	
	public String addCharBox(String number) throws IOException;
	
	public String insertBoardWrite(String submitJson, String subject, User user) throws IOException;
}
