package com.microservice.ged.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.RolesService;

@RestController
@CrossOrigin("*")
public class RolesController {
	
	@Autowired
	private RolesService rolesService;
	

	@Autowired
	private GroupUserService groupUserService;

	@GetMapping("/roles/all")
	public ResponseEntity<Page<Roles>> findAll( 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Roles> roles = rolesService.findAll( page, size);
			if(roles.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(roles);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}
	
	@GetMapping("/roles/cant-be-add")
	public ResponseEntity<Page<Roles>> findRoleToAdd(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		GroupUser groupUser = groupUserService.findById(idGroup);
		if(groupUser==null) {
			throw new Exception("Group don't exist");
		}
		Page<Roles> roles = rolesService.findRoleToAdd(groupUser, page, size);
		if(roles.isEmpty()) {
			throw new Exception("List empty");
		}
		return  ResponseEntity.ok().body(roles);	
	}
	
	@GetMapping("/roles/cant-be-add-name")
	public ResponseEntity<Page<Roles>> findRoleToAddName(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "name",required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		GroupUser groupUser = groupUserService.findById(idGroup);
		if(groupUser==null) {
			throw new Exception("Group don't exist");
		}
		if(name.isBlank()) {
			throw new Exception("Search value is blank");
		}
		if(name.isEmpty()) {
			throw new Exception("Search value is empty");
		}
		Page<Roles> roles = rolesService.findRoleToAdd(groupUser, page, size);
		if(roles.isEmpty()) {
			throw new Exception("List empty");
		}
		return  ResponseEntity.ok().body(roles);		
	}

	@GetMapping("/roles/search-by-name")
	public ResponseEntity<Page<Roles>> searchByName(
			@RequestParam(name = "name",required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Roles> roles = rolesService.searchRole(name,page, size);
				if(roles.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(roles);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}
	/*
	@PutMapping("/roles/update")
	public ResponseEntity<?> update(
			@RequestBody Roles roles,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			rolesService.update(roles, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}*/
/*
	@PostMapping("/roles/add")
	public ResponseEntity<?> add(
			@RequestBody Roles roles,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			rolesService.add(roles, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@DeleteMapping("/roles/delete")
	public ResponseEntity<?> delete(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			rolesService.delete(name, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/roles/find-by-name")
	public ResponseEntity<?> findByName(
			@RequestParam(name = "name", defaultValue = "") String name) {
		try {
			return  ResponseEntity.ok().body(rolesService.findByName(name));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

*/
}
