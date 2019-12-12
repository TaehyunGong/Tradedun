package com.thkong.tradedun.Common.dao;

import java.util.Map;

import com.thkong.tradedun.Auction.vo.AvatarMastar;

public interface LogsDao {

	void insertRareAvatarSearch(AvatarMastar am);

	void insertShowRoomAvatarSearch(Map<String, String> map);

	void insertUserLoginLog(Map<String, String> map);

	
}
