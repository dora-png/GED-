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

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.service.ProfilesServiceBasic;
import com.microservice.ged.utils.GroupDroitsBean;
import com.microservice.ged.utils.GroupProfilesBean;

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
	private ProfilesServiceBasic profilesServiceBasic;
	
	@Autowired
	private DroitGroupsService droitGroupsService;

	@GetMapping("/group/all")
	public ResponseEntity<Page<GroupUser>> findAllGroup(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception  {
		try {
			Page<GroupUser> GroupUser = groupUserService.findAllGroup( page, size, status);
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
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<GroupUser> groupUser = groupUserService.findGroupByNameContaining(name,page, size, status);
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
			@RequestBody GroupUser groupUser,
			@RequestParam(name = "ids", required = true) List<Long> ids) throws Exception {
		groupUserService.saveGroupUser(groupUser, ids);
		return  ResponseEntity.ok().build();		
	}
	
	@PostMapping("/group/update")
	public ResponseEntity<?> updateGroupUser(
			@RequestBody GroupUser groupUser) throws Exception {
		try {
			groupUserService.updateGroupUser(groupUser);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping("/group/role-list-id")
	public ResponseEntity<List<Long>> droitGroupList(
			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "status", required = true) int status) throws Exception {
		try {
			List<Long> idDroits = groupUserService.listDroitInGroupUser(id,status);
			return  ResponseEntity.ok().body(idDroits);		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	

	/*@GetMapping("/group/role-page")
	public ResponseEntity<Page<Droits>> droitGroupPages(
			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		try {
			List<Long> idDroits = groupUserServiceBasic.findGroupById(id);
			return  ResponseEntity.ok().body(idDroits);		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}*/
		
	@PostMapping("/group/add_role")
	public ResponseEntity<?> addDroitToGroup(
			@RequestBody List<GroupDroitsBean> groupDroitsBeanList) throws Exception {
		//droitGroupsService.addDroitToGroup(groupDroitsBeanList);
		return  ResponseEntity.ok().build();		
	}
	
	@PostMapping("/group/delete_droit")
	public ResponseEntity<?> removeDroitToGroup(
			@RequestBody GroupDroitsBean groupDroitsBean) throws Exception {
		try {
			//droitGroupsService.removeDroitToGroup(groupDroitsBean);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/group/fing-by-id")
	public ResponseEntity<GroupUser> findGroupUserById(@RequestParam(name = "id") Long id) throws Exception {
        GroupUser groupUser = groupUserServiceBasic.findGroupById(id);
        if(groupUser==null) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(groupUser);
	}
	
	@GetMapping("/group/find-All-droit")
	public ResponseEntity<List<Droits>> findAllDroit(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status) throws Exception {
		List<Droits> droitsList = groupUserService.listDroits(id,status);
        if(droitsList.isEmpty()) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(droitsList);
	}
	
	@GetMapping("/group/find-All-profil")
	public ResponseEntity<List<Profiles>> findAllProfile(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status) throws Exception {
		List<Profiles> profileList = groupUserService.listProfiles(id,status);
        if(profileList.isEmpty()) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(profileList);
	}
		
	@GetMapping("/group/fing-by-name")
	public ResponseEntity<GroupUser> findGroupUserByName(@RequestParam(name = "name") String name) throws Exception {
		return  ResponseEntity.ok().body(groupUserService.findGroupByName(name));
	}
	
	@GetMapping("/group/find-profile-group")
	public ResponseEntity<GroupUser> findGroupOfProfile(@RequestParam(name = "id") Long idProfile) throws Exception {
		Profiles profile = profilesServiceBasic.findProfileById(idProfile);
		return ResponseEntity.ok().body(groupProfileService.findGroupOfProfile(profile));
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/group/find-droit-in-group")
	public ResponseEntity<Page<Droits>> findAllDroitInGroupUser(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Droits> droitsList = groupUserService.listDroitInGroupUserPage(id, page,size,status);
        if(droitsList.isEmpty()) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(droitsList);
	}

	@GetMapping("/group/find-droit-in-group-by-name")
	public ResponseEntity<Page<Droits>> findAllDroitInGroupUserByName(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size
			) throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Droits> groupUser = groupUserService.listDroitInGroupUserPageName(id,name,page, size, status);
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
	
	@GetMapping("/group/find-droit-to-add-in-group")
	public ResponseEntity<Page<Droits>> findAllDroitToAddInGroupUser(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Droits> droitsList = groupUserService.listDroitToAddInGroupUser(id, page,size);
        if(droitsList.isEmpty()) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(droitsList);
	}

	@GetMapping("/group/find-droit-to-add-in-group-by-name")
	public ResponseEntity<Page<Droits>> findAllDroitToAddInGroupUserByName(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size
			) throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Droits> groupUser = groupUserService.listDroitToAddInGroupUserName(id,name,page, size);
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
	
	
	////////////////////////////////////////////
	

	@GetMapping("/group/find-profile-in-group")
	public ResponseEntity<Page<Profiles>> findAllProfileInGroupUser(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Profiles> profilesList = groupUserService.listProfilesInGroupUserPage(id, page,size,status);
        if(profilesList.isEmpty()) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(profilesList);
	}

	@GetMapping("/group/find-profile-in-group-by-name")
	public ResponseEntity<Page<Profiles>> findAllProfileInGroupUserByName(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size
			) throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Profiles> profiles = groupUserService.listProfilesInGroupUserPageName(id,name,page, size, status);
				if(profiles.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(profiles);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}
	}
	
	@GetMapping("/group/find-profile-to-add-in-group")
	public ResponseEntity<Page<Profiles>> findAllProfilesToAddInGroupUser(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Profiles> profilesList = groupUserService.listProfilesToAddInGroupUser(id, page,size);
        if(profilesList.isEmpty()) {
        	return  ResponseEntity.noContent().build();
        }
		return  ResponseEntity.ok().body(profilesList);
	}

	@GetMapping("/group/find-profiles-to-add-in-group-by-name")
	public ResponseEntity<Page<Profiles>> findAllProfilesToAddInGroupUserByName(
			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size
			) throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Profiles> profilesList = groupUserService.listProfilesToAddInGroupUserName(id,name,page, size);
				if(profilesList.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(profilesList);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}
	}
	
	///////////////////////////////////////
	
	
	@PostMapping("/group/delete_droit-to-group")
	public ResponseEntity<?> removeDroitToGroupUser(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "idDroit", required = true) Long idDroit
			) throws Exception {
		try {
			groupUserService.removeDroitToGroup(idGroup, idDroit);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/group/add_droit-to-group")
	public ResponseEntity<?> addDroitToGroupUser(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "idDroit", required = true) Long idDroit
			) throws Exception {
		try {
			groupUserService.addDroitToGroup(idGroup, idDroit);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	///////////////////////////////////////
	
	
	@PostMapping("/group/delete_profile-to-group")
	public ResponseEntity<?> removeProfileToGroupUser(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "idProfile", required = true) Long idProfile
			) throws Exception {
		try {
			groupUserService.removeProfileToGroup(idGroup, idProfile);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/group/add_profile-to-group")
	public ResponseEntity<?> addProfileToGroupUser(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "idProfile", required = true) Long idProfile
			) throws Exception {
		try {
			groupUserService.addProfileToGroup(idGroup, idProfile);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
	
}
