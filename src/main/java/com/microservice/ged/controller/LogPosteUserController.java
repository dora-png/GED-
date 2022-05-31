package com.microservice.ged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.Users;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.service.LogPosteUserService;

@RestController
@CrossOrigin("*")
public class LogPosteUserController {


	private LogPosteUserService logPosteUserService;

	@GetMapping("/logposteuser/user-log")
	public ResponseEntity<Page<LogPosteUser>> findByUser(
			@RequestBody Users users,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		try {
			Page<LogPosteUser> logPosteUser = logPosteUserService.logUser(users, page, size);
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
			@RequestBody Postes postes,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		try {
			Page<LogPosteUser> logPosteUser = logPosteUserService.logPoste(postes, page, size);
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
	public ResponseEntity<Postes> currentPosteOfUser(@RequestBody Users users) {
		try {
			Postes poste = logPosteUserService.currentPosteOfUser(users);
			if(poste==null) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(poste);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}
}
