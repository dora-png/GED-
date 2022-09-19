package com.microservice.ged.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.DroitGroups;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.utils.GroupDroitsBean;

public interface DroitGroupsService {
	Page<DroitGroups> findAllDroitOfGroup(GroupUser groupUser, int page, int size, int status) throws Exception;
	List<Long> findListDroit(GroupUser groupUser, int status)throws Exception;
	List<Droits> findDroitOfGroup(GroupUser groupUser, int status) throws Exception;
	void addDroitToGroupBasic(GroupUser groupUser, List<Droits> droits) throws Exception;
	void addDroitToGroup(GroupUser groupUser, Droits droits) throws Exception;
	void removeDroitToGroup(GroupUser groupUser, Droits droits) throws Exception;
}
