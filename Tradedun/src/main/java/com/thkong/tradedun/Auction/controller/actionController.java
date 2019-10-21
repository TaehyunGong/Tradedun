package com.thkong.tradedun.Auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class actionController {

	@RequestMapping(value="/action/avatarSeachList", method = RequestMethod.GET)
	public @ResponseBody String avatarSeachList(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character) {
		System.out.println(server +" , " + character);
		return "확인";
	}
}
