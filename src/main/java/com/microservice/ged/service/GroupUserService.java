package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.GroupUser;

public interface GroupUserService {
	Page<GroupUser> findAllGroup(int page, int size);
	Page<GroupUser> defaultGroup(int page, int size);
	Page<GroupUser> findByNameContaining(String name, int page, int size);
	void saveGroupUser(GroupUser groupUser) throws Exception;
	void updateNameGroupUser(GroupUser groupUser) throws Exception;
	void addRole(GroupUser groupUser) throws Exception;
	void addPoste(GroupUser groupUser) throws Exception;
	void removeRole(GroupUser groupUser) throws Exception;
	void removePoste(GroupUser groupUser) throws Exception;
	GroupUser findByName(String name) throws Exception;
	GroupUser findById(Long id) throws Exception;

}
