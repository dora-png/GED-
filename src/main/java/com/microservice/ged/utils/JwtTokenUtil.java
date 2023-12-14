package com.microservice.ged.utils;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil implements Serializable
{

    public static final long JWT_TOKEN_VALIDITY = 1000 * 3600;


    public  Collection<SimpleGrantedAuthority> getAllGrantedAuthorityFromToken(String token) {
    	token = token.replace(SecurityConstants.TOKEN_PREFIX, SecurityConstants.REMOVE)
				  .replace(SecurityConstants.TOKEN_SUFIX, SecurityConstants.REMOVE);
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String[] roles = decodedJWT.getClaim(SecurityConstants.ROLES).asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
        	authorities.add(new SimpleGrantedAuthority(role));
         });
        return authorities;
    }
    
    public String  getSubject(String token) {
    	token = token.replace(SecurityConstants.TOKEN_PREFIX, SecurityConstants.REMOVE)
				  .replace(SecurityConstants.TOKEN_SUFIX, SecurityConstants.REMOVE);
    	Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return username;
    }   
    
    public String generateAuthentificationToken(String userName, String color, String name, String sigle) {
    	String authentificationToken = JWT.create().withSubject(userName)
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_REFRESH))
				.withClaim("name",name)
				.withClaim("sigle",sigle)
				.withClaim("color",color)
				.sign(Algorithm.HMAC256(SecurityConstants.SECRET));
    	return authentificationToken;
    }
    
    public String generateAuthorizationToken(String userName,  List<String> authorities) {
    	String jwtToken = JWT.create().withSubject(userName)
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.withClaim(
						SecurityConstants.ROLES,
						authorities)
				.sign(Algorithm.HMAC256(SecurityConstants.SECRET));
        return jwtToken;
    }
    
    // validate token
    public Boolean validateToken(String jwt) {
    	if(jwt != null && jwt.startsWith(SecurityConstants.TOKEN_PREFIX) && jwt.endsWith(SecurityConstants.TOKEN_SUFIX)) {
    		return true;
    	}else {
    		return false;
    	}
    }
}