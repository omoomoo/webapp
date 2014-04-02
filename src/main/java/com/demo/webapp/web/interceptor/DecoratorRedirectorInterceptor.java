package com.demo.webapp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DecoratorRedirectorInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO why exception without if?
		if (modelAndView != null) {
			String viewName = modelAndView.getViewName();
			String decorator = request.getParameter("_decorator");

			// TODO anything else to do?
			// TODO 需要判断重定向里面是否包含参数
			if (viewName.startsWith("redirect:") && decorator != null) {
				modelAndView.setViewName(viewName + "?_decorator=" + decorator);
			}

			System.out.println("***************view name:" + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
