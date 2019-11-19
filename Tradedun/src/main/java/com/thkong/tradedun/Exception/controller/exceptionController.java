package com.thkong.tradedun.Exception.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class exceptionController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/error")
	public String forward404(HttpServletResponse res) {
		log.error("에러 남");
		return "/Exception/except";
	}
}
