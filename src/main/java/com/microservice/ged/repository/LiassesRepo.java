package com.microservice.ged.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.TypeLiasses;

@Repository
public interface LiassesRepo extends JpaRepository<Liasses, Long>{
	Liasses findByArchiveTrueAndIdliasse(Long idliasse);
	Liasses findByArchiveFalseAndIdliasse(Long idliasse);
	Liasses findByProfileidAndName(Profiles profileid, String name);
	Liasses findByProfileidAndSigle(Profiles profileid, String sigle);
	Page<Liasses> findByArchiveTrue(Pageable pageable);
	Page<Liasses> findByArchiveFalse(Pageable pageable);
	Page<Liasses> findByNameLike(String name, Pageable pageable);
	Page<Liasses> findByNameLikeAndArchiveTrue(String name, Pageable pageable);
	Page<Liasses> findByNameLikeAndArchiveFalse(String name, Pageable pageable);
	Page<Liasses> findBySigleLike(String sigle, Pageable pageable);
	Page<Liasses> findBySigleLikeAndArchiveTrue(String sigle, Pageable pageable);
	Page<Liasses> findBySigleLikeAndArchiveFalse(String sigle, Pageable pageable);
	Page<Liasses> findByTypeliasse(TypeLiasses typeliasse, Pageable pageable);
	Page<Liasses> findByTypeliasseAndArchiveTrue(TypeLiasses typeliasse, Pageable pageable);
	Page<Liasses> findByTypeliasseAndArchiveFalse(TypeLiasses typeliasse, Pageable pageable);
	Page<Liasses> findByProfileid(Profiles profileid, Pageable pageable);
	Page<Liasses> findByProfileidAndArchiveTrue(Profiles profileid, Pageable pageable);
	Page<Liasses> findByProfileidAndArchiveFalse(Profiles profileid, Pageable pageable);
	Page<Liasses> findByDateCreationAndArchiveFalse(Date dateCreation, Pageable pageable);
	Page<Liasses> findByDateCreationBetweenAndArchiveFalse(Date date1, Date date2, Pageable pageable);
	Page<Liasses> findByDateCreationAndArchiveTrue(Date dateCreation, Pageable pageable);
	Page<Liasses> findByDateCreationBetweenAndArchiveTrue(Date date1, Date date2, Pageable pageable);
	
	//List<Date> listeYear()
	
}
