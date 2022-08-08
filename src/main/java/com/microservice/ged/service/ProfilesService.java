package com.microservice.ged.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.ProfilesStructure;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.utils.ProfileStructureBean;

public interface ProfilesService {
	public Page<Profiles> findAllProfiles(int page, int size) throws Exception;
	public Page<Profiles> searchByProfilesName(String name,int page, int size) throws Exception;
	public Page<Profiles> searchByProfilesType(TypeUser typeprofil,int page, int size) throws Exception;	
	void addUserInProfiles(Long idProfiles, String userName) throws Exception;
	public Page<ProfilesStructure> histotiqueProfilee(Long profileId,int page, int size) throws Exception;
	public ProfileStructureBean currentStructureOfProfile(Long profileId) throws Exception ;
	public void add(Long profileId, Long structureId) throws Exception ;	
	public List<String> listUserToAffect(Long profile, int page, int size) throws Exception;
	public void add(Profiles profiles) throws Exception;
	public void updateName(Long idProfile, String name) throws Exception;
	public void updateUser(Long idProfile, String user) throws Exception;
	public void setStatus(Long idProfile) throws Exception ;
	public String getPwdUserInProfole(String userLogin, Long idProfile) throws Exception;
	public void changePwd(String userLogin, Long idProfile) throws Exception;
	public Profiles findProfileById(Long id) throws Exception;
	public Profiles findProfileByName(String name) throws Exception;
	public Profiles findProfileByUserName(String currentUser) throws Exception;

}
