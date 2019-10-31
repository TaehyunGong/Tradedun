package com.thkong.tradedun.Auction.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thkong.tradedun.Auction.service.auctionService;

@Controller
public class auctionController {

	@Autowired
	auctionService service;
	
	@RequestMapping(value="/auction/charSeachList", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String charSeachList(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number) throws IOException {
		return service.charSeachList(server, character, number);
	}
	
	@RequestMapping(value="/auction/charAvatarSeach", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String charAvatarSeach(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number
										  , @RequestParam(required = true) String kind) throws IOException {
		return service.charAvatarSeach(server, character, number, kind);
	}
	
	@RequestMapping(value="/auction/addCharBox", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String addCharBox(@RequestParam(required = true) String number) throws IOException {
		return service.addCharBox(number);
	}
	
	@RequestMapping(value="/auction/insertBoardWrite", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody String insertBoardWrite(@RequestParam(required = true) String submitJson
											, @RequestParam(required = true) String subject) throws IOException {
		return service.insertBoardWrite(submitJson);
	}
}
