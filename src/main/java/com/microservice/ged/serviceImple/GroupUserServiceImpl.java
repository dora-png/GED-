package com.microservice.ged.serviceImple;

import java.util.ArrayList;
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

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.GroupUserRepo;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.DroitService;
import com.microservice.ged.service.DroitServiceBasic;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.ProfilesServiceBasic;
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
	DroitServiceBasic droitServiceBasic;
	
	@Autowired
	DroitService droitService;
		
	@Autowired
	GroupProfileService groupProfileService;

	@Autowired
	ProfilesServiceBasic profilesServiceBasic;
	
	@Autowired
	ProfilesService profilesService;
	
	@Override
	public Page<GroupUser> findAllGroup(int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return groupUserRepo.findByStatusFalse(PageRequest.of(page, size,Sort.by("idgroupes").descending()));				
			case 1:
				return groupUserRepo.findByStatusTrue(PageRequest.of(page, size,Sort.by("idgroupes").descending()));
			case 2:
				return groupUserRepo.findAll(PageRequest.of(page, size, Sort.by("idgroupes").descending()));
			default:
				throw new Exception("Bad request");
		}
	}
	
	@Override
	public Page<GroupUser> findGroupByNameContaining(String name, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return groupUserRepo.findByNameContainingAndStatusFalse(name , PageRequest.of(page, size,Sort.by("idgroupes").descending()));				
			case 1:
				return groupUserRepo.findByNameContainingAndStatusTrue(name , PageRequest.of(page, size,Sort.by("idgroupes").descending()));	
			case 2:
				return groupUserRepo.findByNameContaining(name, PageRequest.of(page, size,Sort.by("idgroupes").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<GroupUser> findGroupBySigleContaining(String sigle, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return groupUserRepo.findBySigleContainingAndStatusFalse(sigle , PageRequest.of(page, size,Sort.by("idgroupes").descending()));				
			case 1:
				return groupUserRepo.findBySigleContainingAndStatusTrue(sigle , PageRequest.of(page, size,Sort.by("idgroupes").descending()));	
			case 2:
				return groupUserRepo.findBySigleContaining(sigle, PageRequest.of(page, size,Sort.by("idgroupes").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public void saveGroupUser(GroupUser groupUser, List<Long> listDroits) throws Exception {
		List<Droits> droits = new ArrayList<>();
		Droits droit = null;
		for(Long i : listDroits) {
			droit = droitServiceBasic.findDroitsById(i);
			if(droit == null ) {
				throw new Exception("Invalid data");				
			}
			if(!droits.contains(droit) ) {
				droits.add(droit);
			}
		}
		groupUser.setDateCreation(null);
		groupUser.setIdgroupes(null);
		groupUser.setStatus(true);
		groupUser = groupUserRepo.save(groupUser);		
		droitGroupsService.addDroitToGroupBasic(groupUser, droits);
	}

	@Override
	public GroupUser findGroupByName(String name) throws Exception {
		return groupUserRepo.findByName(name);
	}

	@Override
	public GroupUser findGroupBySigle(String sigle) throws Exception {
		return groupUserRepo.findBySigle(sigle);
	}

	@Override
	public void updateGroupUser(GroupUser groupUser) throws Exception {
		GroupUser groupUsers = groupUserRepo.findByIdgroupes(groupUser.getIdgroupes());
		if(groupUsers == null ) {
			throw new Exception("Invalid data");				
		}		
		groupUser.setIdgroupes(groupUsers.getIdgroupes());
		groupUser.setDateCreation(groupUsers.getDateCreation());
		groupUser = groupUserRepo.save(groupUser);	
	}

	@Override
	public Page<GroupUser> listGroupToAdd(Long profileId, int page, int size, int status) throws Exception {
		Profiles profile = profilesServiceBasic.findProfileById(profileId);
		if(profile==null)
			throw new Exception("Profiles not found");
		List<Long> ids = new ArrayList<>();
		GroupUser groupUser = groupProfileService.findGroupOfProfile(profile);
		if(groupUser != null) {
			if(groupUser.getStatus()) {
				ids.add(groupUser.getIdgroupes());				
			}
		}
		switch (status) {
			case 0:
				return groupUserRepo.findByIdgroupesNotInAndStatusFalse(ids, PageRequest.of(page, size,Sort.by("idgroupes").descending()));				
			case 1:
				return groupUserRepo.findByIdgroupesNotInAndStatusTrue(ids, PageRequest.of(page, size,Sort.by("idgroupes").descending()));
			case 2:
				return groupUserRepo.findByIdgroupesNotIn(ids, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<GroupUser> listGroupToAddFindGroupByNameContaining(String name, Long profileId, int page, int size, int status) throws Exception {
		Profiles profile = profilesServiceBasic.findProfileById(profileId);
		if(profile==null)
			throw new Exception("Profiles not found");
		List<Long> ids = new ArrayList<>();
		GroupUser groupUser = groupProfileService.findGroupOfProfile(profile);
		if(groupUser != null) {
			if(groupUser.getStatus()) {
				ids.add(groupUser.getIdgroupes());				
			}
		}
		switch (status) {
			case 0:
				return groupUserRepo.findByNameContainingAndIdgroupesNotInAndStatusFalse(name, ids, PageRequest.of(page, size,Sort.by("idgroupes").descending()));				
			case 1:
				return groupUserRepo.findByNameContainingAndIdgroupesNotInAndStatusTrue(name, ids, PageRequest.of(page, size,Sort.by("idgroupes").descending()));
			case 2:
				return groupUserRepo.findByNameContainingAndIdgroupesNotIn(name, ids, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<GroupUser> listGroupToAddFindGroupBySigleContaining(String sigle, Long profileId, int page, int size, int status) throws Exception {
		Profiles profile = profilesServiceBasic.findProfileById(profileId);
		if(profile==null)
			throw new Exception("Profiles not found");
		List<Long> ids = new ArrayList<>();
		GroupUser groupUser = groupProfileService.findGroupOfProfile(profile);
		if(groupUser != null) {
			if(groupUser.getStatus()) {
				ids.add(groupUser.getIdgroupes());				
			}
		}
		switch (status) {
			case 0:
				return groupUserRepo.findBySigleContainingAndIdgroupesNotInAndStatusFalse(sigle, ids, PageRequest.of(page, size,Sort.by("idgroupes").descending()));				
			case 1:
				return groupUserRepo.findBySigleContainingAndIdgroupesNotInAndStatusTrue(sigle, ids, PageRequest.of(page, size,Sort.by("idgroupes").descending()));
			case 2:
				return groupUserRepo.findBySigleContainingAndIdgroupesNotIn(sigle, ids, PageRequest.of(page, size, Sort.by("idgroupes").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public List<Long> listDroitInGroupUser(Long groupId, int status) throws Exception {
		GroupUser groupUser  = groupUserRepo.findByIdgroupes(groupId);
		if(groupUser == null) {
			throw new Exception("Invalid data");
		}		
		return droitGroupsService.findListDroit(groupUser, status);
	}

	@Override
	public List<Droits> listDroits(Long groupId, int status) throws Exception {
		GroupUser groupUser = groupUserRepo.findByIdgroupes(groupId);
		if(groupUser == null)
			throw new Exception("Group Not Exist");
		return droitGroupsService.findDroitOfGroup(groupUser, status);
	}

	@Override
	public List<Profiles> listProfiles(Long groupId, int status) throws Exception {
		GroupUser groupUser = groupUserRepo.findByIdgroupes(groupId);
		if(groupUser == null)
			throw new Exception("Group Not Exist");
		return groupProfileService.findAllProfilesInGroupList(groupUser, status);
	}

	@Override
	public Page<Droits> listDroitInGroupUserPage(Long groupId, int page, int size, int status) throws Exception {
		List<Long> list = this.listDroitInGroupUser(groupId, status);		
		return droitService.findDroitsToGroup(list,page,size);
	}
	
	@Override
	public Page<Droits> listDroitInGroupUserPageName(Long groupId, String name, int page, int size, int status) throws Exception {
		List<Long> list = this.listDroitInGroupUser(groupId, status);		
		return droitService.findDroitsToGroupName(list, name, page, size);
	}

	@Override
	public Page<Droits> listDroitToAddInGroupUser(Long groupId, int page, int size) throws Exception {
		List<Long> ids = this.listDroitInGroupUser(groupId, 1);
		if(ids.isEmpty()) {
			return droitService.findAllDroits(page, size);
		}	
		return droitService.findDroitsToAdd(ids, page, size);
	}

	@Override
	public Page<Droits> listDroitToAddInGroupUserName(Long groupId, String name, int page, int size) throws Exception {
		List<Long> ids = this.listDroitInGroupUser(groupId, 1);
		if(ids.isEmpty()) {
			return droitService.findDroitsByDescription(name,page, size);
		}	
		return droitService.findDroitsToAddName(ids, name, page, size);
	}

	@Override
	public List<Long> listProfilesInGroupUser(Long groupId, int status) throws Exception {
		GroupUser groupUser  = groupUserRepo.findByIdgroupes(groupId);
		if(groupUser == null) {
			throw new Exception("Invalid data");
		}		
		return groupProfileService.findAllIdProfilesInGroupList(groupUser, status);
	}

	@Override
	public Page<Profiles> listProfilesInGroupUserPage(Long groupId, int page, int size, int status) throws Exception {
		List<Long> list = this.listProfilesInGroupUser(groupId, status);
		return profilesService.findProfilesToGroup(list, page, size);
	}

	@Override
	public Page<Profiles> listProfilesInGroupUserPageName(Long groupId, String name, int page, int size, int status)
			throws Exception {
		List<Long> list = this.listProfilesInGroupUser(groupId, status);	
		return profilesService.findProfilesToGroupName(list, name, page, size);
	}

	@Override
	public Page<Profiles> listProfilesToAddInGroupUser(Long groupId, int page, int size) throws Exception {
		List<Long> ids = this.listProfilesInGroupUser(groupId, 1);
		if(ids.isEmpty()) {
			return profilesService.findAllProfiles(page, size, 1);
		}
		return profilesService.findProfilesToAdd(ids, page, size);
	}

	@Override
	public Page<Profiles> listProfilesToAddInGroupUserName(Long groupId, String name, int page, int size)
			throws Exception {
		List<Long> ids = this.listProfilesInGroupUser(groupId, 1);
		if(ids.isEmpty()) {
			return profilesService.searchByProfilesName(name,page, size,1);
		}	
		return profilesService.findProfilesToAddName(ids, name, page, size);
	}

	@Override
	public void addDroitToGroup(Long idGroupUser, Long idDroit) throws Exception {
		GroupUser groupUser = groupUserRepo.findByIdgroupes(idGroupUser);
		if(groupUser==null) {
			throw new Exception("group not found");
		}
		Droits droit = droitServiceBasic.findDroitsById(idDroit);
		if(droit==null) {
			throw new Exception("droit not found");
		}
		droitGroupsService.addDroitToGroup(groupUser, droit);
	}

	@Override
	public void removeDroitToGroup(Long idGroupUser, Long idDroit) throws Exception {
		GroupUser groupUser = groupUserRepo.findByIdgroupes(idGroupUser);
		if(groupUser==null) {
			throw new Exception("group not found");
		}
		Droits droit = droitServiceBasic.findDroitsById(idDroit);
		if(droit==null) {
			throw new Exception("droit not found");
		}
		droitGroupsService.removeDroitToGroup(groupUser, droit);
	}
	
	@Override
	public void addProfileToGroup(Long idGroupUser, Long idProfile) throws Exception {
		GroupUser groupUser = groupUserRepo.findByIdgroupes(idGroupUser);
		if(groupUser==null) {
			throw new Exception("group not found");
		}
		Profiles profile = profilesServiceBasic.findProfileById(idProfile);
		if(profile==null) {
			throw new Exception("profile not found");
		}
		groupProfileService.addGroupToProfilesBasic(groupUser, profile);
	}

	@Override
	public void removeProfileToGroup(Long idGroupUser, Long idProfile) throws Exception {
		GroupUser groupUser = groupUserRepo.findByIdgroupes(idGroupUser);
		if(groupUser==null) {
			throw new Exception("group not found");
		}
		Profiles profile = profilesServiceBasic.findProfileById(idProfile);
		if(profile==null) {
			throw new Exception("profile not found");
		}
		groupProfileService.removeGroupToProfiles(groupUser, profile);
	}

}
