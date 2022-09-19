package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.ProfilesStructure;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.utils.ProfileStructureBean;

public interface ProfilesService {
	public Page<Profiles> findAllProfiles(int page, int size, int status) throws Exception;
	public Page<Profiles> searchByProfilesName(String name,int page, int size, int status) throws Exception;
	public Page<Profiles> searchByProfilesType(TypeUser typeprofil,int page, int size) throws Exception;	
	void addUserInProfiles(Long idProfiles, String userName) throws Exception;
	public Page<ProfilesStructure> histotiqueProfilee(Long profileId,int page, int size) throws Exception;
	public ProfileStructureBean currentStructureOfProfile(Long profileId) throws Exception ;
	public List<String> listUserToAffect(Long profile, int page, int size) throws Exception;
	public void add(Profiles profiles, Long idStructure, Long idGroupUser) throws Exception;
	public void update(Profiles profiles, Long idGroupUser, Long lastGroupUser) throws Exception;
	public void updateName(Long idProfile, String name) throws Exception;
	public void updateUser(Long idProfile, String user) throws Exception;
	public void setStatus(Long idProfile) throws Exception ;
	public String getPwdUserInProfole(String userLogin, Long idProfile) throws Exception;
	public void changePwd(String userLogin, Long idProfile) throws Exception;
	public Profiles findProfileByName(String name) throws Exception;
	public Profiles findProfileByUserLogin(String login) throws Exception;
	public Profiles findProfileByUserName(String currentUser) throws Exception;
	

	public Page<Profiles> findProfilesToAdd(List<Long> profilesIdList, int page, int size) throws Exception ;
	public Page<Profiles> findProfilesToGroup(List<Long> profilesIdList, int page, int size) throws Exception ;
	public Page<Profiles> findProfilesToGroupName(List<Long> profilesIdList, String name, int page, int size) throws Exception ;
	public Page<Profiles> findProfilesToAddName(List<Long> profilesIdList, String name, int page, int size) throws Exception ;

}
