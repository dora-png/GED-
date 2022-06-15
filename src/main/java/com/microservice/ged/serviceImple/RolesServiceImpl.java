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

	@Autowired
	PosteRepo posteRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Override
	public Page<Roles> findAll(Long posteId, int page, int size) throws Exception  {
		this.userExist(posteId, "RROLE");		
		return rolesRepo.findAll(PageRequest.of(page, size, Sort.by("idroles").descending()));
	}

	@Override
	public Page<Roles> searchRole(String role, Long posteId, int page, int size) throws Exception  {
		this.userExist(posteId, "RROLE");
		return rolesRepo.findByNameLike(role, PageRequest.of(page, size, Sort.by("idroles").descending()));
	}

	@Override
	public Roles findById(Long id, Long posteId)  throws Exception  {
		this.userExist(posteId, "RROLE");
		return rolesRepo.findByIdroles(id);
	}

	@Override
	public Roles findByName(String name, Long posteId)  throws Exception  {
		this.userExist(posteId, "RROLE");
		return rolesRepo.findByName(name);
	}

	@Override
	public Page<Roles> findRoleToAdd(Long posteId, Long posteToAddRole, int page, int size)  throws Exception {
		this.userExist(posteId, "RROLE");
		if(posteRepo.findByIdposte(posteToAddRole)==null) {
			throw new Exception("Choise poste to add roles");			
		}
		//Iterator<Roles> rolesIterato = posteRepo.findByIdposte(posteToAddRole).getRoles().iterator();
		//Set<String> rolesNameList = new HashSet<String>();
		//while(rolesIterato.hasNext()) {
		//	rolesNameList.add(rolesIterato.next().getName());
		//}
		return null;
		//return rolesRepo.findByNameNotIn(rolesNameList, PageRequest.of(page, size, Sort.by("idroles").descending()));
		
	}
	
	private void userExist(Long posteId, String roles)  throws Exception   {
		if(posteRepo.findByIdposte(posteId)==null) {
			throw new Exception("Poste not exist");	
		}
		Roles role= rolesRepo.findByName(roles);
		//if(!posteRepo.findByIdposte(posteId).getRoles().contains(role)) {
		//	throw new Exception("Forbidden");				
		//}
	}

	
}
