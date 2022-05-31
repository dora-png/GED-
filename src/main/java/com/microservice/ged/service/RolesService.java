package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Roles;

public interface RolesService {
	public Page<Roles> findAll(String posteName, int page, int size);	
	public Page<Roles> searchRole(String role, String posteName, int page, int size);
	public Roles findById(Long id, String posteName) throws Exception ;
	public Roles findByName(String name, String posteName) throws Exception ;
	//public void add(Roles approle, String posteName) throws Exception ;
	//public void update(Roles approle, String posteName) throws Exception ;
	//public void delete(String roleName, String posteName) throws Exception ;
}
