package com.thkong.tradedun;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thkong.tradedun.Auction.service.auctionService;
import com.thkong.tradedun.Board.service.boardService;
import com.thkong.tradedun.Board.vo.Board;

@Controller
public class HomeController {
	
	@Autowired
	boardService boardService;
	
	@Autowired
	auctionService auctionService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(defaultValue = "main") String page
						, Model model) throws IOException {
		
		//공지사항 리스트
		List<Board> boardList = boardService.selectBoardList();
		model.addAttribute("boardList", boardList);
		
		//레어아바타 직군, 차수 리스트
		String avatarList = auctionService.selectRareAvatarList();
		model.addAttribute("avatarList", avatarList);
		
		return page;
	}
	
	@RequestMapping(value = "/header", method = {RequestMethod.GET, RequestMethod.POST})
	public String header() {
		return "/common/header";
	}
	
	@RequestMapping(value = "/footer", method = {RequestMethod.GET, RequestMethod.POST})
	public String footer() {
		return "/common/footer";
	}
	
	@RequestMapping(value = "/temp", method = {RequestMethod.GET, RequestMethod.POST})
	public String template(@RequestParam(defaultValue = "home") String page) {
		return "/HTML_Template/"+page;
	}
}
