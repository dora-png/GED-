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

@RestController
@CrossOrigin("*")
public class StructureController {
	
	@Autowired
	private StructureService structureservice;

	@GetMapping("/structures/all")
	public ResponseEntity<Page<Structures>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Structures> structures = structureservice.findAll(page, size);
			if(structures.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(structures);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

	@GetMapping("/structures/search-by-name")
	public ResponseEntity<Page<Structures>> searchByName(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Structures> structures = structureservice.searchByName(name,page, size);
				if(structures.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(structures);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}
	
	@GetMapping("/structures/search-by-sigle")
	public ResponseEntity<Page<Structures>> searchBySigle(
			@RequestParam(name = "sigle", defaultValue = "") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(sigle.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(sigle.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Structures> structures = structureservice.searchBySigle(sigle, page, size);
				if(structures.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(structures);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@PutMapping("/structures/update")
	public ResponseEntity<?> update(
			@RequestBody Structures structures,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			structureservice.update(structures, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PostMapping("/structures/add")
	public ResponseEntity<?> add(
			@RequestBody Structures structures,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			structureservice.add(structures, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@DeleteMapping("/structures/delete")
	public ResponseEntity<?> delete(
			@RequestParam(name = "id") long id,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			Structures structures = structureservice.findByIdStructure(id);
			if(structures==null) {
				return ResponseEntity.badRequest().build();
			}
			structureservice.delete(structures, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@GetMapping("/structures/find-by-id")
	public ResponseEntity<Structures> findById(
			@RequestParam(name = "id") Long id) throws Exception {
			
			return  ResponseEntity.ok().body(structureservice.findByIdStructure(id));
	}
	
	@PostMapping("/structures/add-sub-structure")
	public ResponseEntity<?> addSubStructures(
			@RequestBody Structures structuresSup,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			structureservice.addSubStructures(posteName, structuresSup);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping("/structures/remove-sub-structure")
	public ResponseEntity<?> removeSubStructures(
			@RequestBody Structures structuresSup,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			structureservice.removeSubStructures(posteName, structuresSup);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@GetMapping("/structures/list-of-unused")
	public ResponseEntity<Page<Structures>> structureUnUseListe(
			@RequestParam(name = "page", defaultValue = "0") int page) {
		try {
			Page<Structures> structures = structureservice.structureUnUseListe(page, 5);
			if(structures.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(structures);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

}
