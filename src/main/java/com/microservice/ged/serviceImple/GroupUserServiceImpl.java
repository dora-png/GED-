package com.microservice.ged.serviceImple;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.repository.GroupUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.GroupUserService;

@Service
@Transactional
public class GroupUserServiceImpl implements GroupUserService {
	
	@Autowired
	GroupUserRepo groupUserRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Override
	public Page<GroupUser> findAllGroup(int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findAll(PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}

	@Override
	public Page<GroupUser> defaultGroup(int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findAll(PageRequest.of(0, 5, Sort.by("idgroupes").descending()));
	}

	@Override
	public Page<GroupUser> findByNameContaining(String name, int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findByNameContaining(name, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}

	@Override
	public void saveGroupUser(GroupUser groupUser) throws Exception {
		if(groupUserRepo.findByName(groupUser.getName())!=null) {
			throw new Exception("Group with name "+groupUser.getName()+" Already exist");
		}
		groupUserRepo.save(groupUser);
		
	}

	@Override
	public void updateNameGroupUser(GroupUser groupUser) throws Exception {
		if(groupUserRepo.findByIdgroupes(groupUser.getIdgroupes())==null) {
			throw new Exception("Group you want to change name don't exist");
		}
		if(groupUserRepo.findByName(groupUser.getName())!=null) {
			throw new Exception("Group with name "+groupUser.getName()+" Already exist");
		}
		groupUserRepo.save(groupUser);
	}

	@Override
	public void addRole(GroupUser groupUser) throws Exception {
		// TODO Auto-generated method stub
		GroupUser groupUse = groupUserRepo.findByIdgroupes(groupUser.getIdgroupes());
		if(groupUse==null) {
			throw new Exception("Group "+groupUser.getName()+" Don't exist");
		}
		boolean exist=true;
		Roles roles=null;
		Set<Roles> set = new HashSet<Roles>();
	    for (Iterator<Roles> it = groupUser.getRoleslistes().iterator(); it.hasNext(); ) {
	    	roles = it.next();
	        if(!groupUse.getRoleslistes().contains(roles)) {
	        	exist=false;
			}
	    }
	    if(exist) {
			throw new Exception("Group "+groupUser.getName()+" already have Role: "+roles.getName());
		}else {
			groupUse.getRoleslistes().add(roles);
			//groupUserRepo.save(groupUse);
		}
	}

	@Override
	public void addPoste(GroupUser groupUser) throws Exception {
		// TODO Auto-generated method stub
		GroupUser groupUse = groupUserRepo.findByIdgroupes(groupUser.getIdgroupes());
		if(groupUse==null) {
			throw new Exception("Group "+groupUser.getName()+" Don't exist");
		}
		boolean exist=true;
		Postes postes=null;
		Set<Postes> set = new HashSet<Postes>();
	    for (Iterator<Postes> it = groupUser.getPosteslistes().iterator(); it.hasNext(); ) {
	    	postes = it.next();
	        if(!groupUse.getPosteslistes().contains(postes)) {
	        	exist=false;
			}
	    }
	    if(exist) {
			throw new Exception("Group "+groupUser.getName()+" already have Poste: "+postes.getName());
		}else {
			postes.getGroupslistes().add(groupUse);
			groupUse.getPosteslistes().add(postes);
		}
	}

	@Override
	public void removeRole(GroupUser groupUser) throws Exception {
		// TODO Auto-generated method stub
		GroupUser groupUse = groupUserRepo.findByIdgroupes(groupUser.getIdgroupes());
		if(groupUse==null) {
			throw new Exception("Don't exist");
		}
		groupUser.getRoleslistes().forEach(
				(role)->{
					if(groupUse.getRoleslistes().contains(role)) {
						groupUse.getRoleslistes().remove(role);
					}
				}
		);
		groupUserRepo.save(groupUse);
	}

	@Override
	public void removePoste(GroupUser groupUser) throws Exception {
		// TODO Auto-generated method stub
		GroupUser groupUse = groupUserRepo.findByIdgroupes(groupUser.getIdgroupes());
		if(groupUse==null) {
			throw new Exception("Don't exist");
		}
		groupUser.getPosteslistes().forEach(
				(poste)->{
					if(groupUse.getPosteslistes().contains(poste)) {
						groupUse.getPosteslistes().remove(poste);
					}
				}
		);
		groupUserRepo.save(groupUse);
	}

	@Override
	public GroupUser findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return groupUserRepo.findByName(name);
	}

	@Override
	public GroupUser findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return groupUserRepo.findByIdgroupes(id);
	}

}
