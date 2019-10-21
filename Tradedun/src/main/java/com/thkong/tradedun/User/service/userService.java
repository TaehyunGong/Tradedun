package com.thkong.tradedun.User.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.thkong.tradedun.User.vo.User;

public interface userService {

	public String login(String SNS, String redirectUrl);
	
	public User kakaoLogin(String code, String redirectUrl) throws IOException ;
	
	public String logout(HttpSession session) throws IOException;
}
