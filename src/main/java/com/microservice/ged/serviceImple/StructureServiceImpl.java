package com.microservice.ged.serviceImple;

import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.StructureRepo;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.utils.OrganigramSystem;

@Service
@Transactional
public class StructureServiceImpl implements StructureService {

	@Autowired
	private StructureRepo structureRepo;

	@Autowired
	private PosteRepo posteRepo;


	@Override
	public void add(Structures structure) throws Exception {
		if(structureRepo.findByName(structure.getName())!=null) {
			throw new Exception("Structure with name "+structure.getName()+" already exist");
		}
		if(structureRepo.findBySigle(structure.getSigle())!=null) {
			throw new Exception("Structure with sigle "+structure.getSigle()+" already exist");
		}
		if(structureRepo.findByStructureSuperieurIsNullAndActiveTrue()!=null) {
			throw new Exception("Operation Impossible");
		}
		try {
			structure.setDateCreation(null);
			structure.setActive(true);
			structure.setPostes(null);
			structure.setProfiles(null);
			structure.setSousStructure(null);
			structure.setStructureSuperieur(null);
			structureRepo.save(structure);
		} catch (Exception e) {
			throw new Exception("Error while create Structure");
		}
	}

	@Override
	public Page<Structures> findAll(int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return structureRepo.findByActiveFalse(PageRequest.of(page, size,Sort.by("idstructure").descending()));				
			case 1:
				return structureRepo.findByActiveTrue(PageRequest.of(page, size,Sort.by("idstructure").descending()));
			case 2:
				return structureRepo.findAll(PageRequest.of(page, size, Sort.by("idstructure").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<Structures> searchByName(String name, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return structureRepo.findByNameContainingAndActiveFalse(name, PageRequest.of(page, size, Sort.by("idstructure").descending()));				
			case 1:
				return structureRepo.findByNameContainingAndActiveTrue(name, PageRequest.of(page, size, Sort.by("idstructure").descending()));
			case 2:
				return structureRepo.findByNameContaining(name, PageRequest.of(page, size, Sort.by("idstructure").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<Structures> searchBySigle(String sigle, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return structureRepo.findBySigleContainingAndActiveFalse(sigle, PageRequest.of(page, size, Sort.by("idstructure").descending()));				
			case 1:
				return structureRepo.findBySigleContainingAndActiveTrue(sigle, PageRequest.of(page, size, Sort.by("idstructure").descending()));
			case 2:
				return structureRepo.findBySigleContaining(sigle, PageRequest.of(page, size, Sort.by("idstructure").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public void delete(Long structureId) throws Exception {
		Structures structure = structureRepo.findByIdstructure(structureId);
		if(structure==null) {
			throw new Exception("Structure not exist");
		}
		try {
			if(structure.getStructureSuperieur() != null) {
				structure.setActive(false);
				structureRepo.save(structure);
			}else{
				throw new Exception("Error while delete");
			}
			
		} catch (Exception e) {
			throw new Exception("Error while delete");
		}

	}

	@Override
	public Structures findByName(String name) throws Exception {
		return structureRepo.findByName(name);
	}

	@Override
	public Structures findBySigle(String sigle) throws Exception {
		return structureRepo.findBySigle(sigle);
	}

	@Override
	public void addPosteToStructures(Structures structure, Postes poste) throws Exception {
		if(structureRepo.findByName(structure.getName())==null) {
			throw new Exception("Unknow Structure "+structure.getName());	
		}
		if(structureRepo.findBySigle(structure.getSigle())==null) {
			throw new Exception("Unknow Structure "+structure.getName());	
		}
		if(posteRepo.findByNameAndActiveTrue(poste.getName())==null) {
			throw new Exception("Unknow Poste "+poste.getName());	
		}
		try {
			poste.setStructure(structure);
			structure.getPostes().add(poste);
			structureRepo.save(structure);
			posteRepo.save(poste);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}

	}

	@Override
	public void removePosteToStructures(String posteName, String structureName) throws Exception {
		Postes postes = posteRepo.findByNameAndActiveTrue(posteName);
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
	public void addSubStructures(Structures subStructures) throws Exception {
		Structures supStructures = subStructures.getStructureSuperieur();
		if(structureRepo.findByIdstructure(supStructures.getIdstructure())==null) {
			throw new Exception("Error while create sub-structure");
		}
		if(structureRepo.findByName(subStructures.getName())!=null) {
			throw new Exception("Error: Already exist");
		}
		if(structureRepo.findBySigle(subStructures.getSigle())!=null) {
			throw new Exception("Error: Already exist");
		}
		supStructures = structureRepo.findByIdstructure(supStructures.getIdstructure());
		subStructures.setStructureSuperieur(supStructures);
		subStructures = structureRepo.save(subStructures);
		supStructures.getSousStructure().add(subStructures);
		structureRepo.save(supStructures);
	}

	@Override
	public void removeSubStructures(Structures supStructures) throws Exception {
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
		return structureRepo.findByIdstructure(id);
	}

	@Override
	public OrganigramSystem ogranigramme() throws Exception {
		Structures structures = structureRepo.findByStructureSuperieurIsNullAndActiveTrue();
		if(structures==null) {
			return null;
		}else {
			OrganigramSystem organigramSystem = sousStructureOrganigrame(structures);	
			return organigramSystem;			
		}
	}
	
	private OrganigramSystem sousStructureOrganigrame(Structures structures){
		OrganigramSystem organigramSystem = new OrganigramSystem();
		organigramSystem.setName(structures.getSigle().toUpperCase());
		organigramSystem.setTitle(structures.getName());
		organigramSystem.setId(structures.getIdstructure());
		if(structures.getSousStructure().isEmpty()) {
			organigramSystem.setChilds(null);				
		}else {
			structures.getSousStructure().forEach(
					(structure)->{						
						organigramSystem.getChilds().add(sousStructureOrganigrame(structure));						
					}
				);	
		}		
		return organigramSystem;
	}

	@Override
	public void update(Structures structures) throws Exception {
		if(structureRepo.findByIdstructure(structures.getIdstructure())==null) {
			throw new Exception("Structure with Id "+structures.getIdstructure()+" not exist");
		}
		if(structureRepo.findByNameAndActiveTrue(structures.getName())!=null) {
			if(structureRepo.findByNameAndActiveTrue(structures.getName()).getIdstructure() != structures.getIdstructure()) {
				throw new Exception("Structure with name "+structures.getName()+" already exist");
			}
		}
		if(structureRepo.findBySigleAndActiveTrue(structures.getSigle())==null) {
			if(structureRepo.findBySigleAndActiveTrue(structures.getSigle()).getIdstructure() != structures.getIdstructure()) {
				throw new Exception("Structure with Sigle "+structures.getSigle()+" already exist");
			}
		}
		Structures structure = structureRepo.findByIdstructure(structures.getIdstructure());
		structure.setName(structures.getName());
		structure.setSigle(structures.getSigle());
		structure.setColor(structures.getColor());
		structure.setDescription(structures.getDescription());
		if(structure.getStructureSuperieur()!=null)
			structure.setActive(structures.isActive());
		try {
			structureRepo.save(structure);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}


}













