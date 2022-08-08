package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.DroitGroups;
import com.microservice.ged.beans.DroitProfiles;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.DroitProfilesRepo;
import com.microservice.ged.repository.GroupProfileRepo;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.DroitProfilesServices;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.GroupProfilesBean;

@Service
@Transactional
public class GroupProfileServiceImpl implements GroupProfileService {
	
	@Autowired
	GroupProfileRepo groupProfileRepo;
	
	@Autowired
	ProfilesService profilesService;
	
	@Autowired
	DroitGroupsService droitGroupsService ;

	@Autowired
	GroupUserService groupUserService;
	
	@Autowired
	GroupUserServiceBasic groupUserServiceBasic;

	@Override
	public Page<GroupProfile> findAllGroupOfProfiles(Long idProfiles, int page, int size) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		return groupProfileRepo.findByProfileId(profiles, PageRequest.of(page, size, Sort.by("idgroupprofile").descending()));
	}

	@Override
	public Page<GroupProfile> findGrouptOfProfilesActive(Long idProfiles, int page, int size) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		return groupProfileRepo.findByProfileIdAndIsactiveTrue(profiles, PageRequest.of(page, size));
	}

	@Override
	public Page<GroupProfile> findAllProfilesInGroup(Long idGroup, int page, int size) throws Exception {
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroup);
		if(groupUser==null) {
			throw new Exception("groupUser id error");
		}
		return groupProfileRepo.findByGroupuserId(groupUser, PageRequest.of(page, size, Sort.by("idgroupprofile").descending()));
	}

	@Override
	public Page<GroupProfile> findProfilesInGroupActive(Long idGroup, int page, int size) throws Exception {
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroup);
		if(groupUser==null) {
			throw new Exception("groupUser id error");
		}
		return groupProfileRepo.findByGroupuserIdAndIsactiveTrue(groupUser, PageRequest.of(page, size, Sort.by("idgroupprofile").descending()));
	}

	@Override
	public Page<GroupUser> findListGroupUserToAdd(Long idProfiles, int page, int size) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		List<Long> groupProfileIdList = new ArrayList<>();
		groupProfileRepo.findByProfileIdAndIsactiveTrue(profiles).forEach(
				(groupProfile)->{
					groupProfileIdList.add(groupProfile.getGroupuserId().getIdgroupes());
				}
		);		
		return groupUserService.findGroupToAdd(groupProfileIdList, page, size);
	}

	@Override
	public void addGroupToProfiles(List<GroupProfilesBean> groupProfilesList) throws Exception {
		groupProfilesList.forEach(
				(groupProfiles)->{
					try {
						GroupUser groupUser = groupUserServiceBasic.findGroupById(groupProfiles.getGroupe());
						Profiles profiles = profilesService.findProfileById(groupProfiles.getProfile());
						if(groupUser!=null && profiles != null) {
							if(groupProfileRepo.findByGroupuserIdAndProfileIdAndIsactiveTrue(groupUser, profiles) == null) {
								groupProfileRepo.save(new GroupProfile(profiles, groupUser, true));							
							}
						}
						
					}catch (Exception e) {
						////////
					}					
				}
		);
		
	}

	@Override
	public void removeGroupToProfiles(GroupProfilesBean groupProfilesBean) throws Exception {
		GroupUser groupUser =  groupUserServiceBasic.findGroupById(groupProfilesBean.getGroupe());
		Profiles profiles = profilesService.findProfileById(groupProfilesBean.getProfile());
		if(groupUser==null) {
			throw new Exception("groupUser not exist");
		}
		if(profiles==null) {
			throw new Exception("profiles not exist");
		}
		GroupProfile groupProfile = groupProfileRepo.findByGroupuserIdAndProfileIdAndIsactiveTrue(groupUser, profiles);
		if(groupProfile != null) {
			groupProfile.setIsactive(false);
			groupProfileRepo.save(groupProfile);							
		}
		
	}

	@Override
	public List<Droits> findListDroit(Long idProfiles) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		List<Long> groupIdList = new ArrayList<>();
		groupProfileRepo.findByProfileIdAndIsactiveTrue(profiles).forEach(
				(groupProfile)->{
					groupIdList.add(groupProfile.getGroupuserId().getIdgroupes());
				}
		);	
		List<Droits> droitsList = new ArrayList<>();
		groupIdList.forEach(
				(groupProfile)->{
					try {
						droitGroupsService.findListDroit(groupProfile).forEach(
								(droits)->{
									if(!droitsList.contains(droits))
										droitsList.add(droits);
								}
						);
					} catch (Exception e) {
						
					}
					
				}
		);	
		return droitsList;
	}

}
