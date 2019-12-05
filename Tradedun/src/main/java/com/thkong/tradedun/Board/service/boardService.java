package com.thkong.tradedun.Board.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface boardService {

	public Map<String, String> uploadFile(File file) throws IOException;

	public void insertBoard(String title, String contents, String categoryCode) throws Exception;
}
