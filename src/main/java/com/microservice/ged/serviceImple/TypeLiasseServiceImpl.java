package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.microservice.ged.beans.TypeLiasses;
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
	/*
	@Autowired
	LogPosteUserRepo logPosteUserRepo;*/
	
	
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
	public void add(TypeLiasses typeLiasses) throws Exception {
		// TODO Auto-generated method stub
		if(typeLiassesRepo.findByName(typeLiasses.getName())!=null) {
			throw new Exception("TypeLiasse with name "+typeLiasses.getName()+" already exist");
		}
		if(typeLiassesRepo.findBySigle(typeLiasses.getSigle())!=null) {
			throw new Exception("TypeLiasse with sigle "+typeLiasses.getSigle()+" already exist");
		}
		try {
			typeLiassesRepo.save(typeLiasses);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public void update(TypeLiasses typeLiasses) throws Exception {
		if(typeLiassesRepo.findByIdtypeliasse(typeLiasses.getIdtypeliasse())==null) {
			throw new Exception("TypeLiasses with name "+typeLiasses.getName()+" not exist");
		}
		try {
			typeLiassesRepo.delete(typeLiasses);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}

	@Override
	public void delete(TypeLiasses typeLiasses) throws Exception {
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
		try {
			typeLiassesRepo.delete(typeLiasses);
		} catch (Exception e) {
			throw new Exception("Error while delete");
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
