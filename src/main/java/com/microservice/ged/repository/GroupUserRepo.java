package com.microservice.ged.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;

public interface GroupUserRepo extends JpaRepository<GroupUser, Long>{
	GroupUser findByIdgroupes(Long id);
	GroupUser findByName(String name);
	Page<GroupUser> findByNameContaining(String name, Pageable pageable);
	List<GroupUser> findByPosteslistesIn(Set<Postes> postes);

}
