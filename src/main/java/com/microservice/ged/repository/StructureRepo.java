package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
@Repository
public interface StructureRepo extends JpaRepository<Structures, Long> {
	Structures findByIdstructure(Long idstructure);
	Structures findByName(String name);
	Structures findBySigle(String sigle);
	Structures findByNameAndActiveTrue(String name);
	Structures findBySigleAndActiveTrue(String sigle);
	Structures findByColor(String color);
	Page<Structures> findByActiveFalse(Pageable pageable);
	Page<Structures> findByActiveTrue(Pageable pageable);
	Page<Structures> findByNameContaining(String name, Pageable pageable);
	Page<Structures> findBySigleContaining(String sigle, Pageable pageable);
	Page<Structures> findByNameContainingAndActiveTrue(String name, Pageable pageable);
	Page<Structures> findBySigleContainingAndActiveTrue(String sigle, Pageable pageable);
	Page<Structures> findByNameContainingAndActiveFalse(String name, Pageable pageable);
	Page<Structures> findBySigleContainingAndActiveFalse(String sigle, Pageable pageable);
	Structures findByStructureSuperieurIsNullAndActiveTrue();

}
