package com.my.framework.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	private Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("########## " + request.getRequestURI());
		return "home/homeChart";
	}

}
