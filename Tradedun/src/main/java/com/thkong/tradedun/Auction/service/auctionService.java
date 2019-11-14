package com.thkong.tradedun.Auction.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.thkong.tradedun.Auction.vo.Auctions;
import com.thkong.tradedun.Auction.vo.AvatarMastar;
import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.User.vo.User;

public interface auctionService {

	public String charSeachList(String server, String character, String number) throws IOException;
	
	public String charAvatarSeach(String server, String character, String number, String kind) throws IOException;
	
	public String addCharBox(String number) throws IOException;
	
	public String insertBoardWrite(String submitJson, String subject, User user) throws IOException;
	
	public String selectRareAvatarList() throws IOException;

	public List<CodeTB> selectAllJobList();

	public Map<String, Object> avatarShowroomSearch(String jobId, String showroom) throws IOException;

	public Map<String, Object> avatarCharacterSetSearch(String string, String string2);
}
