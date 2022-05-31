package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Users;

public interface UserService {
	public Page<Users> findAll(int page, int size);
	public Page<Users> searchByName(String name,int page, int size);
	public Page<Users> searchByLogin(String login,int page, int size);
	public void add(Users users, String posteName) throws Exception;
	public void update(Users users, String posteName) throws Exception;
	public String getPwd(Users users) throws Exception;
	public void changePwd(Users users, String newPdb) throws Exception;
	public void delete(Users users, String posteName) throws Exception;
	public Users findById(Long id) throws Exception;
	public Users findByName(String name) throws Exception;
	public Users findByLogin(String login) throws Exception;

}
