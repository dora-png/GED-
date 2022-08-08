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
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.repository.DroitProfilesRepo;
import com.microservice.ged.service.DroitProfilesServices;
import com.microservice.ged.service.DroitService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.utils.ProfilesDroitBean;

@Service
@Transactional
public class DroitProfilesServicesImpl implements DroitProfilesServices {
	
	@Autowired
	DroitProfilesRepo droitProfilesRepo;
	
	@Autowired
	ProfilesService profilesService;
	

	@Autowired
	DroitService droitService;

	@Override
	public Page<DroitProfiles> findAllDroitOfProfiles(Profiles profiles, int page, int size) throws Exception {
		return droitProfilesRepo.findByProfileId(profiles, PageRequest.of(page, size, Sort.by("iddroitprofile").descending()));
	}

	@Override
	public Page<DroitProfiles> findDroitOfProfilesActive(Long idProfiles, int page, int size) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		return droitProfilesRepo.findByProfileIdAndIsactiveTrue(profiles, PageRequest.of(page, size, Sort.by("iddroitprofile").descending()));
	}

	@Override
	public Page<Droits> findListDroitToAdd(Long idProfiles, int page, int size) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		List<Long> droitsIdList = new ArrayList<>();
		droitProfilesRepo.findByProfileIdAndIsactiveTrue(profiles).forEach(
				(droitProfile)->{
					droitsIdList.add(droitProfile.getDroitId().getIdDroit());
				}
		);		
		return droitService.findDroitsToAdd(droitsIdList, page, size);
	}

	@Override
	public void addDroitToProfiles(List<ProfilesDroitBean> profilesDroitBeanList) throws Exception {
		profilesDroitBeanList.forEach(
				(profilesDroitBean)->{
					try {
						Droits droits = droitService.findDroitsById(profilesDroitBean.getDroit());
						Profiles profiles = profilesService.findProfileById(profilesDroitBean.getProfile());
						if(droits!=null && profiles != null) {
							if(droitProfilesRepo.findByDroitIdAndProfileIdAndIsactiveTrue(droits, profiles) == null) {
								droitProfilesRepo.save(new DroitProfiles(droits, profiles, true));							
							}
						}
						
					}catch (Exception e) {
						////////
					}					
				}
		);
	}

	@Override
	public void removeDroitToProfiles(ProfilesDroitBean profilesDroitBean) throws Exception {
		Droits droits = droitService.findDroitsById(profilesDroitBean.getDroit());
		Profiles profiles = profilesService.findProfileById(profilesDroitBean.getProfile());
		if(droits==null) {
			throw new Exception("Droit not exist");
		}
		if(profiles==null) {
			throw new Exception("profiles not exist");
		}
		DroitProfiles droitProfiles = droitProfilesRepo.findByDroitIdAndProfileIdAndIsactiveTrue(droits, profiles);
		if(droitProfiles != null) {
			droitProfiles.setIsactive(false);
			droitProfilesRepo.save(droitProfiles);							
		}else {
			throw new Exception("sdfdfsdfgsdfgdf");
		}
		
	}

	
	@Override
	public List<Droits> findListDroit(Long idProfiles) throws Exception {
		Profiles profiles = profilesService.findProfileById(idProfiles);
		if(profiles==null) {
			throw new Exception("profiles id error");
		}
		List<Long> droitsIdList = new ArrayList<>();
		droitProfilesRepo.findByProfileIdAndIsactiveTrue(profiles).forEach(
				(droitProfile)->{
					droitsIdList.add(droitProfile.getDroitId().getIdDroit());
				}
		);	
		List<Droits> droitsList = new ArrayList<>();
		droitsIdList.forEach(
				(droitId)->{
					try {
						Droits droits = null;
						droits = droitService.findDroitsById(droitId);
						if(!droitsList.contains(droits)) {
							droitsList.add(droits);
						}
					} catch (Exception e) {
						//
					}
					
				}
		);
		return droitsList;
	}

}
