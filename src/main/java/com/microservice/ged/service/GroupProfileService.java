package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.utils.GroupProfilesBean;

public interface GroupProfileService {
	Page<GroupProfile> findAllGroupOfProfiles(Long idProfiles, int page, int size) throws Exception;
	Page<GroupProfile> findGrouptOfProfilesActive(Long idProfiles, int page, int size)throws Exception;
	Page<GroupProfile> findAllProfilesInGroup(Long idGroup, int page, int size) throws Exception;
	Page<GroupProfile> findProfilesInGroupActive(Long idGroup, int page, int size)throws Exception;
	Page<GroupUser> findListGroupUserToAdd(Long idProfiles, int page, int size)throws Exception;
	List<Droits> findListDroit(Long idProfiles)throws Exception;
	void addGroupToProfiles(List<GroupProfilesBean> groupProfilesList) throws Exception;
	void removeGroupToProfiles(GroupProfilesBean groupProfilesBean) throws Exception;

}
