package com.thkong.tradedun.Auction.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thkong.tradedun.Auction.service.auctionService;

//ajax 및 rest 통신 전용 Controller
@RestController
@RequestMapping(value="/auction")
public class auctionRestController {

	@Autowired
	auctionService service;
	
	/**
	 * @description 검색한 캐릭터의 리스트를 템플릿으로 뿌려준다.
	 * @param server
	 * @param character
	 * @param number
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/charSeachList", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String charSeachList(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number) throws IOException {
		return service.charSeachList(server, character, number);
	}
	
	/**
	 * @description kind의 종류에 따라 각각 다른 템플릿과 로직으로 처리한다.
	 * @param server
	 * @param character
	 * @param number
	 * @param kind
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/charAvatarSeach", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String charAvatarSeach(@RequestParam(required = true) String server
										  , @RequestParam(required = true) String character
										  , @RequestParam(required = true) String number
										  , @RequestParam(required = true) String kind) throws IOException {
		
		String templateResult = null;
		switch(kind) {
			case "wear" : templateResult = service.charAvatarSeach(server, character, number, kind); break;
			case "clone" : templateResult = service.charAvatarSeach(server, character, number, kind); break;
			case "buff" : break;
		}
		
		return templateResult;
	}
	
	/**
	 * @description 판매글에서 추가하기 시 검색창 컴포넌트를 템플릿으로 뿌려준다.
	 * @param number
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/addCharBox", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String addCharBox(@RequestParam(required = true) String number) throws IOException {
		return service.addCharBox(number);
	}
	
	/**
	 * @description 판매글 리스트의 무한페이징을 위해 판매글리스트들을 템플릿으로 뿌려준다.
	 * @param model
	 * @param jobId
	 * @param jobGrowId
	 * @param categoryCode
	 * @param priceRange
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/auctionAvatarListPaging", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String auctionAvatarListPaging(Model model
										, @RequestParam(required = false, defaultValue = "all") String jobId
										, @RequestParam(required = false, defaultValue = "all") String jobGrowId
										, @RequestParam(required = false, defaultValue = "all") String categoryCode
										, @RequestParam(required = false, defaultValue = "0") String priceRange
										, @RequestParam(required = true) int page) throws IOException {
		return service.selectAuctionListPaging(jobId, jobGrowId, categoryCode, priceRange, page);
	}
}
