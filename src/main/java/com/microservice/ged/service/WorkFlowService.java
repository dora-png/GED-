package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowProfiles;
import com.microservice.ged.utils.WorkFlowPosteListe;

public interface WorkFlowService {	
	public Page<WorkFlow> findAllWorkFlow(int page, int size) throws Exception;
	public Page<WorkFlow> searchWorkFlowByName(String name,int page, int size) throws Exception;
	public Page<WorkFlow> searchWorkFlowBySigle(String name,int page, int size) throws Exception;
	public Page<WorkFlowProfiles> allProfilesInWorkFlow(Long idWorkFlow, int page, int size) throws Exception;
	public Page<WorkFlowProfiles> allWorkFlowInProfiles( Long idPostes, int page, int size) throws Exception;
	public void addWorkFlow(WorkFlow workFlow) throws Exception;
	public void addProfileToWorkFlow(List<WorkFlowProfiles> workFlowProfiles) throws Exception;
	public void removeProfileToWorkFlow(WorkFlowProfiles workFlowProfiles) throws Exception;
	public void addLiasseToWorkFlow( WorkFlow workFlow) throws Exception;
	public void updateWorkFlowStatus(Long workFlowid) throws Exception;
	public WorkFlow findWorkFlowById(Long id) throws Exception;
	public WorkFlow findWorkFlowByName(String name) throws Exception;
	public WorkFlow findWorkFlowBySigle(String sigle) throws Exception;

}
