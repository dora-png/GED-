package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Users;

public interface LogPosteUserService {
	public Page<LogPosteUser> logUser(Users users, int page, int size) throws Exception ;
	public Page<LogPosteUser> logPoste(Postes postes, int page, int size) throws Exception ;
	public Postes currentPosteOfUser(Users users) throws Exception ;

}
