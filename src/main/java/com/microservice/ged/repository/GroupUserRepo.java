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
	GroupUser findByIdgroupesAndStatusTrue(Long id);
	GroupUser findByIdgroupes(Long id);
	GroupUser findByNameAndStatusTrue(String name);
	GroupUser findBySigleAndStatusTrue(String sigle);
	Page<GroupUser> findByNameContainingAndStatusTrue(String name, Pageable pageable);
	Page<GroupUser> findBySigleContainingAndStatusTrue(String sigle, Pageable pageable);
	Page<GroupUser> findByStatusTrue(Pageable pageable);
	Page<GroupUser> findByIdgroupesNotInAndStatusTrue(List<Long> ids, Pageable pageable);

}
