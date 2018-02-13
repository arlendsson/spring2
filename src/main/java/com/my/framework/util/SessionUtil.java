package com.my.framework.util;

import javax.servlet.http.HttpSession;

import com.my.framework.security.CustomUserDetails;

public class SessionUtil {

	public static CustomUserDetails getLoginUser(HttpSession session) throws Exception {
		return (CustomUserDetails) session.getAttribute("loginUser");
	}
}
