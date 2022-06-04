package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;

public interface WorkFlowService {
	public Page<WorkFlow> defaultList(int page, int size);
	public Page<WorkFlow> findAll(int page, int size);
	public Page<WorkFlow> searchByName(String name,int page, int size);
	public Page<WorkFlow> searchBySigle(String name,int page, int size);
	public List<WorkFlowPoste> allPosteInWorkFlow(WorkFlow workFlow, String posteName, int page, int size) throws Exception;
	public List<WorkFlowPoste> allWorkFlowInPoste(Postes postes, String posteName, int page, int size) throws Exception;
	public void add(WorkFlow workFlow, String posteName) throws Exception;
	public void addPosteToWorkFlow(WorkFlowPoste workFlowPoste, String posteName) throws Exception;
	public void removePosteToWorkFlow(WorkFlowPoste workFlowPoste, String posteName) throws Exception;
	public void addLiasseToWorkFlow(WorkFlow workFlow, String posteName) throws Exception;
	public void update(WorkFlow workFlow, String posteName) throws Exception;
	public void delete(WorkFlow workFlow, String posteName) throws Exception;
	public WorkFlow findById(Long id) throws Exception;
	public WorkFlow findByName(String name) throws Exception;
	public WorkFlow findBySigle(String sigle) throws Exception;

}
