package com.microservice.ged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowProfiles;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.WorkFlowPosteListe;

@RestController
@CrossOrigin("http://localhost:4200")
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlowService;

	
	@GetMapping("/workflow/all")
	public ResponseEntity<Page<WorkFlow>> findAllWorkFlow(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<WorkFlow> workFlow = workFlowService.findAllWorkFlow(page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}	
	}
		
	@GetMapping("/workflow/search-by-name")
	public ResponseEntity<Page<WorkFlow>> searchWorkFlowByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(name.trim().isEmpty()) {
			throw new Exception("Name is Empty");
		}
		if(name.isBlank()) {
			throw new Exception("Name is Blank");
		}
		Page<WorkFlow> workFlow = workFlowService.searchWorkFlowByName(name,page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}		
	}
		
	@GetMapping("/workflow/search-by-sigle")
	public ResponseEntity<Page<WorkFlow>> searchWorkFlowBySigle(
			@RequestParam(name = "sigle") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(sigle.trim().isEmpty()) {
			throw new Exception("Sige is Empty");
		}
		if(sigle.isBlank()) {
			throw new Exception("Sigle is Blank");
		}
		Page<WorkFlow> workFlow = workFlowService.searchWorkFlowBySigle(sigle,page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}	
	}

	@PostMapping("/workflow/add")
	public ResponseEntity<?> addWorkFlow(
			@RequestBody WorkFlow workFlow) throws Exception {
		workFlowService.addWorkFlow(workFlow);
		return  ResponseEntity.ok().build();	
	}

	@PostMapping("/workflow/update")
	public ResponseEntity<?> updateWorkFlowSatus(@RequestParam(name = "idworkFlow") Long idworkFlow) throws Exception {
		workFlowService.updateWorkFlowStatus(idworkFlow);
		return  ResponseEntity.ok().build();
	}

	@GetMapping("/workflow/find-by-id")
	public ResponseEntity<WorkFlow> findById(@RequestParam(name = "id") Long id) throws Exception {
		return  ResponseEntity.ok().body(workFlowService.findWorkFlowById(id));	
	}


	@GetMapping("/workflow/find-by-name")
	public ResponseEntity<WorkFlow> findByName(@RequestParam(name = "name") String name) throws Exception {
		return  ResponseEntity.ok().body(workFlowService.findWorkFlowByName(name));		
	}


	@GetMapping("/workflow/find-by-sigle")
	public ResponseEntity<WorkFlow> findBySigle(@RequestParam(name = "sigle") String sigle) throws Exception {
		return  ResponseEntity.ok().body(workFlowService.findWorkFlowBySigle(sigle));	
	}
	

	@PostMapping("/workflow/add-profile-in-workflow")
	public ResponseEntity<?> addPosteToWorkFlow(@RequestBody List<WorkFlowProfiles> workFlowProfiles) throws Exception {
		workFlowService.addProfileToWorkFlow( workFlowProfiles);
		return  ResponseEntity.ok().build();		
	}


	@PostMapping("/workflow/remove-profile-in-workflow")
	public ResponseEntity<?> removePosteToWorkFlow(@RequestBody WorkFlowProfiles workFlowProfiles) throws Exception {
		workFlowService.removeProfileToWorkFlow(workFlowProfiles);
		return  ResponseEntity.ok().build();	
	}


	@PostMapping("/workflow/add-liasse-in-workflow")
	public ResponseEntity<?> addLiasseToWorkFlow(@RequestBody WorkFlow workFlow) throws Exception {
		workFlowService.addLiasseToWorkFlow(workFlow);
		return  ResponseEntity.ok().build();	
	}
	
	@GetMapping("/workflow/all-by-profile")
	public ResponseEntity<Page<WorkFlowProfiles>> allWorkFlowInProfiles(
			@RequestParam(name = "idPostes") Long idProfile,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<WorkFlowProfiles> workFlowProfiles = workFlowService.allWorkFlowInProfiles(idProfile, page, size);
		if(workFlowProfiles.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlowProfiles);
		}	
	}
	
	@GetMapping("/workflow/all-by-workflow")
	public ResponseEntity<Page<WorkFlowProfiles>> allProfilesInWorkFlow(
			@RequestParam(name = "idWorkFlow") Long idWorkFlow,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<WorkFlowProfiles> workFlowProfiles = workFlowService.allProfilesInWorkFlow(idWorkFlow, page, size);
		if(workFlowProfiles.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlowProfiles);
		}
	}

}
