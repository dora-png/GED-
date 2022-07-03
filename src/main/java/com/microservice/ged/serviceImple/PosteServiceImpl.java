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

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.repository.StructureRepo;
import com.microservice.ged.utils.OrganigramStructure;
import com.microservice.ged.utils.OrganigramSystem;
import com.microservice.ged.utils.PosteRoleBean;
import com.microservice.ged.service.PosteService;

@Transactional
@Service
public class PosteServiceImpl implements PosteService{
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	

	@Autowired
	private StructureRepo structureRepo;

	
	
	@Override
	public Page<Postes> findAllPoste(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByActiveTrue(PageRequest.of(page, size));
	}
	
	@Override
	public Page<Postes> searchPosteByName(String titre, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByNameContainingAndActiveTrue(titre, PageRequest.of(page, size));
	}
	
	@Override
	public Page<Postes> findPosteByNiveau(Integer niveau, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return null;//posteRepo.findByNiveau(niveau, PageRequest.of(page, size));
	}
	
	@Override
	public Postes findPosteById(long id) throws Exception {
		// TODO Auto-generated method stub
		Postes poste = posteRepo.findByIdposteAndActiveTrue(id);
		if(poste==null) {
			throw new Exception("Poste with Id = "+id+" don't exist");
		}

		return poste;
	}
	
	@Override
	public Postes findPosteByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByNameAndActiveTrue(name);
	}
	
	@Override
	public void updatePoste(Postes poste) throws Exception {
		if(posteRepo.findByIdposteAndActiveTrue(poste.getIdposte())==null) {
			throw new Exception("Poste with name "+poste.getName()+" not exist");
		}
		if(posteRepo.findByNameAndActiveTrue(poste.getName())!=null) {
			throw new Exception("Poste with name "+poste.getName()+" already exist");
		}
		try {
			posteRepo.save(poste);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}
	
	@Override
	public void addPoste(Postes poste) throws Exception {
		// TODO Auto-generated method stub
		if(posteRepo.findByNameAndActiveTrue(poste.getName())!=null) {
			throw new Exception("Poste with name "+poste.getName()+" already exist");
		}
		posteRepo.save(poste);
		
	}
	
	@Override
	public void deletePoste(long id) throws Exception {
		// TODO Auto-generated method stub
		Postes poste = posteRepo.findByIdposteAndActiveTrue(id);
		if(poste==null) {
			throw new Exception("Poste not exist");
		}
		//posteRepo.delete(poste);
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
		/*if(supPostes.getPosteSubalterne().isEmpty()) {
			throw new Exception("toto");
		}else if(supPostes.getPosteSubalterne().size()==1) {
			Iterator<Postes> upposteIterato = supPostes.getPosteSubalterne().iterator();
			Postes subPostes = new Postes();
			while(upposteIterato.hasNext()) {
				subPostes = upposteIterato.next();
			}
			subPostes = posteRepo.findByIdposteAndActiveTrue(subPostes.getIdposte());
			supPostes = posteRepo.findByIdposteAndActiveTrue(supPostes.getIdposte());
			if(supPostes.getIdposte()==subPostes.getIdposte()) {
				throw new Exception("Error cant do this operation");
			}
			if(supPostes.getIdposte()==subPostes.getIdposte()) {
				throw new Exception("Error cant do this operation");
			}
			if(supPostes.getNiveau()==0) {
				supPostes.setNiveau(1);
			}
			subPostes.setNiveau(supPostes.getNiveau()+1);
			supPostes.getPosteSubalterne().add(subPostes);
			subPostes.setPosteSuperieur(supPostes);
			posteRepo.save(subPostes);
			posteRepo.save(supPostes);		
		}else {
			throw new Exception("toto");
		}*/
	}
	
	@Override
	public void removeSubPoste(Postes supPostes) throws Exception {
		/*if(supPostes.getPosteSubalterne().isEmpty()) {
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
			subPostes.setNiveau(0);
			supPostes.getPosteSubalterne().remove(subPostes);
			subPostes.setPosteSuperieur(null);
			posteRepo.save(subPostes);
			posteRepo.save(supPostes);	
		}else {
			throw new Exception("toto");
		}*/
	}

	@Override
	public Page<Postes> searchPosteByStructureAndNiveau(Integer niveau, Structures structures, int page, int size)
			throws Exception {
		return posteRepo.findByStructureAndActiveTrue(niveau, structures, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}

	@Override
	public Page<Postes> findAllStructurePoste(Structures structures, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return posteRepo.findByStructureAndActiveTrue(structures, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}

	@Override
	public Page<Postes> listPosteNotIn(GroupUser groupUsers, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		Set<Long> ids = new HashSet<>();
		groupUsers.getPosteslistes().forEach(
				(poste)->{
					ids.add(poste.getIdposte());
				}
		);
		return posteRepo.findByIdposteNotInAndActiveTrue(ids, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}
	
	@Override
	public Page<Postes> listPosteNotInByName(GroupUser groupUsers, String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		Set<Long> ids = new HashSet<>();
		groupUsers.getPosteslistes().forEach(
			(poste)->{
				ids.add(poste.getIdposte());
			}
		);
		return posteRepo.findByIdposteNotInAndActiveTrueAndNameContaining(ids, name, PageRequest.of(page, size, Sort.by("idposte").descending()));
	}

	
	@Override
	public OrganigramStructure ogranigramme(Long id) throws Exception {
		Structures structures = structureRepo.findByIdstructureAndActiveTrue(id);
		if(structures==null) {
			throw new Exception(" Error while get organigramme exist");
		}
		Postes postes = posteRepo.findByStructureAndPosteSuperieurIsNullAndActiveTrue(structures);
		if(postes==null) {
			return null;
		}else {
			OrganigramStructure organigramStructure = toto(postes);	
			return organigramStructure;			
		}
	}
	
	private OrganigramStructure toto(Postes postes){
		OrganigramStructure organigramStructure = new OrganigramStructure();
		organigramStructure.setName(postes.getName());
		LogPosteUser logPosteUser = logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
		if(logPosteUser!=null){
			organigramStructure.setTitle(logPosteUser.getUserId().getName());
		} else{
			organigramStructure.setTitle("Emplty");
		}
		
		if(postes.getPosteSubalterne().isEmpty()) {
			organigramStructure.setChilds(null);				
		}else {
			postes.getPosteSubalterne().forEach(
					(poste)->{						
						organigramStructure.getChilds().add(toto(poste));						
					}
				);	
		}		
		return organigramStructure;
	}

}


