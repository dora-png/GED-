package com.microservice.ged.serviceImple;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.service.RolesService;

@Transactional
@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesRepo rolesRepo;

	@Override
	public Page<Roles> findAll(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return rolesRepo.findAll(PageRequest.of(page, size, Sort.by("idroles").descending()));
	}

	@Override
	public Page<Roles> findRoleToAdd(GroupUser groupUsers, int page, int size) throws Exception {
		Set<Long> ids = new HashSet<>();
		groupUsers.getPosteslistes().forEach(
				(poste)->{
					ids.add(poste.getIdposte());
				}
		);
		return rolesRepo.findByIdrolesNotIn(ids, PageRequest.of(page, size, Sort.by("idroles").descending()));
	}
	

	@Override
	public Page<Roles> findRoleToAddName(GroupUser groupUsers, String name, int page, int size) throws Exception {
		Set<Long> ids = new HashSet<>();
		groupUsers.getPosteslistes().forEach(
				(poste)->{
					ids.add(poste.getIdposte());
				}
		);
		return rolesRepo.findByIdrolesNotInAndNameContaining(ids, name, PageRequest.of(page, size, Sort.by("idroles").descending()));
	}

	@Override
	public Page<Roles> searchRole(String role, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return rolesRepo.findByNameContaining(role, PageRequest.of(page, size, Sort.by("idroles").descending()));
	}

	@Override
	public Roles findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return rolesRepo.findByIdroles(id);
	}

	@Override
	public Roles findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return rolesRepo.findByName(name);
	}

	

	
}
