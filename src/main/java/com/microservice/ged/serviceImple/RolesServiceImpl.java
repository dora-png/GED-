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
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.service.RolesService;

@Transactional
@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesRepo rolesRepo;

	@Autowired
	PosteRepo posteRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;

	@Override
	public Page<Roles> findAll(String posteName, int page, int size) {
		// TODO Auto-generated method stub
		return rolesRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Roles> searchRole(String role, String posteName, int page, int size) {
		// TODO Auto-generated method stub
		return rolesRepo.findByNameLike(role, PageRequest.of(page, size));
	}
/*
	@Override
	public void add(Roles role, String posteName) throws Exception {
		if(rolesRepo.findByName(role.getName())!=null) {
			throw new Exception("Roles with name "+role.getName()+" already exist");			
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			Appusers appusers = appUserRepo.findByLogin(posteName); 
			if(appusers !=null) {
				try {
					LogPoste logPoste = new LogPoste(
							new Date(),
							"Creation structure "+role.getName(),
							appusers.getLogin(),
							appusers.getName(),
							role.getName(),
							"RULE");
					rolesRepo.save(role);
					logPosteRepo.save(logPoste);
				} catch (Exception e) {
					throw new Exception("Error while create Structure");
				}
			}else {
				throw new Exception("Error while create Structure");
			}
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.isCrole())
							hasRole = true;
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Create rule "+role.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								role.getName(),
								"RULE");
							rolesRepo.save(role);
							logPosteRepo.save(logPoste);
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

	@Override
	public void update(Roles role, String posteName) throws Exception {
		if(rolesRepo.findByIdroles(role.getIdroles())==null) {
			throw new Exception("Role with name "+role.getName()+" not exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.isUrole())
							hasRole = true;
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update Roles "+role.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								role.getName(),
								"ROLES");
							rolesRepo.save(role);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while update");
						}
					}else {
						throw new Exception("You dont have this right update");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}
	}

	@SuppressWarnings("null")
	@Override
	public void delete(String roleName, String posteName) throws Exception {
		Roles role = rolesRepo.findByName(roleName);
		if(role==null) {
			throw new Exception("Role not exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes !=null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.isDrole())
							hasRole = true;
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Delete Role "+role.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								role.getName(),
								"ROLE");
							rolesRepo.delete(role);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while delete");
						}
					}else {
						throw new Exception("You dont have this right delete");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}
	}
*/
	@Override
	public Roles findById(Long id, String posteName) {
		// TODO Auto-generated method stub
		return rolesRepo.findByIdroles(id);
	}

	@Override
	public Roles findByName(String name, String posteName) {
		// TODO Auto-generated method stub
		return rolesRepo.findByName(name);
	}

	
}
