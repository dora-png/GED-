package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.DroitGroups;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.GroupProfileRepo;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.ProfilesServiceBasic;
import com.microservice.ged.utils.GroupProfilesBean;

@Service
@Transactional
public class GroupProfileServiceImpl implements GroupProfileService {
	
	@Autowired
	GroupProfileRepo groupProfileRepo;

	@Override
	public Page<GroupProfile> findAllGroupOfProfiles(Profiles profile, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return groupProfileRepo.findByProfileIdAndIsactiveFalse(profile , PageRequest.of(page, size,Sort.by("idgroupprofile").descending()));				
			case 1:
				return groupProfileRepo.findByProfileIdAndIsactiveTrue(profile , PageRequest.of(page, size,Sort.by("idgroupprofile").descending()));	
			case 2:
				return groupProfileRepo.findByProfileId(profile, PageRequest.of(page, size,Sort.by("idgroupprofile").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<GroupProfile> findAllProfilesInGroup(GroupUser groupUser, int page, int size, int status) throws Exception {

		switch (status) {
			case 0:
				return groupProfileRepo.findByGroupuserIdAndIsactiveFalse(groupUser , PageRequest.of(page, size,Sort.by("idgroupprofile").descending()));				
			case 1:
				return groupProfileRepo.findByGroupuserIdAndIsactiveTrue(groupUser , PageRequest.of(page, size,Sort.by("idgroupprofile").descending()));	
			case 2:
				return groupProfileRepo.findByGroupuserId(groupUser, PageRequest.of(page, size,Sort.by("idgroupprofile").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public GroupUser findGroupOfProfile(Profiles profile) throws Exception {
		return groupProfileRepo.findByProfileIdAndIsactiveTrueAndDateEndIsNull(profile).getGroupuserId();
	}

	@Override
	public void addGroupToProfilesBasic(GroupUser groupUser, List<Profiles> profiles) throws Exception {
		profiles.forEach(
				(profile)->{
					groupProfileRepo.save(new GroupProfile(profile, groupUser, true));
				}
		);
	}

	@Override
	public void removeGroupToProfiles(GroupUser groupUser, Profiles profiles) throws Exception {
		GroupProfile groupProfiles = groupProfileRepo.findByGroupuserIdAndProfileIdAndIsactiveTrueAndDateEndIsNull(groupUser, profiles);
		if(groupProfiles != null) {
			if(groupProfiles.isIsactive()) {
				groupProfiles.setIsactive(false);
				groupProfiles.setDateEnd(new Date());
				groupProfileRepo.save(groupProfiles);							
			}
		}
	}

	@Override
	public void addGroupToProfilesBasic(GroupUser groupUser, Profiles profiles) throws Exception {
		GroupProfile groupProfiles = groupProfileRepo.findByGroupuserIdAndProfileIdAndIsactiveTrueAndDateEndIsNull(groupUser, profiles);
		if(groupProfiles == null) 
			groupProfileRepo.save(new GroupProfile(profiles, groupUser, true));
		
	}
	
	@Override
	public void addGroupToProfiles(GroupUser groupUser, List<Profiles> profiles) throws Exception {
		List<GroupProfile> gpList = groupProfileRepo.findByGroupuserId(groupUser);
		if(gpList.isEmpty()) {
			profiles.forEach(
					(profile)->{
						try {
							groupProfileRepo.save(new GroupProfile(profile, groupUser, true));														
						}catch (Exception e) {
						}					
					}
			);
		} else {
			for(GroupProfile gp : gpList) {}
			
		}
		
				
	}

	@Override
	public Page<Profiles> findAllProfilesInGroupPage(GroupUser groupUser, int page, int size, int status) throws Exception {
		return null;
	}

	@Override
	public List<Profiles> findAllProfilesInGroupList(GroupUser groupUser, int status) throws Exception {
		List<GroupProfile> groupProfiles = new ArrayList<>();
		switch (status) {
			case 0:
				groupProfiles = groupProfileRepo.findByGroupuserIdAndIsactiveFalse(groupUser);				
			case 1:
				groupProfiles = groupProfileRepo.findByGroupuserIdAndIsactiveTrue(groupUser);	
			case 2:
				groupProfiles = groupProfileRepo.findByGroupuserId(groupUser);
			default:
				//throw new Exception("Bad request");
		}
		List<Profiles> list = new ArrayList<>();
		groupProfiles.forEach(
				(groupProfile)->{
					if(groupProfile.isIsactive()) {
						if(!list.contains(groupProfile.getProfileId()))
							list.add(groupProfile.getProfileId());
						
					}
				}
		);
		
		
		return list;
	}

	@Override
	public List<Long> findAllIdProfilesInGroupList(GroupUser groupUser, int status) throws Exception {
		List<Long> profilesIdList = new ArrayList<>();
		List<GroupProfile> groupProfiles = new ArrayList<>();
		switch (status) {
			case 0:
				groupProfiles = groupProfileRepo.findByGroupuserIdAndIsactiveFalse(groupUser);				
			case 1:
				groupProfiles = groupProfileRepo.findByGroupuserIdAndIsactiveTrue(groupUser);	
			default:
				//throw new Exception("Bad request");
		}
		groupProfiles.forEach(
				(groupProfile)->{
					profilesIdList.add(groupProfile.getProfileId().getIdProfiles());
				}
		);	
		return profilesIdList;
	}

}
