package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Structures;

public interface StructureRepo extends JpaRepository<Structures, Long> {
	Structures findByIdstructure(Long idstructure);
	Structures findByName(String name);
	Structures findBySigle(String sigle);
	Page<Structures> findByNameLike(String name, Pageable pageable);
	Page<Structures> findBySigleLike(String sigle, Pageable pageable);

}
