package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.DroitProfiles;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.utils.ProfilesDroitBean;

public interface DroitProfilesServices {
	Page<DroitProfiles> findAllDroitOfProfiles(Profiles profiles, int page, int size) throws Exception;
	Page<DroitProfiles> findDroitOfProfilesActive(Long idProfiles, int page, int size)throws Exception;
	Page<Droits> findListDroitToAdd(Long idProfiles, int page, int size)throws Exception;
	List<Droits> findListDroit(Long idProfiles)throws Exception;
	void addDroitToProfiles(List<ProfilesDroitBean> profilesDroitBeanList) throws Exception;
	void removeDroitToProfiles(ProfilesDroitBean profilesDroitBean) throws Exception;
}
