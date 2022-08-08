package com.microservice.ged.serviceImple;

import static java.nio.file.Files.copy;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.service.FileRessourceService;
import com.microservice.ged.utils.SecurityConstants;

@Service
public class FileRessourceServiceImpl implements FileRessourceService{
	
	@Override
	public List<String> uploadFilesWorkFlow(List<MultipartFile> multipartFiles, String path) throws Exception {
		List<String> filesNames = new ArrayList<>();
		for(MultipartFile file : multipartFiles) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path fileStorage = get(SecurityConstants.LOCAL_STORAGE_WORKFLOW+path.trim(), fileName).toAbsolutePath().normalize();
            //copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filesNames.add(fileName);
        }
		return filesNames;
		
	}
	
	@Override
	public List<String> uploadFilesUser(MultipartFile file, Profiles profile, String path) throws Exception {
		List<String> filesNames = new ArrayList<>();
		String fileName = "";
		String fileDir = "";
		if(file.getOriginalFilename().contains("/")) {
			fileName = file.getOriginalFilename().substring(StringUtils.cleanPath(file.getOriginalFilename()).lastIndexOf("/") + "/".length());
			fileDir = path+file.getOriginalFilename().substring(0, StringUtils.cleanPath(file.getOriginalFilename()).lastIndexOf("/"));
		}else {
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
			fileDir = path.replace("/", "");
		}
		Path fileStorage = get(this.createFolderUser(profile,fileDir).trim(), fileName).toAbsolutePath().normalize();
		copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
		filesNames.add(fileName);
		return filesNames;
		
	}

	@Override
	public String createUserDir(String path) throws SecurityException {
		File theDir = new File(SecurityConstants.LOCAL_STORAGE_USER+path.trim());
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
		return SecurityConstants.LOCAL_STORAGE_USER+path.trim();
	}

	@Override
	public void createWorkFlowDir(String path) throws SecurityException {
		File theDir = new File(SecurityConstants.LOCAL_STORAGE_WORKFLOW+path.trim());
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
	}

	@Override
	public String createFolderUser(Profiles profile, String path) throws Exception {
		// TODO Auto-generated method stub
		String pathToCreate="UserId"+profile+"/"+path.trim();
		return this.createUserDir(pathToCreate);		
	}

	@Override
	public void downloadFiles(List<MultipartFile> multipartFiles) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
