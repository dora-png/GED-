package com.microservice.ged.service;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Roles;

public interface RolesService {
	public Page<Roles> findAll(Long posteId, int page, int size) throws Exception ;	
	public Page<Roles> findRoleToAdd(Long posteId,Long posteToAddRoleId, int page, int size) throws Exception ;
	public Page<Roles> searchRole(String role, Long posteId, int page, int size) throws Exception ;
	public Roles findById(Long id, Long posteId) throws Exception ;
	public Roles findByName(String name, Long posteId) throws Exception ;
	//public void add(Roles approle, String posteName) throws Exception ;
	//public void update(Roles approle, String posteName) throws Exception ;
	//public void delete(String roleName, String posteName) throws Exception ;
}
