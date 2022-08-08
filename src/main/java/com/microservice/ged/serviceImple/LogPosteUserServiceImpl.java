package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteServiceBasic;

@Service
@Transactional
public class LogPosteUserServiceImpl implements LogPosteUserService {
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired
	AppUserService appUserService;
	
	@Autowired
	PosteServiceBasic posteServiceBasic;
	

	@Override
	public Page<LogPosteUser> logUser(String iduser, int page, int size) throws Exception {
		String appUsers =  appUserService.findUserByName(iduser);
		return logPosteUserRepo.findByLdaplogin(appUsers, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPosteUser> logPoste(Long postesId, int page, int size) throws Exception {
		Postes postes = posteServiceBasic.findPosteById(postesId);
		return  logPosteUserRepo.findByPosteId(postes, PageRequest.of(page, size));
	}

	@Override
	public Postes currentPosteOfUser(String iduser) throws Exception {
		String appUsers = appUserService.findUserByName(iduser);
		LogPosteUser logPosteUser = logPosteUserRepo.findByLdaploginAndDateFinIsNull(appUsers);
		if(logPosteUser==null){
			return null ;	
		}
		return logPosteUser.getPosteId();
	}
	
	@Override
	public void add(Long postesId, String iduser) throws Exception {
		Postes poste = posteServiceBasic.findPosteById(postesId);
		String users = appUserService.findUserByName(iduser);
		if(logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste)==null) {//poste non occupe
			if(logPosteUserRepo.findByLdaploginAndDateFinIsNull(users)==null) {//user sans poste
				LogPosteUser logPosteUser = new LogPosteUser(poste, users);
				logPosteUserRepo.save(logPosteUser);						
			}else {//user avec poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByLdaploginAndDateFinIsNull(users);				
				logPosteUsers.setDateFin(new Date());
				LogPosteUser logPosteUser = new LogPosteUser(poste, users);
				logPosteUserRepo.save(logPosteUsers);
				logPosteUserRepo.save(logPosteUser);
				
			}
		}else {//poste occupe
			if(logPosteUserRepo.findByLdaploginAndDateFinIsNull(users)==null) {//user sans poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
			
				logPosteUsers.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUsers);
				LogPosteUser logPosteUser = new LogPosteUser(poste, users);
				logPosteUserRepo.save(logPosteUser);							
			}else {//user avec poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByLdaploginAndDateFinIsNull(users);
				LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
				///////////////////////////////////
				logPosteUsers.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUsers);
				logPosteUser.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUser);
				LogPosteUser newlogPosteUser = new LogPosteUser(poste, users);
				logPosteUserRepo.save(newlogPosteUser);				
			}
			
		}
	}

	@Override
	public String currentUserOfPoste(Long postesId) throws Exception {
		Postes postes = posteServiceBasic.findPosteById(postesId);
		LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
		if(logPosteUser==null){
			return null ;	
		}
		return logPosteUser.getLdaplogin();
	}
}
