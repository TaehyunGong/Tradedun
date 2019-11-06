package com.thkong.tradedun.Auction.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thkong.tradedun.Auction.service.auctionService;
import com.thkong.tradedun.User.vo.User;

@Controller
@RequestMapping(value="/auction")
public class auctionController {

	@Autowired
	auctionService service;
	
	/**
	 * @description 포워딩) 세션에 유저가 없다면 잘못된 접근이라 알리고 에러페이지로 넘김
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/AuctionWriter")
	public String auctionWriter(HttpSession session) {
		User user = (User)session.getAttribute("user");
		
		String page = "404";
		if(user != null)
			page = "/Auction/AuctionWriter";
		
		return page;
	}
	
	/**
	 * @description 포워딩) 아바타 세트 검색 페이지로 포워딩 
	 * @return
	 */
	@RequestMapping(value="/avatarSetSearch")
	public String avatarSetSearch(Model model) {
		model.addAttribute("", "");
		return "/Auction/AvatarSetSearch";
	}
	
	/**
	 * @description 포워딩) 아바타 세트 검색 페이지로 포워딩 
	 * @return
	 */
	@RequestMapping(value="/avatarShowroomSearch")
	public String avatarShowroomSearch(Model model) {
		model.addAttribute("", "");
		return "/Auction/AvatarShowroomSearch";
	}
	
	@RequestMapping(value="/charSeachList", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String charSeachList(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number) throws IOException {
		return service.charSeachList(server, character, number);
	}
	
	@RequestMapping(value="/charAvatarSeach", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String charAvatarSeach(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number
										  , @RequestParam(required = true) String kind) throws IOException {
		return service.charAvatarSeach(server, character, number, kind);
	}
	
	@RequestMapping(value="/addCharBox", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String addCharBox(@RequestParam(required = true) String number) throws IOException {
		return service.addCharBox(number);
	}
	
	@RequestMapping(value="/insertBoardWrite", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody String insertBoardWrite(@RequestParam(required = true) String submitJson
											, @RequestParam(required = true) String subject
											, HttpSession session) throws IOException {
		User user = (User)session.getAttribute("user");
		String page = "404";
		if(user != null) {
			page = service.insertBoardWrite(submitJson, subject, user);
		}
		return page;
	}
}
