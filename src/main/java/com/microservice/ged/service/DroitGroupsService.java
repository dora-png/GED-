package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.DroitGroups;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.utils.GroupDroitsBean;

public interface DroitGroupsService {
	Page<DroitGroups> findAllDroitOfGroup(Long idGroupusers, int page, int size) throws Exception;
	Page<DroitGroups> findDroitOfGroupActive(Long idGroupusers, int page, int size)throws Exception;
	Page<Droits> findListDroitToAdd(Long idGroupusers, int page, int size)throws Exception;
	List<Droits> findListDroit(Long idGroupusers)throws Exception;
	void addDroitToGroup(List<GroupDroitsBean> groupDroitsBeanList) throws Exception;
	void removeDroitToGroup(GroupDroitsBean groupDroitsBean) throws Exception;
}
