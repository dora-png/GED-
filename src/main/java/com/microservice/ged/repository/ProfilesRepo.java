package com.microservice.ged.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.TypeUser;

@Repository
public interface ProfilesRepo extends JpaRepository<Profiles, Long>{
	Profiles findByIdprofiles(Long idProfiles);
	Profiles findByName(String name);
	Profiles findByCurrentUser(String currentUser);
	Page<Profiles> findByNameContainingAndStatusTrue(String name, Pageable pageable);
	Page<Profiles> findByNameContaining(String name, Pageable pageable);
	Page<Profiles> findByTypeprofil(TypeUser typeprofil, Pageable pageable);
	Page<Profiles> findByTypeprofilAndStatusTrue(TypeUser typeprofil, Pageable pageable);
	Page<Profiles> findByIdprofilesNotInAndStatusTrue(Set<Long> ids, Pageable pageable);

}
