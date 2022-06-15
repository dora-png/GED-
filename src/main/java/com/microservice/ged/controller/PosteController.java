package com.microservice.ged.controller;

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

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.beans.Users;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.utils.PosteRoleBean;

@RestController
@CrossOrigin("*")
public class PosteController {
	
	@Autowired
	private PosteService posteservice;
	

	@Autowired
	private StructureService structureService;
	
	@Autowired
	private LogPosteUserService logPosteUserService; 

	@GetMapping("/postes/all")
	public ResponseEntity<Page<Postes>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Postes> postes = posteservice.findAll(page, size);
			if(postes.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(postes);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/postes/search-by-structure-and-niveau")
	public ResponseEntity<Page<Postes>> searchByStructureAndNiveau(
			@RequestBody Structures structures,
			@RequestParam(name = "niveau") int niveau,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Postes> postes = posteservice.searchByStructureAndNiveau(niveau, structures, page, size);
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
	public ResponseEntity<Page<Postes>> findAllStructure(
			@RequestParam(name = "structure")Long structure,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Structures structures = structureService.findByIdStructure(structure);
			if(structures==null) {
				return ResponseEntity.badRequest().build();
			}else {
				Page<Postes> postes = posteservice.findAllStructure(structures, page, size);
				if(postes.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(postes);
				}	
			}
			
					
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@GetMapping("/postes/fing-by-id")
	public ResponseEntity<Postes> findById(@RequestParam(name = "id") Long id) throws Exception {

		return  ResponseEntity.ok().body(posteservice.findById(id));
	}
	
	@GetMapping("/postes/search-by-name")
	public ResponseEntity<Page<Postes>> searchByName(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Postes> postes = posteservice.searchByName(name,page, size);
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
	public ResponseEntity<?> add(
			@RequestBody Postes postes,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			posteservice.add(postes, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PutMapping("/postes/update")
	public ResponseEntity<?> update(
			@RequestBody Postes postes,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			posteservice.update(postes, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PostMapping("/poste/add-subposte")
	public ResponseEntity<?> addSubPoste(
			@RequestBody Postes postes,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			posteservice.addSubPoste(posteName, postes);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}

	
	@PutMapping("/poste/delete-subposte")
	public ResponseEntity<?> removeSubPoste(
			@RequestBody Postes postes,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			posteservice.removeSubPoste(posteName,postes);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}

	@DeleteMapping("/postes/delete")
	public ResponseEntity<?> delete(
			@RequestParam(name = "id") long id,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			posteservice.delete(id, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}

	@PostMapping("/postes/add-user")
	public ResponseEntity<?> addUser(
			@RequestBody Postes postes,
			@RequestBody Users users,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			logPosteUserService.add(postes, users, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}


}