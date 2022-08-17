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
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.repository.DroitGroupsRepo;
import com.microservice.ged.service.DroitGroupsService;
import com.microservice.ged.service.DroitService;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.utils.GroupDroitsBean;

@Service
@Transactional
public class DroitGroupsServiceImpl implements DroitGroupsService {
	
	@Autowired
	DroitGroupsRepo droitGroupsRepo;
	
	@Autowired
	GroupUserServiceBasic groupUserServiceBasic;
	

	@Autowired
	DroitService droitService;

	
	@Override
	public Page<DroitGroups> findAllDroitOfGroup(Long idGroupusers, int page, int size) throws Exception {
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroupusers);
		if(groupUser==null) {
			throw new Exception("Groupe id error");
		}
		return droitGroupsRepo.findByGroupuserId(groupUser, PageRequest.of(page, size, Sort.by("iddroitgroups").descending()));
	}

	@Override
	public Page<DroitGroups> findDroitOfGroupActive(Long idGroupusers, int page, int size) throws Exception {
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroupusers);
		if(groupUser==null) {
			throw new Exception("Groupe id error");
		}
		return droitGroupsRepo.findByGroupuserIdAndIsactiveTrue(groupUser, PageRequest.of(page, size, Sort.by("iddroitgroups").descending()));
	}

	@Override
	public void addDroitToGroup(List<GroupDroitsBean> groupDroitsBeanList) throws Exception {
		groupDroitsBeanList.forEach(
				(groupDroitsBean)->{
					try {
						Droits droits = droitService.findDroitsById(groupDroitsBean.getDroit());
						GroupUser groupUser = groupUserServiceBasic.findGroupById(groupDroitsBean.getGroupe());
						if(droits!=null && groupUser != null) {
							if(droitGroupsRepo.findByDroitIdAndGroupuserIdAndIsactiveTrue(droits, groupUser) == null) {
								droitGroupsRepo.save(new DroitGroups(droits, groupUser, true));							
							}
						}
						
					}catch (Exception e) {
						////////
					}					
				}
		);
		
	}

	@Override
	public void removeDroitToGroup(GroupDroitsBean groupDroitsBean) throws Exception {
		Droits droits = droitService.findDroitsById(groupDroitsBean.getDroit());
		GroupUser groupUser = groupUserServiceBasic.findGroupById(groupDroitsBean.getGroupe());
		if(droits==null) {
			throw new Exception("Droit not exist");
		}
		if(groupUser==null) {
			throw new Exception("groupUser not exist");
		}
		DroitGroups droitGroups = droitGroupsRepo.findByDroitIdAndGroupuserIdAndIsactiveTrue(droits, groupUser);
		if(droitGroups != null) {
			droitGroups.setIsactive(false);
			droitGroupsRepo.save(droitGroups);							
		}
		
	}

	@Override
	public Page<Droits> findListDroitToAdd(Long idGroupusers, int page, int size) throws Exception {
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroupusers);
		if(groupUser==null) {
			throw new Exception("groupUser not exist");
		}
		List<Long> droitsIdList = new ArrayList<>();
		droitGroupsRepo.findByGroupuserIdAndIsactiveTrue(groupUser).forEach(
				(droitGroup)->{
					droitsIdList.add(droitGroup.getDroitId().getIddroit());
				}
		);		
		return droitService.findDroitsToAdd(droitsIdList, page, size);
	}

	@Override
	public List<Droits> findListDroit(Long idGroupusers) throws Exception {
		GroupUser groupUser = groupUserServiceBasic.findGroupById(idGroupusers);
		if(groupUser==null) {
			throw new Exception("groupUser not exist");
		}
		List<Droits> droitsIdList = new ArrayList<>();
		droitGroupsRepo.findByGroupuserIdAndIsactiveTrue(groupUser).forEach(
				(droitGroup)->{
					droitsIdList.add(droitGroup.getDroitId());
				}
		);		
		return droitsIdList;
	}

}
