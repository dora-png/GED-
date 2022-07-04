package com.microservice.ged.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.Users;
import com.microservice.ged.service.UserService;

@RestController
//@CrossOrigin("*")
public class UsersController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users/all")
	public ResponseEntity<Page<Users>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Users> users = userService.findAll(page, size);
			if(users.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(users);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}
	
	@GetMapping("/users/to-add")
	public ResponseEntity<Page<Users>> listUserToAdd(
			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Users> users = userService.listUserToAffect(id,page, size);
			if(users.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(users);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

	@GetMapping("/users/search-by-name")
	public ResponseEntity<Page<Users>> searchByName(
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(name.trim().isEmpty()) {
			throw new Exception("Name to search is empty");
		}
		if(name.isBlank()) {
			throw new Exception("Name to search is blank");
		}
		Page<Users> users = userService.searchByName(name,page, size);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}

	@GetMapping("/users/search-by-login")
	public ResponseEntity<Page<Users>> searchByLogin(
			@RequestParam(name = "login", required = true) String login,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(login.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(login.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Users> users = userService.searchByLogin(login,page, size);
				if(users.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(users);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}
	}

	@PostMapping("/users/add")
	public ResponseEntity<?> add(
			@RequestBody Users users,
			@RequestParam(name = "posteName") String posteName) {
		try {
			userService.add(users);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/users/by-id")
	public ResponseEntity<Users> findUserById(@RequestParam(name = "id", required = true) Long id) throws Exception {
		return  ResponseEntity.ok().body(userService.findById(id));	
	}

}
