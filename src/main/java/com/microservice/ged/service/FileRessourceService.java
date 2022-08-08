package com.microservice.ged.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.utils.SecurityConstants;

public interface FileRessourceService {
	public List<String> uploadFilesWorkFlow(List<MultipartFile> multipartFiles, String path) throws Exception ;
	public List<String> uploadFilesUser(MultipartFile multipartFiles, Profiles profile, String path) throws Exception ;
	public String createUserDir(String path) throws SecurityException;
	public void createWorkFlowDir(String path) throws SecurityException ;
	public String createFolderUser(Profiles profile, String path) throws Exception ;
	public void downloadFiles(List<MultipartFile> multipartFiles) throws Exception ;
}
