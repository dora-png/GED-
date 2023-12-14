package com.microservice.ged.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.microservice.ged.beans.RequestLogin;
import com.microservice.ged.service.RequestLoginService;
import javax.annotation.PostConstruct;
import javax.naming.directory.Attributes;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OpenLdapAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapContextSource contextSource;
    
    @Autowired
    private RequestLoginService requestLoginService;

    private LdapTemplate ldapTemplate;
    
    @PostConstruct
    private void initContext() {
        contextSource.setUrl("ldap://localhost:8389");
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUserDn("ou=people,ou=groups,");
        contextSource.afterPropertiesSet();
        ldapTemplate = new LdapTemplate(contextSource);
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {    	
        Filter filter = new EqualsFilter("uid", authentication.getName());
        Boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(),
                authentication.getCredentials().toString());
        if (authenticate) {
        	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            UserDetails userDetails = new User(authentication.getName() ,authentication.getCredentials().toString()
                    ,grantedAuthorities);
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                    authentication.getCredentials().toString() , grantedAuthorities);  
            List<RequestLogin> requestLogin = requestLoginService.getRequestLogin("ip address", authentication.getName());
            if(!requestLogin.isEmpty()) {
            	if(requestLogin.size()<3) {
            		requestLoginService.deleteRequestLogin("ip address", authentication.getName());
            	}
            	else {
            		throw new DisabledException("Account is lock, contact administrator to unlock your account");
            	}
            }
            return auth;

        } else {
        	List<RequestLogin> requestLogin = requestLoginService.getRequestLogin("ip address", authentication.getName());
        	if (requestLogin.size()==0) {
 			   requestLoginService.saveRequestLogin(new RequestLogin("ip address", authentication.getName()));
 			  throw new BadCredentialsException("Bad Credentials, try again: 2 tentatives");
 		   } else if (requestLogin.size() == 1) {
 			  requestLoginService.saveRequestLogin(new RequestLogin("ip address", authentication.getName()));
 			 throw  new BadCredentialsException("Bad Credentials, try again:  1 tentatives");
 		   } else if (requestLogin.size() == 2) {
  			  requestLoginService.saveRequestLogin(new RequestLogin("ip address", authentication.getName()));
  			 throw  new BadCredentialsException("Bad Credentials, account has been locked, contact administrator to unlock your account");
  		   }else{
 			  throw new DisabledException("Account is lock, contact administrator to unlock your account");
 		   }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
