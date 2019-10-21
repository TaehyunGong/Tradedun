package com.thkong.tradedun.Auction.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thkong.tradedun.Auction.service.auctionService;
import com.thkong.tradedun.Auction.vo.Character;

@Controller
public class auctionController {

	@Autowired
	auctionService service;
	
	@RequestMapping(value="/action/avatarSeachList", method = RequestMethod.GET)
	public @ResponseBody List<Character> avatarSeachList(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character) throws IOException {
		List<Character> list = service.avatarSeachList(server, character).getRows();
		return list;
	}
}