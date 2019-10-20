package com.thkong.tradedun.User.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thkong.tradedun.User.service.userService;

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
		req.getSession().setAttribute("user", service.kakaoLogin(code, redirectUrl));
		
		return null;
	}
}
