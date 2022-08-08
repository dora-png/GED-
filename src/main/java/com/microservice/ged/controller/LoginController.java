package com.microservice.ged.controller;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.security.config.OpenLdapAuthenticationProvider;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.DroitProfilesServices;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.JwtRequest;
import com.microservice.ged.utils.JwtTokenUtil;
import com.microservice.ged.utils.SecurityConstants;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    
	@Autowired
	DroitsRepo droitsRepo;
	
	@Autowired
	ProfilesService profilesService;
	
	@Autowired
	GroupProfileService groupProfileService;
	
	@Autowired
	DroitProfilesServices droitProfilesServices;

	@Autowired
	AppUserService appUserService;
	
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
    {    	
        if(!authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())){
        	throw new Exception();
        }
        List<String> authorities = new ArrayList<>();
        if(authenticationRequest.getUsername().equals("root")) {
        	List<Droits> droits =  droitsRepo.findAll();
			droits.forEach(
					(droit)->{
						if(!authorities.contains(droit.getAbbr())) {
							authorities.add(droit.getAbbr());
						}
					}
				 );
			authorities.add("ADMIN");
        } else {
        	Profiles profiles = profilesService.findProfileByUserName(authenticationRequest.getUsername());
        	if(profiles != null) {
        		droitProfilesServices.findListDroit(profiles.getIdProfiles()).forEach(
        				(droits)->{
        					if(!authorities.contains(droits.getAbbr())) {
    							authorities.add(droits.getAbbr());
    						}
        				}
        		);
        		groupProfileService.findListDroit(profiles.getIdProfiles()).forEach(
        				(droits)->{
        					if(!authorities.contains(droits.getAbbr())) {
    							authorities.add(droits.getAbbr());
    						}
        				}
        		);
        		
        	}
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(
        		SecurityConstants.HEADER_STRING, 
        		SecurityConstants.TOKEN_PREFIX + jwtTokenUtil.generateAuthorizationToken(authenticationRequest.getUsername(), authorities) + SecurityConstants.TOKEN_SUFIX);
        Map<String, String> idToken = new HashMap<>();
		idToken.put("RefreshToken", jwtTokenUtil.generateAuthentificationToken(authenticationRequest.getUsername()));
        return ResponseEntity.ok().headers(responseHeaders).body(idToken);
    }

    
    private Boolean authenticate(String username, String password) throws Exception {
    	Boolean userExist = false;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            userExist=true;
        } catch (DisabledException e) {
            throw new Exception(e.getLocalizedMessage());
        } catch (BadCredentialsException e) {
            throw new Exception(e.getLocalizedMessage());
        }
        return userExist;
    }
}