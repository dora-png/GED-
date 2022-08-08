package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.ProfilesStructure;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.GroupUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.ProfilesRepo;
import com.microservice.ged.repository.ProfilesStructureRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.ProfileStructureBean;

@Transactional
@Service
public class ProfilesServiceImpl implements ProfilesService {
	
	@Autowired
	ProfilesRepo profilesRepo;
	
	@Autowired
	ProfilesStructureRepo profilesStructureRepo;
	
	@Autowired 
	AppUserService appUserService;

	@Override
	public Page<Profiles> findAllProfiles(int page, int size) throws Exception {
		return profilesRepo.findAll(PageRequest.of(page, size,Sort.by("idprofiles").descending()));
	}

	@Override
	public Page<Profiles> searchByProfilesName(String name, int page, int size) throws Exception {
		return profilesRepo.findByNameContaining(name,PageRequest.of(page, size,Sort.by("idprofiles").descending()));
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
	public void add(Profiles profiles) throws Exception {
		profiles.setIdProfiles(null);
		profiles.setDateCreation(null);
		profiles.setStatus(true);
		profilesRepo.save(profiles);
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
	public Profiles findProfileById(Long id) throws Exception {
		return profilesRepo.findByIdprofiles(id);
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
	public void add(Long profileId, Long structureId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserInProfiles(Long idProfiles, String userName) throws Exception {
		Profiles profiles = this.findProfileById(idProfiles);
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
	

}
