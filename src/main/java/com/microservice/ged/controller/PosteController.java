package com.microservice.ged.controller;

import java.util.HashSet;
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
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.utils.OrganigramStructure;
import com.microservice.ged.utils.OrganigramSystem;
import com.microservice.ged.utils.PosteRoleBean;

@RestController
@CrossOrigin("*")
public class PosteController {
	
	@Autowired
	private PosteService posteservice;

	@Autowired
	private ProfilesService profilesService;
	
	@Autowired
	private StructureService structureService;
	
	@Autowired
	private LogPosteUserService logPosteUserService; 

	@GetMapping("/postes/all")
	public ResponseEntity<Page<Postes>> findAllPoste(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Postes> postes = posteservice.findAllPoste(page, size);
			if(postes.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(postes);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/postes/search-by-structure")
	public ResponseEntity<Page<Postes>> findAllStructurePoste(
			@RequestParam(name = "structure", required = true)Long structure,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception{
		Page<Postes> postes = posteservice.findAllStructurePoste(structure, page, size);
		if(postes.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(postes);
		}
	}
	
	@GetMapping("/postes/fing-by-id")
	public ResponseEntity<Postes> findPosteById(@RequestParam(name = "id", required = true) Long id) throws Exception {

		return  ResponseEntity.ok().body(posteservice.findPosteById(id));
	}
	
	@GetMapping("/postes/search-by-name")
	public ResponseEntity<Page<Postes>> searchPosteByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Postes> postes = posteservice.searchPosteByName(name,page, size);
				if(postes.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(postes);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@PostMapping("/postes/add")
	public ResponseEntity<?> addPoste(
			@RequestBody Postes postes) throws Exception {
		try {
			posteservice.addPoste(postes);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PostMapping("/postes/update")
	public ResponseEntity<?> updatePoste(
			@RequestBody Postes postes) throws Exception {
		posteservice.updatePoste(postes);
		return  ResponseEntity.ok().build();	
	}

	@PostMapping("/poste/add-subposte")
	public ResponseEntity<?> addSubPoste(
			@RequestBody Postes postes) throws Exception {
		posteservice.addSubPoste(postes);
		return  ResponseEntity.ok().build();	
	}

	
	/*@PostMapping("/poste/delete-subposte")
	public ResponseEntity<?> removeSubPoste(
			@RequestBody Postes postes) throws Exception {
		posteservice.removeSubPoste(postes);
		return  ResponseEntity.ok().build();	
	}*/


	@PostMapping("/postes/add-user")
	public ResponseEntity<?> addUser(
			@RequestParam(name = "idpostes", required = true) Long idpostes,
			@RequestParam(name = "idusers", required = true) Long idusers) throws Exception {
		logPosteUserService.add(idpostes, idusers);
		return  ResponseEntity.ok().build();
	}
	
	@GetMapping("/postes/organigram")
	public ResponseEntity<OrganigramStructure> ogranigramme(@RequestParam(name = "id") Long id) throws Exception {
		OrganigramStructure organigramStructure  = posteservice.ogranigramme(id);
		if(organigramStructure==null) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(organigramStructure);
		}		
	}


}