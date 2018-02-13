package com.my.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.framework.exception.SessionException;
import com.my.framework.security.CustomUserDetails;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		log.debug("########## LoginInterceptor");

		HttpSession session = request.getSession();

		CustomUserDetails loginUser = (CustomUserDetails) session.getAttribute("loginUser");

		if (loginUser == null || StringUtils.isEmpty(loginUser.getId())) {
			throw new SessionException("session expired.");
		}

		return true;

	}
}
