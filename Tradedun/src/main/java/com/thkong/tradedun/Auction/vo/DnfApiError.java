package com.thkong.tradedun.Auction.vo;

import lombok.Data;

@Data
public class DnfApiError {

	private Error error;
	
	@Data
	private class Error{
		private String status;
		private String code;
		private String message;
	}
	
}
