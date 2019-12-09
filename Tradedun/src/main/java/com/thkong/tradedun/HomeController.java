package com.thkong.tradedun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thkong.tradedun.Board.service.boardService;
import com.thkong.tradedun.Board.vo.Board;

@Controller
public class HomeController {
	
	@Autowired
	boardService boardService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(defaultValue = "main") String page
						, Model model) {
		
		List<Board> boardList = boardService.selectBoardList();
		model.addAttribute("boardList", boardList);
		
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
	
}
