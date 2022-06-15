package com.microservice.ged.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long>{
	Roles findByIdroles(Long idroles);
	Roles findByName(String name);
	Page<Roles> findByNameLike(String name, Pageable pageable);
	Page<Roles> findByNameNotIn(Set<String> name, Pageable pageable);


}
