package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;

@Repository
public interface DocsRepo extends JpaRepository<Docs, Long>{
	Docs findByIddoc(Long iddoc);
	Docs findByName(String name);
	Page<Docs> findByNameLike(String name, Pageable pageable);
	Page<Docs> findByLiasse(Liasses liasse, Pageable pageable);
	
}
