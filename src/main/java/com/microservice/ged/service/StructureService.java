package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;

public interface StructureService {

	public Structures findById(long id) throws Exception ;
	public void add(Structures structure, String posteName) throws Exception;
	public Page<Structures> findAll(int page, int size) throws Exception;
	public Page<Structures> structureUnUseListe(int page, int size) throws Exception;
	public Page<Structures> searchByName(String name,int page, int size) throws Exception;
	public Page<Structures> searchBySigle(String sigle,int page, int size) throws Exception;
	public void update(Structures structure, String posteName) throws Exception;
	public void delete(Structures structure, String posteName) throws Exception;
	public Structures findByIdStructure(Long id) throws Exception;
	public Structures findByName(String name) throws Exception;
	public Structures findBySigle(String sigle) throws Exception;
	public void removePosteToStructures(String posteName, String structureName) throws Exception;
	public void addSubStructures(String posteName,Structures supStructures) throws Exception;
	public void addPosteToStructures(String posteName, Structures structure, Postes poste) throws Exception;
	public void removeSubStructures(String posteName,Structures supStructures) throws Exception;

}
