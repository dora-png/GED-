package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.utils.PosteRoleBean;

public interface PosteService {
	public Page<Postes> findAll(int page, int size) throws Exception ;
	public Page<Postes> searchByName(String titre, int page, int size) throws Exception ;
	public Page<Postes> findByNiveau(Integer niveau, int page, int size) throws Exception ;
	public Page<Postes> searchByStructureAndNiveau(Integer niveau,Structures structures, int page, int size) throws Exception ;
	public Postes findById(long id) throws Exception ;
	public Postes findByName(String name) throws Exception ;
	public void update(Postes poste, String posteName) throws Exception ;
	public void add(Postes poste, String posteName) throws Exception ;
	public void delete(long id, String posteName) throws Exception ;
	public void addRolePoste(PosteRoleBean posterolebean, String posteName) throws Exception ;
	public void addSubPoste(String posteName,Postes supPostes) throws Exception ;
	public void removeRoleToPoste(PosteRoleBean posterolebean, String posteName) throws Exception;
	public void removeSubPoste(String posteName,String supPoste, String subPoste) throws Exception ;

}
