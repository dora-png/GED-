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

import com.microservice.ged.beans.TypeDocs;
import com.microservice.ged.service.TypeDocsService;

@RestController
@CrossOrigin("*")
public class TypeDocsController {
	
	@Autowired
	private TypeDocsService typeDocsService;

	@GetMapping("/typedocs/all")
	public ResponseEntity<Page<TypeDocs>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		try {
			Page<TypeDocs> typeDocs = typeDocsService.findAll(page, size);
			if(typeDocs.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(typeDocs);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

	@GetMapping("/typedocs/search-by-name")
	public ResponseEntity<Page<TypeDocs>> searchByName(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<TypeDocs> typeDocs = typeDocsService.searchByName(name,page, size);
				if(typeDocs.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(typeDocs);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@GetMapping("/typedocs/search-by-sigle")
	public ResponseEntity<Page<TypeDocs>> searchBySigle(
			@RequestParam(name = "sigle", defaultValue = "") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(sigle.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(sigle.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<TypeDocs> typeDocs = typeDocsService.searchBySigle(sigle,page, size);
				if(typeDocs.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(typeDocs);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@PutMapping("/typeDocs/update")
	public ResponseEntity<?> update(
			@RequestBody TypeDocs typeDocs) throws Exception {
		try {
			typeDocsService.update(typeDocs);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PostMapping("/typedocs/add")
	public ResponseEntity<?> add(
			@RequestBody TypeDocs typeDocs) throws Exception {
		try {
			typeDocsService.add(typeDocs);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@DeleteMapping("/typedocs/delete")
	public ResponseEntity<?> delete(
			@RequestParam(name = "id") long id) throws Exception {
		try {
			TypeDocs typeDocs = typeDocsService.findById(id);
			if(typeDocs==null) {
				return ResponseEntity.badRequest().build();
			}
			typeDocsService.delete(typeDocs);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/typedocs/find-by-name")
	public ResponseEntity<TypeDocs> findByName(
			@RequestParam(name = "name", defaultValue = "") String name) {
		try {
			return  ResponseEntity.ok().body(typeDocsService.findByName(name));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/typedocs/find-by-sigle")
	public ResponseEntity<TypeDocs> findBySigle(
			@RequestParam(name = "sigle") String sigle) {
		try {
			return  ResponseEntity.ok().body(typeDocsService.findBySigle(sigle));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/typedocs/find-by-id")
	public ResponseEntity<TypeDocs> findById(
			@RequestParam(name = "id") Long id) {
		try {
			return  ResponseEntity.ok().body(typeDocsService.findById(id));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}


}
