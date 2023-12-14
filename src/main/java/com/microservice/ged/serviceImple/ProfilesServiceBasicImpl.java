package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.ProfilesRepo;
import com.microservice.ged.service.ProfilesServiceBasic;

@Service
@Transactional
public class ProfilesServiceBasicImpl implements ProfilesServiceBasic{

	@Autowired
	ProfilesRepo profilesRepo;
	
	@Override
	public Profiles findProfileById(Long id) throws Exception {
		return profilesRepo.findByIdprofiles(id);
	}

}
