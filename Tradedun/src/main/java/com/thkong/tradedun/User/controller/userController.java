package com.thkong.tradedun.User.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thkong.tradedun.User.service.userService;
import com.thkong.tradedun.User.vo.User;

@Controller
public class userController {

	@Autowired
	userService service;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@RequestParam(name = "SNS", required = true) String SNS, HttpServletRequest req) {
		String redirectUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
		return service.login(SNS, redirectUrl);
	}
	
	@RequestMapping(value="/kakaoLogin", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(name = "code", required = true) String code, HttpServletRequest req) throws IOException {
		String redirectUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
		
		//로그인 및 회원가입한 사용자의 id를 세션에 담아 home으로 리다렉트 해준다.
		req.getSession().setAttribute("user", service.kakaoLogin(code, redirectUrl));
		
		return "redirect:/";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String login(HttpSession session) throws IOException {
		if(service.logout(session) != null) {
			//로그아웃시 모든 세션을 제거해준다.
			session.invalidate();
		}else {
			//모종의 사유로 로그아웃 요청실패시 예외발생
			new IOException();
		}
		
		return "redirect:/";
	}
	
}