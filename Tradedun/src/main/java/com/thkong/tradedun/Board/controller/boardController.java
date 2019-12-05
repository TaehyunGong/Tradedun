package com.thkong.tradedun.Board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thkong.tradedun.Auction.vo.Category;
import com.thkong.tradedun.Board.service.boardService;
import com.thkong.tradedun.Board.vo.Board;

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
	public String auctionWriter(Model model) {
		List<Board> boardList = service.selectBoardList();
		model.addAttribute("boardList", boardList);
		return "/Board/Notice";
	}
	
	/**
	 * @description 포워딩) 글작성 페이지로 board 카테고리 리스트를 가지고 넘김
	 * @return
	 */
	@RequestMapping(value="/boardWriter")
	public String boardWriter(Model model) {
		List<Category> categoryList = service.selectBoardCategoryList();
		model.addAttribute("categroyList", categoryList);
		
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
	
	/**
	 * @description 글 상세 보기
	 * @param boardNo
	 * @param categoryCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam(required = true) int boardNo
							, @RequestParam(required = true) String categoryCode
							, Model model) {
		Board board = service.selectBoard(boardNo, categoryCode);
		model.addAttribute("board", board);
		return "/Board/BoardDetail";
	}

}