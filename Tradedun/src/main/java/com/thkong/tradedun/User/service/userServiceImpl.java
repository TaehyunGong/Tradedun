package com.thkong.tradedun.User.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Common.dao.LogsDao;
import com.thkong.tradedun.User.dao.userDao;
import com.thkong.tradedun.User.vo.Access_token_info;
import com.thkong.tradedun.User.vo.KakaoInfoDetail;
import com.thkong.tradedun.User.vo.KakaoLoginOutput;
import com.thkong.tradedun.User.vo.User;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Transactional
@Service
public class userServiceImpl implements userService {

	@Value("#{props['kakako.RestKey']}")
	private String kakaoRestKey;  
	
	@Value("#{props['kakako.AdminKey']}")
	private String kakaoAdminKey;
	
	@Autowired
	private userDao dao;
	
	//더미 로그 전용 dao
	@Autowired
	private LogsDao logsDao;

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * @description rest Response가 200이 아니라면 이 메소드를 실행하여 에러 메세지를 출력한다.
	 * @param <T>
	 * @param res
	 */
	public <T> void exceptionMethod(HttpResponse<T> res) {
		System.out.println("Oh No! Status " + res.getStatus());
    	System.out.println("Parsing Exception: "+ res.getStatusText());
    	
    	res.getParsingError().ifPresent(e -> {
    		System.out.println("ifPresent Exception: "+ e);
    		System.out.println("Original body: " + e.getOriginalBody());
    	});
	}
	
	/**
	 * @description 카카오톡 로그인을 하기위해 카카오 로그인 페이지로 리다이렉트 url를 생성해준다.
	 * @createDate 2019. 10. 19.
	 * @param SNS
	 * @param req
	 * @return Kakao Redirect URL
	 */
	@Override
	public String login(String SNS, String redirectUrl) {
		
		StringBuilder loginUrl = new StringBuilder();
		loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=")
		.append(kakaoRestKey)
		.append("&redirect_uri=")
		//카카오 앱에 등록한 redirect URL, contextPath는 변하지 않는다.
		.append(redirectUrl+"/kakaoLogin") 
		.append("&response_type=code");
		
		return "redirect:"+loginUrl.toString();
	}

	/**
	 * @description 카카오 로그인 한 사용자의 code를 받아 POST로 재요청 후 access_token를 발급받는다.
	 * @createDate 2019. 10. 19.
	 * @param code
	 * @param redirectUrl
	 * @return Kakao user Unique ID
	 * @throws IOException
	 */
	@Override
	public User kakaoLogin(String code, String redirectUrl) throws IOException {
		
		//사용자가 취소 누르면 error 파라메터를 받음 
		// 그때 여기서 구분해야할듯
		KakaoLoginOutput output = Unirest.post("https://kauth.kakao.com/oauth/token")
										.header("charset", "utf-8")
										.field("grant_type", "authorization_code")
										.field("client_id", kakaoRestKey)
										.field("redirect_uri", redirectUrl + "/kakaoLogin")
										.field("code", code)
										.asObject(KakaoLoginOutput.class)
										.ifFailure(res -> exceptionMethod(res))
										.getBody();
		
		return kakaoInfo(output.getAccess_token());
	}
	
	/**
	 * @description 카카오톡의 access_token를 가지고 고유의 userid를 가져온다.
	 * @createDate 2019. 10. 21.
	 * @param access_token
	 * @throws IOException
	 */
	public User kakaoInfo(String access_token) throws IOException {
		Access_token_info output = Unirest.get("https://kapi.kakao.com/v1/user/access_token_info")
										.header("Authorization", "Bearer "+ access_token)
										.asObject(Access_token_info.class)
										.ifFailure(res -> exceptionMethod(res))
										.getBody();
		
		User user = snsLoginAndJoin(output.getId());
		user.setAccess_token(access_token);
		
		return user;
	}
	
	/**
	 * @description SNS종류에 따라 로그인을 시도한다. 만약 최초 ID라면 회원가입도 동시에 시켜준다.
	 * @createDate 2019. 10. 21.
	 * @param userNo
	 * @param sns
	 * @return
	 * @throws IOException 
	 */
	public User snsLoginAndJoin(String userId) throws IOException {
		User user = dao.selectUserOne(userId);
		
		//해당 유저가 가입돼 있지 않다면 가입시켜준다.
		if(user == null) {
			user =kakaoInfoDetail(userId);
			
			//만약 회원가입을 실패한다면 에러를 반환한다.
			if(dao.insertUser(user) == 0) {
				new IOException();
			}
		}
		
		return user;
	}
	
	
	/**
	 * @description 카카오 userId로 해당 디테일한 정보들은 가져온다.
	 * @createDate 2019. 10. 21.
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public User kakaoInfoDetail(String userId) throws IOException {
		KakaoInfoDetail detail = Unirest.post("https://kapi.kakao.com/v2/user/me")
										.header("Authorization", "KakaoAK "+ kakaoAdminKey)
										.field("target_id_type", "user_id")
										.field("target_id", userId)
										.asObject(KakaoInfoDetail.class)
										.ifFailure(res -> exceptionMethod(res))
										.getBody();
		
		User user = new User();
		user.setUserNo(userId);
		user.setNickName(detail.getProperties().getNickname());
		user.setEmail(detail.getKakao_account().getEmail());
		user.setCreateDT(new Date());
		
		return user;
	}

	/**
	 * @description session에 있는 access_token를 가져와 로그아웃 요청 처리를 해주고 성공여부를 알기위해 id를 반환해준다.
	 * @createDate 2019. 10. 21.
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@Override
	public String logout(HttpSession session) throws IOException {
		User user = (User)session.getAttribute("user");
		
		Access_token_info output = Unirest.post("https://kapi.kakao.com/v1/user/logout")
										.header("Authorization", "Bearer "+ user.getAccess_token())
										.asObject(Access_token_info.class)
										.ifFailure(res -> exceptionMethod(res))
										.getBody();
		return output.getId();
	}

	/**
	 * @description 마이 페이지의 내용을 저장한다.
	 * @createDate 2020. 01. 22.
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUserInfo(User user) {
		return dao.updateUserInfo(user);
	}

	/**
	 * @description 마이 페이지의 내용을 가져온다.
	 * @createDate 2020. 01. 22.
	 * @param user
	 * @return
	 */
	@Override
	public User selectUserInfo(String userId) {
		return dao.selectUserInfo(userId);
	}

}
