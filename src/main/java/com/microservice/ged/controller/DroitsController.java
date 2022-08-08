package com.microservice.ged.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.microservice.ged.beans.Droits;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.utils.DroitsBean;
import com.microservice.ged.utils.ProfilesDroitBean;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.DroitProfilesServices;
import com.microservice.ged.service.DroitService;
import com.microservice.ged.service.GroupProfileService;

@RestController
@CrossOrigin("*")
public class DroitsController {
	
	@Autowired
	private DroitProfilesServices droitProfilesServices ;
	

	@Autowired
	private DroitService droitService ;
	

	@Autowired
	private GroupProfileService groupProfileService;

	

	/*
	 * Page<Droits> findListDroitToAdd(Long idProfiles, int page, int size)throws
	 * Exception; void addDroitToProfiles(List<ProfilesDroitBean>
	 * profilesDroitBeanList) throws Exception; void
	 * removeDroitToProfiles(ProfilesDroitBean profilesDroitBean) throws Exception;
	 * Page<Droits> findListDroitToAdd(Long idGroupusers, int page, int size)
	 */
	@PostMapping("/droit/profile/list4212165droittoad54564d")
	public ResponseEntity<Page<Droits>> findListDroitToAdd( 
			@RequestBody List<Long> idDroits,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		System.err.println(idDroits);
		return  ResponseEntity.ok().body(droitService.findDroitsToAdd(idDroits, page, size));	
	}
	
	
	@GetMapping("/droit/profile")
	public ResponseEntity<List<DroitsBean>> findAllDroitUser( 
			@RequestParam(name = "id", required = true) Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			List<Droits> roles = droitProfilesServices.findListDroit(idProfile);
			List<DroitsBean> droitsBeansList = new ArrayList<>();
			if(!roles.isEmpty()) {
				roles.forEach(
						(role)->{
							droitsBeansList.add(new DroitsBean(
									role.getIddroit(), 
									role.getName(), 
									role.getAbbr(), 
									role.isCreate(), 
									role.isRead(), 
									role.isUpdate(),
									role.isDelete(), 
									role.getDateCreation(), 
									true));
						}
				);
				
			}
			groupProfileService.findListDroit(idProfile).forEach(
					(droits)->{
						if(!roles.contains(droits)) {
							droitsBeansList.add(new DroitsBean(
									droits.getIddroit(), 
									droits.getName(), 
									droits.getAbbr(), 
									droits.isCreate(), 
									droits.isRead(), 
									droits.isUpdate(),
									droits.isDelete(), 
									droits.getDateCreation(), 
									false));
						}
					}
			);
			//Page<Droits> pageDroits =  new PageImpl<>(roles, PageRequest.of(page, size, Sort.by("idDroit").descending()), roles.size());
			return  ResponseEntity.ok().body(droitsBeansList);		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}
	
	/*@GetMapping("/droi/groupe")
	public ResponseEntity<Page<Droits>> findRoleToAdd(
			@RequestParam(name = "idGroup", required = true) Long idGroup,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		droitGroupsService.findAllDroitOfGroup(idGroup, page, size);
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
