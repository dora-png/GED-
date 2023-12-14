package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.utils.GroupDroitsBean;
import com.microservice.ged.utils.GroupProfilesBean;

public interface GroupUserService {
	Page<GroupUser> findAllGroup(int page, int size, int status) throws Exception ;
	Page<GroupUser> listGroupToAdd(Long profileId, int page, int size, int status) throws Exception ;
	Page<GroupUser> listGroupToAddFindGroupByNameContaining(String name, Long profileId, int page, int size, int status) throws Exception ;
	Page<GroupUser> listGroupToAddFindGroupBySigleContaining(String sigle, Long profileId, int page, int size, int status) throws Exception ;
	List<Long> listDroitInGroupUser(Long groupId, int status) throws Exception;
	Page<Droits> listDroitInGroupUserPage(Long groupId, int page, int size, int status) throws Exception ;
	Page<Droits> listDroitInGroupUserPageName(Long groupId, String name, int page, int size, int status) throws Exception ;
	Page<Droits> listDroitToAddInGroupUser(Long groupId, int page, int size) throws Exception ;
	Page<Droits> listDroitToAddInGroupUserName(Long groupId, String name, int page, int size) throws Exception ;
	Page<GroupUser> findGroupByNameContaining(String name, int page, int size, int status) throws Exception ;
	Page<GroupUser> findGroupBySigleContaining(String sigle, int page, int size, int status) throws Exception ;
	void saveGroupUser(GroupUser groupUser, List<Long> listDroits) throws Exception;
	void updateGroupUser(GroupUser groupUser) throws Exception;
	void addDroitToGroup(Long idGroupUser, Long idDroit) throws Exception;
	void removeDroitToGroup(Long idGroupUser, Long idDroit) throws Exception;
	void addProfileToGroup(Long idGroupUser, Long idProfile) throws Exception;
	void removeProfileToGroup(Long idGroupUser, Long idProfile) throws Exception;
	GroupUser findGroupByName(String name) throws Exception;
	GroupUser findGroupBySigle(String sigle) throws Exception;
	List<Droits> listDroits(Long groupId, int status) throws Exception;
	List<Profiles> listProfiles(Long groupId, int status) throws Exception;	
	List<Long> listProfilesInGroupUser(Long groupId, int status) throws Exception;
	Page<Profiles> listProfilesInGroupUserPage(Long groupId, int page, int size, int status) throws Exception ;
	Page<Profiles> listProfilesInGroupUserPageName(Long groupId, String name, int page, int size, int status) throws Exception ;
	Page<Profiles> listProfilesToAddInGroupUser(Long groupId, int page, int size) throws Exception ;
	Page<Profiles> listProfilesToAddInGroupUserName(Long groupId, String name, int page, int size) throws Exception ;

}
