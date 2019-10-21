package com.thkong.tradedun.User.vo;

import lombok.Data;

@Data
public class Access_token_info {
	
	private String id;
	private String expiresInMillis;
	private String appId;

}
