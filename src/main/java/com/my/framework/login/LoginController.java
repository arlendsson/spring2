package com.my.framework.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.framework.mypage.MyPageService;
import com.my.framework.security.CustomUserDetails;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	MyPageService service;
	
	@RequestMapping(value = "/loginPage")
	public String loginPage() throws Exception {
		return "login/loginPage";
	}
	
	@RequestMapping(value = "/loginForm")
	public String loginForm() throws Exception {
		return "login/loginForm";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		CustomUserDetails userDetails = (CustomUserDetails) session.getAttribute("loginUser");
		
		log.debug("########## Welcome logout! {}, {}", session.getId(), "");
		log.debug("########## Welcome logout! {}, {}", "", userDetails.getId());
		session.removeAttribute("loginUser");
		session.invalidate();
		
		return "redirect:login/loginPage";
	}

	@RequestMapping(value = "/loginDuplicate")
	public void loginDuplicate() throws Exception {
		log.debug("########## Welcome loginDuplicate!");
	}
	
	
	
}
