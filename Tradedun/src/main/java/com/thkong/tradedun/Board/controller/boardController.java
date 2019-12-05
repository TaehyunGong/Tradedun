package com.thkong.tradedun.Board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	public String boardInsert(MultipartHttpServletRequest req) {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		
		System.out.println("제목 : " + title);
		System.out.println(contents);
		
		return "/Board/BoardWriter";
	}

}
