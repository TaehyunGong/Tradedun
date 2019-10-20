package com.thkong.tradedun.User.vo;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private String userNo;
	private String snsId;
	private String nickName;
	private String email;
	private char emailYN;
	private char messageYN;
	private Date createDT;
	private Date deleteDT;
}
