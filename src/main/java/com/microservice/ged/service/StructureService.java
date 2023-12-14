package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.utils.OrganigramSystem;

public interface StructureService {

	public void add(Structures structure) throws Exception;
	public Page<Structures> findAll(int page, int size, int status) throws Exception;
	public OrganigramSystem ogranigramme() throws Exception;
	public Page<Structures> searchByName(String name,int page, int size, int status) throws Exception;
	public Page<Structures> searchBySigle(String sigle,int page, int size, int status) throws Exception;
	public void update(Structures structure) throws Exception;
	public void delete(Long structureId) throws Exception;
	public Structures findByIdStructure(Long id) throws Exception;
	public Structures findByName(String name) throws Exception;
	public Structures findBySigle(String sigle) throws Exception;
	public void removePosteToStructures(String posteName, String structureName) throws Exception;
	public void addSubStructures(Structures supStructures) throws Exception;
	public void addPosteToStructures( Structures structure, Postes poste) throws Exception;
	public void removeSubStructures(Structures supStructures) throws Exception;

}
