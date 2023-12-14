package com.microservice.ged.repository;

import java.util.List;
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
	Page<Profiles> findByStatusTrue(Pageable pageable);
	Page<Profiles> findByStatusFalse(Pageable pageable);
	Page<Profiles> findByCurrentUserContaining(String name, Pageable pageable);
	Page<Profiles> findByCurrentUserContainingAndStatusTrue(String name, Pageable pageable);
	Page<Profiles> findByCurrentUserContainingAndStatusFalse(String name, Pageable pageable);
	Page<Profiles> findByNameContaining(String name, Pageable pageable);
	Page<Profiles> findByNameContainingAndStatusTrue(String name, Pageable pageable);
	Page<Profiles> findByNameContainingAndStatusFalse(String name, Pageable pageable);
	Page<Profiles> findByNameLike(String name, Pageable pageable);
	Page<Profiles> findByNameLikeAndStatusTrue(String name, Pageable pageable);
	Page<Profiles> findByNameLikeAndStatusFalse(String name, Pageable pageable);
	Page<Profiles> findByTypeprofil(TypeUser typeprofil, Pageable pageable);
	Page<Profiles> findByTypeprofilAndStatusTrue(TypeUser typeprofil, Pageable pageable);
	Page<Profiles> findByIdprofilesNotInAndStatusTrue(List<Long> ids, Pageable pageable);
	Page<Profiles> findByIdprofilesNotInAndNameContainingAndStatusTrue(List<Long> ids, String name, Pageable pageable);
	Page<Profiles> findByIdprofilesInAndNameContainingAndStatusTrue(List<Long> ids, String name, Pageable pageable);
	Page<Profiles> findByIdprofilesInAndStatusTrue(List<Long> ids, Pageable pageable);
	Profiles findNameByName(String name);

}
