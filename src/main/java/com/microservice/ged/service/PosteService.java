package com.microservice.ged.service;



import org.springframework.data.domain.Page;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.utils.OrganigramStructure;
import com.microservice.ged.utils.OrganigramSystem;

public interface PosteService {
	public Page<Postes> findAllPoste(int page, int size) throws Exception ;
	public Page<Postes> findAllStructurePoste(Long structuresId, int page, int size) throws Exception ;
	public Page<Postes> searchPosteByName(String titre, int page, int size) throws Exception ;
	public Postes findPosteByName(String name) throws Exception ;
	public Postes findPosteById(Long id) throws Exception ;
	public void updatePoste(Postes poste) throws Exception ;
	public void addPoste(Postes poste) throws Exception ;
	public void updatePosteStatus(long id) throws Exception ;
	public void addSubPoste(Postes supPostes) throws Exception ;
	public OrganigramStructure ogranigramme(Long id) throws Exception;

}
