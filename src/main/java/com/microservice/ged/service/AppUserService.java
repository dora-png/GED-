package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Appusers;

public interface AppUserService {
	public Page<Appusers> findAll(int page, int size);
	public Appusers findByLogin(String login);
	public Appusers findByName(String name);
	public Appusers findById(Long id);
	public Appusers addUser(Appusers user);
	public void updateUser(Appusers user);

}
