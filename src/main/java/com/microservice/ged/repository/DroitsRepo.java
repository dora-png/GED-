package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.ged.beans.Droits;

@Repository
public interface DroitsRepo extends JpaRepository<Droits, Long>{
	Droits findByIddroit(Long iddroit);
	Droits findByName(String name);
	Droits findByUri(String uri);
	Droits findByDescription(String description);
	Page<Droits> findByDescriptionContaining(String description, Pageable pageable);
	Page<Droits> findByNameContaining(String name, Pageable pageable);
	Page<Droits> findByNameNotIn(List<String> name, Pageable pageable);
	Page<Droits> findByIddroitNotIn(List<Long> ids, Pageable pageable);
	Page<Droits> findByIddroitIn(List<Long> ids, Pageable pageable);
	Page<Droits> findByIddroitInAndNameContaining(List<Long> ids, String name, Pageable pageable);
	Page<Droits> findByIddroitNotInAndNameContaining(List<Long> ids, String name, Pageable pageable);
}
