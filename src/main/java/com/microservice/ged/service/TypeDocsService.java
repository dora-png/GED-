package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.TypeDocs;


public interface TypeDocsService {
	public Page<TypeDocs> findAll(int page, int size);
	public Page<TypeDocs> searchByName(String name,int page, int size) throws Exception;
	public Page<TypeDocs> searchBySigle(String sigle,int page, int size) throws Exception;
	public void add(TypeDocs typeDocs) throws Exception;
	public void update(TypeDocs typeDocs) throws Exception;
	public void delete(TypeDocs typeDocs) throws Exception;
	public TypeDocs findById(Long id) throws Exception;
	public TypeDocs findByName(String name) throws Exception;
	public TypeDocs findBySigle(String sigle) throws Exception;

}
