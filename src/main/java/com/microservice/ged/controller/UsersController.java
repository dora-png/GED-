   package com.microservice.ged.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.DroitProfilesServices;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.ProfileStructureBean;
import com.microservice.ged.utils.ProfilesDroitBean;

@RestController
@CrossOrigin("*")
public class UsersController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private ProfilesService profilesService;
	
	@Autowired
	DroitProfilesServices droitProfilesServices;

	@GetMapping("/profile/all")
	public ResponseEntity<Page<Profiles>> findAllProfiles(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Profiles> users = profilesService.findAllProfiles(page, size);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}
	
	@GetMapping("/profile/internal")
	public ResponseEntity<Page<Profiles>> findAllUserActiveByTypeUserInternal(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Profiles> users = profilesService.searchByProfilesType(TypeUser.INTERN_ACTOR, page, size);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}			
	}
	
	@GetMapping("/profile/other")
	public ResponseEntity<Page<Profiles>> findAllUserActiveByTypeUserOther(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Profiles> users = profilesService.searchByProfilesType(TypeUser.EXTERN_ACTOR, page, size);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}

	@GetMapping("/profile/users_internal_add")
	public ResponseEntity<List<String>> findAllUserToAdd() throws Exception {
		List<String> users = appUserService.findAllUser();
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}
	
	@GetMapping("/profile/search-by-name")
	public ResponseEntity<Page<Profiles>> searchUsersByName(
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(name.trim().isEmpty()) {
			throw new Exception("Name to search is empty");
		}
		if(name.isBlank()) {
			throw new Exception("Name to search is blank");
		}
		Page<Profiles> users = profilesService.searchByProfilesName(name, page, size);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}
	
	@GetMapping("/profile/current_S1231tructurebyd")
	public ResponseEntity<ProfileStructureBean> currentStructure(
			@RequestParam(name = "id", required = true) Long idProfile) throws Exception {
		return  ResponseEntity.ok().body(profilesService.currentStructureOfProfile(idProfile));
	}
	
	@GetMapping("/profile/list_User")
	public ResponseEntity<Page<String>> listUserToAffect(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		List<String> userToAffectList =  profilesService.listUserToAffect(idProfile, page, size);
		Page<String> pageLdapUser =  new PageImpl<>(userToAffectList, PageRequest.of(page, size), userToAffectList.size());
		return  ResponseEntity.ok().body(pageLdapUser);	
	}
	
	@PostMapping("/profile/add_droit")
	public ResponseEntity<?> addDroitsToUsers(
			@RequestBody List<ProfilesDroitBean> profilesDroitBeanList) throws Exception {
		droitProfilesServices.addDroitToProfiles(profilesDroitBeanList);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/user")
	public ResponseEntity<?> addUserInProfiles(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "userName", required = true) String userName
			) throws Exception {
		profilesService.addUserInProfiles(idProfile, userName);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/remove_droit")
	public ResponseEntity<?> removeDroitsToUsers(
			@RequestBody ProfilesDroitBean profilesDroitBean) throws Exception {
		droitProfilesServices.removeDroitToProfiles(profilesDroitBean);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/create")
	public ResponseEntity<?> addUsers(
			@RequestBody Profiles profiles) throws Exception {
		profilesService.add(profiles);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/set_StaTus")
	public ResponseEntity<?> setProfileStatus(
			@RequestParam(name = "id", required = true) Long idProfile) throws Exception {
		profilesService.setStatus(idProfile);
		return  ResponseEntity.ok().build();
	}
	
	@GetMapping("/profile/find_by_id")
	public ResponseEntity<Profiles> findUserById(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		return  ResponseEntity.ok().body(profilesService.findProfileById(idProfile));	
	}
}
