package com.thkong.tradedun.Common;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter{

	/* 인터셉터는 나중에 사용하도록하자..
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 if(log.isDebugEnabled()) {
			 log.debug("====================================== START ======================================");
			 log.debug(" Request URI \t: " + request.getRequestURI());
		 }
		return super.preHandle(request, response, handler);
	}
	@Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("====================================== END ======================================\n");
		}
	}
	*/

}
