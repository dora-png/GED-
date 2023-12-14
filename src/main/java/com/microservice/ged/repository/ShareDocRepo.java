package com.microservice.ged.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.ShareDoc;
import com.microservice.ged.beans.TypeShare;

@Repository
public interface ShareDocRepo  extends JpaRepository<ShareDoc, Long>{
	ShareDoc findByIdsharedocAndActiveTrue(Long idsharedoc);
	Page<ShareDoc> findByLoginUserAndActiveTrue(String login, Pageable pageable);
	ShareDoc findByLoginUserAndActiveTrueAndTypeShare(String login,TypeShare typeShare);
	Page<ShareDoc> findByActiveTrue(Pageable pageable);


}
