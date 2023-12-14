package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.WorkFlow;

@Repository
public interface WorkFlowRepo extends JpaRepository<WorkFlow, Long>{
	WorkFlow findBySigle(String sigle);
	WorkFlow findByName(String name);
	WorkFlow findByIdworkflows(Long iduser);
	Page<WorkFlow> findByNameContaining(String name, Pageable pageable);
	Page<WorkFlow> findBySigleContaining(String name, Pageable pageable);

}
