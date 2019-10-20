package com.thkong.tradedun.User.vo;

import lombok.Data;

@Data
public class Kakao_account {
	private boolean profile_needs_agreement;
	private boolean email_needs_agreement;
	private boolean is_email_valid;
	private boolean is_email_verified;
	private String email;
	private boolean age_range_needs_agreement;
	private String age_range;
	private boolean birthday_needs_agreement;
	private String birthday;
	private boolean gender_needs_agreement;
	private String gender;
}
