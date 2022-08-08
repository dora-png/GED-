package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.ged.beans.Droits;

@Repository
public interface DroitsRepo extends JpaRepository<Droits, Long>{
	Droits findByIddroit(Long idDroit);
	Droits findByName(String name);
	Droits findByAbbr(String abbr);
	Page<Droits> findByAbbrContaining(String abbr, Pageable pageable);
	Page<Droits> findByAbbrNotIn(List<String> abbr, Pageable pageable);
	Page<Droits> findByNameContaining(String name, Pageable pageable);
	Page<Droits> findByNameNotIn(List<String> name, Pageable pageable);
	Page<Droits> findByIddroitNotIn(List<Long> ids, Pageable pageable);
	Page<Droits> findByIddroitNotInAndNameContaining(List<Long> ids, String name, Pageable pageable);
	Page<Droits> findByIddroitNotInAndAbbrContaining(List<Long> ids, String abbr, Pageable pageable);
}
