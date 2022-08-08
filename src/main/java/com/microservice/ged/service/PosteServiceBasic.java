package com.microservice.ged.service;

import com.microservice.ged.beans.Postes;

public interface PosteServiceBasic {
	public Postes findPosteById(Long id) throws Exception ;
}
