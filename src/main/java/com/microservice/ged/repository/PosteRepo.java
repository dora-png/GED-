package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;

@Repository
public interface PosteRepo extends JpaRepository<Postes, Long>{
	Postes findByIdposte(Long idposte);
	Postes findByName(String name);
	Page<Postes> findByNiveauAndStructure(Integer niveau, Structures structures,  Pageable pageable);
	Page<Postes> findByNiveau(Integer niveau, Pageable pageable);
	Page<Postes> findByNameLike(String name, Pageable pageable);

}
