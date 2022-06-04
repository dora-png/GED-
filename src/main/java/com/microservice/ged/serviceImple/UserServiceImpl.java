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
import com.microservice.ged.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;

	@Override
	public Page<Users> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return userRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Users> searchByName(String name, int page, int size) {
		// TODO Auto-generated method stub
		return userRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Users> searchByLogin(String login, int page, int size) {
		// TODO Auto-generated method stub
		return userRepo.findByLoginLike(login, PageRequest.of(page, size));
	}

	@Override
	public void add(Users users, String posteName) throws Exception {
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			Appusers appusers = appUserRepo.findByLogin(posteName); 
			if(appusers !=null) {
				try {
					LogPoste logPoste = new LogPoste(
							"Creation user "+users.getName(),
							appusers.getLogin(),
							appusers.getName(),
							users.getName(),
							"USER");
					userRepo.save(users);
					logPosteRepo.save(logPoste);
				} catch (Exception e) {
					throw new Exception("Error while create user");
				}
				
			}else {
				throw new Exception("Votre poste ne vous permet pas cette action");
			}
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="CUSER")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
									"Creation user "+users.getName(),
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									users.getName(),
									"USER");
							userRepo.save(users);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while create user");
						}
					}else {
						throw new Exception("You dont have this right create user");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
			
		}
		
	}

	@Override
	public void update(Users users, String posteName) throws Exception {
		// TODO Auto-generated method stub
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					if(users.getIduser()==logPosteUser.getUserId().getIduser()) {
						try {
							LogPoste logPoste = new LogPoste(
									"Update user "+users.getName(),
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									users.getName(),
									"USER");
							userRepo.save(users);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while update user");
						}							
					}else{
						boolean  hasRole=false;
						for(Roles roles : postes.getRoles()) {
							if(roles.getName()=="UUSER")
								hasRole = roles.isUpdate();							
						}
						if(hasRole) {
							try {
								LogPoste logPoste = new LogPoste(
										"Update user "+users.getName(),
										logPosteUser.getUserId().getLogin(),
										logPosteUser.getPosteId().getName(),
										users.getName(),
										"USER");
								userRepo.save(users);
								logPosteRepo.save(logPoste);
							} catch (Exception e) {
								throw new Exception("Error while update user");
							}
						}else {
							throw new Exception("You dont have this right create user");
						}
					}

				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}
	}

	@Override
	public String getPwd(Users users) throws Exception {
		// TODO Auto-generated method stub
		Users user = userRepo.findByIduser(users.getIduser());
		if(user==null) {
			throw new Exception("User dont exist");
		}
		
		return user.getPassword();
	}

	@Override
	public void changePwd(Users users, String newPdb) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Users users, String posteName) throws Exception {
		// TODO Auto-generated method stub
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="DUSER")
							hasRole = roles.isDelete();	
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
									"Delete user "+users.getName(),
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									users.getName(),
									"USER");
							userRepo.delete(users);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while delete user");
						}
					}else {
						throw new Exception("You dont have this right delete user");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}

	}

	@Override
	public Users findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow(() -> new Exception(""));
	}

	@Override
	public Users findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return userRepo.findByName(name);
	}

	@Override
	public Users findByLogin(String login) throws Exception {
		// TODO Auto-generated method stub
		return userRepo.findByLogin(login);
	}

}
