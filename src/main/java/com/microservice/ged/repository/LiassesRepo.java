package com.microservice.ged.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.TypeLiasses;

@Repository
public interface LiassesRepo extends JpaRepository<Liasses, Long>{
	Liasses findByIdliasse(Long idliasse);
	Liasses findByName(String name);
	Liasses findBySigle(String sigle);
	Page<Liasses> findByNameLike(String name, Pageable pageable);
	Page<Liasses> findBySigleLike(String sigle, Pageable pageable);
	Page<Liasses> findByTypeliasse(TypeLiasses typeliasse, Pageable pageable);
	Page<Liasses> findByDateCreation(Date dateCreation, Pageable pageable);
	Page<Liasses> findByDateCreationBetween(Date date1, Date date2, Pageable pageable);
	
	//List<Date> listeYear()
	
}
