package com.microservice.ged.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.TypeLiasses;

public interface LiasseService {
	public Page<Liasses> searchLiassesByName(String name, int page, int size) throws Exception ;
	public Page<Liasses> searchLiassesBySigle(String sigle, int page, int size) throws Exception ;
	public void add(Liasses liasse, String posteName) throws Exception ;
	public void update(Liasses liasse, String posteName) throws Exception ;
	public void addDocToLiasse(Liasses liasse, String posteName) throws Exception ;
	public Liasses findByName(String name) throws Exception ;
	public Liasses findBySigle(String sigle) throws Exception ;
	public Page<Liasses> findByTypeliasse(TypeLiasses typeliasse, int page, int size) throws Exception ;
	public Page<Liasses> findByDateCreation(Date dateCreation, int page, int size) throws Exception ;
	public Page<Liasses> findByDateCreationBetween(Date date1, Date date2, int page, int size) throws Exception ;
}
