package com.thkong.tradedun.Common.dao;

import java.util.ArrayList;
import java.util.Map;

public interface LogsDao {
	
	void insertLogs(Map<String, Object> map);

	ArrayList<Map<String, String>> selectUserSearchList(String userNo);

}
