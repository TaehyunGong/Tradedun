package com.thkong.tradedun.Board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thkong.tradedun.Common.FileLib;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	private FileLib fileLib;
	
	@Override
	public Map<String, String> uploadFile(File file) throws IOException{
		String fileName= fileLib.uploadFile(file);
		
		// json 데이터로 등록
        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("uploaded", "1");
		jsonMap.put("fileName", fileName);
		jsonMap.put("url", "/upImage/board/"+fileName);
		
		return jsonMap;
	}
}
