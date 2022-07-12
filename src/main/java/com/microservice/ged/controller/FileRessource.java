package com.microservice.ged.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.ged.utils.SecurityConstants;
import com.microservice.ged.utils.UploadResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@CrossOrigin("*")
public class FileRessource {
	// define a location
	public static final String DIRECTORY = System.getProperty("user.home.Desktop.src");
	
	//define a method to upload
	
	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
		createFolderIfNotExists();
		List<String> filesNames = new ArrayList<>();
		for(MultipartFile file : multipartFiles) {
			System.err.println(file.getOriginalFilename());
			System.err.println(file.getName());
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path fileStorage = get(SecurityConstants.LOCAL_STORAGE, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filesNames.add(fileName);
        }
        return ResponseEntity.ok().body(filesNames);
	}
	
	 // Define a method to download files
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
	
	public static void createFolderIfNotExists() throws SecurityException {
		File theDir = new File(SecurityConstants.LOCAL_STORAGE);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
	}
	

}

