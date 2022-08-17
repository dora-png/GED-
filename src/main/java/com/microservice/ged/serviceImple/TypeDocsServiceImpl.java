package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.TypeDocs;
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
	/*
	@Autowired
	LogPosteUserRepo logPosteUserRepo;*/
	

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
	public void add(TypeDocs typeDocs) throws Exception {
		// TODO Auto-generated method stub
		if(typeDocsRepo.findByName(typeDocs.getName())!=null) {
			throw new Exception("TypeDocs with name "+typeDocs.getName()+" already exist");
		}
		if(typeDocsRepo.findBySigle(typeDocs.getSigle())!=null) {
			throw new Exception("TypeDocs with sigle "+typeDocs.getSigle()+" already exist");
		}
		try {
			typeDocsRepo.save(typeDocs);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public void update(TypeDocs typeDocs) throws Exception {
		if(typeDocsRepo.findByIdtypedoc(typeDocs.getIdtypedoc())==null) {
			throw new Exception("TypedOCs with name "+typeDocs.getName()+" not exist");
		}
		try {
			typeDocsRepo.delete(typeDocs);
		} catch (Exception e) {
			throw new Exception("Error while update");
		}
	}

	@Override
	public void delete(TypeDocs typeDocs) throws Exception {
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
		try {
			typeDocsRepo.delete(typeDocs);
		} catch (Exception e) {
			throw new Exception("Error while delete");
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
