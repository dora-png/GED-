package com.microservice.ged.serviceImple;

import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.repository.StructureRepo;
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
	private StructureRepo structureRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	
	@Override
	public Page<Postes> findAll(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public Page<Postes> searchByName(String titre, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByNameContaining(titre, PageRequest.of(page, size));
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
		try {
			posteRepo.save(poste);
		} catch (Exception e) {
			throw new Exception("Error while update");
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
					poste.setNiveau(0);
					if(structureRepo.findByIdstructure(poste.getStructure().getIdstructure())==null) {
						throw new Exception("Error while create Structure");
					}else {
						posteRepo.save(poste);
					}
					
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
						try {
							poste.setNiveau(0);
							posteRepo.save(poste);
						} catch (Exception e) {
							throw new Exception("Error while create");
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
				
						try {
							posteRepo.delete(poste);
						} catch (Exception e) {
							throw new Exception("Error while delete");
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
		if(supPostes.getPosteSubalterne().isEmpty()) {
			throw new Exception("toto");
		}else if(supPostes.getPosteSubalterne().size()==1) {
			Iterator<Postes> upposteIterato = supPostes.getPosteSubalterne().iterator();
			Postes subPostes = new Postes();
			while(upposteIterato.hasNext()) {
				subPostes = upposteIterato.next();
			}
			subPostes = posteRepo.findByIdposte(subPostes.getIdposte());
			supPostes = posteRepo.findByIdposte(supPostes.getIdposte());
			if(supPostes.getIdposte()==subPostes.getIdposte()) {
				throw new Exception("Error cant do this operation");
			}
			if(supPostes.getIdposte()==subPostes.getIdposte()) {
				throw new Exception("Error cant do this operation");
			}
			Postes postes = posteRepo.findByName(posteName);
			if(postes == null) {
				throw new Exception("Unknow Poste "+posteName);
			}else {
				LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
				if(logPosteUser!=null) {
					if(logPosteUser.getUserId()!=null) {
							try {
								if(supPostes.getNiveau()==0) {
									supPostes.setNiveau(1);
								}
								subPostes.setNiveau(supPostes.getNiveau()+1);
								supPostes.getPosteSubalterne().add(subPostes);
								subPostes.setPosteSuperieur(supPostes);
								posteRepo.save(subPostes);
								posteRepo.save(supPostes);
							} catch (Exception e) {
								throw new Exception("Error while update");
							}
					}else {
						throw new Exception("Cet utilisateur ne peut effectuer cette action");
					}
				}else {
					throw new Exception("toto");
				}
			}		
		}else {
			throw new Exception("toto");
		}
	}
	
	@Override
	public void removeSubPoste(String posteName,Postes supPostes) throws Exception {
		if(supPostes.getPosteSubalterne().isEmpty()) {
			throw new Exception("toto");
		}else if(supPostes.getPosteSubalterne().size()==1) {
			Iterator<Postes> upposteIterato = supPostes.getPosteSubalterne().iterator();
			Postes subPostes = new Postes();
			while(upposteIterato.hasNext()) {
				subPostes = upposteIterato.next();
			}
			subPostes = posteRepo.findByIdposte(subPostes.getIdposte());
			supPostes = posteRepo.findByIdposte(supPostes.getIdposte());
			if(supPostes.getIdposte()==subPostes.getIdposte()) {
				throw new Exception("Error cant do this operation");
			}
			if(supPostes.getIdposte()==subPostes.getIdposte()) {
				throw new Exception("Error cant do this operation");
			}
			Postes postes = posteRepo.findByName(posteName);
			if(postes == null) {
				throw new Exception("Unknow Poste "+posteName);
			}else {
				LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
				if(logPosteUser!=null) {
					if(logPosteUser.getUserId()!=null) {
						boolean  hasRole=true;
						/*
						 * for(Roles roles : postes.getRoles()) { if(roles.getName()=="UPOSTE") hasRole
						 * = roles.isUpdate(); }
						 */
						if(hasRole && supPostes.getPosteSubalterne().contains(subPostes)) {
							try {
								subPostes.setNiveau(0);
								supPostes.getPosteSubalterne().remove(subPostes);
								subPostes.setPosteSuperieur(null);
								posteRepo.save(subPostes);
								posteRepo.save(supPostes);
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
		}else {
			throw new Exception("toto");
		}
	}

	@Override
	public Page<Postes> searchByStructureAndNiveau(Integer niveau, Structures structures, int page, int size)
			throws Exception {
		return posteRepo.findByNiveauAndStructure(niveau, structures, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}

	@Override
	public Page<Postes> findAllStructure(Structures structures, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByStructure(structures, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}

	

}


