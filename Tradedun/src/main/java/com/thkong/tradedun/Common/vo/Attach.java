package com.thkong.tradedun.Common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Attach {

	private int attachNo;
	private int targetNo;
	private String kindCode;
	private String attachOrigin;
	private String attachExtenstion;
	private String attachName;
	private Date deleteDT;
	
}
