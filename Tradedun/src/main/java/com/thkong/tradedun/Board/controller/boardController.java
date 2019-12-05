package com.thkong.tradedun.Board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thkong.tradedun.Board.service.boardService;

@Controller
@RequestMapping(value="/board")
public class boardController {

	@Autowired
	boardService service;
	
	/**
	 * @description 포워딩) 공지사항 리스트로 페이지 넘김
	 * @return
	 */
	@RequestMapping(value="/notice")
	public String auctionWriter() {
		return "/Board/Notice";
	}
	
	/**
	 * @description 포워딩) 글작성 페이지로 넘김
	 * @return
	 */
	@RequestMapping(value="/boardWriter")
	public String boardWriter() {
		return "/Board/BoardWriter";
	}
	
	/**
	 * @description 글작성 컨트롤러
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/boardInsert", method = RequestMethod.POST)
	public String boardInsert(MultipartHttpServletRequest req) throws Exception{
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String categoryCode = req.getParameter("category");
		
		service.insertBoard(title, contents, categoryCode);
		
		return "/Board/BoardWriter";
	}

}
