package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.ShareDoc;
import com.microservice.ged.beans.TypeShare;
import com.microservice.ged.repository.ShareDocRepo;
import com.microservice.ged.service.ShareDocService;

@Service
@Transactional
public class ShareDocServiceImpl implements ShareDocService {
	
	@Autowired
	ShareDocRepo shareDocRepo;

	@Override
	public Page<ShareDoc> findAllShared(String login, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return shareDocRepo.findByLoginUserAndActiveTrue(login, PageRequest.of(page, size));
	}

	@Override
	public void addShareLiasse(ShareDoc shareDoc) throws Exception {
		// TODO Auto-generated method stub
		TypeShare share = TypeShare.LIASSE;
		if(shareDocRepo.findByLoginUserAndActiveTrueAndTypeShare(shareDoc.getLoginUser(), share) == null) {
			throw new Exception("Liasse deja parteger avec cet utilisateur");			
		}
		shareDocRepo.save(shareDoc);
	}

	@Override
	public void addShareDoc(ShareDoc shareDoc) throws Exception {
		// TODO Auto-generated method stub
		TypeShare share = TypeShare.DOCUMENT;
		if(shareDocRepo.findByLoginUserAndActiveTrueAndTypeShare(shareDoc.getLoginUser(), share) == null) {
			throw new Exception("Document deja parteger avec cet utilisateur");			
		}
		shareDocRepo.save(shareDoc);
	}

	@Override
	public void removeShareLiasse(ShareDoc shareDoc) throws Exception {
		// TODO Auto-generated method stub
		TypeShare share = TypeShare.LIASSE;
		if(shareDocRepo.findByLoginUserAndActiveTrueAndTypeShare(shareDoc.getLoginUser(), share) == null) {
			throw new Exception("partege non existant");			
		}
		shareDoc.setActive(false);
		shareDocRepo.save(shareDoc);
	}

	@Override
	public void removeShareDoc(ShareDoc shareDoc) throws Exception {
		// TODO Auto-generated method stub
		TypeShare share = TypeShare.DOCUMENT;
		if(shareDocRepo.findByLoginUserAndActiveTrueAndTypeShare(shareDoc.getLoginUser(), share) == null) {
			throw new Exception("partege non existant");			
		}
		shareDoc.setActive(false);
		shareDocRepo.save(shareDoc);
		
	}

}
