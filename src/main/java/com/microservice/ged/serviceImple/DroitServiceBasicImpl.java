package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.service.DroitServiceBasic;

@Service
@Transactional
public class DroitServiceBasicImpl implements DroitServiceBasic {
	
	@Autowired
	DroitsRepo droitsRepo;

	@Override
	public Droits findDroitsById(Long id) throws Exception {
		return droitsRepo.findByIddroit(id);
	}

	@Override
	public Droits findDroitsByName(String name) throws Exception {
		return droitsRepo.findByName(name);
	}

}
