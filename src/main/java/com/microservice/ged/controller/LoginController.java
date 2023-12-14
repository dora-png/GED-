package com.microservice.ged.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.*;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.JwtRequest;
import com.microservice.ged.utils.JwtTokenUtil;
import com.microservice.ged.utils.SecurityConstants;

@RestController
@RequestMapping("/login")
public class LoginController {

    // Utilisé pour authentifier l'utilisateur.
     @Autowired
    private AuthenticationManager authenticationManager;
    
    // Service utilisé pour obtenir les droits d'un groupe.
    @Autowired
    DroitGroupsService droitGroupsService;
    
	@Autowired
	DroitsRepo droitsRepo;
	
	@Autowired
	ProfilesService profilesService;
	
	@Autowired
	GroupProfileService groupProfileService;
	
	@Autowired
	LogPosteUserService logPosteUserService;
	
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

 
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    //@PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
    {  
    	
        try {
        	if(!authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())){
            	throw new Exception();
            }
	        List<String> authorities = new ArrayList<>();
	        String color = null;
	        String structureName = null;
	        String structureSigle = null;
            if(authenticationRequest.getUsername().equals("root")) {
            	List<Droits> droits =  droitsRepo.findAll();
    			droits.forEach(
    					(droit)->{
    						if(!authorities.contains(droit.getName())) {
    							authorities.add(droit.getName());
    						}
    					}
    				 );
    			//authorities.add("ADMIN");
    			color = "#ff4444";
    			structureName = "Super User";
    			structureSigle = "ROOT";
    			
            } else {
            	Profiles profiles = profilesService.findProfileByUserLogin(authenticationRequest.getUsername());
            	if(profiles != null) {
            		if(!profiles.isStatus()) {
                        throw new Exception("This user is ban");
                    }
            		GroupUser groupUser = groupProfileService.findGroupOfProfile(profiles);
            		if(groupUser!= null) {
            			if(groupUser.getStatus()) {
            				droitGroupsService.findDroitOfGroup(groupUser,1).forEach(
                    				(droits)->{
                							authorities.add(droits.getName());
                    				}
                    		);
            			}
            		}
            		color = profiles.getStructure().getColor();
    				structureSigle = profiles.getStructure().getSigle();
    				structureName = profiles.getStructure().getName();
            	}
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(
            		SecurityConstants.HEADER_STRING, 
            		SecurityConstants.TOKEN_PREFIX + jwtTokenUtil.generateAuthorizationToken(authenticationRequest.getUsername(), authorities) + SecurityConstants.TOKEN_SUFIX);
            Map<String, String> idToken = new HashMap<>();
    		idToken.put("RefreshToken", jwtTokenUtil.generateAuthentificationToken(authenticationRequest.getUsername(), color, structureName, structureSigle));
            return ResponseEntity.ok().headers(responseHeaders).body(idToken);			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}        
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