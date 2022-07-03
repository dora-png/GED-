package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.repository.LiassesRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.repository.WorkFlowPosteRepo;
import com.microservice.ged.repository.WorkFlowRepo;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.WorkFlowPosteListe;

@Transactional
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	
	@Autowired
	WorkFlowPosteRepo workFlowPosteRepo;
	
	@Autowired
	WorkFlowRepo workFlowRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	RolesRepo rolesRepo;
	
	@Autowired
	LiassesRepo liassesRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	

	
	private void userExist(Long posteId, String roles)  throws Exception   {
		if(posteRepo.findByIdposteAndActiveTrue(posteId)==null) {
			throw new Exception("Poste not exist");	
		}
		Roles role= rolesRepo.findByName(roles);
		//if(!posteRepo.findByIdposte(posteId).getRoles().contains(role)) {
		//	throw new Exception("Forbidden");				
		//}
	}

	@Override
	public Page<WorkFlow> defaultList(int page, int size) throws Exception {
		return workFlowRepo.findAll(PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlow> findAll(int page, int size) throws Exception {
		return workFlowRepo.findAll(PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlow> searchByName(String name, int page, int size) throws Exception {
		return workFlowRepo.findByNameContaining(name, PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlow> searchBySigle(String name, int page, int size) throws Exception {
		return workFlowRepo.findBySigleContaining(name, PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlowPoste> allPosteInWorkFlow(Long idWorkFlow, int page, int size) throws Exception {
		WorkFlow workFlow = workFlowRepo.findByIdworkflows(idWorkFlow);
		if(workFlow==null) {
			throw new Exception("Error");	
		}
		return workFlowPosteRepo.findByIsactiveTrueAndWorkflowIdOrderByLevelDesc(workFlow, PageRequest.of(page, size));
	}

	@Override
	public Page<WorkFlowPoste> allWorkFlowInPoste(Long idPostes, int page, int size) throws Exception {
		Postes poste = posteRepo.findByIdposteAndActiveTrue(idPostes);
		if(poste==null) {
			throw new Exception("Error");	
		}
		return workFlowPosteRepo.findByIsactiveTrueAndPosteIdOrderByLevelDesc(poste, PageRequest.of(page, size));
	}

	@Override
	public void add(WorkFlow workFlow) throws Exception {
		if(workFlowRepo.findByName(workFlow.getName())!=null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" already exist");
		}
		if(workFlowRepo.findBySigle(workFlow.getSigle())!=null) {
			throw new Exception("WorkFlow with sigle "+workFlow.getSigle()+" already exist");
		}
		workFlowRepo.save(workFlow);		
	}

	@Override
	public void addPosteToWorkFlow(List<WorkFlowPosteListe> workFlowPosteListe) throws Exception {
			List<WorkFlowPoste> workFlowPoste = new ArrayList<>();
			List<WorkFlowPoste> flowPosteSet = new ArrayList<>();
			WorkFlowPoste flowPosteSet1 = new WorkFlowPoste();
			for(WorkFlowPosteListe workFlowPosteList : workFlowPosteListe) {
				WorkFlow workFlow = workFlowRepo.findByIdworkflows(workFlowPosteList.getIdWorkFlow());
				Postes poste = posteRepo.findByIdposteAndActiveTrue(workFlowPosteList.getIdPoste());
				if(workFlow==null) {
					throw new Exception("WorkFlow not exist");
				}
				if(poste==null) {
					throw new Exception("Poste not exist");
				}
				flowPosteSet1 = workFlowPosteRepo.findByIsactiveTrueAndWorkflowIdAndPosteId(workFlow, poste);
				if(flowPosteSet1!=null) {
					flowPosteSet1.setIsactive(false);	
					flowPosteSet.add(flowPosteSet1);
				}
				WorkFlowPoste flowPoste = new WorkFlowPoste(poste,  workFlow, workFlowPosteList.getIndex());
				flowPoste.setIsactive(true);
				workFlowPoste.add(flowPoste);
			}
			try {
				workFlowPosteRepo.saveAll(flowPosteSet);
				workFlowPosteRepo.saveAll(workFlowPoste);
			} catch (Exception e) {
				throw new Exception("Error while update workFlow");
			}		
	}

	@Override
	public void removePosteToWorkFlow(List<WorkFlowPosteListe> workFlowPosteListe) throws Exception {
		
			List<WorkFlowPoste> workFlowPoste = new ArrayList<>();
			List<WorkFlowPoste> flowPosteSet = new ArrayList<>();
			WorkFlowPoste flowPosteSet1 = new WorkFlowPoste();
			for(WorkFlowPosteListe workFlowPosteList : workFlowPosteListe) {
				WorkFlow workFlow = workFlowRepo.findByIdworkflows(workFlowPosteList.getIdWorkFlow());
				Postes poste = posteRepo.findByIdposteAndActiveTrue(workFlowPosteList.getIdPoste());
				if(workFlow==null) {
					throw new Exception("WorkFlow not exist");
				}
				if(poste==null) {
					throw new Exception("Poste not exist");
				}
				flowPosteSet1 = workFlowPosteRepo.findByIsactiveTrueAndWorkflowIdAndPosteId(workFlow, poste);
				if(flowPosteSet1!=null) {
					flowPosteSet1.setIsactive(false);	
					flowPosteSet.add(flowPosteSet1);
				}
				WorkFlowPoste flowPoste = new WorkFlowPoste(poste,  workFlow, workFlowPosteList.getIndex());
				flowPoste.setIsactive(workFlowPosteList.isExist());
				workFlowPoste.add(flowPoste);
			}
			try {
				workFlowPosteRepo.saveAll(flowPosteSet);
				workFlowPosteRepo.saveAll(workFlowPoste);
			} catch (Exception e) {
				throw new Exception("Error while update workFlow");
			}
	}

	@Override
	public void addLiasseToWorkFlow(WorkFlow workFlow) throws Exception {
		
		if(workFlow.getLiasses().isEmpty()) {
			throw new Exception("Liasse is Empty");
		}else if(workFlow.getLiasses().size()==1) {
			Iterator<Liasses> liasseIterato = workFlow.getLiasses().iterator();
			Liasses liasse = new Liasses();
			while(liasseIterato.hasNext()) {
				liasse = liasseIterato.next();
			}
			workFlow =workFlowRepo.findByIdworkflows(workFlow.getIdworkflows());
			if(workFlow==null) {
				throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
			}
			liasse = liassesRepo.findByIdliasse(liasse.getIdliasse());
			if(liasse==null) {
				throw new Exception("Liasse not exist");
			}
			if(workFlow.getLiasses().contains(liasse)) {
				throw new Exception("Liasse Already exist in workflow");
			}
				try {
					
					liasse.setWorkflowid(workFlow);
					liassesRepo.save(liasse);
					workFlow.getLiasses().add(liasse);
					workFlowRepo.save(workFlow);
				} catch (Exception e) {
					throw new Exception("Error while update workFlow");
				}
		}
		
	}

	@Override
	public void update(WorkFlow workFlow) throws Exception {
		if(workFlowRepo.findByIdworkflows(workFlow.getIdworkflows())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
			try {
				workFlowRepo.save(workFlow);
			} catch (Exception e) {
				throw new Exception("Error while update workFlow");
			}
		
	}

	@Override
	public void delete( WorkFlow workFlow) throws Exception {
		if(workFlowRepo.findByName(workFlow.getName())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		if(workFlowRepo.findBySigle(workFlow.getSigle())==null) {
			throw new Exception("WorkFlow with sigle "+workFlow.getSigle()+" not exist");
		}
		if(workFlowRepo.findByIdworkflows(workFlow.getIdworkflows())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		try {
				
				workFlowRepo.delete(workFlow);
			} catch (Exception e) {
				throw new Exception("Error while delete workFlow");
			}			
	}

	@Override
	public WorkFlow findById(Long id) throws Exception {
		return workFlowRepo.findById(id).orElseThrow(() -> new Exception("Not exist"));
	}

	@Override
	public WorkFlow findByName(String name) throws Exception {
		return workFlowRepo.findByName(name);
	}

	@Override
	public WorkFlow findBySigle(String sigle) throws Exception {
		return workFlowRepo.findBySigle(sigle);
	}



}
