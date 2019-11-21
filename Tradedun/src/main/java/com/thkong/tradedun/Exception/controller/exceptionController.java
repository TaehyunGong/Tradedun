package com.thkong.tradedun.Exception.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class exceptionController {

	@RequestMapping("/error")
	public String forward404(HttpServletResponse res) {
		return "/Exception/except";
	}
}
