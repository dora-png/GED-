package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.beans.ProfilesStructure;

public interface ProfilesStructureRepo extends JpaRepository<ProfilesStructure, Long> {
	Page<ProfilesStructure> findByProfilesId(Profiles profilesId, Pageable pageable);
	Page<ProfilesStructure> findByStructureId(Structures structureId, Pageable pageable);
	ProfilesStructure findByProfilesIdAndDateEndIsNull(Profiles profilesId);
	ProfilesStructure findByStructureIdAndDateEndIsNull(Structures structureId);
	ProfilesStructure findByProfilesIdAndStructureIdAndDateEndIsNull(Profiles profilesId, Structures structureId);
}
