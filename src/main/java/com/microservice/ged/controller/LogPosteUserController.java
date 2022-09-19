package com.microservice.ged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.ProfilesService;
//import com.microservice.ged.service.UserService;

@RestController
@CrossOrigin("*")
public class LogPosteUserController {

	@Autowired
	private LogPosteUserService logPosteUserService;
	
	@Autowired
	private PosteService posteService;
	

	@Autowired
	private ProfilesService userService;

	@GetMapping("/logposteuser/user-log")
	public ResponseEntity<Page<LogPosteUser>> findByUser(
			@RequestParam(name = "idProfile", required = true) Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<LogPosteUser> logPosteUser = logPosteUserService.logUser(idProfile, page, size);
			if(logPosteUser.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(logPosteUser);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

	@GetMapping("/logposteuser/poste-log")
	public ResponseEntity<Page<LogPosteUser>> findByPoste(
			@RequestParam(name = "idPoste", required = true) Long idPoste,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<LogPosteUser> logPosteUser = logPosteUserService.logPoste(idPoste, page, size);
			if(logPosteUser.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(logPosteUser);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

	@GetMapping("/logposteuser/currentposte")
	public ResponseEntity<Postes> currentPosteOfUser(@RequestParam(name = "idUser" , required = true) Long idUser) throws Exception{
		try {
			Postes poste = logPosteUserService.currentPosteOfUser(idUser);
			return  ResponseEntity.ok().body(poste);
		} catch (Exception e) {
			return  ResponseEntity.noContent().build();
		}
		
	}
	
	@GetMapping("/logposteuser/currentuser")
	public ResponseEntity<Profiles> currentUserOfPoste(@RequestParam(name = "idPoste" , required = true) Long idPoste) throws Exception{
		Profiles users = logPosteUserService.currentUserOfPoste(idPoste);
		if(users==null) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}
}
