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
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.WorkFlowPosteListe;

@RestController
@CrossOrigin("http://localhost:4200")
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private PosteService posteService;
	
	@GetMapping("/workflow/all")
	public ResponseEntity<Page<WorkFlow>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<WorkFlow> workFlow = workFlowService.findAll(page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}	
	}
		
	@GetMapping("/workflow/search-by-name")
	public ResponseEntity<Page<WorkFlow>> searchByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(name.trim().isEmpty()) {
			throw new Exception("Name is Empty");
		}
		if(name.isBlank()) {
			throw new Exception("Name is Blank");
		}
		Page<WorkFlow> workFlow = workFlowService.searchByName(name,page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}		
	}
		
	@GetMapping("/workflow/search-by-sigle")
	public ResponseEntity<Page<WorkFlow>> searchBySigle(
			@RequestParam(name = "sigle") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		if(sigle.trim().isEmpty()) {
			throw new Exception("Sige is Empty");
		}
		if(sigle.isBlank()) {
			throw new Exception("Sigle is Blank");
		}
		Page<WorkFlow> workFlow = workFlowService.searchBySigle(sigle,page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}	
	}

	@PostMapping("/workflow/add")
	public ResponseEntity<?> add(
			@RequestBody WorkFlow workFlow) throws Exception {
		workFlowService.add(workFlow);
		return  ResponseEntity.ok().build();	
	}

	@PostMapping("/workflow/update")
	public ResponseEntity<?> update(
			@RequestBody WorkFlow workFlow) throws Exception {
		System.err.println(workFlow.getName());
		workFlowService.update(workFlow);
		return  ResponseEntity.ok().build();
	}

	@DeleteMapping("/workflow/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") Long id) throws Exception {
		WorkFlow workFlow = workFlowService.findById(id);
		if(workFlow==null) {
			return ResponseEntity.badRequest().build();
		}
		workFlowService.delete(workFlow);
		return  ResponseEntity.ok().build();	
	}

	@GetMapping("/workflow/find-by-id")
	public ResponseEntity<WorkFlow> findById(@RequestParam(name = "id") Long id) throws Exception {
		return  ResponseEntity.ok().body(workFlowService.findById(id));	
	}


	@GetMapping("/workflow/find-by-name")
	public ResponseEntity<WorkFlow> findByName(@RequestParam(name = "name") String name) throws Exception {
		return  ResponseEntity.ok().body(workFlowService.findByName(name));		
	}


	@GetMapping("/workflow/find-by-sigle")
	public ResponseEntity<WorkFlow> findBySigle(@RequestParam(name = "sigle") String sigle) throws Exception {
		return  ResponseEntity.ok().body(workFlowService.findBySigle(sigle));	
	}
	

	@PostMapping("/workflow/add-poste-in-workflow")
	public ResponseEntity<?> addPosteToWorkFlow(@RequestBody List<WorkFlowPoste> workFlowPoste) throws Exception {
		workFlowService.addPosteToWorkFlow( workFlowPoste);
		return  ResponseEntity.ok().build();		
	}


	@PostMapping("/workflow/remove-poste-in-workflow")
	public ResponseEntity<?> removePosteToWorkFlow(@RequestBody WorkFlowPoste workFlowPoste) throws Exception {
		workFlowService.removePosteToWorkFlow(workFlowPoste);
		return  ResponseEntity.ok().build();	
	}


	@PostMapping("/workflow/add-liasse-in-workflow")
	public ResponseEntity<?> addLiasseToWorkFlow(@RequestBody WorkFlow workFlow) throws Exception {
		workFlowService.addLiasseToWorkFlow(workFlow);
		return  ResponseEntity.ok().build();	
	}
	
	@GetMapping("/workflow/all-by-poste")
	public ResponseEntity<Page<WorkFlowPoste>> allWorkFlowInPoste(
			@RequestParam(name = "idPostes") Long idPostes,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<WorkFlowPoste> workFlow = workFlowService.allWorkFlowInPoste(idPostes, page, size);
		if(workFlow.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlow);
		}	
	}
	
	@GetMapping("/workflow/all-by-workflow")
	public ResponseEntity<Page<WorkFlowPoste>> allPosteInWorkFlow(
			@RequestParam(name = "idWorkFlow") Long idWorkFlow,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
		Page<WorkFlowPoste> workFlowsPoste = workFlowService.allPosteInWorkFlow(idWorkFlow, page, size);
		if(workFlowsPoste.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return  ResponseEntity.ok().body(workFlowsPoste);
		}
	}

}
