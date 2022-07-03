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

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;
import com.microservice.ged.service.DocsService;

@RestController
@CrossOrigin("*")
public class DocsController {
	
	@Autowired
	DocsService docsService;

	@GetMapping("/docs/search-by-name")
	public ResponseEntity<Page<Docs>> searchByName(
			@RequestParam(name = "sigle") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Docs> docs = docsService.searchDocsByName(name,page, size);
				if(docs.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(docs);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	
	@GetMapping("/docs/search-by-liasse")
	public ResponseEntity<Page<Docs>> searchByLiasse(
			@RequestBody Liasses liasses,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<Docs> docs = docsService.searchDocsByLiasses(liasses, page, size);
				if(docs.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(docs);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}	

	@PostMapping("/docs/add")
	public ResponseEntity<?> add(
			@RequestBody Docs docs) throws Exception {
		try {
			docsService.add(docs);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PutMapping("/docs/update")
	public ResponseEntity<?> update(
			@RequestBody Docs docs) throws Exception {
		try {
			docsService.update(docs);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PutMapping("/docs/update-setter")
	public ResponseEntity<?> updateSetter(
			@RequestBody Docs docs,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			docsService.updateSetter(docs, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

}
