package com.project.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService service;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) {

		
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		log.info("아이디 비밀번호 기본 세팅 id : root, pw : 1234");
		
		if(!username.equals("root") || !password.equals("1234")) {
			log.info("Invalid UserName or Password");
			throw new BadCredentialsException(username);
		}
		
        
		//user.getAuthorities()
		
		return new UsernamePasswordAuthenticationToken(username, password);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		// return false;
	}

	private boolean matchPassword(String loginPwd, String password) {
		return loginPwd.equals(password);
	}

}