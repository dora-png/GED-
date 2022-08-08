package com.microservice.ged.service;

import org.springframework.data.domain.Page;
import com.microservice.ged.beans.ShareDoc;

public interface ShareDocService {
	public Page<ShareDoc> findAllShared(String login, int page, int size) throws Exception ;	
	public void addShareLiasse(ShareDoc shareDoc) throws Exception ;
	public void addShareDoc(ShareDoc shareDoc) throws Exception ;	
	public void removeShareLiasse(ShareDoc shareDoc) throws Exception ;
	public void removeShareDoc(ShareDoc shareDoc) throws Exception ;
}
