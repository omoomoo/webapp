package com.webapp.demo.web.controller.csrf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * CSRF拦截器
 * 
 */
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return true;
		} else {
			String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
			String requestToken = CSRFTokenManager.getTokenFromRequest(request);
			if (sessionToken.equals(requestToken)) {
				return true;
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				return false;
			}
		}
	}
}