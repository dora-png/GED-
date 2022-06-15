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

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.WorkFlowPosteListe;

@RestController
@CrossOrigin("*")
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private PosteService posteService;

	@GetMapping("/workflow/all")
	public ResponseEntity<Page<WorkFlow>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<WorkFlow> workFlow = workFlowService.findAll(page, size);
			if(workFlow.isEmpty()) {
				workFlow = workFlowService.defaultList(0, 5);
				if(workFlow.isEmpty()) {
					return  ResponseEntity.noContent().build();
				}else {
					return  ResponseEntity.ok().body(workFlow);
				}
			}else {
				return  ResponseEntity.ok().body(workFlow);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
		
	@GetMapping("/workflow/search-by-name")
	public ResponseEntity<Page<WorkFlow>> searchByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
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
			@RequestParam(name = "name") String sigle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
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
			workFlowService.add(workFlow);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}

	@PutMapping("/workflow/update")
	public ResponseEntity<?> update(
			@RequestBody WorkFlow workFlow,
			@RequestParam(name = "posteName") String posteName) throws Exception {
		try {
			workFlowService.update(workFlow);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}

	@DeleteMapping("/workflow/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") Long id) throws Exception {
		try {
			WorkFlow workFlow = workFlowService.findById(id);
			if(workFlow==null) {
				return ResponseEntity.badRequest().build();
			}
			workFlowService.delete(workFlow);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}

	@GetMapping("/workflow/find-by-id")
	public ResponseEntity<WorkFlow> findById(@RequestParam(name = "id") Long id) throws Exception {
		try {
			return  ResponseEntity.ok().body(workFlowService.findById(id));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@GetMapping("/workflow/find-by-name")
	public ResponseEntity<WorkFlow> findByName(@RequestParam(name = "name") String name) throws Exception {
		try {
			return  ResponseEntity.ok().body(workFlowService.findByName(name));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@GetMapping("/workflow/find-by-sigle")
	public ResponseEntity<WorkFlow> findBySigle(@RequestParam(name = "sigle") String sigle) throws Exception {
		try {
			return  ResponseEntity.ok().body(workFlowService.findBySigle(sigle));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	

	@PostMapping("/workflow/add-poste-in-workflow")
	public ResponseEntity<?> addPosteToWorkFlow(@RequestBody List<WorkFlowPosteListe> workFlowPosteListe) throws Exception {
		try {
			workFlowService.addPosteToWorkFlow( workFlowPosteListe);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}


	@PostMapping("/workflow/remove-poste-in-workflow")
	public ResponseEntity<?> removePosteToWorkFlow(@RequestBody List<WorkFlowPosteListe> workFlowPosteListe) throws Exception {
		try {
			workFlowService.removePosteToWorkFlow(workFlowPosteListe);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}


	@PostMapping("/workflow/add-liasse-in-workflow")
	public ResponseEntity<?> addLiasseToWorkFlow(@RequestBody WorkFlow workFlow) throws Exception {
		try {
			workFlowService.addLiasseToWorkFlow(workFlow);
			return  ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}	
	}
	
	@GetMapping("/workflow/all-by-poste")
	public ResponseEntity<Page<WorkFlowPoste>> allWorkFlowInPoste(
			@RequestParam(name = "idPostes") Long idPostes,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<WorkFlowPoste> workFlow = workFlowService.allWorkFlowInPoste(idPostes, page, size);
			if(workFlow.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(workFlow);
			}			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@GetMapping("/workflow/all-by-workflow")
	public ResponseEntity<Page<WorkFlowPoste>> allPosteInWorkFlow(
			@RequestParam(name = "idWorkFlow") Long idWorkFlow,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		try {
			Page<WorkFlowPoste> workFlows = workFlowService.allPosteInWorkFlow(idWorkFlow, page, size);
			if(workFlows.isEmpty()) {
				return  ResponseEntity.noContent().build();
			}else {
				return  ResponseEntity.ok().body(workFlows);
			}	
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
