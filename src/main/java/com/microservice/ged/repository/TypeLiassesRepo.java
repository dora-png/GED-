package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.TypeLiasses;

@Repository
public interface TypeLiassesRepo extends JpaRepository<TypeLiasses, Long>{
	TypeLiasses findBySigle(String sigle);
	TypeLiasses findByName(String name);
	TypeLiasses findByIdtypeliasse(Long iduser);
	Page<TypeLiasses> findByNameLike(String name, Pageable pageable);
	Page<TypeLiasses> findBySigleLike(String name, Pageable pageable);

}
