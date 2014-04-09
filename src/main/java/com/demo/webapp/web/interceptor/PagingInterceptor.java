package com.demo.webapp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tiger 分页参数拦截器
 */
public class PagingInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respnse, Object obj) throws Exception {
		try {
			int page = Integer.parseInt(request.getParameter("page"));
			int limit = Integer.parseInt(request.getParameter("limit"));
		} catch (Exception e) {

		}

		return true;
	}

}
