package com.microservice.ged.serviceImple;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.service.DroitService;

@Transactional
@Service
public class DroitServiceImpl implements DroitService {

	@Autowired
	DroitsRepo droitsRepo;

	@Override
	public Page<Droits> findAllDroits(int page, int size) throws Exception {
		return droitsRepo.findAll(PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}

	@Override
	public Page<Droits> findDroitsToAdd(List<Long> droitsIdList, int page, int size) throws Exception {
		return droitsRepo.findByIddroitNotIn(droitsIdList, PageRequest.of(page, size));
	}
	
	@Override
	public Page<Droits> findDroitsToGroup(List<Long> droitsIdList, int page, int size) throws Exception {
		return droitsRepo.findByIddroitIn(droitsIdList, PageRequest.of(page, size));
	}
	
	@Override
	public Page<Droits> findDroitsToGroupName(List<Long> droitsIdList, String name, int page, int size) throws Exception {
		return droitsRepo.findByIddroitInAndNameContaining(droitsIdList, name, PageRequest.of(page, size));
	}
	

	@Override
	public Page<Droits> findDroitsToAddName(List<Long> droitsIdList, String name, int page, int size) throws Exception {
		return droitsRepo.findByIddroitNotInAndNameContaining(droitsIdList, name, PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}

	@Override
	public Page<Droits> searchDroits(String role, int page, int size) throws Exception {
		return droitsRepo.findByNameContaining(role, PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}

	@Override
	public void add(Droits droits) throws Exception {
		if(droitsRepo.findByName(droits.getName()) != null) {
			throw new Exception("Droit with name "+droits.getName()+" already exist");
		}
		if(droitsRepo.findByDescription(droits.getDescription()) != null) {
			throw new Exception("Droit with description "+droits.getDescription()+" already exist");
		}
		if(droitsRepo.findByDescription(droits.getUri()) != null) {
			throw new Exception("Droit with end point "+droits.getUri()+" already exist");
		}
		droits.setIddroit(null);
		droits.setDateCreation(null);
		droitsRepo.save(droits);		
	}

	@Override
	public Page<Droits> findDroitsByDescription(String description, int page, int size) throws Exception {
		return droitsRepo.findByDescriptionContaining(description, PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}
	
}
