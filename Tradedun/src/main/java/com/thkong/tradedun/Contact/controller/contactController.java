package com.thkong.tradedun.Contact.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thkong.tradedun.Auction.vo.CodeTB;
import com.thkong.tradedun.Contact.service.contactService;
import com.thkong.tradedun.Contact.vo.Contact;

@Controller
@RequestMapping(value="/contact")
public class contactController {

	@Autowired
	private contactService service;
	
	/**
	 * @description 포워딩) 문의종류를 DB에서 가져와 문의페이지로 넘김
	 * @return
	 */
	@RequestMapping(value="/contactMail")
	public String contactMail(Model model) {
		List<CodeTB> list = service.selectContactCodeList();
		model.addAttribute("list", list);
		
		return "/Contact/contactMail";
	}
	
	/**
	 * @description 포워딩) 문의종류를 DB에서 가져와 문의페이지로 넘김
	 * @return
	 */
	@RequestMapping(value="/contactResult")
	public String contactResult(Model model) {
		
		return "/Contact/contactResult";
	}
	
	/**
	 * @description 문의 메일 발송 및 DB 저장 컨트롤러
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/sendContact", method = RequestMethod.POST)
	public String sendContact(MultipartHttpServletRequest req
							, Model model) throws Exception{
		
		Contact contact = new Contact();
		String code = req.getParameter("code");
		String email = req.getParameter("email");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		
		contact.setContactKind(code);
		contact.setFromEmail(email);
		contact.setTitle(title);
		contact.setContents(contents);
		
		File file = null;
		MultipartFile multifile = req.getFile("attach");
		
		//첨부파일이 있다면 file을 가져온다.
		if(!req.getFile("attach").isEmpty()) {
			file = new File(multifile.getOriginalFilename());
		}
		boolean isCheck = service.sendContact(contact, file, multifile.getInputStream());
		
		return "redirect:/contact/contactResult";
	}
	
}
