package com.microservice.ged.service;

import com.microservice.ged.beans.Droits;

public interface DroitServiceBasic {
	public Droits findDroitsById(Long id) throws Exception ;
	public Droits findDroitsByName(String name) throws Exception ;
}
