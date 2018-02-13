package com.my.framework.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.my.framework.mypage.MyPageService;
import com.my.framework.mypage.MyPageVo;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private MyPageService service;
	
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String user_id = (String) authentication.getPrincipal();
		String user_pw = (String) authentication.getCredentials();
		
		log.debug("########## CustomAuthenticationProvider.authenticate {}", user_id + " / " + user_pw);
		
		MyPageVo loginUser = null;
		try {
			loginUser = service.selectUser(new MyPageVo(user_id, new BCryptPasswordEncoder().encode(user_pw)));
			log.debug("########## loginUser : {}", loginUser.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("Bad credentials - Search Login User");
		}
		
		if (loginUser != null && StringUtils.isNotEmpty(loginUser.getId())) {
			/**
			 * BCryptPasswordEncoder.matches(CharSequence rawPassword, String encodedPassword) 
			 * BCrypt.checkpw(String plaintext, String hashed)
			 */
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(user_pw, loginUser.getPw())) {
				log.debug("########## login success!");
				List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
				
				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user_id, user_pw, roles);
				result.setDetails(new CustomUserDetails(loginUser.getId(), loginUser.getName()));
				
				return result;
			} else {
				throw new BadCredentialsException("Bad credentials - password not matched!");
			}
		} else {
			log.error("########## login failed...");
			throw new BadCredentialsException("Bad credentials");
			
		}
		
	}

}
