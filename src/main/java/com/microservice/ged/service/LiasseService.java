package com.microservice.ged.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.TypeLiasses;

public interface LiasseService {
	/*public Page<Liasses> findByNameLike(String name, int page, int size) throws Exception ;
	public Page<Liasses> findByNameLikeAndArchiveTrue(String name, int page, int size) throws Exception ;
	public Page<Liasses> findByNameLikeAndArchiveFalse(String name, int page, int size) throws Exception ;
	public Page<Liasses> findBySigleLike(String sigle, int page, int size) throws Exception ;
	public Page<Liasses> findBySigleLikeAndArchiveTrue(String sigle, int page, int size) throws Exception ;
	public Page<Liasses> findBySigleLikeAndArchiveFalse(String sigle, int page, int size) throws Exception ;
	public Page<Liasses> findAllLiasses(int page, int size);
	public Page<Liasses> findByArchiveFalse(int page, int size);
	public Page<Liasses> findByArchiveTrue(int page, int size);
	
	public Page<Liasses> findByTypeliasse(TypeLiasses typeliasse, int page, int size) throws Exception ;
	public Page<Liasses> findByTypeliasseAndArchiveTrue(TypeLiasses typeliasse, int page, int size) throws Exception ;
	public Page<Liasses> findByTypeliasseAndArchiveFalse(TypeLiasses typeliasse, int page, int size) throws Exception ;



	public Liasses addLiasseForWorkflow(Liasses liasse) throws Exception ;
	public Liasses addLiasseForWorkflowImportData(Liasses liasse) throws Exception ;
	public Liasses addLiasseForUser(Liasses liasse) throws Exception ;
	public Liasses addLiasseForUserImportData(Liasses liasse) throws Exception ;
	public void createLiasseWorkFlow(Liasses liasse) throws Exception ;
	public void createLiasseUser(Liasses liasse) throws Exception ;
	public void updateName(Liasses liasse) throws Exception ;
	public void archiveLiasse(Liasses liasse) throws Exception ;
	public void addDocToLiasse(Liasses liasse) throws Exception ;
	public Liasses findByIdliasse(Long id) throws Exception ;
	
	public Page<Liasses> findByDateCreationAndArchiveFalse(Date dateCreation, int page, int size) throws Exception ;
	public Page<Liasses> findByDateCreationBetweenAndArchiveFalse(Date date1, Date date2, int page, int size) throws Exception ;
	public Page<Liasses> findByDateCreationAndArchiveTrue(Date dateCreation, int page, int size) throws Exception ;
	public Page<Liasses> findByDateCreationBetweenAndArchiveTrue(Date date1, Date date2, int page, int size) throws Exception ;*/
}
