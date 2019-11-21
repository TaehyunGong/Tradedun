package com.thkong.tradedun.Auction.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thkong.tradedun.Auction.service.auctionService;
import com.thkong.tradedun.Auction.vo.AuctionSalesBoard;

//ajax 및 rest 통신 전용 Controller
@RestController
@RequestMapping(value="/auction")
public class auctionRestController {

	@Autowired
	auctionService service;
	
	@RequestMapping(value="/charSeachList", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String charSeachList(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number) throws IOException {
		return service.charSeachList(server, character, number);
	}
	
	@RequestMapping(value="/charAvatarSeach", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String charAvatarSeach(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number
										  , @RequestParam(required = true) String kind) throws IOException {
		return service.charAvatarSeach(server, character, number, kind);
	}
	
	@RequestMapping(value="/addCharBox", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String addCharBox(@RequestParam(required = true) String number) throws IOException {
		return service.addCharBox(number);
	}
	
	@RequestMapping(value="/auctionAvatarListPaging", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public List<AuctionSalesBoard> auctionAvatarListPaging(Model model
										, @RequestParam(required = false, defaultValue = "all") String jobId
										, @RequestParam(required = false, defaultValue = "all") String jobGrowId
										, @RequestParam(required = false, defaultValue = "all") String categoryCode
										, @RequestParam(required = false, defaultValue = "0") String priceRange
										, @RequestParam(required = true) int page) throws IOException {
		return service.selectAuctionListPaging(jobId, jobGrowId, categoryCode, priceRange, page);
	}
}
