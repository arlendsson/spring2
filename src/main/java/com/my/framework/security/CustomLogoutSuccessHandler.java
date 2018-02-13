package com.my.framework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//CustomUserDetails loginUser = (CustomUserDetails) authentication.getDetails();
		log.debug("########## onLogoutSuccess");
		//log.debug("########## loginUser : {}", loginUser);
		
		super.onLogoutSuccess(request, response, authentication);
	}


}
