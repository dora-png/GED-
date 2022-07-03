package com.microservice.ged.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.beans.Users;

@Repository
public interface PosteRepo extends JpaRepository<Postes, Long>{
	Postes findByIdposteAndActiveTrue(Long idposte);
	Postes findByNameAndActiveTrue(String name);
	//Postes findByNameAndRolesIn(String name, Set<Roles> listeRoles);
	Page<Postes> findByStructureAndActiveTrue(Integer niveau, Structures structures,  Pageable pageable);
	Page<Postes> findByNameContainingAndActiveTrue(String name, Pageable pageable);
	Page<Postes> findByStructureAndActiveTrue(Structures structures,  Pageable pageable);
	Page<Postes> findByActiveTrue(Pageable pageable);
	Page<Postes> findByGroupslistesNotInAndActiveTrueAndNameContaining(Set<GroupUser> groupUser,String name, Pageable pageable);
	Page<Postes> findByGroupslistesNotAndActiveTrue(GroupUser groupUser, Pageable pageable);
	Postes findByStructureAndPosteSuperieurIsNullAndActiveTrue(Structures structures);
	Page<Postes> findByIdposteNotInAndActiveTrue(Set<Long> ids, Pageable pageable);
	Page<Postes> findByIdposteNotInAndActiveTrueAndNameContaining(Set<Long> ids, String name, Pageable pageable);

}
