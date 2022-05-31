package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.LogPoste;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.service.LogPosteService;

@Transactional
@Service
public class LogPosteServiceImpl implements LogPosteService{

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;

	@Override
	public Page<LogPoste> logUser(String loginuser, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteRepo.findByLoginuser(loginuser, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPoste> logPoste(String namePoste, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteRepo.findByPostename(namePoste, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPoste> logType(String type, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteRepo.findByType(type, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPoste> logObject(String objectname, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteRepo.findByObjectname(objectname, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPoste> logObjectAndType(String objectname, String type, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteRepo.findByObjectnameAndType(objectname, type, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPoste> logPosteAndType(String postename, String type, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteRepo.findByPostenameAndType(postename, type, PageRequest.of(page, size));
	}

	@Override
	public void add(LogPoste logPosteUser) throws Exception {
		// TODO Auto-generated method stub
		logPosteRepo.save(logPosteUser);
	}

}
