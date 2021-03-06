package com.thkong.tradedun.Auction.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thkong.tradedun.Auction.service.auctionService;
import com.thkong.tradedun.Auction.vo.AuctionSalesBoardDetail;
import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Common.DnfApiException;
import com.thkong.tradedun.User.vo.User;

@Controller
@RequestMapping(value="/auction")
public class auctionController {

	@Autowired
	auctionService service;
	
	//템플릿 jsp 적용을 위한 임시용 매핑
	@RequestMapping(value = "/auctionMenu", method = {RequestMethod.GET, RequestMethod.POST})
	public String auction() {
		return "/Auction/AuctionMenu";
	}
	
	/**
	 * @description 포워딩) 판매글 작성, 세션에 유저가 없다면 잘못된 접근이라 알리고 에러페이지로 넘김
	 * @param session
	 * @return
	 */
//	@RequestMapping(value="/AuctionWriter")
	public String auctionWriter(HttpSession session, Model model) {
		User user = null;
		if(session.getAttribute("user") != null) {
			user = (User)session.getAttribute("user");
		}
		
		String page = "/Auction/AuctionMenu";
		if(user != null)
			page = "/Auction/AuctionWriter";
		else
			//비로그인 상태라면 alert창으로 로그인이 필요하다 알림
			model.addAttribute("msg","<script>alert('로그인이 필요한 서비스입니다.');</script>");
		
		return page;
	}
	
	/**
	 * @description 포워딩) 레어 아바타 세트 검색 페이지로 포워딩 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/rareAvatarSet")
	public String avatarCharacterSet(Model model) throws IOException {
		String avatarList = service.selectRareAvatarList();
		model.addAttribute("avatarList", avatarList);
		
		return "/Auction/RareAvatarSet";
	}
	
	/**
	 * @description 차수 레어 아바타를 경매장에서 긁어서 가져와 뿌려준다.
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/avatarCharacterSetSearch")
	public String avatarCharacterSetSearch(Model model
										, @RequestParam(required = true) String jobId
										, @RequestParam(required = true) String categoryCode) throws IOException {

		Map<String, Object> mapList = service.avatarCharacterSetSearch(jobId, categoryCode);
		model.addAttribute("auctions", mapList.get("auctions"));
		model.addAttribute("choiceAvatar", mapList.get("choiceAvatar"));
		model.addAttribute("rowPriceSum", mapList.get("rowPriceSum"));
		model.addAttribute("searchCount", mapList.get("searchCount"));
		
		model.addAttribute("title", "레어아바타 차수 검색 결과");
		
		return "/Auction/AvatarSearch";
	}
	
	/**
	 * @description 판매글 작성 INSERT 로직
	 * @param submitJson
	 * @param subject
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/insertBoardWrite", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insertBoardWrite(@RequestParam(required = true) String submitJson
											, @RequestParam(required = true) String subject
											, HttpSession session) throws IOException {
		User user = null;
		if(session.getAttribute("user") != null) {
			user = (User)session.getAttribute("user");
		}
		
		String page = "404";
		//등록된 아이템, 제목, 유저가 유효하다면 이 조건을 실행하여 포워딩 패스를 넘겨준다.
		if(user != null && "1".equals(service.insertBoardWrite(submitJson, subject, user))) {
			page = "redirect:/auction/AuctionList";
		}
		return page;
	}
	
	/**
	 * @description 포워딩) 아바타 세트 검색 페이지로 포워딩 
	 * @return
	 */
	@RequestMapping(value="/avatarShowroom")
	public String avatarShowroom(Model model) {
		List<CodeTB> jobList = service.selectAllJobList();
		model.addAttribute("jobList", jobList);
		return "/Auction/AvatarShowroom";
	}
	
	/**
	 * @description 포워딩) 쇼룸 검색 결과 조회, 쇼룸 문장을 파싱하여 경매장 검색 후 JSON 반환
	 * @param jobId
	 * @param showroom
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/avatarShowroomSearch", method = RequestMethod.POST, produces = "text/plain; charset=utf8") 
	public String avatarShowroomSearch(@RequestParam(required = true) String jobId
									 , @RequestParam(required = true) String showroom
									 , Model model) throws IOException {
		Map<String, Object> mapList = service.avatarShowroomSearch(jobId, showroom);
		model.addAttribute("auctions", mapList.get("auctions"));
		model.addAttribute("choiceAvatar", mapList.get("choiceAvatar"));
		model.addAttribute("rowPriceSum", mapList.get("rowPriceSum"));
		
		model.addAttribute("title", "위시리스트 검색 결과");
		
		return "/Auction/AvatarSearch";
	}
	
	/**
	 * @description 포워딩) 판매글 리스트 페이지로 포워딩
	 * @param model
	 * @return
	 * @throws IOException 
	 */
//	@RequestMapping(value="/AuctionList")
	public String auctionList(Model model
							, @RequestParam(required = false, defaultValue = "all") String jobId
							, @RequestParam(required = false, defaultValue = "all") String jobGrowId
							, @RequestParam(required = false, defaultValue = "all") String categoryCode
							, @RequestParam(required = false, defaultValue = "0") String priceRange) throws IOException {
		
		Map<String, Object> mapList = service.selectAuctionList(jobId, jobGrowId, categoryCode, priceRange);
		model.addAttribute("jobGrowAvatarList", mapList.get("jobGrowAvatarList"));	//검색 조건 select JSON 데이터
		model.addAttribute("boardList", mapList.get("boardList"));	//조건으로 검색한 판매글 리스트
		
		model.addAttribute("jobId", jobId);
		model.addAttribute("jobGrowId", jobGrowId);
		model.addAttribute("categoryCode", categoryCode);
		model.addAttribute("priceRange", priceRange);
		
		return "/Auction/AuctionList";
	}
	
	/**
	 * @description 포워딩) 판매글 상세 리스트 조회 
	 * @return
	 */
	@RequestMapping(value="/auctionBoardDetail", method = RequestMethod.GET)
	public String auctionBoardDetail(Model model
								, @RequestParam(required = true) String boardNo
								, @RequestParam(required = true) String charBox) {
		
		AuctionSalesBoardDetail boardDetail = service.selectAuctionSalesBoardDetail(boardNo);
		model.addAttribute("boardDetail", boardDetail);
		model.addAttribute("charBox", charBox);
		
		return "/Auction/AuctionDetail";
	}
	
	
	/**
	 * @description 점검중이거나 DNF API서버에 일시적으로 문제가 있을경우 예외처리
	 * @param req
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DnfApiException.class)
	public ModelAndView DNFAPIError(HttpServletRequest req, Exception exception)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/dnfInspect");
		return mav;
	}
}
