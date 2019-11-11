package com.thkong.tradedun.Auction.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CodeTB {

	private String code;
	private String codeKind;
	private String codeName;
	private Date deleteDT;
	private int sort;
	private String refCode;
}
