package com.thkong.tradedun.User.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thkong.tradedun.Common.httpConnection;
import com.thkong.tradedun.User.dao.userDao;
import com.thkong.tradedun.User.vo.Access_token_info;
import com.thkong.tradedun.User.vo.KakaoInfoDetail;
import com.thkong.tradedun.User.vo.KakaoLoginOutput;
import com.thkong.tradedun.User.vo.User;

@Transactional
@Service
public class userServiceImpl implements userService {

	@Value("#{props['kakako.RestKey']}")
	private String kakaoRestKey;  
	
	@Value("#{props['kakako.AdminKey']}")
	private String kakaoAdminKey;
	
	@Autowired
	private userDao dao;
	
	private httpConnection conn = httpConnection.getInstance();
	private ObjectMapper mapper = null;
	
	public userServiceImpl() {
		this.mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
	public String kakaoLogin(String code, String redirectUrl) throws IOException {
		
		//사용자가 취소 누르면 error 파라메터를 받음 
		// 그때 여기서 구분해야할듯
		Map<String, String> map = new HashMap<String, String>();
		map.put("grant_type=", "authorization_code");
		map.put("client_id=", kakaoRestKey); //카카오 앱에 있는 REST KEY
		map.put("redirect_uri=", redirectUrl+"/kakaoLogin"); //카카오 앱에 등록한 redirect URL
		map.put("code=", code);
		
		String out = conn.HttpPostConnection("https://kauth.kakao.com/oauth/token", map).toString();
		
		KakaoLoginOutput output = mapper.readValue(out, KakaoLoginOutput.class);
		
		kakaoInfo(output.getAccess_token());
		
		return output.getAccess_token();
	}
	
	/**
	 * @description SNS종류에 따라 로그인을 시도한다. 만약 최초 ID라면 회원가입도 동시에 시켜준다.
	 * @createDate 2019. 10. 21.
	 * @param userNo
	 * @param sns
	 * @return
	 * @throws IOException 
	 */
	public User snsLoginAndJoin(String userId, String sns) throws IOException {
		User user = dao.selectUserOne(userId);
		
		//해당 유저가 가입돼 있지 않다면 가입시켜준다.
		if(user == null) {
			int result = dao.insertUser(kakaoInfoDetail(userId));
			throw new IOException();
		}
		
		return user;
	}
	
	
	/**
	 * @description 카카오톡의 access_token를 가지고 고유의 userid를 가져온다.
	 * @createDate 2019. 10. 21.
	 * @param access_token
	 * @throws IOException
	 */
	public void kakaoInfo(String access_token) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "Bearer "+ access_token);
		
		String out = conn.HttpGetConnection("https://kapi.kakao.com/v1/user/access_token_info", map).toString();
		Access_token_info output = mapper.readValue(out, Access_token_info.class);
		
		snsLoginAndJoin(output.getId(), "kakao");
	}
	
	/**
	 * @description 카카오 userId로 해당 디테일한 정보들은 가져온다.
	 * @createDate 2019. 10. 21.
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public User kakaoInfoDetail(String userId) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "KakaoAK "+ kakaoAdminKey);
		map.put("target_id_type=", "user_id");
		map.put("target_id=", userId);
		
		String result = conn.HttpPostConnection("https://kapi.kakao.com/v2/user/me", map).toString();
		KakaoInfoDetail detail = mapper.readValue(result, KakaoInfoDetail.class);

		User user = new User();
		user.setUserNo(userId);
		user.setNickName(detail.getProperties().getNickname());
		user.setEmail(detail.getKakao_account().getEmail());
		user.setCreateDT(new Date());
		
		return user;
	}

}
