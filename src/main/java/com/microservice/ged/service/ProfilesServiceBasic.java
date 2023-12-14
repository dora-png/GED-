package com.microservice.ged.service;

import com.microservice.ged.beans.Profiles;

public interface ProfilesServiceBasic {
	public Profiles findProfileById(Long id) throws Exception;

}
