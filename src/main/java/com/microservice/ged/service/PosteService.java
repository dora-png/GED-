package com.microservice.ged.service;



import org.springframework.data.domain.Page;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.utils.OrganigramStructure;
import com.microservice.ged.utils.OrganigramSystem;

public interface PosteService {
	public Page<Postes> findAllPoste(int page, int size) throws Exception ;
	public Page<Postes> listPosteNotInByName(GroupUser groupUsers, String name, int page, int size) throws Exception ;
	public Page<Postes> listPosteNotIn(GroupUser groupUsers,int page, int size) throws Exception ;
	public Page<Postes> findAllStructurePoste(Structures structures, int page, int size) throws Exception ;
	public Page<Postes> searchPosteByName(String titre, int page, int size) throws Exception ;
	public Page<Postes> findPosteByNiveau(Integer niveau, int page, int size) throws Exception ;
	public Page<Postes> searchPosteByStructureAndNiveau(Integer niveau,Structures structures, int page, int size) throws Exception ;
	public Postes findPosteById(long id) throws Exception ;
	public Postes findPosteByName(String name) throws Exception ;
	public void updatePoste(Postes poste) throws Exception ;
	public void addPoste(Postes poste) throws Exception ;
	public void deletePoste(long id) throws Exception ;
	public void addSubPoste(Postes supPostes) throws Exception ;
	public void removeSubPoste(Postes supPostes) throws Exception ;
	public OrganigramStructure ogranigramme(Long id) throws Exception;

}
