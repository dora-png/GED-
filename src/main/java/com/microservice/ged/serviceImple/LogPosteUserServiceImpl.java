package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.LogPoste;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Users;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteRepo;
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
	
	
	@Autowired 
	LogPosteRepo logPosteRepo;

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
		if(userRepo.findByLogin(users.getLogin())==null) {
			throw new Exception("User with login "+users.getLogin()+" Dont exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			Appusers appusers = appUserRepo.findByLogin(posteName); 
			if(appusers !=null) {
				try {					
					if(logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste)==null) {//poste non occupe
						if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
							LogPoste logPoste = new LogPoste(
									"Update Poste "+poste.getName()+" change user",
									appusers.getLogin(),
									appusers.getName(),
									poste.getName(),
									"POSTE");
							LogPoste logPoste1 = new LogPoste(
									"Update User "+users.getLogin()+" change poste",
									appusers.getLogin(),
									appusers.getName(),
									users.getName(),
									"USER");
							LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
							logPosteUserRepo.save(logPosteUser);
							logPosteRepo.save(logPoste);
							logPosteRepo.save(logPoste1);							
						}else {//user avec poste
							LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
							LogPoste logPoste = new LogPoste(
									"Update Poste "+logPosteUsers.getPosteId().getName()+" End",
									appusers.getLogin(),
									appusers.getName(),
									logPosteUsers.getPosteId().getName(),
									"POSTE");
							LogPoste logPoste1 = new LogPoste(
									"Update Poste "+poste.getName()+" Change user",
									appusers.getLogin(),
									appusers.getName(),
									poste.getName(),
									"POSTE");
							LogPoste logPoste2 = new LogPoste(
									"Update User "+users.getLogin()+" end",
									appusers.getLogin(),
									appusers.getName(),
									users.getName(),
									"USER");
							LogPoste logPoste3 = new LogPoste(
									"Update User "+users.getLogin()+" Change poste",
									appusers.getLogin(),
									appusers.getName(),
									users.getName(),
									"USER");
							logPosteUsers.setDateFin(new Date());
							LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
							logPosteUserRepo.save(logPosteUsers);
							logPosteUserRepo.save(logPosteUser);
							logPosteRepo.save(logPoste);
							logPosteRepo.save(logPoste1);
							logPosteRepo.save(logPoste2);
							logPosteRepo.save(logPoste3);
							
						}
					}else {//poste occupe
						if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
							LogPosteUser logPosteUsers = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
							LogPoste logPoste = new LogPoste(
									"Update Poste "+poste.getName()+" End",
									appusers.getLogin(),
									appusers.getName(),
									poste.getName(),
									"POSTE");
							LogPoste logPoste1 = new LogPoste(
									"Update User "+logPosteUsers.getUserId().getLogin()+" End",
									appusers.getLogin(),
									appusers.getName(),
									logPosteUsers.getUserId().getName(),
									"USER");
							LogPoste logPoste2 = new LogPoste(
									"Update Poste "+poste.getName()+" Change",
									appusers.getLogin(),
									appusers.getName(),
									poste.getName(),
									"POSTE");
							LogPoste logPoste3 = new LogPoste(
									"Update User "+users.getLogin()+" Change poste",
									appusers.getLogin(),
									appusers.getName(),
									users.getName(),
									"USER");
							logPosteUsers.setDateFin(new Date());
							LogPosteUser logPosteUser = new LogPosteUser("AFFECTATION", poste, users);
							logPosteUserRepo.save(logPosteUsers);
							logPosteUserRepo.save(logPosteUser);
							logPosteRepo.save(logPoste);
							logPosteRepo.save(logPoste1);
							logPosteRepo.save(logPoste2);
							logPosteRepo.save(logPoste3);							
						}else {//user avec poste
							LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
							LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
							///////////////////////////////////
							LogPoste logPoste = new LogPoste(
									"Update Poste "+logPosteUsers.getPosteId().getName()+" End",
									appusers.getLogin(),
									appusers.getName(),
									logPosteUsers.getPosteId().getName(),
									"POSTE");
							LogPoste logPoste1 = new LogPoste(
									"Update User "+users.getName()+" End",
									appusers.getLogin(),
									appusers.getName(),
									users.getName(),
									"USER");
							////////////////////////////////
							LogPoste logPoste2 = new LogPoste(
									"Update Poste "+poste.getName()+" End",
									appusers.getLogin(),
									appusers.getName(),
									poste.getName(),
									"POSTE");
							LogPoste logPoste3 = new LogPoste(
									"Update User "+logPosteUser.getUserId().getName()+" End",
									appusers.getLogin(),
									appusers.getName(),
									logPosteUser.getUserId().getName(),
									"USER");
							//////////////////////////////////////////////////////////
							LogPoste logPoste4 = new LogPoste(
									"Update User "+users.getName()+" Change Poste",
									appusers.getLogin(),
									appusers.getName(),
									users.getName(),
									"USER");
							LogPoste logPoste5 = new LogPoste(
									"Update Poste "+poste.getName()+" Change user",
									appusers.getLogin(),
									appusers.getName(),
									poste.getName(),
									"POSTE");
							
							logPosteUsers.setDateFin(new Date());
							logPosteUser.setDateFin(new Date());
							LogPosteUser newlogPosteUser = new LogPosteUser("AFFECTATION", poste, users);
							logPosteUserRepo.save(logPosteUsers);
							logPosteRepo.save(logPoste);
							logPosteRepo.save(logPoste1);
							logPosteUserRepo.save(logPosteUser);
							logPosteRepo.save(logPoste2);
							logPosteRepo.save(logPoste3);
							logPosteUserRepo.save(newlogPosteUser);
							logPosteRepo.save(logPoste4);
							logPosteRepo.save(logPoste5);
							
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
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="UPOSTE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {					
							if(logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste)==null) {//poste non occupe
								if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
									LogPoste logPoste = new LogPoste(
											"Update Poste "+poste.getName()+" change user",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											poste.getName(),
											"POSTE");
									LogPoste logPoste1 = new LogPoste(
											"Update User "+users.getLogin()+" change poste",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											users.getName(),
											"USER");
									LogPosteUser logPosteUser1 = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUser1);
									logPosteRepo.save(logPoste);
									logPosteRepo.save(logPoste1);							
								}else {//user avec poste
									LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
									LogPoste logPoste = new LogPoste(
											"Update Poste "+logPosteUsers.getPosteId().getName()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											logPosteUsers.getPosteId().getName(),
											"POSTE");
									LogPoste logPoste1 = new LogPoste(
											"Update Poste "+poste.getName()+" Change user",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											poste.getName(),
											"POSTE");
									LogPoste logPoste2 = new LogPoste(
											"Update User "+users.getLogin()+" end",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											users.getName(),
											"USER");
									LogPoste logPoste3 = new LogPoste(
											"Update User "+users.getLogin()+" Change poste",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											users.getName(),
											"USER");
									logPosteUsers.setDateFin(new Date());
									LogPosteUser logPosteUser1 = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUsers);
									logPosteUserRepo.save(logPosteUser1);
									logPosteRepo.save(logPoste);
									logPosteRepo.save(logPoste1);
									logPosteRepo.save(logPoste2);
									logPosteRepo.save(logPoste3);
									
								}
							}else {//poste occupe
								if(logPosteUserRepo.findByUserIdAndDateFinIsNull(users)==null) {//user sans poste
									LogPosteUser logPosteUsers = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
									LogPoste logPoste = new LogPoste(
											"Update Poste "+poste.getName()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											poste.getName(),
											"POSTE");
									LogPoste logPoste1 = new LogPoste(
											"Update User "+logPosteUsers.getUserId().getLogin()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											logPosteUsers.getUserId().getName(),
											"USER");
									LogPoste logPoste2 = new LogPoste(
											"Update Poste "+poste.getName()+" Change",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											poste.getName(),
											"POSTE");
									LogPoste logPoste3 = new LogPoste(
											"Update User "+users.getLogin()+" Change poste",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											users.getName(),
											"USER");
									logPosteUsers.setDateFin(new Date());
									LogPosteUser logPosteUser1 = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUsers);
									logPosteUserRepo.save(logPosteUser1);
									logPosteRepo.save(logPoste);
									logPosteRepo.save(logPoste1);
									logPosteRepo.save(logPoste2);
									logPosteRepo.save(logPoste3);							
								}else {//user avec poste
									LogPosteUser logPosteUsers = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
									LogPosteUser logPosteUser1 = logPosteUserRepo.findByPosteIdAndDateFinIsNull(poste);
									///////////////////////////////////
									LogPoste logPoste = new LogPoste(
											"Update Poste "+logPosteUsers.getPosteId().getName()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											logPosteUsers.getPosteId().getName(),
											"POSTE");
									LogPoste logPoste1 = new LogPoste(
											"Update User "+users.getName()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											users.getName(),
											"USER");
									////////////////////////////////
									LogPoste logPoste2 = new LogPoste(
											"Update Poste "+poste.getName()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											poste.getName(),
											"POSTE");
									LogPoste logPoste3 = new LogPoste(
											"Update User "+logPosteUser.getUserId().getName()+" End",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											logPosteUser.getUserId().getName(),
											"USER");
									//////////////////////////////////////////////////////////
									LogPoste logPoste4 = new LogPoste(
											"Update User "+users.getName()+" Change Poste",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											users.getName(),
											"USER");
									LogPoste logPoste5 = new LogPoste(
											"Update Poste "+poste.getName()+" Change user",
											logPosteUser.getUserId().getLogin(),
											logPosteUser.getPosteId().getName(),
											poste.getName(),
											"POSTE");
									
									logPosteUsers.setDateFin(new Date());
									logPosteUser1.setDateFin(new Date());
									LogPosteUser newlogPosteUser = new LogPosteUser("AFFECTATION", poste, users);
									logPosteUserRepo.save(logPosteUsers);
									logPosteRepo.save(logPoste);
									logPosteRepo.save(logPoste1);
									logPosteUserRepo.save(logPosteUser1);
									logPosteRepo.save(logPoste2);
									logPosteRepo.save(logPoste3);
									logPosteUserRepo.save(newlogPosteUser);
									logPosteRepo.save(logPoste4);
									logPosteRepo.save(logPoste5);
									
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
