package com.microservice.ged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.service.GroupUserService;

@RestController
@CrossOrigin("*")
public class GroupUserController {
	
	@Autowired
	private GroupUserService groupUserService;

	@GetMapping("/group/all")
	public ResponseEntity<Page<GroupUser>> findAllGroup(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<GroupUser> GroupUser = groupUserService.findAllGroup( page, size);
			if(GroupUser.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(GroupUser);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}
	
	@GetMapping("/group/search-by-name")
	public ResponseEntity<Page<GroupUser>> searchGroupUserByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<GroupUser> groupUser = groupUserService.findByNameContaining(name,page, size);
				if(groupUser.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(groupUser);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}		
	}
	
	@PostMapping("/group/add")
	public ResponseEntity<?> addGroupUser(
			@RequestBody GroupUser groupUser) throws Exception {
		groupUserService.saveGroupUser(groupUser);
		return  ResponseEntity.ok().build();		
	}
	
	@PostMapping("/group/update")
	public ResponseEntity<?> updateGroupUserName(
			@RequestBody GroupUser groupUser) throws Exception {
		try {
			groupUserService.updateNameGroupUser(groupUser);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping("/group/add-poste")
	public ResponseEntity<?> addPosteToGroup(
			@RequestBody GroupUser groupUser) throws Exception {
		groupUserService.addPoste(groupUser);
		return  ResponseEntity.ok().build();	
	}
	
	@PostMapping("/group/add-role")
	public ResponseEntity<?> addRoleToGroup(
			@RequestBody GroupUser groupUser) throws Exception {
		groupUserService.addRole(groupUser);
		return  ResponseEntity.ok().build();		
	}
	
	@PostMapping("/group/delete-poste")
	public ResponseEntity<?> removePosteToGroup(
			@RequestBody GroupUser groupUser) throws Exception {
		try {
			groupUserService.removePoste(groupUser);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/group/delete-rome")
	public ResponseEntity<?> removeRoleToGroup(
			@RequestBody GroupUser groupUser) throws Exception {
		try {
			groupUserService.removeRole(groupUser);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/group/fing-by-id")
	public ResponseEntity<GroupUser> findGroupUserById(@RequestParam(name = "id") Long id) throws Exception {
        GroupUser groupUser = groupUserService.findById(id);
        if(groupUser==null) {
        	throw new Exception("This Group don't exist");
        }
		return  ResponseEntity.ok().body(groupUser);
	}
		
	@GetMapping("/group/fing-by-name")
	public ResponseEntity<GroupUser> findGroupUserByName(@RequestParam(name = "name") String name) throws Exception {

		return  ResponseEntity.ok().body(groupUserService.findByName(name));
	}

}
