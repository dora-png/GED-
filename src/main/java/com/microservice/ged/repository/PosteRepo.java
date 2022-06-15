package com.microservice.ged.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Structures;

@Repository
public interface PosteRepo extends JpaRepository<Postes, Long>{
	Postes findByIdposte(Long idposte);
	Postes findByName(String name);
	//Postes findByNameAndRolesIn(String name, Set<Roles> listeRoles);
	Page<Postes> findByNiveauAndStructure(Integer niveau, Structures structures,  Pageable pageable);
	Page<Postes> findByNiveau(Integer niveau, Pageable pageable);
	Page<Postes> findByNameContaining(String name, Pageable pageable);
	Page<Postes> findByStructure(Structures structures,  Pageable pageable);
//	Page<Postes> findByStructureIsNull(Pageable pageable);

}
