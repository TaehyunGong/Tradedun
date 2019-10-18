package com.thkong.tradedun.User.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

	@Value("#{props['kakako.RestKey']}")
	private String kakaoRestKey;  
	
	@Value("#{props['kakako.AdminKey']}")
	private String kakaoAdminKey;
	
	/**
	 * @description 카카오톡 로그인을 하기위해 카카오 로그인 페이지로 리다이렉트 url를 생성해준다.
	 * @createDate 2019. 10. 19.
	 * @param SNS
	 * @param req
	 * @return Kakao Redirect URL
	 */
	@Override
	public String login(String SNS, HttpServletRequest req) {
		String redirectUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
		
		StringBuilder loginUrl = new StringBuilder();
		loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=")
		.append(kakaoRestKey)
		.append("&redirect_uri=")
		//카카오 앱에 등록한 redirect URL, contextPath는 변하지 않는다.
		.append(redirectUrl+"/kakaoLogin") 
		.append("&response_type=code");
		
		return "redirect:"+loginUrl.toString();
	}

}
