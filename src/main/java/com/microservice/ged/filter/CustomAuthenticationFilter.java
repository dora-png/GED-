package com.microservice.ged.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.ged.beans.RequestLogin;
import com.microservice.ged.beans.Users;
import com.microservice.ged.service.RequestLoginService;
import com.microservice.ged.service.UserService;
import com.microservice.ged.utils.SecurityConstants;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	
	private AuthenticationManager authenticationManager;
	
	private RequestLoginService requestLoginService;

	private UserService userService;
	
	private String login=null;
	
	
	
	
	/**
	 * @param authenticationManager
	 * @param requestLoginService
	 * @param userService
	 */
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
			RequestLoginService requestLoginService, UserService userService) {
		super();
		this.authenticationManager = authenticationManager;
		this.requestLoginService = requestLoginService;
		this.userService = userService;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	
		Users appUser = new Users();
		try {
			appUser = new ObjectMapper().readValue(request.getInputStream(), Users.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		this.login = appUser.getUsername();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword());
		return authenticationManager.authenticate(authenticationToken);
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authorization) throws IOException, ServletException {
		/*
		 * Le Token de connexion
		 */
		User springUser = (User) authorization.getPrincipal();

		String JwtToken = JWT.create().withSubject(springUser.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.withIssuer(request.getRequestURL().toString())
				/*.withClaim("roles",
						springUser.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()))*/
				.sign(Algorithm.HMAC256(SecurityConstants.SECRET));
		response.setHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + JwtToken);

		/*
		 * Le Token de rafrechissement
		 */
		String JwtRefreshToken = JWT.create().withSubject(springUser.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_REFRESH))
				.withClaim("roles",
						springUser.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET));
	//	response.addHeader("Acces-Token", SecurityConstants.TOKEN_PREFIX + JwtToken);
/*
		Map<String, String> idToken = new HashMap();
		idToken.put("Acces-Token", SecurityConstants.TOKEN_PREFIX + JwtToken);
		idToken.put("Refresh-Token", JwtRefreshToken);
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), idToken);*/
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		if(request.getHeader("X-FORWARDED-FOR").equals(null)) {

		   response.setContentType("text/plain");
		   response.setStatus(401);
		   new ObjectMapper().writeValue(response.getOutputStream(), "Username or password is incorrect");

	   }else {

		   RequestLogin requestLogin = requestLoginService.getRequestLogin(request.getHeader("X-FORWARDED-FOR"), this.login);

		   if (requestLogin == null) {
			   requestLoginService.saveRequestLogin(new RequestLogin(request.getHeader("X-FORWARDED-FOR"), this.login, 1));
			   response.setContentType("text/plain");
			   response.setStatus(401);
			   new ObjectMapper().writeValue(response.getOutputStream(), "Username or password is incorrect");

		   } else if (requestLogin.getCount() < 3) {

			   requestLogin.setCount(requestLogin.getCount() + 1);
			   requestLoginService.saveRequestLogin(requestLogin);
			   response.setContentType("text/plain");
			   response.setStatus(401);
			   new ObjectMapper().writeValue(response.getOutputStream(), "Username or password is incorrect");

		   } else if(requestLogin.getCount() == 3){

			   requestLogin.setCount(requestLogin.getCount() + 1);
			   requestLoginService.saveRequestLogin(requestLogin);
			   Users u = userService.findByUsername(this.login);

			   if(u == null){

				   response.setContentType("text/plain");
				   response.setStatus(401);
				   new ObjectMapper().writeValue(response.getOutputStream(), "The account "+this.login+" is not exist or locked, please contact service");

			   }else {

				   u.setStatus(false);
				   try {
					userService.add(u);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   response.setContentType("text/plain");
				   response.setStatus(401);
				   new ObjectMapper().writeValue(response.getOutputStream(), this.login+" account has been locked ");
			   }
		   }else if(requestLogin.getCount() > 3){

			   response.setContentType("text/plain");
			   response.setStatus(403);
			   new ObjectMapper().writeValue(response.getOutputStream(), this.login+" account has been locked please contact service or go to password is locked ");
		   }
	   }
 }

}
