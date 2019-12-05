package com.thkong.tradedun.Board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value="/board")
public class boardController {

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
	
	@RequestMapping(value="/boardInsert")
	public String boardInsert(MultipartHttpServletRequest req) {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		
		System.out.println("제목 : " + title);
		System.out.println(contents);
		
		return "/Board/BoardWriter";
	}
}
