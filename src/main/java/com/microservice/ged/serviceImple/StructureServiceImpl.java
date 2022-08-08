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
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Override
	public Structures findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Structures structure) throws Exception {
		if(structureRepo.findByNameAndActiveTrue(structure.getName())!=null) {
			throw new Exception("Structure with name "+structure.getName()+" already exist");
		}
		if(structureRepo.findBySigleAndActiveTrue(structure.getSigle())!=null) {
			throw new Exception("Structure with sigle "+structure.getSigle()+" already exist");
		}
		try {
			structureRepo.save(structure);
		} catch (Exception e) {
			throw new Exception("Error while create Structure");
		}
	}

	@Override
	public Page<Structures> findAll(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findByActiveTrue(PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	@Override
	public Page<Structures> searchByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findByNameContainingAndActiveTrue(name, PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	@Override
	public Page<Structures> searchBySigle(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findBySigleContainingAndActiveTrue(sigle, PageRequest.of(page, size, Sort.by("idstructure").descending()));
	}

	@Override
	public void update(Structures structure) throws Exception {
		if(structureRepo.findByIdstructureAndActiveTrue(structure.getIdstructure())==null) {
			throw new Exception("Structure with name "+structure.getName()+" not exist");
		}
		try {
			structureRepo.save(structure);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}

	@Override
	public void delete(Structures structure) throws Exception {
		// TODO Auto-generated method stub
		if(structureRepo.findByNameAndActiveTrue(structure.getName())==null) {
			throw new Exception("Structure with name "+structure.getName()+" not exist");
		}
		if(structureRepo.findBySigleAndActiveTrue(structure.getSigle())==null) {
			throw new Exception("Structure with sigle "+structure.getSigle()+" not exist");
		}
		if(structureRepo.findByIdstructureAndActiveTrue(structure.getIdstructure())==null) {
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
		return structureRepo.findByNameAndActiveTrue(name);
	}

	@Override
	public Structures findBySigle(String sigle) throws Exception {
		// TODO Auto-generated method stub
		return structureRepo.findBySigleAndActiveTrue(sigle);
	}

	@Override
	public void addPosteToStructures(Structures structure, Postes poste) throws Exception {
		if(structureRepo.findByNameAndActiveTrue(structure.getName())==null) {
			throw new Exception("Unknow Structure "+structure.getName());	
		}
		if(structureRepo.findBySigleAndActiveTrue(structure.getSigle())==null) {
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
		Structures structure = structureRepo.findByNameAndActiveTrue(structureName);
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
		if(structureRepo.findByIdstructureAndActiveTrue(supStructures.getIdstructure())==null) {
			throw new Exception("Error while create sub-structure");
		}
		if(structureRepo.findByNameAndActiveTrue(subStructures.getName())!=null) {
			throw new Exception("Error: Already exist");
		}
		if(structureRepo.findBySigleAndActiveTrue(subStructures.getSigle())!=null) {
			throw new Exception("Error: Already exist");
		}
		supStructures = structureRepo.findByIdstructureAndActiveTrue(supStructures.getIdstructure());
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
			if(structureRepo.findByIdstructureAndActiveTrue(supStructures.getIdstructure())==null) {
				throw new Exception("Not exist");				
			}
			if(structureRepo.findByIdstructureAndActiveTrue(subStructures.getIdstructure())==null) {
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
		return structureRepo.findByIdstructureAndActiveTrue(id);
	}

	@Override
	public OrganigramSystem ogranigramme() throws Exception {
		Structures structures = structureRepo.findByStructureSuperieurIsNullAndActiveTrue();
		if(structures==null) {
			return null;
		}else {
			OrganigramSystem organigramSystem = toto(structures);	
			return organigramSystem;			
		}
	}
	
	private OrganigramSystem toto(Structures structures){
		OrganigramSystem organigramSystem = new OrganigramSystem();
		organigramSystem.setName(structures.getSigle().toUpperCase());
		organigramSystem.setTitle(structures.getName());
		organigramSystem.setId(structures.getIdstructure());
		if(structures.getSousStructure().isEmpty()) {
			organigramSystem.setChilds(null);				
		}else {
			structures.getSousStructure().forEach(
					(structure)->{						
						organigramSystem.getChilds().add(toto(structure));						
					}
				);	
		}		
		return organigramSystem;
	}

	

}













