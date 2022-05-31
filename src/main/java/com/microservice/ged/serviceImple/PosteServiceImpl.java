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
import com.microservice.ged.beans.Structures;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.utils.PosteRoleBean;
import com.microservice.ged.service.PosteService;

@Transactional
@Service
public class PosteServiceImpl implements PosteService{
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	private RolesRepo rolesRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;
	
	
	@Override
	public Page<Postes> findAll(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public Page<Postes> searchByName(String titre, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByNameLike(titre, PageRequest.of(page, size));
	}
	
	@Override
	public Page<Postes> findByNiveau(Integer niveau, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByNiveau(niveau, PageRequest.of(page, size));
	}
	
	@Override
	public Postes findById(long id) throws Exception {
		// TODO Auto-generated method stub
		Postes poste = posteRepo.findById(id).orElseThrow(() -> new Exception(""));

		return poste;
	}
	
	@Override
	public Postes findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByName(name);
	}
	
	@Override
	public void update(Postes poste, String posteName) throws Exception {
		if(posteRepo.findByIdposte(poste.getIdposte())==null) {
			throw new Exception("Poste with name "+poste.getName()+" not exist");
		}
		if(posteRepo.findByName(poste.getName())!=null) {
			throw new Exception("Poste with name "+poste.getName()+" already exist");
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
						if(roles.getName()=="UPOSTE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update Poste "+poste.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								poste.getName(),
								"POSTE");
							posteRepo.save(poste);
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
	
	@Override
	public void add(Postes poste, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(posteRepo.findByName(poste.getName())!=null) {
			throw new Exception("Poste with name "+poste.getName()+" already exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			Appusers appusers = appUserRepo.findByLogin(posteName); 
			if(appusers !=null) {
				try {
					LogPoste logPoste = new LogPoste(
							new Date(),
							"Creation poste "+poste.getName(),
							appusers.getLogin(),
							appusers.getName(),
							poste.getName(),
							"POSTE");
					posteRepo.save(poste);
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
						if(roles.getName()=="CPOSTE")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Create poste "+poste.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								poste.getName(),
								"POSTE");
							posteRepo.save(poste);
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
	public void delete(long id, String posteName) throws Exception {
		// TODO Auto-generated method stub
		Postes poste = posteRepo.findByIdposte(id);
		if(poste==null) {
			throw new Exception("Poste not exist");
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
						if(roles.getName()=="DPOSTE")
							hasRole = roles.isDelete();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Delete Poste "+poste.getDescription(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								poste.getName(),
								"POSTE");
							posteRepo.delete(poste);
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
	
	@Override
	public void addRolePoste(PosteRoleBean posterolebean, String posteName) throws Exception {
		Postes poste = this.posteRepo.findByIdposte(posterolebean.getPoste());
		Roles role = this.rolesRepo.findByName(posterolebean.getRole());
		if(poste==null) {
			throw new Exception("Poste not exist");
		}
		if(role==null) {
			throw new Exception("Role not exist");
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
						if(roles.getName()=="RROLE")
							hasRole = roles.isRead();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update Poste "+poste.getName()+" add rule "+role.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								poste.getName(),
								"ROLE");
							poste.getRoles().add(role);
							posteRepo.save(poste);
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
	
	@Override
	public void addSubPoste(String posteName,Postes supPostes) throws Exception {
		// TODO Auto-generated method stub
		Postes subPostes = supPostes.getPosteSubalterne().get(0);
		if(supPostes.getName()==subPostes.getName()) {
			throw new Exception("Error cant do this operation");
		}
		if(supPostes.getIdposte()==subPostes.getIdposte()) {
			throw new Exception("Error cant do this operation");
		}
		if(supPostes.getNiveau()>=subPostes.getNiveau()) {
			throw new Exception("Error cant do this operation");
		}
		if(posteRepo.findByName(supPostes.getName())==null) {
			throw new Exception("Poste "+supPostes.getName()+" dont exist");
		}
		if(posteRepo.findByIdposte(supPostes.getIdposte())==null) {
			throw new Exception("Poste "+supPostes.getName()+" dont exist");
		}
		if(posteRepo.findByName(subPostes.getName())==null) {
			throw new Exception("Poste "+subPostes.getName()+" dont exist");
		}
		if(posteRepo.findByIdposte(subPostes.getIdposte())==null) {
			throw new Exception("Poste "+subPostes.getName()+" dont exist");
		}
		Postes postes = posteRepo.findByName(posteName);
		if(postes == null) {
			throw new Exception("Unknow Poste "+posteName);
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
							LogPoste logPoste = new LogPoste(
									new Date(),
									"Update Postes "+supPostes.getName()+" (add SubPostes) ",
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									supPostes.getName(),
									"POSTE");
							LogPoste logPoste1 = new LogPoste(
								new Date(),
								"Update Postes "+subPostes.getName()+" (set SupPostes) ",
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								subPostes.getName(),
								"POSTE");
							subPostes.setNiveau(supPostes.getNiveau()+1);
							supPostes.getPosteSubalterne().add(subPostes);
							subPostes.setPosteSuperieur(supPostes);
							posteRepo.save(subPostes);
							posteRepo.save(supPostes);
							logPosteRepo.save(logPoste);
							logPosteRepo.save(logPoste1);
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
	
	@Override
	public void removeRoleToPoste(PosteRoleBean posterolebean, String posteName) throws Exception {
		Postes poste = this.posteRepo.findByIdposte(posterolebean.getPoste());
		Roles role = this.rolesRepo.findByName(posterolebean.getRole());
		if(poste==null) {
			throw new Exception("Poste not exist");
		}
		if(role==null) {
			throw new Exception("Role not exist");
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
						if(roles.getName()=="RROLE")
							hasRole = roles.isRead();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update Poste "+poste.getName()+" add rule "+role.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								poste.getName(),
								"ROLE");
							poste.getRoles().remove(role);
							posteRepo.save(poste);
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

	@Override
	public void removeSubPoste(String posteName,String supPoste, String subPoste) throws Exception {
		// TODO Auto-generated method stub
		Postes supPostes = posteRepo.findByName(supPoste);
		Postes subPostes = posteRepo.findByName(subPoste);
		if(supPostes==null) {
			throw new Exception("Poste "+supPoste+" dont exist");
		}
		if(subPostes==null) {
			throw new Exception("Poste "+subPoste+" dont exist");
		}		
		
		if(supPostes.getName()==subPostes.getName()) {
			throw new Exception("Error cant do this operation");
		}
		if(supPostes.getIdposte()==subPostes.getIdposte()) {
			throw new Exception("Error cant do this operation");
		}
		if(supPostes.getNiveau()<=subPostes.getNiveau()) {
			throw new Exception("Error cant do this operation");
		}
		Postes postes = posteRepo.findByName(posteName);
		if(postes == null) {
			throw new Exception("Unknow Poste "+posteName);
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
							LogPoste logPoste = new LogPoste(
									new Date(),
									"Update Postes "+supPostes.getName()+" (remove SubPostes) ",
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									supPostes.getName(),
									"POSTE");
							LogPoste logPoste1 = new LogPoste(
								new Date(),
								"Update Postes "+subPostes.getName()+" (set SupPostes) ",
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								subPostes.getName(),
								"POSTE");
							subPostes.setNiveau(supPostes.getNiveau());
							supPostes.getPosteSubalterne().remove(subPostes);
							subPostes.setPosteSuperieur(null);
							posteRepo.save(subPostes);
							posteRepo.save(supPostes);
							logPosteRepo.save(logPoste);
							logPosteRepo.save(logPoste1);
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

	@Override
	public Page<Postes> searchByStructureAndNiveau(Integer niveau, Structures structures, int page, int size)
			throws Exception {
		return posteRepo.findByNiveauAndStructure(niveau, structures, PageRequest.of(page, size));
	}
	

}


