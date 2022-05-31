package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;

public interface WorkFlowPosteRepo extends JpaRepository<WorkFlowPoste, Long> {
	Page<WorkFlowPoste> findByPosteId(Postes posteId, Pageable pageable);
	Page<WorkFlowPoste> findByActiveTrueAndWorkflowId(WorkFlow workflowId, Pageable pageable);
	WorkFlowPoste findByActiveTrueAndWorkflowIdAndPosteId(WorkFlow workflowId,Postes posteId);
	WorkFlowPoste findByActiveTrueAndWorkflowIdAndLevel(WorkFlow workflowId,int level);
}
