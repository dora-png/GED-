package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.DroitProfiles;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.Profiles;

@Repository
public interface DroitProfilesRepo extends JpaRepository<DroitProfiles, Long>{
	DroitProfiles findByIddroitprofileAndIsactiveTrue(Long iddroitgroups);
	Page<DroitProfiles> findByIsactiveTrue(Pageable pageable);
	Page<DroitProfiles> findByDroitIdAndIsactiveTrue(Droits droitId, Pageable pageable);
	DroitProfiles findByDroitIdAndProfileIdAndIsactiveTrue(Droits droitId, Profiles profilesId);
	Page<DroitProfiles> findByProfileId(Profiles profilesId, Pageable pageable);
	Page<DroitProfiles> findByProfileIdAndIsactiveTrue(Profiles profilesId, Pageable pageable);
	List<DroitProfiles> findByProfileIdAndIsactiveTrue(Profiles profilesId);

}
