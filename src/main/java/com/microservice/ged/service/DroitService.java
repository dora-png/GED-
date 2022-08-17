package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Droits;

public interface DroitService {
	public Page<Droits> findAllDroits(int page, int size) throws Exception ;
	public Page<Droits> findDroitsToAdd(List<Long> droitsIdList, int page, int size) throws Exception ;
	public Page<Droits> findDroitsToAddName(List<Long> droitsIdList, String name, int page, int size) throws Exception ;
	public Page<Droits> searchDroits(String role, int page, int size) throws Exception ;
	public Droits findDroitsById(Long id) throws Exception ;
	public Droits findDroitsByName(String name) throws Exception ;
	public void add(Droits droits) throws Exception ;
}
