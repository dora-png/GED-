package com.microservice.ged.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Appusers;

public interface AppUserRepo extends JpaRepository<Appusers, Long>{
	Appusers findByLogin(String login);
	Appusers findByName(String name);
	Appusers findByIduser(Long iduser);
}
