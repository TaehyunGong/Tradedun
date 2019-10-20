package com.thkong.tradedun.User.dao;

import com.thkong.tradedun.User.vo.User;

public interface userDao {

	public User selectUserOne(String userId);
	
	public int insertUser(User user);
}
