package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;

public interface WorkFlowPosteRepo extends JpaRepository<WorkFlowPoste, Long> {
	Page<WorkFlowPoste> findByIsactiveTrueAndPosteIdOrderByLevelDesc(Postes posteId, Pageable pageable);
	Page<WorkFlowPoste> findByIsactiveTrueAndWorkflowIdOrderByLevelDesc(WorkFlow workflowId, Pageable pageable);
	Page<WorkFlowPoste> findByIsactiveTrueAndPosteId(Postes posteId, Pageable pageable);
	Page<WorkFlowPoste> findByIsactiveTrueAndWorkflowId(WorkFlow workflowId, Pageable pageable);
	WorkFlowPoste findByIsactiveTrueAndWorkflowIdAndPosteId(WorkFlow workflowId,Postes posteId);
	WorkFlowPoste findByIsactiveTrueAndWorkflowIdAndLevel(WorkFlow workflowId,int level);
}
