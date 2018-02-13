package com.my.framework.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		log.debug("########## onAuthenticationSuccess");
		
		// 세션에 로그인 사용자 정보 저장
		CustomUserDetails loginUser = (CustomUserDetails) authentication.getDetails();
		request.getSession().setAttribute("loginUser", loginUser);
		log.debug("########## loginUser : {}", loginUser.toString());

		ObjectMapper om = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("success", true);
		map.put("loginUser", loginUser);
		
		String jsonString = om.writeValueAsString(map);
		
		OutputStream out = response.getOutputStream();
		out.write(jsonString.getBytes());
	}
	
	private String getReturnUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			return request.getSession().getServletContext().getContextPath();
		}
		
		return savedRequest.getRedirectUrl();
	}

}
