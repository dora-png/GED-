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
	public Page<Droits> findDroitsToAddName(List<Long> droitsIdList, String name, int page, int size) throws Exception {
		return droitsRepo.findByIddroitNotInAndNameContaining(droitsIdList, name, PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}

	@Override
	public Page<Droits> searchDroits(String role, int page, int size) throws Exception {
		return droitsRepo.findByNameContaining(role, PageRequest.of(page, size, Sort.by("iddroit").descending()));
	}

	@Override
	public Droits findDroitsById(Long id) throws Exception {
		return droitsRepo.findByIddroit(id);
	}

	@Override
	public Droits findDroitsByName(String name) throws Exception {
		return droitsRepo.findByName(name);
	}

	@Override
	public void add(Droits droits) throws Exception {
		if(droitsRepo.findByName(droits.getName()) != null) {
			throw new Exception("Droit already exist");
		}
		if(droitsRepo.findByAbbr(droits.getAbbr()) != null) {
			throw new Exception("Droit already exist");
		}
		droits.setIddroit(null);
		droits.setDateCreation(null);
		droitsRepo.save(droits);		
	}

	@Override
	public void update(Long droitsId, String name) throws Exception {
		Droits droits = droitsRepo.findByIddroit(droitsId);
		if(droits != null) {
			throw new Exception("Droit already exist");
		}
		if(droitsRepo.findByName(name) != null) {
			throw new Exception("Droit already exist");
		}		
		droits.setName(name);
		droitsRepo.save(droits);
		
	}

	

	
}
