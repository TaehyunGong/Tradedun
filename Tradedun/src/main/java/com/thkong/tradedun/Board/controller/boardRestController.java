package com.thkong.tradedun.Board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thkong.tradedun.Board.service.boardService;

@RestController
@RequestMapping(value="/board")
public class boardRestController {

	@Autowired
	boardService service;
	
	/**
	 * @description ckEditor로 이미지 업로드시 파일업로드 로직
	 * @param multiFile
	 * @return
	 * @throws  
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadFile")
	public Map<String, String> uploadFile(MultipartHttpServletRequest multiFile) throws IOException{
		
		MultipartFile multifile = multiFile.getFile("upload");
		
		File file = new File(multifile.getOriginalFilename());
		
		multifile.transferTo(file);
		Map<String, String> jsonMap = service.uploadFile(file);
		
		return jsonMap;
	}
}
