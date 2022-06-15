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
import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.repository.AppUserRepo;
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
					structureRepo.save(structure);
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
						structureRepo.save(structure);
					} catch (Exception e) {
						throw new Exception("Error while create Structure");
					}
					}else {
						throw new Exception("You dont have this right create");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
		}
	}

	@Override
	public Page<Structures> findAll(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findAll(PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	@Override
	public Page<Structures> searchByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findByNameContaining(name, PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	@Override
	public Page<Structures> searchBySigle(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findBySigleContaining(sigle, PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	@Override
	public void update(Structures structure, String posteName) throws Exception {
		if(structureRepo.findByIdstructure(structure.getIdstructure())==null) {
			throw new Exception("Structure with name "+structure.getName()+" not exist");
		}
		try {
			structureRepo.save(structure);
		} catch (Exception e) {
			throw new Exception("Error while update");
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
		try {
			structureRepo.save(structure);
		} catch (Exception e) {
			throw new Exception("Error while delete");
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
		try {
			postes.setStructure(structure);
			structure.getPostes().add(postes);
			structureRepo.save(structure);
			posteRepo.save(postes);
		} catch (Exception e) {
			throw new Exception("Error while update");
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
		try {
			postes.setStructure(null);
			structure.getPostes().remove(postes);
			structureRepo.save(structure);
			posteRepo.save(postes);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}

	}
	
	@Override
	public void addSubStructures(String posteName,Structures supStructures) throws Exception {
		if(supStructures.getSousStructure().isEmpty()) {
			throw new Exception("toto");
		}else if(supStructures.getSousStructure().size()==1) {
			Iterator<Structures> upstructureIterato = supStructures.getSousStructure().iterator();
			Structures subStructures = new Structures();
			while(upstructureIterato.hasNext()) {
				subStructures = upstructureIterato.next();
			}
			if(structureRepo.findByIdstructure(supStructures.getIdstructure())==null) {
				throw new Exception("Not exist");				
			}
			if(structureRepo.findByIdstructure(subStructures.getIdstructure())==null) {
				throw new Exception("Not exist");				
			}
			if(supStructures.getIdstructure()==subStructures.getIdstructure()) {
				throw new Exception("Error cant do this operation");
			}
			supStructures = structureRepo.findByIdstructure(supStructures.getIdstructure());
				try {
					supStructures.getSousStructure().add(subStructures);
					subStructures.setStructureSuperieur(supStructures);
					structureRepo.save(subStructures);
					structureRepo.save(supStructures);
				} catch (Exception e) {
					throw new Exception("Error while update");
				}	
		}else {
			throw new Exception("toto");
		}	
	}

	@Override
	public void removeSubStructures(String posteName,Structures supStructures) throws Exception {
		if(supStructures.getSousStructure().isEmpty()) {
			throw new Exception("toto");
		}else if(supStructures.getSousStructure().size()==1) {
			Iterator<Structures> upstructureIterato = supStructures.getSousStructure().iterator();
			Structures subStructures = new Structures();
			while(upstructureIterato.hasNext()) {
				subStructures = upstructureIterato.next();
			}
			if(structureRepo.findByIdstructure(supStructures.getIdstructure())==null) {
				throw new Exception("Not exist");				
			}
			if(structureRepo.findByIdstructure(subStructures.getIdstructure())==null) {
				throw new Exception("Not exist");				
			}
			if(supStructures.getIdstructure()==subStructures.getIdstructure()) {
				throw new Exception("Error cant do this operation");
			}
			try {
				supStructures.getSousStructure().remove(subStructures);
				subStructures.setStructureSuperieur(null);
				structureRepo.save(subStructures);
				structureRepo.save(supStructures);
			} catch (Exception e) {
				throw new Exception("Error while update");
			}
	
			
		}else {
			throw new Exception("toto");
		}		
	}

	@Override
	public Structures findByIdStructure(Long id) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findById(id).orElseThrow(() -> new Exception(""));
	}

	@Override
	public Page<Structures> structureUnUseListe(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findByStructureSuperieurIsNullAndSousStructureIsNull(PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	

}













