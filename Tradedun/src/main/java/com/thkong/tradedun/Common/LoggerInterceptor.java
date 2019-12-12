package com.thkong.tradedun.Common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thkong.tradedun.Common.dao.LogsDao;
import com.thkong.tradedun.User.vo.User;

public class LoggerInterceptor extends HandlerInterceptorAdapter{

	//더미 로그 전용 dao
	@Autowired
	private LogsDao logsDao;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override 
	public boolean preHandle(HttpServletRequest request
						   , HttpServletResponse response
						   , Object handler) throws Exception {
		//request의 파라메터나 헤더를 파싱하여 가져온다.
		Map<String, Object> map = convertMap(request);
		
		log.info("요청 URL : " + map.get("requestUrl") + ",\t" + "ClientIP : " + map.get("clientIP") + ",\t" + "referer : " + map.get("referer"));
		
		//로그는 시스템에 절대 영향에 끼쳐서는 안되기 때문에 try/catch 처리
		try {
			logsDao.insertLogs(map);
		}catch(Exception ex) {
			log.error(ex);
		}
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * @description 요청들어온 모든 파라메터의 값을 빼내어 map으로 변환한다.
	 * 				이때 최대 파라메터 최대 5개만 가져오고 각각 순서대로 attr1, 2, 3로 키를 갖는다. 
	 * 				Session에 user 값이 있다면 userNo 값도 넣어줌 
	 * @param req
	 * @return
	 */
	public Map<String, Object> convertMap(HttpServletRequest req){
		Enumeration params = req.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		
		//파라메터의 갯수 카운터 변수
		int count = 1;
		
		//요청의 파라메터를 반복하여 모든 파라메터를 MAP으로 변경
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    map.put("attr"+count, req.getParameter(name));
		    count+=1;
		}

		//유저의 정보를 가져온다. 비로그인경우의 조건추가
		User user = (User)req.getSession().getAttribute("user");
		if(user != null) {
			map.put("userNo", user.getUserNo());
		}
		
		//기본적인 Header 정보들
		map.put("requestUrl", req.getRequestURI());
		map.put("host", req.getHeader("host"));
		map.put("user-agent", req.getHeader("user-agent"));
		map.put("referer", req.getHeader("referer"));
		map.put("cookie", req.getHeader("cookie"));
		map.put("clientIP", getClientIpAddr(req));
		
		return map;
	}
	
	/**
	 * @description 클라이언트 IP 추출
	 * @param request
	 * @return
	 */
	public String getClientIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	 
	    return ip;
	}

	
	@Override 
	public void postHandle(HttpServletRequest request
						 , HttpServletResponse response
						 , Object handler
						 , ModelAndView modelAndView) throws Exception {
	}
	
}
