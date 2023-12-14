package com.microservice.ged.filter;



import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.ged.beans.Trace;
import com.microservice.ged.repository.TraceRepository;
import com.microservice.ged.utils.JwtTokenUtil;
import com.microservice.ged.utils.SecurityConstants;

@Component
public class TraceInterceptor implements HandlerInterceptor {

	@Autowired
	private TraceRepository tracerepository;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
    	response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
        		+ "Access-Control-Allow-Credentials, Authorization");
		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			if(request.getServletPath().equals("/login")) {
	            return true;
	        } else {
	            String jwt = request.getHeader(SecurityConstants.HEADER_STRING); 
	            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
	            if(!jwtTokenUtil.validateToken(jwt)) {
	            	return false;
	            }

	            try {
	            	Collection<SimpleGrantedAuthority> authorities = jwtTokenUtil.getAllGrantedAuthorityFromToken(jwt);
	            	String username =  jwtTokenUtil.getSubject(jwt);
	                 UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(username, null, authorities);
	                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                 return true;
	            }catch (Exception e) {
	            	response.setContentType("text/plain");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
					return false;
	            }	           
	        }
		}        
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
    	System.err.println("TraceInterceptor postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/*
		 * String user = "Visitor";
		 * 
		 * 
		 * System.err.println("TraceInterceptor");
		 * 
		 * Trace t = new Trace(user, request.getRequestURI(),
		 * request.getRequestURL().toString(), null, request.getMethod(),
		 * response.getStatus());
		 * 
		 * 
		 * if (request.getHeader("x-forwarded-for") == null) {
		 * t.setIp(request.getRemoteAddr()); }else{
		 * t.setIp(request.getHeader("x-forwarded-for")); }
		 * 
		 * 
		 * if (request.getUserPrincipal() == null) { tracerepository.save(t); } else {
		 * t.setUsername(request.getUserPrincipal().getName()); tracerepository.save(t);
		 * }
		 */

	}

}

