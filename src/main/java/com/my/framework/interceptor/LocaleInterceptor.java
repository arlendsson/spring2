package com.my.framework.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class LocaleInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(LocaleInterceptor.class);
	// 다국어 설정
	@Autowired
	SessionLocaleResolver localeResolver;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		log.debug("########## LocaleInterceptor");

		HttpSession session = request.getSession();

		localeResolver.setDefaultLocale(Locale.KOREA);

		super.postHandle(request, response, handler, modelAndView);
	}

}
