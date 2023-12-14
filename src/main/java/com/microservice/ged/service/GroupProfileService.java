package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.utils.GroupProfilesBean;

public interface GroupProfileService {
	Page<GroupProfile> findAllGroupOfProfiles(Profiles profile, int page, int size, int status) throws Exception;
	Page<GroupProfile> findAllProfilesInGroup(GroupUser groupUser, int page, int size, int status) throws Exception;
	Page<Profiles> findAllProfilesInGroupPage(GroupUser groupUser, int page, int size, int status) throws Exception;
	List<Profiles> findAllProfilesInGroupList(GroupUser groupUser, int status) throws Exception;
	List<Long> findAllIdProfilesInGroupList(GroupUser groupUser, int status) throws Exception;
	GroupUser findGroupOfProfile(Profiles profile)throws Exception;
	void addGroupToProfilesBasic(GroupUser groupUser, Profiles profiles) throws Exception;
	void addGroupToProfilesBasic(GroupUser groupUser, List<Profiles> profiles) throws Exception;
	void addGroupToProfiles(GroupUser groupUser, List<Profiles> profiles) throws Exception;
	void removeGroupToProfiles(GroupUser groupUser, Profiles profiles) throws Exception;

}
