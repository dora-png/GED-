package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;
//import com.microservice.ged.beans.Postes;
import com.microservice.ged.repository.DocsRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
//import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.WorkFlowProfilesRepo;
import com.microservice.ged.service.DocsService;

@Service
@Transactional
public class DocsServiceImpl implements DocsService {
	
	@Autowired
	private DocsRepo docsRepo;
	
	@Autowired
	WorkFlowProfilesRepo workFlowProfilesRepo;
/*
	@Autowired
	private PosteRepo posteRepo;*/
	
	/*@Autowired
	LogPosteUserRepo logPosteUserRepo;*/

	@Override
	public Page<Docs> searchDocsByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return docsRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Docs> searchDocsByLiasses(Liasses liasse, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return docsRepo.findByLiasse(liasse, PageRequest.of(page, size));
	}

	@Override
	public void add(Docs docs) throws Exception {
		if(docsRepo.findByName(docs.getName())!=null) {
			throw new Exception("Documents with name "+docs.getName()+" already exist");
		}
			docsRepo.save(docs);
	}

	@Override
	public void update(Docs docs) throws Exception {
		if(docsRepo.findByIddoc(docs.getIddoc())==null) {
			throw new Exception("Document with name "+docs.getName()+" not exist");
		}
		try {
			docsRepo.save(docs);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}
	
	@Override
	public void updateSetter(Docs docs, String posteName) throws Exception {
		if(docsRepo.findByIddoc(docs.getIddoc())==null) {
			throw new Exception("Document with name "+docs.getName()+" not exist");
		}
		//Postes postes =  posteRepo.findByNameAndActiveTrue(posteName);
		//kjhkjhjkhkljhkljhlkj
	}

	@Override
	public void arhive(Docs docs) throws Exception {
		// TODO Auto-generated method stub
		docs.setArchive(true);
		docsRepo.save(docs);
	}

}
