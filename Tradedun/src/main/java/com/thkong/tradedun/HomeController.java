package com.thkong.tradedun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	//템플릿 jsp 적용을 위한 임시용 매핑
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(defaultValue = "home") String page) {
		return page;
	}
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
		return "/common/header";
	}
	
	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footer() {
		return "/common/footer";
	}
	
	//템플릿 jsp 적용을 위한 임시용 매핑
	@RequestMapping(value = "/AuctionMenu", method = RequestMethod.GET)
	public String auction() {
		return "/Auction/AuctionMenu";
	}
	
}
