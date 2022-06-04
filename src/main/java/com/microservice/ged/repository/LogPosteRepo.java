package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.LogPoste;

@Repository
public interface LogPosteRepo extends JpaRepository<LogPoste, Long>{
	Page<LogPoste> findByLoginuser(String loginuser, Pageable pageable);
	Page<LogPoste> findByType(String type, Pageable pageable);
	Page<LogPoste> findByPostename(String postename, Pageable pageable);
	Page<LogPoste> findByObjectname(String objectname, Pageable pageable);
	Page<LogPoste> findByObjectnameAndType(String objectname, String type, Pageable pageable);
	Page<LogPoste> findByPostenameAndType(String postename, String type, Pageable pageable);
	List<LogPoste> findTop5ByTypeAndPostename( String type, String postename);
	
}
