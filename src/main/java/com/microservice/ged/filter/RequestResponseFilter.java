package com.microservice.ged.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
//import com.microservice.ged.service.UserService;
import com.microservice.ged.utils.JwtTokenUtil;
import com.microservice.ged.utils.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class RequestResponseFilter implements Filter {
   // @Autowired
    //private UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(req.getUserPrincipal() != null) {

          //  Users springUser = userService.findByUsername(req.getUserPrincipal().getName());
//get subject in send token

            /*String JwtaccessToken = JWT.create().withSubject(req.getUserPrincipal().getName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000))
                    .withIssuer(req.getRequestURL().toString())
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET));

            res.addHeader(
            		SecurityConstants.HEADER_STRING, 
            		SecurityConstants.TOKEN_PREFIX + jwtTokenUtil.generateAuthorizationToken(authenticationRequest.getUsername(), authorities) + SecurityConstants.TOKEN_SUFIX);
            */chain.doFilter(request, response);

        }else{
                chain.doFilter(request, response);
        }

    }
}
