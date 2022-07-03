package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Users;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.UserRepo;
import com.microservice.ged.service.LogPosteUserService;

@Service
@Transactional
public class LogPosteUserServiceImpl implements LogPosteUserService {
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	AppUserRepo appUserRepo; 
	

	@Override
	public Page<LogPosteUser> logUser(Users users, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteUserRepo.findByUserId(users, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPosteUser> logPoste(Postes postes, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return  logPosteUserRepo.findByPosteId(postes, PageRequest.of(page, size));
	}

	@Override
	public Postes currentPosteOfUser(Users users) throws Exception {
		// TODO Auto-generated method stub
		LogPosteUser logPosteUser = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
		if(logPosteUser==null){
			throw new Exception("User with login "+users.getUsername()+" don't exist");			
		}
		return logPosteUser.getPosteId();
	}
	
	@Override
	public Users currentUserOfPoste(Postes postes) throws Exception {
		// TODO Auto-generated method stub
		LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
		if(logPosteUser==null){
			throw new Exception("Poste "+postes.getName()+" don't Have user");			
		}
		return logPosteUser.getUserId();
	}

	@Override
	public void add(Postes poste, Users users) throws Exception {
		if(logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste)==null) {//poste non occupe
			if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
				LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
				logPosteUserRepo.save(logPosteUser);						
			}else {//user avec poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);				
				logPosteUsers.setDateFin(new Date());
				LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
				logPosteUserRepo.save(logPosteUsers);
				logPosteUserRepo.save(logPosteUser);
				
			}
		}else {//poste occupe
			if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
			
				logPosteUsers.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUsers);
				LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
				logPosteUserRepo.save(logPosteUser);							
			}else {//user avec poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
				LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
				///////////////////////////////////
				logPosteUsers.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUsers);
				logPosteUser.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUser);
				LogPosteUser newlogPosteUser = new LogPosteUser("AFFECTATION", poste, users);
				logPosteUserRepo.save(newlogPosteUser);				
			}
			
		}
	}
}
