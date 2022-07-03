package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;

public interface StructureRepo extends JpaRepository<Structures, Long> {
	Structures findByIdstructureAndActiveTrue(Long idstructure);
	Structures findByNameAndActiveTrue(String name);
	Structures findBySigleAndActiveTrue(String sigle);
	Page<Structures> findByActiveTrue(Pageable pageable);
	Page<Structures> findByNameContainingAndActiveTrue(String name, Pageable pageable);
	Page<Structures> findBySigleContainingAndActiveTrue(String sigle, Pageable pageable);
	Structures findByStructureSuperieurIsNullAndActiveTrue();

}
