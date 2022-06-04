package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.LogPoste;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.TypeLiasses;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.DocsRepo;
import com.microservice.ged.repository.LiassesRepo;
import com.microservice.ged.repository.LogPosteRepo;
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
	
	@Autowired 
	LogPosteRepo logPosteRepo;

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
	public void add(Liasses liasse, String posteName) throws Exception {
		if(liassesRepo.findByName(liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findBySigle(liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Error while create Structure");	
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="CLIASSE")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Create Liasse "+liasse.getSigle(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								liasse.getName(),
								"LIASSES");
							liassesRepo.save(liasse);
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
	public void update(Liasses liasse, String posteName) throws Exception {
		if(liassesRepo.findByIdliasse(liasse.getIdliasse())==null) {
			throw new Exception("Liasse with name "+liasse.getName()+" not exist");
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
						if(roles.getName()=="ULIASSE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Update Liasse "+liasse.getSigle(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								liasse.getName(),
								"LIASSES");
							liassesRepo.save(liasse);
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
	public void addDocToLiasse(Liasses liasse, String posteName) throws Exception {
		Docs docs = liasse.getDocs().get(0);
		if(liassesRepo.findByIdliasse(liasse.getIdliasse())==null) {
			throw new Exception("Liasse with name "+liasse.getName()+" not exist");
		}
		if(docsRepo.findByName(docs.getName())==null) {
			throw new Exception("Document with name "+docs.getName()+" not exist");
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
						if(roles.getName()=="ULIASSE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Update Liasse "+liasse.getSigle()+" add doc",
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								liasse.getName(),
								"LIASSES");
							docsRepo.save(docs);
							liassesRepo.save(liasse);
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

}
