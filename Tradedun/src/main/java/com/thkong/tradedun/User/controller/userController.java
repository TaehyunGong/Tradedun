package com.thkong.tradedun.User.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class userController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "";
	}
}
