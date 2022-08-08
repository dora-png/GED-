package com.microservice.ged.serviceImple;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.StructureRepo;
import com.microservice.ged.utils.OrganigramStructure;
import com.microservice.ged.utils.OrganigramSystem;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.StructureService;

@Transactional
@Service
public class PosteServiceImpl implements PosteService{
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	LogPosteUserService logPosteUserService;
	
	@Autowired
	StructureService structureService;	
	
	@Override
	public Page<Postes> findAllPoste(int page, int size) throws Exception {
		return posteRepo.findByActiveTrue(PageRequest.of(page, size));
	}
	
	@Override
	public Page<Postes> searchPosteByName(String titre, int page, int size) throws Exception {
		return posteRepo.findByNameContainingAndActiveTrue(titre, PageRequest.of(page, size));
	}
	
	@Override
	public Postes findPosteByName(String name) throws Exception {
		return posteRepo.findByNameAndActiveTrue(name);
	}
	
	@Override
	public void updatePosteName(Long idPoste, String name) throws Exception {
		Postes poste = posteRepo.findByIdposteAndActiveTrue(idPoste);
		if(poste == null) {
			throw new Exception("Poste not exist");
		}
		poste.setName(name);
		posteRepo.save(poste);
	}
	
	@Override
	public void addPoste(Postes poste) throws Exception {
		poste.setDateCreation(null);
		poste.setIdposte(null);
		if(poste.getStructure()==null) {
			throw new Exception("Unknow structure to this Poste");
		}
		posteRepo.save(poste);
		
	}
	
	@Override
	public void updatePosteStatus(long id) throws Exception {
		Postes poste = posteRepo.findByIdposteAndActiveTrue(id);
		if(poste==null) {
			throw new Exception("Poste not exist");
		}
		poste.setActive(!poste.isActive());
		posteRepo.save(poste);
	}
			
	@Override
	public void addSubPoste(Postes subPostes) throws Exception {
		Postes supPostes = subPostes.getPosteSuperieur();
		if(posteRepo.findByIdposteAndActiveTrue(supPostes.getIdposte())==null) {
			throw new Exception("Error while create sub-structure");
		}
		supPostes = posteRepo.findByIdposteAndActiveTrue(supPostes.getIdposte());
		subPostes.setPosteSuperieur(supPostes);
		subPostes = posteRepo.save(subPostes);
		supPostes.getPosteSubalterne().add(subPostes);
		posteRepo.save(supPostes);
	}
	
	@Override
	public Page<Postes> findAllStructurePoste(Structures structures, int page, int size) throws Exception {
		return posteRepo.findByStructureAndActiveTrue(structures, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}
	
	@Override
	public OrganigramStructure ogranigramme(Long id) throws Exception {
		Structures structures = structureService.findByIdStructure(id);
		if(structures==null) {
			throw new Exception(" Error while get organigramme exist");
		}
		Postes postes = posteRepo.findByStructureAndPosteSuperieurIsNullAndActiveTrue(structures);
		if(postes==null) {
			return null;
		}else {
			OrganigramStructure organigramStructure = this.getUserInPoste(postes);	
			return organigramStructure;			
		}
	}
	
	private OrganigramStructure getUserInPoste(Postes postes) {
		OrganigramStructure organigramStructure = new OrganigramStructure();
		organigramStructure.setName(postes.getName());
		String users = null;
		try {
			users = logPosteUserService.currentUserOfPoste(postes.getIdposte());
			if(users != null){
				organigramStructure.setTitle(users);
			} else{
				organigramStructure.setTitle("Emplty");
			}
		} catch (Exception e) {
			organigramStructure.setTitle("Emplty");
		} 		
		if(postes.getPosteSubalterne().isEmpty()) {
			organigramStructure.setChilds(null);				
		}else {
			postes.getPosteSubalterne().forEach(
					(poste)->{						
						organigramStructure.getChilds().add(getUserInPoste(poste));						
					}
				);	
		}		
		return organigramStructure;
	}

}


