package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;

public interface DocsService {
	public Page<Docs> searchDocsByName(String name, int page, int size) throws Exception ;
	public Page<Docs> searchDocsByLiasses(Liasses liasse, int page, int size) throws Exception ;
	public void add(Docs docs, String posteName) throws Exception ;
	public void update(Docs docs, String posteName) throws Exception ;
	public void updateSetter(Docs docs, String posteName) throws Exception ;
	public void arhive(Docs docs, String posteName) throws Exception ;

}
