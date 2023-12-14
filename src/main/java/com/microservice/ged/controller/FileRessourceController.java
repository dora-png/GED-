package com.microservice.ged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.ged.beans.Profiles;
import com.microservice.ged.service.FileRessourceService;
import com.microservice.ged.service.ProfilesService;

//import com.microservice.ged.service.UserService;
import java.util.List;

@RestController
@CrossOrigin("*")
public class FileRessourceController {
	
	@Autowired
	ProfilesService profilesService;
	
	
	@Autowired
	FileRessourceService fileRessourceService;
	
	
	@PostMapping("/uploadfile-user")
	public ResponseEntity<List<String>> uploadFilesUser(
			@RequestParam(name = "files", required = true) MultipartFile multipartFiles,
			@RequestParam(name = "login", required = true) String login,
			@RequestParam(name = "path", defaultValue = "default/") String path
			) throws Exception {
		Profiles user = profilesService.findProfileByUserLogin(login);
		if(user==null || !user.isStatus()) {
			throw new Exception("User "+login+" don't exist");
		}
        return ResponseEntity.ok().body(fileRessourceService.uploadFilesUser(multipartFiles, user, path));
	}
	
	@PostMapping("/uploadmultifile-user")
	public ResponseEntity<List<String>> uploadMultiFilesUser(
			@RequestParam(name = "files", required = true) MultipartFile multipartFiles,
			@RequestParam(name = "login", required = true) String login,
			@RequestParam(name = "path", defaultValue = "default/") String path
			) throws Exception {
		Profiles user = profilesService.findProfileByUserLogin(login);
		if(user==null || !user.isStatus()) {
			throw new Exception("User "+login+" don't exist");
		}
        return ResponseEntity.ok().body(fileRessourceService.uploadMultiFilesUser(multipartFiles, user, path));
	}
	
	@PostMapping("/uploadfile-workflow")
	public ResponseEntity<List<String>> uploadFilesWorkFlow(
			@RequestParam(name = "files", required = true) List<MultipartFile> multipartFiles,
			@RequestParam(name = "path", required = true) String path
			) throws Exception {			
		fileRessourceService.createWorkFlowDir(path);
        return ResponseEntity.ok().body(fileRessourceService.uploadFilesWorkFlow(multipartFiles, path));
	}
	
	 // Define a method to download files
   /* @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(SecurityConstants.LOCAL_STORAGE).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }*/

}

