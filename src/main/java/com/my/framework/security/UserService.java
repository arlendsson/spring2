package com.my.framework.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class UserService implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserDetailsService.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("UserService.loadUserByUsername");
		
		String password = "1234";
		
		CustomUserDetails user = new CustomUserDetails(username, password);
		
		return user;
	}

}
