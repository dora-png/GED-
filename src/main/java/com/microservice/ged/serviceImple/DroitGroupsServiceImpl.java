package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.microservice.ged.service.DroitServiceBasic;
import com.microservice.ged.service.GroupUserServiceBasic;
import com.microservice.ged.utils.GroupDroitsBean;

@Service
@Transactional
public class DroitGroupsServiceImpl implements DroitGroupsService {
	
	@Autowired
	DroitGroupsRepo droitGroupsRepo;

	@Override
	public void addDroitToGroupBasic(GroupUser groupUser, List<Droits> droits) throws Exception {
		droits.forEach(
				(droitGroup)->{
					droitGroupsRepo.save(new DroitGroups(droitGroup, groupUser, true));
				}
		);
	}

	@Override
	public Page<DroitGroups> findAllDroitOfGroup(GroupUser groupUser, int page, int size, int status) throws Exception {
		switch (status) {
			case 0:
				return droitGroupsRepo.findByGroupuserIdAndIsactiveFalse(groupUser , PageRequest.of(page, size,Sort.by("iddroitgroups").descending()));				
			case 1:
				return droitGroupsRepo.findByGroupuserIdAndIsactiveTrue(groupUser , PageRequest.of(page, size,Sort.by("iddroitgroups").descending()));	
			case 2:
				return droitGroupsRepo.findByGroupuserId(groupUser, PageRequest.of(page, size,Sort.by("iddroitgroups").descending()));
			default:
				throw new Exception("Bad request");
		}
	}

	@Override
	public List<Long> findListDroit(GroupUser groupUser, int status) throws Exception {
		List<Long> droitsIdList = new ArrayList<>();
		List<DroitGroups> droitsGroups = new ArrayList<>();
		switch (status) {
			case 0:
				droitsGroups = droitGroupsRepo.findByGroupuserIdAndIsactiveFalse(groupUser);				
			case 1:
				droitsGroups = droitGroupsRepo.findByGroupuserIdAndIsactiveTrue(groupUser);	
			default:
				//throw new Exception("Bad request");
		}
		droitsGroups.forEach(
				(droitGroup)->{
					droitsIdList.add(droitGroup.getDroitId().getIddroit());
				}
		);	
		return droitsIdList;
	}

	@Override
	public void removeDroitToGroup(GroupUser groupUser, Droits droits) throws Exception {
		DroitGroups droitGroups = droitGroupsRepo.findByDroitIdAndGroupuserIdAndIsactiveTrue(droits, groupUser);
		if(droitGroups != null) {
			if(droitGroups.isIsactive()) {
				droitGroups.setIsactive(false);
				droitGroupsRepo.save(droitGroups);							
			}
		}
	}

	@Override
	public void addDroitToGroup(GroupUser groupUser, Droits droits) throws Exception {
		if(droitGroupsRepo.findByDroitIdAndGroupuserIdAndIsactiveTrue(droits, groupUser) == null)
			droitGroupsRepo.save(new DroitGroups(droits, groupUser, true));	
	}

	@Override
	public List<Droits> findDroitOfGroup(GroupUser groupUser, int status) throws Exception {
		List<Droits> droits = new ArrayList<>();
		droitGroupsRepo.findByGroupuserId(groupUser).forEach(
				(droitGroup)->{
					switch(status) {
						case 0:
							if(!droitGroup.isIsactive())
								droits.add(droitGroup.getDroitId());							
						case 1:
							if(droitGroup.isIsactive())
								droits.add(droitGroup.getDroitId());							
						case 2:
							droits.add(droitGroup.getDroitId());
						default:
						
					}
					
				}
				
		);
		return droits;
	}

}
