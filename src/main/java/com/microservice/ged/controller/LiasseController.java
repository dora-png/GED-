package com.microservice.ged.controller;

import java.util.List;

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

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.TypeLiasses;
import com.microservice.ged.service.LiasseService;

@RestController
@CrossOrigin("*")
public class LiasseController {
	
	@Autowired
	LiasseService liasseService;

	@GetMapping("/liasses/search-by-sigle")
	public ResponseEntity<Page<Liasses>> searchBySigleLiasse(
			@RequestParam(name = "sigle") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(sigle.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(sigle.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Liasses> liasses = liasseService.searchLiassesBySigle(sigle,page, size);
				if(liasses.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(liasses);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	
	@GetMapping("/liasses/search-by-name")
	public ResponseEntity<Page<Liasses>> searchByNameLiasse(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<Liasses> liasses = liasseService.searchLiassesByName(name,page, size);
				if(liasses.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(liasses);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@PostMapping("/liasses/update")
	public ResponseEntity<?> updateLiasse(
			@RequestBody Liasses liasses) throws Exception {
		try {
			liasseService.update(liasses);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping("/liasses/share")
	public ResponseEntity<?> shareLiasse(
			@RequestParam(name = "idLiasse", required = true) Long idLiasse,
			@RequestBody List<Long> idUsers) throws Exception {

			
			return  ResponseEntity.ok().build();	
	}

	@PostMapping("/liasses/doc-to-liasses-add")
	public ResponseEntity<?> addDocToLiasse(
			@RequestBody Liasses liasses) throws Exception {
		try {
			liasseService.addDocToLiasse(liasses);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/liasses/find-by-typeliasse")
	public ResponseEntity<Page<Liasses>> findByTypeliasse(
			@RequestBody TypeLiasses typeliasse,
			@RequestParam(name = "posteName") String posteName,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) throws Exception {
		try {
			Page<Liasses> liasses = liasseService.findByTypeliasse(typeliasse,page, size);
			if(liasses.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(liasses);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}


	@GetMapping("/liasses/find-by-name")
	public ResponseEntity<Liasses> findByNameLiasse(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		return  ResponseEntity.ok().body(liasseService.findByName(name));
	}
	

}
