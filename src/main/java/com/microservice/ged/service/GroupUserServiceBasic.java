package com.microservice.ged.service;

import com.microservice.ged.beans.GroupUser;

public interface GroupUserServiceBasic {

	public GroupUser findGroupById(Long id) throws Exception;

}
