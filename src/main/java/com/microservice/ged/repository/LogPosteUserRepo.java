package com.microservice.ged.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Users;

@Repository
public interface LogPosteUserRepo extends JpaRepository<LogPosteUser, Long>{
	Page<LogPosteUser> findByUserId(Users userId, Pageable pageable);
	Page<LogPosteUser> findByPosteId(Postes posteId, Pageable pageable);
	LogPosteUser findByUserIdAndDateFinIsNull(Users userId);
	LogPosteUser findByPosteIdAndDateFinIsNull(Postes posteId);
	LogPosteUser findByUserIdAndPosteIdAndDateFinIsNull(Users userId, Postes posteId);
	
}
