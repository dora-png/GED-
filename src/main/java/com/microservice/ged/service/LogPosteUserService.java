package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;

public interface LogPosteUserService {
	public Page<LogPosteUser> logUser(String iduser, int page, int size) throws Exception ;
	public Page<LogPosteUser> logPoste(Long postesId, int page, int size) throws Exception ;
	public Postes currentPosteOfUser(String iduser) throws Exception ;
	public String currentUserOfPoste(Long postesId) throws Exception ;
	public void add(Long postesId, String iduser) throws Exception ;

}
