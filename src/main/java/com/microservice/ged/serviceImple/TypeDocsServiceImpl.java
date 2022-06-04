package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.LogPoste;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.TypeDocs;
import com.microservice.ged.repository.LogPosteRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.TypeDocsRepo;
import com.microservice.ged.service.TypeDocsService;

@Service
@Transactional
public class TypeDocsServiceImpl implements TypeDocsService {
	
	@Autowired
	TypeDocsRepo typeDocsRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;


	@Override
	public Page<TypeDocs> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return typeDocsRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<TypeDocs> searchByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return typeDocsRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<TypeDocs> searchBySigle(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return typeDocsRepo.findBySigleLike(sigle, PageRequest.of(page, size));
	}

	@Override
	public void add(TypeDocs typeDocs, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(typeDocsRepo.findByName(typeDocs.getName())!=null) {
			throw new Exception("TypeDocs with name "+typeDocs.getName()+" already exist");
		}
		if(typeDocsRepo.findBySigle(typeDocs.getSigle())!=null) {
			throw new Exception("TypeDocs with sigle "+typeDocs.getSigle()+" already exist");
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
						if(roles.getName()=="CTYPEDOC")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Create TypeDoc "+typeDocs.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								typeDocs.getName(),
								"TYPEDOCS");
							typeDocsRepo.save(typeDocs);
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
	public void update(TypeDocs typeDocs, String posteName) throws Exception {
		if(typeDocsRepo.findByIdtypedoc(typeDocs.getIdtypedoc())==null) {
			throw new Exception("TypedOCs with name "+typeDocs.getName()+" not exist");
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
						if(roles.getName()=="UTYPEDOC")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Update TypeDoc "+typeDocs.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								typeDocs.getName(),
								"TYPEDOCS");
							typeDocsRepo.delete(typeDocs);
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
	public void delete(TypeDocs typeDocs, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(typeDocsRepo.findByName(typeDocs.getName())==null) {
			throw new Exception("TypeDoc with name "+typeDocs.getName()+" not exist");
		}
		if(typeDocsRepo.findBySigle(typeDocs.getSigle())==null) {
			throw new Exception("TypeDoc with sigle "+typeDocs.getSigle()+" not exist");
		}
		if(typeDocsRepo.findByIdtypedoc(typeDocs.getIdtypedoc())==null) {
			throw new Exception("TypeDocs with name "+typeDocs.getName()+" not exist");
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
						if(roles.getName()=="DTYPEDOC")
							hasRole = roles.isDelete();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								"Delete TypeDocs "+typeDocs.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								typeDocs.getName(),
								"TYPEDOCS");
							typeDocsRepo.delete(typeDocs);
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
	public TypeDocs findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return typeDocsRepo.findById(id).orElseThrow(() -> new Exception(""));
	}

	@Override
	public TypeDocs findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return typeDocsRepo.findByName(name);
	}

	@Override
	public TypeDocs findBySigle(String sigle) throws Exception {
		// TODO Auto-generated method stub
		return typeDocsRepo.findBySigle(sigle);
	}

}
