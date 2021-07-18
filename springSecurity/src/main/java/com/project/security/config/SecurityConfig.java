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
				.loginPage("/login") // �α��� �õ� �ÿ� ���� ������ �Ķ���� username , password action =
				.usernameParameter("username") // username �Ķ���� ���� ����
				.passwordParameter("password") // password �Ķ���� ���� ����
				
				.permitAll()
				.successHandler(successHandler())
				.failureHandler(failHandler())
				// .loginProcessingUrl("/loginKyung") // �α��� ó�� (post���) ���� ���� url
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
