package com.microservice.ged.service;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Roles;

public interface RolesService {
	public Page<Roles> findAll(int page, int size) throws Exception ;
	public Page<Roles> findRoleToAdd(GroupUser groupUsers, int page, int size) throws Exception ;
	public Page<Roles> findRoleToAddName(GroupUser groupUsers, String name, int page, int size) throws Exception ;
	public Page<Roles> searchRole(String role, int page, int size) throws Exception ;
	public Roles findById(Long id) throws Exception ;
	public Roles findByName(String name) throws Exception ;
	//public void add(Roles approle, String posteName) throws Exception ;
	//public void update(Roles approle, String posteName) throws Exception ;
	//public void delete(String roleName, String posteName) throws Exception ;
}
