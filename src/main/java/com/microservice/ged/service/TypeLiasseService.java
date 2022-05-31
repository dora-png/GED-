package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.TypeLiasses;

public interface TypeLiasseService {
	public Page<TypeLiasses> findAll(int page, int size);
	public Page<TypeLiasses> searchByName(String name,int page, int size) throws Exception;
	public Page<TypeLiasses> searchBySigle(String sigle,int page, int size) throws Exception;
	public void add(TypeLiasses typeLiasses, String posteName) throws Exception;
	public void update(TypeLiasses typeLiasses, String posteName) throws Exception;
	public void delete(TypeLiasses typeLiasses, String posteName) throws Exception;
	public TypeLiasses findById(Long id) throws Exception;
	public TypeLiasses findByName(String name) throws Exception;
	public TypeLiasses findBySigle(String sigle) throws Exception;

}
