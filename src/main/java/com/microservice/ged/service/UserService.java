package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Users;

public interface UserService {
	public Page<Users> defaultList(int page, int size) throws Exception;
	public Page<Users> findAll(int page, int size) throws Exception;
	public Page<Users> searchByName(String name,int page, int size) throws Exception;
	public Page<Users> searchByLogin(String login,int page, int size) throws Exception;
	public void add(Users users) throws Exception;
	public void update(Users users) throws Exception;
	public void save(Users users) throws Exception;
	public void setStatus(Users users);
	public String getPwd(Long posteId, Users users) throws Exception;
	public void changePwd(Long posteId, Users users, String newPdb) throws Exception;
	public void delete(Users users) throws Exception;
	public Users findById(Long id) throws Exception;
	public Users findByName(String name) throws Exception;
	public Users findByUsername(String login);

}
