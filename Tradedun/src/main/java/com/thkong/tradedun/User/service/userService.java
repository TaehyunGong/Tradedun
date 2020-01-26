package com.thkong.tradedun.User.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.thkong.tradedun.User.vo.User;

public interface userService {

	public String login(String SNS, String redirectUrl);
	
	public User kakaoLogin(String code, String redirectUrl) throws IOException ;
	
	public String logout(HttpSession session) throws IOException;

	public boolean updateUserInfo(User user);

	public User selectUserInfo(String userId);

	public ArrayList<Map<String, String>> selectUserSearchList(String userNo, int row);

	public Map<String, Object> selectUnderPageNumber(String userNo, int row);
}
