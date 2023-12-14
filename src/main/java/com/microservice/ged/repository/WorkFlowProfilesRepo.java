package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowProfiles;

public interface WorkFlowProfilesRepo extends JpaRepository<WorkFlowProfiles, Long> {
	Page<WorkFlowProfiles> findByIsactiveTrueAndProfilesIdOrderByLevelDesc(Profiles profilesId, Pageable pageable);
	Page<WorkFlowProfiles> findByIsactiveTrueAndWorkflowIdOrderByLevelDesc(WorkFlow workflowId, Pageable pageable);
	Page<WorkFlowProfiles> findByIsactiveTrueAndProfilesId(Profiles profilesId, Pageable pageable);
	Page<WorkFlowProfiles> findByIsactiveTrueAndWorkflowId(WorkFlow workflowId, Pageable pageable);
	WorkFlowProfiles findByIsactiveTrueAndWorkflowIdAndProfilesId(WorkFlow workflowId,Profiles profilesId);
	WorkFlowProfiles findByIsactiveTrueAndWorkflowIdAndLevel(WorkFlow workflowId,int level);
	List<WorkFlowProfiles> findByIsactiveTrueAndWorkflowIdAndLevelGreaterThan(WorkFlow workflowId, int level);
}
