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
import com.microservice.ged.repository.StructureRepo;
import com.microservice.ged.service.StructureService;

@Service
@Transactional
public class StructureServiceImpl implements StructureService {

	@Autowired
	private StructureRepo structureRepo;

	@Autowired
	private PosteRepo posteRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;
	
	@Override
	public Structures findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Structures structure, String posteName) throws Exception {
		if(structureRepo.findByName(structure.getName())!=null) {
			throw new Exception("Structure with name "+structure.getName()+" already exist");
		}
		if(structureRepo.findBySigle(structure.getSigle())!=null) {
			throw new Exception("Structure with sigle "+structure.getSigle()+" already exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			Appusers appusers = appUserRepo.findByLogin(posteName); 
			if(appusers !=null) {
				try {
					LogPoste logPoste = new LogPoste(
							"Creation structure "+structure.getName(),
							appusers.getLogin(),
							appusers.getName(),
							structure.getName(),
							"STRUCTURE");
					structureRepo.save(structure);
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
						if(roles.getName()=="CSTRUCTURE")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Create Structure "+structure.getSigle(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								structure.getName(),
								"STRUCTURE");
							structureRepo.save(structure);
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
	public Page<Structures> findAll(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Structures> searchByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Structures> searchBySigle(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findBySigleLike(sigle, PageRequest.of(page, size));
	}

	@Override
	public void update(Structures structure, String posteName) throws Exception {
		if(structureRepo.findByIdstructure(structure.getIdstructure())==null) {
			throw new Exception("Structure with name "+structure.getName()+" not exist");
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
						if(roles.getName()=="USTRUCTURE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Update Structure "+structure.getSigle(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								structure.getName(),
								"STRUCTURE");
							structureRepo.save(structure);
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
	public void delete(Structures structure, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(structureRepo.findByName(structure.getName())==null) {
			throw new Exception("Structure with name "+structure.getName()+" not exist");
		}
		if(structureRepo.findBySigle(structure.getSigle())==null) {
			throw new Exception("Structure with sigle "+structure.getSigle()+" not exist");
		}
		if(structureRepo.findByIdstructure(structure.getIdstructure())==null) {
			throw new Exception("Structure with name "+structure.getName()+" not exist");
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
						if(roles.getName()=="DSTRUCTURE")
							hasRole = roles.isDelete();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Delete Structure "+structure.getSigle(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								structure.getName(),
								"STRUCTURE");
							structureRepo.save(structure);
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
	public Structures findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findByName(name);
	}

	@Override
	public Structures findBySigle(String sigle) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findBySigle(sigle);
	}

	@Override
	public void addPosteToStructures(String posteName,  Structures structure, Postes poste) throws Exception {
		// TODO Auto-generated method stub
		Postes postes = posteRepo.findByName(posteName);
		if(structureRepo.findByName(structure.getName())==null) {
			throw new Exception("Unknow Structure "+structure.getName());	
		}
		if(structureRepo.findBySigle(structure.getSigle())==null) {
			throw new Exception("Unknow Structure "+structure.getName());	
		}
		if(posteRepo.findByName(poste.getName())==null) {
			throw new Exception("Unknow Poste "+poste.getName());	
		}
		if(postes == null) {
			throw new Exception("Unknow Poste "+posteName+" dont have rulesqqq");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="USTRUCTURE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Update Structure "+structure.getSigle()+" (add poste) "+postes.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								structure.getName(),
								"STRUCTURE");
							postes.setStructure(structure);
							structure.getPostes().add(postes);
							structureRepo.save(structure);
							posteRepo.save(postes);
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
	public void removePosteToStructures(String posteName, String structureName) throws Exception {
		// TODO Auto-generated method stub
		Postes postes = posteRepo.findByName(posteName);
		Structures structure = structureRepo.findByName(structureName);
		if(structure == null) {
			throw new Exception("Unknow Structure "+structureName);
		}
		if(postes == null) {
			throw new Exception("Unknow Poste "+posteName);
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="USTRUCTURE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Update Structure "+structure.getSigle()+" (remove poste) "+postes.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								structure.getName(),
								"STRUCTURE");
							postes.setStructure(null);
							structure.getPostes().remove(postes);
							structureRepo.save(structure);
							posteRepo.save(postes);
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
	public void addSubStructures(String posteName,Structures supStructures, Structures subStructures) throws Exception {
		// TODO Auto-generated method stub
		if(supStructures.getName()==subStructures.getName()) {
			throw new Exception("Error cant do this operation");
		}
		if(supStructures.getIdstructure()==subStructures.getIdstructure()) {
			throw new Exception("Error cant do this operation");
		}
		if(supStructures.getLevel()<subStructures.getLevel()) {
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
						if(roles.getName()=="USTRUCTURE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
									"Update Structure "+supStructures.getSigle()+" (add SubStructures) ",
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									supStructures.getName(),
									"STRUCTURE");
							LogPoste logPoste1 = new LogPoste(
								"Update Structure "+subStructures.getSigle()+" (set SupStructures) ",
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								subStructures.getName(),
								"STRUCTURE");
							subStructures.setLevel(supStructures.getLevel()+1);
							supStructures.getSousStructure().add(subStructures);
							subStructures.setStructureSuperieur(supStructures);
							structureRepo.save(subStructures);
							structureRepo.save(supStructures);
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
	public void removeSubStructures(String posteName,Structures supStructures, Structures subStructures) throws Exception {
		// TODO Auto-generated method stub
		if(supStructures.getName()==subStructures.getName()) {
			throw new Exception("Error cant do this operation");
		}
		if(supStructures.getIdstructure()==subStructures.getIdstructure()) {
			throw new Exception("Error cant do this operation");
		}
		if(supStructures.getLevel()>=subStructures.getLevel()) {
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
						if(roles.getName()=="USTRUCTURE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
									"Update Structure "+supStructures.getSigle()+" (remove SubStructures) ",
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									supStructures.getName(),
									"STRUCTURE");
							LogPoste logPoste1 = new LogPoste(
								"Update Structure "+subStructures.getSigle()+" (set SupStructures) ",
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								subStructures.getName(),
								"STRUCTURE");

							subStructures.setLevel(supStructures.getLevel());
							supStructures.getSousStructure().remove(subStructures);
							subStructures.setStructureSuperieur(null);
							structureRepo.save(subStructures);
							structureRepo.save(supStructures);
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
	public Structures findByIdStructure(Long id) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findById(id).orElseThrow(() -> new Exception(""));
	}

	

}













