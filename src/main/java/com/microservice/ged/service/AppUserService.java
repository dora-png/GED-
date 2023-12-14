package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

public interface AppUserService {
	public List<String> findAllUser();
	public List<String> findAllUserByNameNotLike(String name);
	public String findUserByName(String name);
	public String findUserByLogin(String login);

}
