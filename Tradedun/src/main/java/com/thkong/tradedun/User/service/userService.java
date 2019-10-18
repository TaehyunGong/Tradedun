package com.thkong.tradedun.User.service;

import java.io.IOException;

public interface userService {

	public String login(String SNS, String redirectUrl);
	
	public String kakaoLogin(String code, String redirectUrl) throws IOException ;
}
