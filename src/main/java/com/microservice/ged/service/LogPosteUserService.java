package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;

public interface LogPosteUserService {
	public Page<LogPosteUser> logUser(Long iduser, int page, int size) throws Exception ;
	public Page<LogPosteUser> logPoste(Long postesId, int page, int size) throws Exception ;
	public Postes currentPosteOfUser(Long iduser) throws Exception ;
	public Profiles currentUserOfPoste(Long postesId) throws Exception ;
	public void add(Long postesId, Long iduser) throws Exception ;

}
