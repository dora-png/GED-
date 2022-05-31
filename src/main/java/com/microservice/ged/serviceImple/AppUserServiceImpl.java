package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.AppUserService;

@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	AppUserRepo appUserRepo;

	@Autowired
	PosteRepo posteRepo;
	
	
	@Override
	public Page<Appusers> findAll(int page, int size) {
		Pageable pageOfData = PageRequest.of(page, size, Sort.by("iduser").ascending());
		return appUserRepo.findAll(pageOfData);
	}

	@Override
	public Appusers findByLogin(String login) {
		// TODO Auto-generated method stub
		return appUserRepo.findByLogin(login);
	}

	@Override
	public Appusers findByName(String name) {
		// TODO Auto-generated method stub
		return appUserRepo.findByName(name);
	}

	@Override
	public Appusers findById(Long id) {
		// TODO Auto-generated method stub
		return appUserRepo.findByIduser(id);
	}

	@Override
	public Appusers addUser(Appusers user) {
		// TODO Auto-generated method stub
		return appUserRepo.save(user);
	}

	@Override
	public void updateUser(Appusers user) {
		// TODO Auto-generated method stub
		appUserRepo.save(user);
	}

}
