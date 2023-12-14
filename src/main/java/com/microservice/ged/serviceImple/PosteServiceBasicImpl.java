package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Postes;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.PosteServiceBasic;

@Service
@Transactional
public class PosteServiceBasicImpl implements PosteServiceBasic{
	
	@Autowired
	PosteRepo posteRepo;

	@Override
	public Postes findPosteById(Long id) throws Exception {
		Postes poste = posteRepo.findByIdposteAndActiveTrue(id);
		if(poste==null) {
			throw new Exception("Poste with Id = "+id+" don't exist");
		}
		return poste;
	}

}
