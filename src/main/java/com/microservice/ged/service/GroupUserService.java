package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.utils.GroupDroitsBean;
import com.microservice.ged.utils.GroupProfilesBean;
import com.microservice.ged.utils.ProfilesDroitBean;

public interface GroupUserService {
	Page<GroupUser> findGroupToAdd(List<Long> groupUserIdList, int page, int size) throws Exception ;
	Page<GroupUser> findAllGroup(int page, int size);
	Page<GroupUser> findAllActiveGroup(int page, int size);
	Page<GroupUser> findGroupByNameContaining(String name, int page, int size);
	Page<GroupUser> findGroupBySigleContaining(String sigle, int page, int size);
	void saveGroupUser(GroupUser groupUser) throws Exception;
	void updateNameGroupUser(Long idGroup, String name) throws Exception;
	void updateSigleGroupUser(Long idGroup, String sigle) throws Exception;
	void updateStatusGroupUser(Long idGroup) throws Exception;
	void addDroitToGroupe(List<GroupDroitsBean> groupDroitsBeanList) throws Exception;
	void removeDroitToGroupe(GroupDroitsBean groupDroitsBean) throws Exception;
	void addDroitToProfil(List<ProfilesDroitBean> profilesDroitBeanList) throws Exception;
	void removeDroitToProfil(ProfilesDroitBean profilesDroitBean) throws Exception;
	GroupUser findGroupByName(String name) throws Exception;
	GroupUser findGroupBySigle(String sigle) throws Exception;

}
