package com.microservice.ged.controller;

import java.util.List;

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

import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.utils.GroupDroitsBean;
import com.microservice.ged.utils.GroupProfilesBean;
import com.microservice.ged.utils.ProfilesDroitBean;

@RestController
@CrossOrigin("*")
public class GroupUserController {
	
	@Autowired
	private GroupUserService groupUserService;
	
	@Autowired
	private GroupUserServiceBasic groupUserServiceBasic;
	
	@Autowired
	private GroupProfileService groupProfileService;
	
	@Autowired
	private DroitGroupsService droitGroupsService;

	@GetMapping("/group/all")
	public ResponseEntity<Page<GroupUser>> findAllGroup(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception  {
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
	
	@GetMapping("/group/all/forprofile3456dfgf")
	public ResponseEntity<Page<GroupProfile>> findAllGroupForProfile(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception  {
		try {
			Page<GroupProfile> groupUserProfile = groupProfileService.findGrouptOfProfilesActive(idProfile, page, size);
			if(groupUserProfile.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(groupUserProfile);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}
	
	@GetMapping("/group/search-by-name")
	public ResponseEntity<Page<GroupUser>> searchGroupUserByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<GroupUser> groupUser = groupUserService.findGroupByNameContaining(name,page, size);
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
			@RequestParam(name = "id", required = true) Long idGroup,
			@RequestParam(name="name", required = true) String name) throws Exception {
		try {
			groupUserService.updateNameGroupUser(idGroup, name);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping("/group/add_profile")
	public ResponseEntity<?> addProfileToGroup(
			@RequestBody List<GroupProfilesBean> groupProfilesBeanList) throws Exception {
		groupProfileService.addGroupToProfiles(groupProfilesBeanList);
		return  ResponseEntity.ok().build();	
	}
	
	@PostMapping("/group/add_role")
	public ResponseEntity<?> addDroitToGroup(
			@RequestBody List<GroupDroitsBean> groupDroitsBeanList) throws Exception {
		droitGroupsService.addDroitToGroup(groupDroitsBeanList);
		return  ResponseEntity.ok().build();		
	}
	
	@PostMapping("/group/delete_profile")
	public ResponseEntity<?> removeProfileToGroup(
			@RequestBody GroupProfilesBean groupProfilesBean) throws Exception {
		try {
			groupProfileService.removeGroupToProfiles(groupProfilesBean);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/group/delete_droit")
	public ResponseEntity<?> removeDroitToGroup(
			@RequestBody GroupDroitsBean groupDroitsBean) throws Exception {
		try {
			droitGroupsService.removeDroitToGroup(groupDroitsBean);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/group/fing-by-id")
	public ResponseEntity<GroupUser> findGroupUserById(@RequestParam(name = "id") Long id) throws Exception {
        GroupUser groupUser = groupUserServiceBasic.findGroupById(id);
        if(groupUser==null) {
        	throw new Exception("This Group don't exist");
        }
		return  ResponseEntity.ok().body(groupUser);
	}
		
	@GetMapping("/group/fing-by-name")
	public ResponseEntity<GroupUser> findGroupUserByName(@RequestParam(name = "name") String name) throws Exception {

		return  ResponseEntity.ok().body(groupUserService.findGroupByName(name));
	}

}
