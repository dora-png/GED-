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

import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.service.WorkFlowService;

@RestController
@CrossOrigin("*")
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlowService;

	@GetMapping("/workflow/all")
	public ResponseEntity<Page<WorkFlow>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		try {
			Page<WorkFlow> workFlow = workFlowService.findAll(page, size);
			if(workFlow.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(workFlow);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
		
	@GetMapping("/workflow/search-by-name")
	public ResponseEntity<Page<WorkFlow>> searchByName(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(name.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(name.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<WorkFlow> workFlow = workFlowService.searchByName(name,page, size);
				if(workFlow.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(workFlow);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}
		
	@GetMapping("/workflow/search-by-sigle")
	public ResponseEntity<Page<WorkFlow>> searchBySigle(
			@RequestParam(name = "name", defaultValue = "") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		if(sigle.trim().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}else if(sigle.isBlank()) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				Page<WorkFlow> workFlow = workFlowService.searchBySigle(sigle,page, size);
				if(workFlow.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(workFlow);
				}			
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}	
		}	
	}

	@PostMapping("/workflow/add")
	public ResponseEntity<?> add(
			@RequestBody WorkFlow workFlow,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			workFlowService.add(workFlow, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@PutMapping("/workflow/update")
	public ResponseEntity<?> update(
			@RequestBody WorkFlow workFlow,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			workFlowService.update(workFlow, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@DeleteMapping("/workflow/delete")
	public ResponseEntity<?> delete(
			@RequestParam(name = "id") long id,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			WorkFlow workFlow = workFlowService.findById(id);
			if(workFlow==null) {
				return ResponseEntity.badRequest().build();
			}
			workFlowService.delete(workFlow, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/workflow/find-by-id")
	public ResponseEntity<WorkFlow> findById(
			@RequestParam(name = "id") long id) throws Exception {
		try {
			return  ResponseEntity.ok().body(workFlowService.findById(id));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@GetMapping("/workflow/find-by-name")
	public ResponseEntity<WorkFlow> findByName(
			@RequestParam(name = "name") String name) throws Exception {
		try {
			return  ResponseEntity.ok().body(workFlowService.findByName(name));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@GetMapping("/workflow/find-by-sigle")
	public ResponseEntity<WorkFlow> findBySigle(
			@RequestBody WorkFlow workFlow,
			@RequestParam(name = "sigle") String sigle) throws Exception {
		try {
			return  ResponseEntity.ok().body(workFlowService.findBySigle(sigle));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	

	@PostMapping("/workflow/add-poste-in-workflow")
	public ResponseEntity<?> addPosteToWorkFlow(
			@RequestBody WorkFlowPoste workFlowPoste,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			workFlowService.addPosteToWorkFlow(workFlowPoste, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@PostMapping("/workflow/remove-poste-in-workflow")
	public ResponseEntity<?> removePosteToWorkFlow(
			@RequestBody WorkFlowPoste workFlowPoste,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			workFlowService.removePosteToWorkFlow(workFlowPoste, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@PostMapping("/workflow/add-liasse-in-workflow")
	public ResponseEntity<?> addLiasseToWorkFlow(
			@RequestBody WorkFlow workFlow,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			workFlowService.addLiasseToWorkFlow(workFlow, posteName);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

}
