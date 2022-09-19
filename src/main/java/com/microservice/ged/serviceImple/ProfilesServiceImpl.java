package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.ProfilesStructure;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.repository.GroupProfileRepo;
import com.microservice.ged.repository.ProfilesRepo;
import com.microservice.ged.repository.ProfilesStructureRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.utils.ProfileStructureBean;

@Transactional
@Service
public class ProfilesServiceImpl implements ProfilesService {
	
	@Autowired
	ProfilesRepo profilesRepo;
	
	@Autowired
	GroupProfileRepo groupProfileRepo;
	
	@Autowired
	ProfilesStructureRepo profilesStructureRepo;
	
	@Autowired 
	AppUserService appUserService;
	
	@Autowired
	StructureService structureService;
	
	@Autowired
	GroupUserServiceBasic groupUserServiceBasic;

	
	@Override
	public Page<Profiles> findAllProfiles(int page, int size, int status) throws Exception {
		switch (status) {
		case 0:
			return profilesRepo.findByStatusFalse(PageRequest.of(page, size,Sort.by("idprofiles").descending()));					
		case 1:
			return profilesRepo.findByStatusTrue(PageRequest.of(page, size,Sort.by("idprofiles").descending()));	
		case 2:
			return profilesRepo.findAll(PageRequest.of(page, size,Sort.by("idprofiles").descending()));
		default:
			throw new Exception("Bad request");
		}
	}

	@Override
	public Page<Profiles> searchByProfilesName(String name, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return profilesRepo.findByNameContainingAndStatusFalse(name , PageRequest.of(page, size,Sort.by("idprofiles").descending()));				
			case 1:
				return profilesRepo.findByNameContainingAndStatusTrue(name , PageRequest.of(page, size,Sort.by("idprofiles").descending()));	
			case 2:
				return profilesRepo.findByNameContaining(name, PageRequest.of(page, size,Sort.by("idprofiles").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public Page<Profiles> searchByProfilesType(TypeUser typeprofil, int page, int size) throws Exception {
		return profilesRepo.findByTypeprofilAndStatusTrue(typeprofil,PageRequest.of(page, size,Sort.by("idprofiles").descending()));
	}

	@Override
	public List<String> listUserToAffect(Long profile, int page, int size) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(profile);
		if(profiles == null ) {
			throw new Exception("Profile donc exist");
		}
		if(profiles.getTypeprofil().equals(TypeUser.EXTERN_ACTOR)) {
			return appUserService.findAllUserByNameNotLike(profiles.getCurrentUser());
		} else {
			return appUserService.findAllUserByNameNotLike(profiles.getCurrentUser());
		}
	}

	@Override
	public void add(Profiles profiles, Long idStructure, Long idGroupUser) throws Exception {
		Structures structures = structureService.findByIdStructure(idStructure);
		if(structures==null) {
			throw new Exception("structure non existante");
		}
		if(appUserService.findUserByName(profiles.getCurrentUser())==null){
			throw new Exception("User "+profiles.getCurrentUser()+" don't exist");
		}
		profiles.setIdProfiles(null);
		profiles.setDateCreation(null);
		profiles.setStatus(true);
		profiles.setStructure(structures);
		profiles = profilesRepo.save(profiles);
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroupUser);
		if(groupUser==null) {
			throw new Exception("Profile creer sans le groupe (groupe user inexistant)");
		}else {
			if(groupProfileRepo.findByGroupuserIdAndProfileIdAndIsactiveTrueAndDateEndIsNull(groupUser, profiles) == null) {
				groupProfileRepo.save(new GroupProfile(profiles, groupUser, true));							
			}
		}
				
	}
	
	@Override
	public void updateUser(Long idProfile, String user) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(idProfile);
		if(profiles == null) {
			throw new Exception("profile don't exist");
		}
		String appUser = appUserService.findUserByName(user);
		if(appUser == null) {
			throw new Exception("User don't exist");
		}
		profiles.setCurrentUser(user);
		profilesRepo.save(profiles);		
	}

