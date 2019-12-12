package com.thkong.tradedun.Board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import com.thkong.tradedun.User.vo.User;

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
	public String boardWriter(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		
		//비 로그인 상태면 잘못된 접근이라 알림
		if(user == null) 
			return "/Exception/except";
		
		List<Category> categoryList = service.selectBoardCategoryList(user);
		model.addAttribute("categroyList", categoryList);
		model.addAttribute("action", "/board/boardInsert");
		
		return "/Board/BoardWriter";
	}
	
	/**
	 * @description 글작성 컨트롤러
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/boardInsert", method = RequestMethod.POST)
	public String boardInsert(MultipartHttpServletRequest req
							, HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		
		//비 로그인 상태면 잘못된 접근이라 알림
		if(user == null) 
			return "/Exception/except";
		
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String categoryCode = req.getParameter("category");
		
		Board board = new Board();
		board.setUserNo(user.getUserNo());
		board.setTitle(title);
		board.setContents(contents);
		board.setCategoryCode(categoryCode);
		
		service.insertBoard(board);
		
		return "redirect:/board/notice";
	}
	
	/**
	 * @description 포워딩) 글 상세 보기
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

	/**
	 * @description 포워딩) 글 수정
	 * @param boardNo
	 * @param categoryCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/boardModify", method = RequestMethod.GET)
	public String boardModify(@RequestParam(required = true) int boardNo
							, @RequestParam(required = true) String categoryCode
							, Model model
							, HttpSession session) {
		User user = (User)session.getAttribute("user");
		String page = "/Exception/except";
		
		if(user == null) {
			return page;
		}
		
		//글 작성자 나 관리자만 수정할수 있다.
		if(user.getUserNo().equals("1192936782")) {
			page = "/Board/BoardWriter";
			
			List<Category> categoryList = service.selectBoardCategoryList(user);
			Board board = service.selectBoard(boardNo, categoryCode);
			
			model.addAttribute("categroyList", categoryList);
			model.addAttribute("board", board);
			model.addAttribute("action", "/board/boardModifyApply");
		}
		return page;
	}
	
	/**
	 * @description 수정된 글내용을 update
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/boardModifyApply", method = RequestMethod.POST)
	public String boardModify(MultipartHttpServletRequest req
							, HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		
		//비 로그인 상태면 잘못된 접근이라 알림
		if(user == null) 
			return "/Exception/except";
			
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String categoryCode = req.getParameter("category");
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setTitle(title);
		board.setContents(contents);
		board.setCategoryCode(categoryCode);
		
		service.updateBoard(board, user);
		
		return "redirect:/board/boardDetail?boardNo="+ boardNo + "&categoryCode=" + categoryCode;
	}
	
	/**
	 * @description 글 삭제
	 * @param boardNo
	 * @param categoryCode
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/boardDelete", method = RequestMethod.GET)
	public String boardDelete(@RequestParam(required = true) int boardNo
							, @RequestParam(required = true) String categoryCode
							, Model model
							, HttpSession session) {
		User user = (User)session.getAttribute("user");
		String page = service.deleteBoard(boardNo, categoryCode, user);
		return page;
	}
	
}
