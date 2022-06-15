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

import com.microservice.ged.beans.TypeLiasses;
import com.microservice.ged.service.TypeLiasseService;

@RestController
@CrossOrigin("*")
public class TypeLiasseController {
	
	@Autowired
	private TypeLiasseService typeLiasseService;

	@GetMapping("/typeliasse/all")
	public ResponseEntity<Page<TypeLiasses>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		try {
			Page<TypeLiasses> typeLiasses = typeLiasseService.findAll(page, size);
			if(typeLiasses.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(typeLiasses);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
	}

	@GetMapping("/typeliasse/search-by-name")
	public ResponseEntity<Page<TypeLiasses>> searchByName(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<TypeLiasses> typeLiasses = typeLiasseService.searchByName(name,page, size);
				if(typeLiasses.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(typeLiasses);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@GetMapping("/typeliasse/search-by-sigle")
	public ResponseEntity<Page<TypeLiasses>> searchBySigle(
			@RequestParam(name = "sigle", defaultValue = "") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(sigle.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(sigle.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<TypeLiasses> typeLiasses = typeLiasseService.searchBySigle(sigle,page, size);
				if(typeLiasses.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(typeLiasses);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@PutMapping("/typeliasse/update")
	public ResponseEntity<?> update(
			@RequestBody TypeLiasses typeLiasses,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			typeLiasseService.update(typeLiasses, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PostMapping("/typeliasse/add")
	public ResponseEntity<?> add(
			@RequestBody TypeLiasses typeLiasses,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			typeLiasseService.add(typeLiasses, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@DeleteMapping("/typeliasse/delete")
	public ResponseEntity<?> delete(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			TypeLiasses typeLiasses = typeLiasseService.findById(id);
			if(typeLiasses==null) {
				return ResponseEntity.badRequest().build();
			}
			typeLiasseService.delete(typeLiasses, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/typeliasse/find-by-name")
	public ResponseEntity<TypeLiasses> findByName(
			@RequestParam(name = "name") String name) {
		try {
			return  ResponseEntity.ok().body(typeLiasseService.findByName(name));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/typeliasse/find-by-sigle")
	public ResponseEntity<TypeLiasses> findBySigle(@RequestParam(name = "id") Long id) {
		try {
			return  ResponseEntity.ok().body(typeLiasseService.findById(id));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/typeliasse/find-by-id")
	public ResponseEntity<TypeLiasses> findBySigle(
			@RequestParam(name = "sigle", defaultValue = "") String sigle) {
		try {
			return  ResponseEntity.ok().body(typeLiasseService.findBySigle(sigle));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}


	
	

}
