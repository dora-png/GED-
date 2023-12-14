   package com.microservice.ged.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.ProfilesServiceBasic;
import com.microservice.ged.utils.ProfileStructureBean;

import javax.validation.constraints.NotNull;

   @RestController
@CrossOrigin("*")
public class UsersController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	ProfilesServiceBasic profilesServiceBasic;
	
	@Autowired
	private ProfilesService profilesService;

	@GetMapping("/profile/all")
    //@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<Profiles>> findAllProfiles(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "2") int size) throws Exception {
		Page<Profiles> users = profilesService.findAllProfiles(page, size, status);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}
	
	@GetMapping("/profile/list-to-add-in-poste")
	public ResponseEntity<Page<Profiles>> findAllProfilesToAddInPoste(
			@RequestParam(name = "idProfile", required = true) Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Profiles> users = profilesService.findProfilesToAdd(List.of(id),page, size);
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
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(name.trim().isEmpty()) {
			throw new Exception("Name to search is empty");
		}
		Page<Profiles> users = profilesService.searchByProfilesName(name, page, size, status);
		if(users.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(users);
		}		
	}
	
	@GetMapping("/profile/search-by-user-name")
	public ResponseEntity<Page<Profiles>> searchUsersByUserName(
			@RequestParam(name = "name", required = true) String username,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(username.trim().isEmpty()) {
			throw new Exception("Name to search is empty");
		}
		Page<Profiles> users = profilesService.searchByProfilesUserName(username, page, size, status);
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
	
	@PostMapping("/profile/user")
	public ResponseEntity<?> addUserInProfiles(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "userName", required = true) String userName
			) throws Exception {
		profilesService.addUserInProfiles(idProfile, userName);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/create")
	public ResponseEntity<?> addUsers(
			@RequestParam(name = "idstructure", required = true) Long idStructure,
			@RequestParam(name = "idgroupuser", defaultValue = "0") Long idGroupUser,
			@RequestBody Profiles profiles) throws Exception {
		profilesService.add(profiles, idStructure, idGroupUser);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/set_StaTus")
	public ResponseEntity<?> setProfileStatus(
			@RequestParam(name = "id", required = true) Long idProfile) throws Exception {
		profilesService.setStatus(idProfile);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/set_namekjk2132123")
	public ResponseEntity<?> setProfileName(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "name", required = true) String name) throws Exception {
		profilesService.updateName(idProfile, name);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/set_userkjk2132123")
	public ResponseEntity<?> setProfile(
			@RequestParam(name = "idlastGroupUser", required = true) Long idLastGroupUser,
			@RequestParam(name = "idgroupuser", required = true) Long idGroupUser,
			@RequestBody Profiles profiles) throws Exception {
		profilesService.update(profiles, idGroupUser, idLastGroupUser);
		return  ResponseEntity.ok().build();
	}
	
	@GetMapping("/profile/find_by_id")
	public ResponseEntity<Profiles> findUserById(
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		return  ResponseEntity.ok().body(profilesServiceBasic.findProfileById(idProfile));	
	}
}
