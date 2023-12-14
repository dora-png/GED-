package com.microservice.ged.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;

public interface GroupUserRepo extends JpaRepository<GroupUser, Long>{
	GroupUser findByIdgroupes(Long id);
	GroupUser findByName(String name);
	GroupUser findBySigle(String sigle);
	
	

	Page<GroupUser> findByNameContainingAndIdgroupesNotInAndStatusFalse(String name, List<Long> ids, Pageable pageable);
	Page<GroupUser> findBySigleContainingAndIdgroupesNotInAndStatusFalse(String sigle, List<Long> ids, Pageable pageable);
	Page<GroupUser> findByNameContainingAndIdgroupesNotIn(String name, List<Long> ids, Pageable pageable);
	Page<GroupUser> findBySigleContainingAndIdgroupesNotIn(String sigle, List<Long> ids, Pageable pageable);
	Page<GroupUser> findByNameContainingAndIdgroupesNotInAndStatusTrue(String name, List<Long> ids, Pageable pageable);
	Page<GroupUser> findBySigleContainingAndIdgroupesNotInAndStatusTrue(String sigle, List<Long> ids, Pageable pageable);
	
	Page<GroupUser> findByNameContainingAndStatusFalse(String name, Pageable pageable);
	Page<GroupUser> findBySigleContainingAndStatusFalse(String sigle, Pageable pageable);
	Page<GroupUser> findByNameContaining(String name, Pageable pageable);
	Page<GroupUser> findBySigleContaining(String sigle, Pageable pageable);
	Page<GroupUser> findByNameContainingAndStatusTrue(String name, Pageable pageable);
	Page<GroupUser> findBySigleContainingAndStatusTrue(String sigle, Pageable pageable);
	Page<GroupUser> findByStatusFalse(Pageable pageable);
	Page<GroupUser> findByStatusTrue(Pageable pageable);
	Page<GroupUser> findByIdgroupesNotIn(List<Long> ids, Pageable pageable);
	Page<GroupUser> findByIdgroupesNotInAndStatusTrue(List<Long> ids, Pageable pageable);
	Page<GroupUser> findByIdgroupesNotInAndStatusFalse(List<Long> ids, Pageable pageable);

}
