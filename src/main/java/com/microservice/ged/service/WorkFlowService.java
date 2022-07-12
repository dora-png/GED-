package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.utils.WorkFlowPosteListe;

public interface WorkFlowService {
	public Page<WorkFlow> defaultList(int page, int size) throws Exception;
	public Page<WorkFlow> findAll(int page, int size) throws Exception;
	public Page<WorkFlow> searchByName(String name,int page, int size) throws Exception;
	public Page<WorkFlow> searchBySigle(String name,int page, int size) throws Exception;
	public Page<WorkFlowPoste> allPosteInWorkFlow(Long idWorkFlow, int page, int size) throws Exception;
	public Page<WorkFlowPoste> allWorkFlowInPoste( Long idPostes, int page, int size) throws Exception;
	public void add(WorkFlow workFlow) throws Exception;
	public void addPosteToWorkFlow(List<WorkFlowPoste> workFlowPoste) throws Exception;
	public void removePosteToWorkFlow(WorkFlowPoste workFlowPoste) throws Exception;
	public void addLiasseToWorkFlow( WorkFlow workFlow) throws Exception;
	public void update(WorkFlow workFlow) throws Exception;
	public void delete(WorkFlow workFlow) throws Exception;
	public WorkFlow findById(Long id) throws Exception;
	public WorkFlow findByName(String name) throws Exception;
	public WorkFlow findBySigle(String sigle) throws Exception;

}
