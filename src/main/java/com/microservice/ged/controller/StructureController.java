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

import com.microservice.ged.beans.Structures;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.utils.OrganigramSystem;

@RestController
@CrossOrigin("*")
public class StructureController {
	
	@Autowired
	private StructureService structureservice;

	@GetMapping("/structures/all")
	public ResponseEntity<Page<Structures>> findAllStructures(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<Structures> structures = structureservice.findAll(page, size,status);
		if(structures.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(structures);
		}		
	}

	@GetMapping("/structures/search-by-name")
	public ResponseEntity<Page<Structures>> searchStructuresByName(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			Page<Structures> structures = structureservice.searchByName(name,page, size, status);
			if(structures.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(structures);
			}	
		}	
	}
	
	@GetMapping("/structures/search-by-sigle")
	public ResponseEntity<Page<Structures>> searchStructuresBySigle(
			@RequestParam(name = "sigle", defaultValue = "") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "status", required = true) int status,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(sigle.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(sigle.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			Page<Structures> structures = structureservice.searchBySigle(sigle, page, size,status);
			if(structures.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(structures);
			}	
		}	
	}
	
	

	@PostMapping("/structures/update")
	public ResponseEntity<?> updateNameStructure(
			@RequestBody Structures structure) throws Exception {
		structureservice.update(structure);
		return  ResponseEntity.ok().build();	
	}

	@PostMapping("/structures/add")
	public ResponseEntity<?> addStructures(
			@RequestBody Structures structures) throws Exception {
		structureservice.add(structures);
		return  ResponseEntity.ok().build();	
	}

	@DeleteMapping("/structures/delete")
	public ResponseEntity<?> deleteStructures(
			@RequestParam(name = "id") long id) throws Exception {
		structureservice.delete(id);
		return  ResponseEntity.ok().build();	
	}
	
	@GetMapping("/structures/find-by-id")
	public ResponseEntity<Structures> findStructuresById(
			@RequestParam(name = "id") Long id) throws Exception {
			
			return  ResponseEntity.ok().body(structureservice.findByIdStructure(id));
	}
	
	@PostMapping("/structures/add-sub-structure")
	public ResponseEntity<?> addSubStructures(
			@RequestBody Structures structuresSup) throws Exception {
		structureservice.addSubStructures( structuresSup);
		return  ResponseEntity.ok().build();
	}
	
	@PostMapping("/structures/remove-sub-structure")
	public ResponseEntity<?> removeSubStructures(
			@RequestBody Structures structuresSup) throws Exception {
		structureservice.removeSubStructures(structuresSup);
		return  ResponseEntity.ok().build();	
	}
	
	@GetMapping("/structures/list-of-unused")
	public ResponseEntity<OrganigramSystem> ogranigramme() throws Exception {
		OrganigramSystem organigramSystem  = structureservice.ogranigramme();
		if(organigramSystem==null) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(organigramSystem);
		}		
	}

}
