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
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowProfiles;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.repository.LiassesRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.ProfilesRepo;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.repository.WorkFlowProfilesRepo;
import com.microservice.ged.repository.WorkFlowRepo;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.WorkFlowPosteListe;

@Transactional
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	
	@Autowired
	WorkFlowProfilesRepo workFlowProfilesRepo;
	
	@Autowired
	WorkFlowRepo workFlowRepo;
	
	@Autowired
	ProfilesRepo profilesRepo;
	
	@Autowired
	DroitsRepo droitsRepo;
	
	@Autowired
	LiassesRepo liassesRepo;
	/*
	@Autowired
	LogPosteUserRepo logPosteUserRepo;*/
	

	
	/*private void userExist(Long posteId, String droits)  throws Exception   {
		if(posteRepo.findByIdposteAndActiveTrue(posteId)==null) {
			throw new Exception("Poste not exist");	
		}
		Droits role= droitsRepo.findByName(droits);
		//if(!posteRepo.findByIdposte(posteId).getRoles().contains(role)) {
		//	throw new Exception("Forbidden");				
		//}
	}*/

	@Override
	public Page<WorkFlow> findAllWorkFlow(int page, int size) throws Exception {
		return workFlowRepo.findAll(PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlow> searchWorkFlowByName(String name, int page, int size) throws Exception {
		return workFlowRepo.findByNameContaining(name, PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlow> searchWorkFlowBySigle(String name, int page, int size) throws Exception {
		return workFlowRepo.findBySigleContaining(name, PageRequest.of(page, size,Sort.by("idworkflows").descending()));
	}

	@Override
	public Page<WorkFlowProfiles> allProfilesInWorkFlow(Long idWorkFlow, int page, int size) throws Exception {
		WorkFlow workFlow = workFlowRepo.findByIdworkflows(idWorkFlow);
		if(workFlow==null) {
			throw new Exception("Error");	
		}
		return workFlowProfilesRepo.findByIsactiveTrueAndWorkflowIdOrderByLevelDesc(workFlow, PageRequest.of(page, size));
	}

	@Override
	public Page<WorkFlowProfiles> allWorkFlowInProfiles(Long idPostes, int page, int size) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(idPostes);
		if(profiles==null) {
			throw new Exception("Error");	
		}
		return workFlowProfilesRepo.findByIsactiveTrueAndProfilesIdOrderByLevelDesc(profiles, PageRequest.of(page, size));
	}

	@Override
	public void addWorkFlow(WorkFlow workFlow) throws Exception {
		if(workFlowRepo.findByName(workFlow.getName())!=null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" already exist");
		}
		if(workFlowRepo.findBySigle(workFlow.getSigle())!=null) {
			throw new Exception("WorkFlow with sigle "+workFlow.getSigle()+" already exist");
		}
		workFlow.setIdworkflows(null);
		workFlow.setDateCreation(null);
		workFlow.setLiasses(null);
		workFlow.setTypeDocs(null);
		workFlowRepo.save(workFlow);		
	}

	@Override
	public void addProfileToWorkFlow(List<WorkFlowProfiles> workFlowProfiles) throws Exception {
			try {
				workFlowProfilesRepo.saveAll(workFlowProfiles);
			} catch (Exception e) {
				throw new Exception("Error while update workFlow");
			}		
	}

	@Override
	public void removeProfileToWorkFlow(WorkFlowProfiles workFlowProfiles) throws Exception {
			try {
				int level = workFlowProfiles.getLevel();
				workFlowProfiles.setIsactive(false);
				workFlowProfilesRepo.save(workFlowProfiles);
				List<WorkFlowProfiles> workFlowProfilesList = workFlowProfilesRepo.findByIsactiveTrueAndWorkflowIdAndLevelGreaterThan(workFlowProfiles.getWorkflowId(), level);
				if(!workFlowProfilesList.isEmpty()) {
					for(WorkFlowProfiles item : workFlowProfilesList) {
						item.setLevel(item.getLevel()-1);
						workFlowProfilesRepo.save(item);
					}
				}
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
			liasse = liassesRepo.findByArchiveFalseAndIdliasse(liasse.getIdliasse());
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
	public void updateWorkFlowStatus(Long workFlowid) throws Exception {
		WorkFlow workFlow = workFlowRepo.findByIdworkflows(workFlowid);
		if(workFlow==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		try {
			//workFlow.
			workFlowRepo.save(workFlow);
		} catch (Exception e) {
			throw new Exception("Error while update workFlow");
		}		
	}

	@Override
	public WorkFlow findWorkFlowById(Long id) throws Exception {
		return workFlowRepo.findById(id).orElseThrow(() -> new Exception("Not exist"));
	}

	@Override
	public WorkFlow findWorkFlowByName(String name) throws Exception {
		return workFlowRepo.findByName(name);
	}

	@Override
	public WorkFlow findWorkFlowBySigle(String sigle) throws Exception {
		return workFlowRepo.findBySigle(sigle);
	}


}
