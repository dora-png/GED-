package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.repository.GroupUserRepo;
import com.microservice.ged.service.GroupUserServiceBasic;


@Service
@Transactional
public class GroupUserServiceBasicImpl implements GroupUserServiceBasic {
	
	@Autowired
	GroupUserRepo groupUserRepo;
	
	
	@Override
	public GroupUser findGroupById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return groupUserRepo.findByIdgroupes(id);
	}
}
