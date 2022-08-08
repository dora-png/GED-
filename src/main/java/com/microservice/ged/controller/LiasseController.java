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
	/*
	@Autowired
	LiasseService liasseService;
	
	@GetMapping("/liasses/all")
	public ResponseEntity<Page<Liasses>> findAllLiasse(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		Page<Liasses> liasses = liasseService.findAllLiasses(page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	@GetMapping("/liasses/archive")
	public ResponseEntity<Page<Liasses>> findLiasseArchived(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		Page<Liasses> liasses = liasseService.findByArchiveTrue(page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	@GetMapping("/liasses/not-archived")
	public ResponseEntity<Page<Liasses>> findLiasseNotArchived(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		Page<Liasses> liasses = liasseService.findByArchiveFalse(page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	@GetMapping("/liasses/search-by-sigle")
	public ResponseEntity<Page<Liasses>> searchLiasseBySigle(
			@RequestParam(name = "sigle", required = true) String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		if(sigle.trim().isEmpty()) {
			throw new Exception("Sigle is Empty");
		}
		if(sigle.isBlank()) {
			throw new Exception("Sigle is Blank");
		}
		Page<Liasses> liasses = liasseService.findBySigleLike(sigle,page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	@GetMapping("/liasses/search-archive-by-sigle")
	public ResponseEntity<Page<Liasses>> searchBySigleLiasseArchive(
			@RequestParam(name = "sigle", required = true) String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		if(sigle.trim().isEmpty()) {
			throw new Exception("Sigle is Empty");
		}
		if(sigle.isBlank()) {
			throw new Exception("Sigle is Blank");
		}
		Page<Liasses> liasses = liasseService.findBySigleLikeAndArchiveTrue(sigle,page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	@GetMapping("/liasses/search-by-sigle-user")
	public ResponseEntity<Page<Liasses>> searchBySigleLiasse(
			@RequestParam(name = "sigle", required = true) String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size)  throws Exception {
		if(sigle.trim().isEmpty()) {
			throw new Exception("Sigle is Empty");
		}
		if(sigle.isBlank()) {
			throw new Exception("Sigle is Blank");
		}
		Page<Liasses> liasses = liasseService.findBySigleLikeAndArchiveFalse(sigle,page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	@GetMapping("/liasses/search-by-name")
	public ResponseEntity<Page<Liasses>> searchLiasseByName(
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception  {
		if(name.trim().isEmpty()) {
			throw new Exception("Name is Empty");
		}if(name.isBlank()) {
			throw new Exception("Name is Blank");
		}
		Page<Liasses> liasses = liasseService.findByNameLike(name,page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}
	
	@GetMapping("/liasses/search-archive-by-name")
	public ResponseEntity<Page<Liasses>> searchByNameLiasseArchive(
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception  {
		if(name.trim().isEmpty()) {
			throw new Exception("Name is Empty");
		}if(name.isBlank()) {
			throw new Exception("Name is Blank");
		}
		Page<Liasses> liasses = liasseService.findByNameLikeAndArchiveTrue(name,page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}
	
	@GetMapping("/liasses/search-by-name-user")
	public ResponseEntity<Page<Liasses>> searchByNameLiasse(
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception  {
		if(name.trim().isEmpty()) {
			throw new Exception("Name is Empty");
		}if(name.isBlank()) {
			throw new Exception("Name is Blank");
		}
		Page<Liasses> liasses = liasseService.findByNameLikeAndArchiveFalse(name,page, size);
		if(liasses.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(liasses);
		}
	}

	
	@PostMapping("/liasses/create-workflow")
	public ResponseEntity<?> createLiasseWorkFlow(
			@RequestBody Liasses liasses) throws Exception {
		try {
			liasseService.updateName(liasses);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping("/liasses/create-user")
	public ResponseEntity<?> createLiasseUser(
			@RequestBody Liasses liasses) throws Exception {
		try {
			liasseService.createLiasseUser(liasses);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	
	
	@PostMapping("/liasses/update")
	public ResponseEntity<?> updateLiasse(
			@RequestBody Liasses liasses) throws Exception {
		try {
			liasseService.updateName(liasses);
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

	@GetMapping("/liasses/find-by-id")
	public ResponseEntity<Liasses> findByNameLiasse(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		return  ResponseEntity.ok().body(liasseService.findByIdliasse(id));
	}
	*/

}