	@Override
	public void updateName(Long idProfile, String name) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(idProfile);
		if(profiles == null) {
			throw new Exception("profile don't exist");
		}
		profiles.setName(name);
		profilesRepo.save(profiles);	
	}

	@Override
	public void setStatus(Long idProfile) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(idProfile);
		if(profiles == null) {
			throw new Exception("profile don't exist");
		}
		profiles.setStatus(!profiles.isStatus());
		profilesRepo.save(profiles);
	}

	@Override
	public String getPwdUserInProfole(String userLogin, Long idProfile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePwd(String userLogin, Long idProfile) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Profiles findProfileByName(String name) throws Exception {
		return profilesRepo.findByName(name);
	}

	@Override
	public Profiles findProfileByUserName(String currentUser) throws Exception {
		return profilesRepo.findByCurrentUser(currentUser);
	}
	
	@Override
	public Profiles findProfileByUserLogin(String login) throws Exception {
		String name = appUserService.findUserByLogin(login);
		return profilesRepo.findByCurrentUser(name);
	}

	@Override
	public Page<ProfilesStructure> histotiqueProfilee(Long profileId, int page, int size) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(profileId);
		return profilesStructureRepo.findByProfilesId(profiles, PageRequest.of(page, size,Sort.by("dateSend").descending()));
	}

	@Override
	public ProfileStructureBean currentStructureOfProfile(Long profileId) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(profileId);
		ProfilesStructure profilesStructure =  profilesStructureRepo.findByProfilesIdAndDateEndIsNull(profiles);
		return profilesStructure == null ? 
				null : 
				new ProfileStructureBean(
					profilesStructure.getStructureId().getIdstructure(), 
					profilesStructure.getStructureId().getName(), 
					profilesStructure.getStructureId().getSigle()
				)
				;
	}

	@Override
	public void addUserInProfiles(Long idProfiles, String userName) throws Exception {
		Profiles profiles = profilesRepo.findByIdprofiles(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		String user = appUserService.findUserByName(userName);
		if(user==null) {
			throw new Exception("user "+userName+" don't exist");
		}
		profiles.setCurrentUser(user);
		profilesRepo.save(profiles);
		
	}

	@Override
	public void update(Profiles profiles, Long idGroupUser, Long lastGroupUser) throws Exception {
		
		if(appUserService.findUserByName(profiles.getCurrentUser())==null){
			throw new Exception("User "+profiles.getCurrentUser()+" don't exist");
		}
		Profiles profile = profilesRepo.findByIdprofiles(profiles.getIdProfiles());
		if(profile==null) {
			throw new Exception("le profile es inexistant");
		}
		GroupUser groupUserLast = groupUserServiceBasic.findGroupById(lastGroupUser);
		GroupUser groupUserNew = groupUserServiceBasic.findGroupById(idGroupUser);
		if(groupUserNew==null) {
			throw new Exception("new groupe user inexistant");
		}
		if(groupUserLast==null) {
			throw new Exception("Last groupe user inexistant");
		}		
		if(groupUserLast.getIdgroupes() != groupUserNew.getIdgroupes()) {
			GroupProfile groupProfileLast =  groupProfileRepo.findByGroupuserIdAndProfileIdAndIsactiveTrueAndDateEndIsNull(groupUserLast, profile);
			if(groupProfileLast == null) {
				throw new Exception("new groupe user inexistant");						
			}
			groupProfileLast.setIsactive(false);
			groupProfileRepo.save(groupProfileLast);
			List<TypeUser> listTypeUser = new ArrayList<>();
			listTypeUser.add(TypeUser.EXTERN_ACTOR);
			listTypeUser.add(TypeUser.INTERN_ACTOR);
			if(!listTypeUser.contains(profiles.getTypeprofil())) {
				throw new Exception("Type de profile inexistant");
			}
			profile.setCurrentUser(profiles.getCurrentUser());
			profile.setName(profiles.getName() );
			profile.setTypeprofil(profiles.getTypeprofil());
			profiles = profilesRepo.save(profiles);
			groupProfileRepo.save(new GroupProfile(profile, groupUserNew, true));	
		}else {
			profile.setCurrentUser(profiles.getCurrentUser());
			profile.setName(profiles.getName() );
			profile.setTypeprofil(profiles.getTypeprofil());
			profiles = profilesRepo.save(profiles);			
		}
		
	}

	@Override
	public Page<Profiles> findProfilesToAdd(List<Long> profilesIdList, int page, int size) throws Exception {
		return profilesRepo.findByIdprofilesNotInAndStatusTrue(profilesIdList, PageRequest.of(page, size));
	}

	@Override
	public Page<Profiles> findProfilesToGroup(List<Long> profilesIdList, int page, int size) throws Exception {
		return profilesRepo.findByIdprofilesInAndStatusTrue(profilesIdList, PageRequest.of(page, size));
	}

	@Override
	public Page<Profiles> findProfilesToGroupName(List<Long> profilesIdList, String name, int page, int size)
			throws Exception {
		return profilesRepo.findByIdprofilesInAndNameContainingAndStatusTrue(profilesIdList, name, PageRequest.of(page, size));
	}

	@Override
	public Page<Profiles> findProfilesToAddName(List<Long> profilesIdList, String name, int page, int size)
			throws Exception {
		return profilesRepo.findByIdprofilesNotInAndNameContainingAndStatusTrue(profilesIdList, name, PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}
	

}
