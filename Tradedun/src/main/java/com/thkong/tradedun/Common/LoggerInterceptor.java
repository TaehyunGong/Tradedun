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

public class LoggerInterceptor extends HandlerInterceptorAdapter{

	//더미 로그 전용 dao
	@Autowired
	private LogsDao logsDao;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override 
	public boolean preHandle(HttpServletRequest request
						   , HttpServletResponse response
						   , Object handler) throws Exception {
		
		Map<String, String> map = null;
		
		try {
			switch(request.getRequestURI()) {
			case "/login" :  
				map = convertMap(request);
				
				map.put("mapper", request.getRequestURI());
				map.put("host", request.getHeader("host"));
				map.put("user-agent", request.getHeader("user-agent"));
				map.put("referer", request.getHeader("referer"));
				map.put("cookie", request.getHeader("cookie"));
				
				logsDao.insertUserLoginLog(map);
				
				break;
			case "/kakaoLogin" : break;
			case "/logout" : break;
			
			}
		}catch(Exception ex) {
			
		}
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * @description 요청들어온 모든 파라메터의 값을 빼내어 map으로 변환
	 * @param req
	 * @return
	 */
	public Map<String, String> convertMap(HttpServletRequest req){
		Enumeration params = req.getParameterNames();
		Map<String, String> map = new HashMap<String, String>();
		
		//요청의 파라메터를 반복하여 모든 파라메터를 MAP으로 변경
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    map.put(name, req.getParameter(name));
		}

		return map;
	}
	
	@Override 
	public void postHandle(HttpServletRequest request
						 , HttpServletResponse response
						 , Object handler
						 , ModelAndView modelAndView) throws Exception {
	}
	
}
