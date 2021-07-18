package com.project.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
	
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(customAuthenticationProvider);
		// auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http

				.csrf()
				.disable()
				.formLogin()
				.loginPage("/login") // 로그인 시도 시에 가는 페이지 파라미터 username , password action =
				.usernameParameter("username") // username 파라미터 변수 세팅
				.passwordParameter("password") // password 파라미터 변수 세팅
				
				.permitAll()
				.successHandler(successHandler())
				.failureHandler(failHandler())
				// .loginProcessingUrl("/loginKyung") // 로그인 처리 (post방식) 으로 보낼 url
				.and().authorizeRequests()
				.anyRequest()
				.authenticated();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub
			}
		};
	}
	
	@Bean
	public AuthenticationFailureHandler failHandler() {
		return new AuthenticationFailureHandler() {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			// TODO Auto-generated method stub
			}
		};
	}


}
