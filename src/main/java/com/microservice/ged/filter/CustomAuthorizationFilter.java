package com.microservice.ged.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.ged.utils.SecurityConstants;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       // response.addHeader("Access-Control-Allow-Origin", "*");
      //  response.addHeader("Access-Control-Allow-Headers", 
      //  		"Origin, Accept, X-Requested-With, Content-Type, "
      //  		+ "Access-Control-Request-Method, "
      //  		+ "Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
        		+ "Access-Control-Allow-Credentials, Authorization");
		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			if(request.getServletPath().equals("/login")) {
	            filterChain.doFilter(request, response);
	        } else {
	            String jwt = request.getHeader(SecurityConstants.HEADER_STRING);            
	            if(jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX) || !jwt.endsWith(SecurityConstants.TOKEN_SUFIX)) {
	            	filterChain.doFilter(request, response);
	            	return;
	            }  
	            try {
	            	String token = jwt.replace(SecurityConstants.TOKEN_PREFIX, SecurityConstants.REMOVE)
	            					  .replace(SecurityConstants.TOKEN_SUFIX, SecurityConstants.REMOVE);
	            	Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
	                JWTVerifier verifier = JWT.require(algorithm).build();
	                DecodedJWT decodedJWT = verifier.verify(token);
	                String username = decodedJWT.getSubject();
	                String[] roles = decodedJWT.getClaim(SecurityConstants.ROLES).asArray(String.class);
	                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
	                stream(roles).forEach(role -> {
	                	authorities.add(new SimpleGrantedAuthority(role));
	                 });
	                 UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(username, null, authorities);
	                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                 filterChain.doFilter(request, response);
	            }catch (Exception e) {
	            	response.setContentType("text/plain");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					new ObjectMapper().writeValue(response.getOutputStream(), "Tokken Modified, vous allez etre deconnecte dans 5 secondes");
	            }	           
	        }
		}        
    }

}
