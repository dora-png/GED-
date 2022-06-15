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
		if(logPosteUser!=null){
			return logPosteUser.getPosteId();
		} else{
			return null;
		}
	}

	@Override
	public void add(Postes poste, Users users, String posteName) throws Exception {
		if(posteRepo.findByIdposte(poste.getIdposte())==null) {
			throw new Exception("Postes "+poste.getName()+" Dont exist");
		}
		if(posteRepo.findByName(poste.getName())==null) {
			throw new Exception("Postes with name "+poste.getName()+" Dont exist");
		}
		if(userRepo.findByIduser(users.getIduser())==null) {
			throw new Exception("User "+users.getName()+" Dont exist");
		}
		if(userRepo.findByName(users.getName())==null) {
			throw new Exception("User with name "+users.getName()+" Dont exist");
		}
		if(userRepo.findByUsername(users.getUsername())==null) {
			throw new Exception("User with login "+users.getUsername()+" Dont exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			Appusers appusers = appUserRepo.findByLogin(posteName); 
			if(appusers !=null) {
				try {					
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
							LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
							logPosteUserRepo.save(logPosteUsers);
							logPosteUserRepo.save(logPosteUser);							
						}else {//user avec poste
							LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
							LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
							///////////////////////////////////
							logPosteUsers.setDateFin(new Date());
							logPosteUser.setDateFin(new Date());
							LogPosteUser newlogPosteUser = new LogPosteUser("AFFECTATION", poste, users);
							logPosteUserRepo.save(logPosteUsers);
							logPosteUserRepo.save(logPosteUser);
							logPosteUserRepo.save(newlogPosteUser);
							
						}
						
					}

				} catch (Exception e) {
					throw new Exception("Error while create");
				}
			}else {
				throw new Exception("Error while create");
			}
				
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=true;
					/*
					 * for(Roles roles : postes.getRoles()) { if(roles.getName()=="UPOSTE") hasRole
					 * = roles.isUpdate(); }
					 */
					if(hasRole) {
						try {					
							if(logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste)==null) {//poste non occupe
								if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
									
									LogPosteUser logPosteUser1 = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUser1);							
								}else {//user avec poste
									LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
									
									logPosteUsers.setDateFin(new Date());
									LogPosteUser logPosteUser1 = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUsers);
									logPosteUserRepo.save(logPosteUser1);
									
								}
							}else {//poste occupe
								if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
									LogPosteUser logPosteUsers = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
									
									logPosteUsers.setDateFin(new Date());
									LogPosteUser logPosteUser1 = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUsers);
									logPosteUserRepo.save(logPosteUser1);							
								}else {//user avec poste
									LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
									LogPosteUser logPosteUser1 = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
									///////////////////////////////////
									
									logPosteUsers.setDateFin(new Date());
									logPosteUser1.setDateFin(new Date());
									LogPosteUser newlogPosteUser = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUsers);
									logPosteUserRepo.save(logPosteUser1);
									logPosteUserRepo.save(newlogPosteUser);
									
								}
								
							}

						} catch (Exception e) {
							throw new Exception("Error while create");
						}
					}else {
						throw new Exception("You dont have this right create");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}
	}
}
