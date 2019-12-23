package com.thkong.tradedun.Contact.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Contact {

	private int contactNo;
	private String contactKind;
	private String fromEmail;
	private String title;
	private String contents;
	private Date createDT;
	
}
