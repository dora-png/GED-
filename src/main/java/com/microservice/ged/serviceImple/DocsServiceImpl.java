package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.repository.DocsRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.WorkFlowPosteRepo;
import com.microservice.ged.service.DocsService;

@Service
@Transactional
public class DocsServiceImpl implements DocsService {
	
	@Autowired
	private DocsRepo docsRepo;
	
	@Autowired
	WorkFlowPosteRepo workFlowPosteRepo;

	@Autowired
	private PosteRepo posteRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;

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
	public void add(Docs docs, String posteName) throws Exception {
		if(docsRepo.findByName(docs.getName())!=null) {
			throw new Exception("Documents with name "+docs.getName()+" already exist");
		}
		try {
			docsRepo.save(docs);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
		
	}

	@Override
	public void update(Docs docs, String posteName) throws Exception {
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
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					if(docs.getCantset().equals(postes.getName())) {
						WorkFlowPoste workFlowPoste = workFlowPosteRepo.findByIsactiveTrueAndWorkflowIdAndPosteId(docs.getLiasse().getWorkflowid(), postes);
						if(workFlowPoste==null) {
							if(workFlowPoste.getLevel()==0) {
								//archiver
								/*
								 * 						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update Document "+docs.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								docs.getName(),
								"DOCUMENTS");
							docsRepo.save(docs);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while update");
						}
								 * */
							}else {
								workFlowPoste = workFlowPosteRepo.findByIsactiveTrueAndWorkflowIdAndLevel(docs.getLiasse().getWorkflowid(), workFlowPoste.getLevel()+1);
								try {
									docs.setCantset(workFlowPoste.getPosteId().getName());									
									docsRepo.save(docs);
								} catch (Exception e) {
									throw new Exception("Error while update");
								}
							}
						}else {
							throw new Exception("You dont have this right update");
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
	public void arhive(Docs docs, String posteName) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
