package com.thkong.tradedun.User.vo;

import lombok.Data;

@Data
public class User {
	private String userNo;
	private String snsId;
	private String nickName;
	private String email;
	private String emailYN;
	private String messageYN;
	private String createDT;
	private String deleteDT;
}
