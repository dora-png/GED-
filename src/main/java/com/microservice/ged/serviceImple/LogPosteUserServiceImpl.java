package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Users;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.service.LogPosteUserService;

@Service
@Transactional
public class LogPosteUserServiceImpl implements LogPosteUserService {
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;

	@Override
	public Page<LogPosteUser> logUser(Users users, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return logPosteUserRepo.findByUserId(users, PageRequest.of(page, size));
	}

	@Override
	public Page<LogPosteUser> logPoste(Postes postes, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return  logPosteUserRepo.findByPosteId(postes, PageRequest.of(page, size));
	}

	@Override
	public Postes currentPosteOfUser(Users users) throws Exception {
		// TODO Auto-generated method stub
		LogPosteUser logPosteUser = logPosteUserRepo.findByUserIdAndDateFinIsNull(users);
		if(logPosteUser!=null){
			return logPosteUser.getPosteId();
		} else{
			return null;
		}
	}
}
