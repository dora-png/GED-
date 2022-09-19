package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteServiceBasic;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.ProfilesServiceBasic;

@Service
@Transactional
public class LogPosteUserServiceImpl implements LogPosteUserService {
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired
	ProfilesService profilesService;
	
	@Autowired
	ProfilesServiceBasic profilesServiceBasic;
	
	@Autowired
	PosteServiceBasic posteServiceBasic;
	

	@Override
	public Page<LogPosteUser> logUser(Long iduser, int page, int size) throws Exception {
		Profiles profiles =  profilesServiceBasic.findProfileById(iduser);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		return logPosteUserRepo.findByLdaplogin(profiles, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPosteUser> logPoste(Long postesId, int page, int size) throws Exception {
		Postes postes = posteServiceBasic.findPosteById(postesId);
		if(postes==null) {
			throw new Exception("postes id error");
		}
		return  logPosteUserRepo.findByPosteId(postes, PageRequest.of(page, size));
	}

	@Override
	public Postes currentPosteOfUser(Long iduser) throws Exception {
		Profiles profiles =  profilesServiceBasic.findProfileById(iduser);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		LogPosteUser logPosteUser = logPosteUserRepo.findByLdaploginAndDateFinIsNull(profiles);
		if(logPosteUser==null){
			return null ;	
		}
		return logPosteUser.getPosteId();
	}
	
	@Override
	public void add(Long postesId, Long iduser) throws Exception {
		System.err.println("eeeeeeeeeeeeee");
		Postes poste = posteServiceBasic.findPosteById(postesId);
		if(poste==null) {
			throw new Exception("postes id error");
		}
		Profiles profiles =  profilesServiceBasic.findProfileById(iduser);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		if(logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste)==null) {//poste non occupe

			System.err.println("poste non occupe");
			if(logPosteUserRepo.findByLdaploginAndDateFinIsNull(profiles)==null) {//user sans poste

				System.err.println("user sans poste");
				LogPosteUser logPosteUser = new LogPosteUser(poste, profiles);
				logPosteUserRepo.save(logPosteUser);						
			}else {//user avec poste
				System.err.println("user avec poste");
				LogPosteUser logPosteUsers = logPosteUserRepo.findByLdaploginAndDateFinIsNull(profiles);				
				logPosteUsers.setDateFin(new Date());
				LogPosteUser logPosteUser = new LogPosteUser(poste, profiles);
				logPosteUserRepo.save(logPosteUsers);
				logPosteUserRepo.save(logPosteUser);
				
			}
		}else {//poste occupe
			System.err.println("poste occupe");
			if(logPosteUserRepo.findByLdaploginAndDateFinIsNull(profiles)==null) {//user sans poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
			
				logPosteUsers.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUsers);
				LogPosteUser logPosteUser = new LogPosteUser(poste, profiles);
				logPosteUserRepo.save(logPosteUser);							
			}else {//user avec poste
				LogPosteUser logPosteUsers = logPosteUserRepo.findByLdaploginAndDateFinIsNull(profiles);
				LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
				///////////////////////////////////
				logPosteUsers.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUsers);
				logPosteUser.setDateFin(new Date());
				logPosteUserRepo.save(logPosteUser);
				LogPosteUser newlogPosteUser = new LogPosteUser(poste, profiles);
				logPosteUserRepo.save(newlogPosteUser);				
			}
			
		}
	}

	@Override
	public Profiles currentUserOfPoste(Long postesId) throws Exception {
		Postes postes = posteServiceBasic.findPosteById(postesId);
		if(postes==null) {
			throw new Exception("postes id error");
		}
		LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
		if(logPosteUser==null){
			return null ;	
		}
		return logPosteUser.getLdaplogin();
	}
}
