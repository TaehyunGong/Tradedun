package com.thkong.tradedun.User.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thkong.tradedun.User.service.userService;
import com.thkong.tradedun.User.vo.User;

@Controller
public class userController {

	@Autowired
	userService service;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@RequestParam(name = "SNS", required = true) String SNS, HttpServletRequest req) {
		String redirectUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
		return service.login(SNS, redirectUrl);
	}
	
	@RequestMapping(value="/kakaoLogin", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(name = "code", required = true) String code, HttpServletRequest req) throws IOException {
		String redirectUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
		
		//로그인 및 회원가입한 사용자의 id를 세션에 담아 home으로 리다렉트 해준다.
		req.getSession().setAttribute("user", service.kakaoLogin(code, redirectUrl));
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String login(HttpSession session) throws IOException {
		if(service.logout(session) != null) {
			//로그아웃시 모든 세션을 제거해준다.
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/user/userMenu", method = RequestMethod.GET)
	public String userMenu(HttpSession session) throws IOException {
		String page = "404";
		if(session.getAttribute("user") != null) {
			page = "/User/userMenu";
		}
		
		return page;
	}
	
	@RequestMapping(value="/user/userInfo", method = RequestMethod.GET)
	public String mySettings(HttpSession session, Model model) throws IOException {
		String page = "404";
		if(session.getAttribute("user") != null) {
			//세션에 있는 유저아이디를 파라메터에 넣어준다.
			User sessionUser = (User)session.getAttribute("user");
			User user = service.selectUserInfo(sessionUser.getUserNo());
			
			model.addAttribute("userInfo", user);
			page = "/User/userInfo";
		}
		
		return page;
	}
	
	@RequestMapping(value="/user/updateUserInfo", method = RequestMethod.POST)
	public String updateUserInfo(HttpSession session, User user) throws IOException {
		String page = "404";
		if(session.getAttribute("user") != null) {
			//세션에 있는 유저아이디를 파라메터에 넣어준다.
			User sessionUser = (User)session.getAttribute("user");
			user.setUserNo(sessionUser.getUserNo());
			
			if(service.updateUserInfo(user)) {
				page = "redirect:/user/userInfo";
			}
			
		}
		
		return page;
	}
	
	@RequestMapping(value="/user/userSearchList", method = RequestMethod.GET)
	public String userSearchList(HttpSession session
							   , Model model
							   , @RequestParam(required=true, defaultValue="1") int row) throws IOException {
		String page = "404";
		if(session.getAttribute("user") != null) {
			//세션에 있는 유저아이디를 파라메터에 넣어준다.
			User sessionUser = (User)session.getAttribute("user");
			ArrayList<Map<String, String>> list = service.selectUserSearchList(sessionUser.getUserNo(), row);
			int logCount = service.selectUserSearchCount(sessionUser.getUserNo());
			
			model.addAttribute("list", list);
			model.addAttribute("logCount",logCount);
			page = "/User/UserSearchList";
		}
		
		return page;
	}
	
}
