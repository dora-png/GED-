package com.microservice.ged.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;

public interface GroupUserRepo extends JpaRepository<GroupUser, Long>{

	GroupUser findByName(String name);
	GroupUser findByNameAndPosteslistesIn(String name, Set<Postes> posteslistes);
	GroupUser findByNameAndRoleslistesIn(String name, Set<Roles> roleslistes);

}
