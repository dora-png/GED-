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
import com.microservice.ged.beans.TypeLiasses;
import com.microservice.ged.repository.LogPosteRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.TypeLiassesRepo;
import com.microservice.ged.service.TypeLiasseService;

@Service
@Transactional
public class TypeLiasseServiceImpl implements TypeLiasseService {
	
	@Autowired
	TypeLiassesRepo typeLiassesRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;

	@Override
	public Page<TypeLiasses> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return typeLiassesRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<TypeLiasses> searchByName(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return typeLiassesRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<TypeLiasses> searchBySigle(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return typeLiassesRepo.findBySigleLike(sigle, PageRequest.of(page, size));
	}

	@Override
	public void add(TypeLiasses typeLiasses, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(typeLiassesRepo.findByName(typeLiasses.getName())!=null) {
			throw new Exception("TypeLiasse with name "+typeLiasses.getName()+" already exist");
		}
		if(typeLiassesRepo.findBySigle(typeLiasses.getSigle())!=null) {
			throw new Exception("TypeLiasse with sigle "+typeLiasses.getSigle()+" already exist");
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
						if(roles.getName()=="CTYPELIASSE")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Create TypeLiasse "+typeLiasses.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								typeLiasses.getName(),
								"TYPELIASSE");
							typeLiassesRepo.save(typeLiasses);
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
	public void update(TypeLiasses typeLiasses, String posteName) throws Exception {
		if(typeLiassesRepo.findByIdtypeliasse(typeLiasses.getIdtypeliasse())==null) {
			throw new Exception("TypeLiasses with name "+typeLiasses.getName()+" not exist");
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
						if(roles.getName()=="UTYPELIASSE")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update TypeLiasse "+typeLiasses.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								typeLiasses.getName(),
								"TYPELIASSE");
							typeLiassesRepo.delete(typeLiasses);
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
	public void delete(TypeLiasses typeLiasses, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(typeLiassesRepo.findByName(typeLiasses.getName())==null) {
			throw new Exception("TypeLiasse with name "+typeLiasses.getName()+" not exist");
		}
		if(typeLiassesRepo.findBySigle(typeLiasses.getSigle())==null) {
			throw new Exception("TypeLiasse with sigle "+typeLiasses.getSigle()+" not exist");
		}
		if(typeLiassesRepo.findByIdtypeliasse(typeLiasses.getIdtypeliasse())==null) {
			throw new Exception("TypeLiasses with name "+typeLiasses.getName()+" not exist");
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
						if(roles.getName()=="DTYPELIASSE")
							hasRole = roles.isDelete();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Delete TypeLiasse "+typeLiasses.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								typeLiasses.getName(),
								"TYPELIASSE");
							typeLiassesRepo.delete(typeLiasses);
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
	public TypeLiasses findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return typeLiassesRepo.findById(id).orElseThrow(() -> new Exception(""));
	}

	@Override
	public TypeLiasses findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return typeLiassesRepo.findByName(name);
	}

	@Override
	public TypeLiasses findBySigle(String sigle) throws Exception {
		// TODO Auto-generated method stub
		return typeLiassesRepo.findBySigle(sigle);
	}

}
