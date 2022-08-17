package com.microservice.ged.serviceImple;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.GroupUserRepo;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.GroupDroitsBean;
import com.microservice.ged.utils.GroupProfilesBean;

@Service
@Transactional
public class GroupUserServiceImpl implements GroupUserService {
	
	@Autowired
	GroupUserRepo groupUserRepo;
	
	@Autowired
	DroitGroupsService droitGroupsService;
		
	@Autowired
	 ProfilesService profilesService;
	
	@Override
	public Page<GroupUser> findAllGroup(int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findAll(PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}
	
	@Override
	public Page<GroupUser> findAllActiveGroup(int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findByStatusTrue(PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}
	
	@Override
	public Page<GroupUser> findGroupByNameContaining(String name, int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findByNameContainingAndStatusTrue(name, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}

	@Override
	public Page<GroupUser> findGroupBySigleContaining(String sigle, int page, int size) {
		// TODO Auto-generated method stub
		return groupUserRepo.findBySigleContainingAndStatusTrue(sigle, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}

	@Override
	public void saveGroupUser(GroupUser groupUser) throws Exception {
		if(groupUserRepo.findByNameAndStatusTrue(groupUser.getName())!=null) {
			throw new Exception("Group with name "+groupUser.getName()+" Already exist");
		}
		if(groupUserRepo.findBySigleAndStatusTrue(groupUser.getName())!=null) {
			throw new Exception("Group with sigle "+groupUser.getName()+" Already exist");
		}
		groupUser.setIdgroupes(null);
		groupUser.setDateCreation(null);
		groupUser.setStatus(true);
		groupUserRepo.save(groupUser);
	}

	@Override
	public void updateNameGroupUser(Long idGroup, String name) throws Exception {
		if(groupUserRepo.findByIdgroupesAndStatusTrue(idGroup)==null) {
			throw new Exception("Group you want to change name don't exist");
		}
		if(groupUserRepo.findByNameAndStatusTrue(name)!=null) {
			throw new Exception("Group with name "+name+" Already exist");
		}
		GroupUser groupUser = groupUserRepo.findByIdgroupesAndStatusTrue(idGroup);
		groupUser.setName(name);
		groupUserRepo.save(groupUser);
	}
	
	@Override
	public void updateSigleGroupUser(Long idGroup, String sigle) throws Exception {
		if(groupUserRepo.findByIdgroupesAndStatusTrue(idGroup)==null) {
			throw new Exception("Group you want to change name don't exist");
		}
		if(groupUserRepo.findBySigleAndStatusTrue(sigle)!=null) {
			throw new Exception("Group with sigle "+sigle+" Already exist");
		}
		GroupUser groupUser = groupUserRepo.findByIdgroupesAndStatusTrue(idGroup);
		groupUser.setSigle(sigle);
		groupUserRepo.save(groupUser);
	}
	
	@Override
	public void updateStatusGroupUser(Long idGroup) throws Exception {
		if(groupUserRepo.findByIdgroupesAndStatusTrue(idGroup)==null) {
			throw new Exception("Group you want to change name don't exist");
		}
		GroupUser groupUser = groupUserRepo.findByIdgroupesAndStatusTrue(idGroup);
		groupUser.setStatus(!groupUser.getStatus());
		groupUserRepo.save(groupUser);
	}

	@Override
	public void addDroitToGroupe(List<GroupDroitsBean> groupDroitsBeanList) throws Exception {
		droitGroupsService.addDroitToGroup(groupDroitsBeanList);
	}

	@Override
	public void removeDroitToGroupe(GroupDroitsBean groupDroitsBean) throws Exception {
		droitGroupsService.removeDroitToGroup(groupDroitsBean);
	}



	@Override
	public GroupUser findGroupByName(String name) throws Exception {
		return groupUserRepo.findByNameAndStatusTrue(name);
	}

	@Override
	public GroupUser findGroupBySigle(String sigle) throws Exception {
		return groupUserRepo.findBySigleAndStatusTrue(sigle);
	}

	@Override
	public Page<GroupUser> findGroupToAdd(List<Long> groupUserIdList, int page, int size) throws Exception {
		return groupUserRepo.findByIdgroupesNotInAndStatusTrue(groupUserIdList, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
	}


}
