package com.microservice.ged.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;

@Repository
public interface LogPosteUserRepo extends JpaRepository<LogPosteUser, Long>{
	Page<LogPosteUser> findByLdaplogin(Profiles ldaplogin, Pageable pageable);
	Page<LogPosteUser> findByPosteId(Postes posteId, Pageable pageable);
	LogPosteUser findByLdaploginAndDateFinIsNull(Profiles ldaplogin);
	LogPosteUser findByPosteIdAndDateFinIsNull(Postes posteId);
	LogPosteUser findByLdaploginAndPosteIdAndDateFinIsNull(Profiles ldaplogin, Postes posteId);
	
}
