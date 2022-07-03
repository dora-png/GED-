package com.microservice.ged.serviceImple;

import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.TypeLiasses;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.DocsRepo;
import com.microservice.ged.repository.LiassesRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.LiasseService;


@Transactional
@Service
public class LiasseServiceImpl implements LiasseService {
	
	@Autowired
	private DocsRepo docsRepo;

	@Autowired
	private LiassesRepo liassesRepo;

	@Autowired
	private PosteRepo posteRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;

	@Override
	public Page<Liasses> searchLiassesByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> searchLiassesBySigle(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findBySigleLike(sigle, PageRequest.of(page, size));
	}

	@Override
	public void update(Liasses liasse) throws Exception {
		if(liassesRepo.findByIdliasse(liasse.getIdliasse())==null) {
			throw new Exception("Liasse with name "+liasse.getName()+" not exist");
		}
		try {
			liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}

	@Override
	public void addDocToLiasse(Liasses liasse) throws Exception {
		if(liasse.getDocs().isEmpty()) {
			throw new Exception("toto");
		}else if(liasse.getDocs().size()==1) {
			Iterator<Docs> docsIterato = liasse.getDocs().iterator();
			Docs docs = new Docs();
			while(docsIterato.hasNext()) {
				docs = docsIterato.next();
			}
			if(liassesRepo.findByIdliasse(liasse.getIdliasse())==null) {
				throw new Exception("Liasse with name "+liasse.getName()+" not exist");
			}
			if(docsRepo.findByIddoc(docs.getIddoc())==null) {
				throw new Exception("Document with name "+docs.getName()+" not exist");
			}
			try {
				docsRepo.save(docs);
				liassesRepo.save(liasse);
			} catch (Exception e) {
				throw new Exception("Error while update");
			}
		}
		

		
		
		
	}

	@Override
	public Liasses findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByName(name);
	}

	@Override
	public Liasses findBySigle(String sigle) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findBySigle(sigle);
	}

	@Override
	public Page<Liasses> findByTypeliasse(TypeLiasses typeliasse, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByTypeliasse(typeliasse, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByDateCreation(Date dateCreation, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByDateCreation(dateCreation, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByDateCreationBetween(Date date1, Date date2, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByDateCreationBetween(date1, date2, PageRequest.of(page, size));
	}

	@Override
	public Liasses addLiasseForWorkflow(Liasses liasse) throws Exception {
		if(liassesRepo.findByName(liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findBySigle(liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public Liasses addLiasseForWorkflowImportData(Liasses liasse) throws Exception {
		if(liassesRepo.findByName(liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findBySigle(liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public Liasses addLiasseForUser(Liasses liasse) throws Exception {
		if(liassesRepo.findByName(liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findBySigle(liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public Liasses addLiasseForUserImportData(Liasses liasse) throws Exception {
		if(liassesRepo.findByName(liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findBySigle(liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

}
