package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.TypeDocs;

@Repository
public interface TypeDocsRepo extends JpaRepository<TypeDocs, Long>{
	TypeDocs findBySigle(String sigle);
	TypeDocs findByName(String name);
	TypeDocs findByIdtypedoc(Long iduser);
	Page<TypeDocs> findByNameLike(String name, Pageable pageable);
	Page<TypeDocs> findBySigleLike(String name, Pageable pageable);
}
