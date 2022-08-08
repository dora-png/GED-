package com.microservice.ged.serviceImple;

import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Docs;
import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.TypeLiasses;
import com.microservice.ged.repository.DocsRepo;
import com.microservice.ged.repository.LiassesRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.service.FileRessourceService;
import com.microservice.ged.service.LiasseService;


@Transactional
@Service
public class LiasseServiceImpl implements LiasseService {
	
	/*@Autowired
	private DocsRepo docsRepo;

	@Autowired
	private LiassesRepo liassesRepo;

	@Autowired
	private PosteRepo posteRepo;

	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;


	@Autowired
	FileRessourceService fileRessourceService;
	


	@Override
	public void addDocToLiasse(Liasses liasse) throws Exception {
		if(liasse.getDocs().isEmpty()) {
			throw new Exception("toto");
		}else if(liasse.getDocs().size()==1) {
			Iterator<Docs> docsIterato = liasse.getDocs().iterator();
			Docs docs = new Docs();
			while(docsIterato.hasNext()) {
				docs = docsIterato.next();
			}
			if(docsRepo.findByIddoc(docs.getIddoc())==null) {
				throw new Exception("Document with name "+docs.getName()+" not exist");
			}
			try {
				docsRepo.save(docs);
				liassesRepo.save(liasse);
			} catch (Exception e) {
				throw new Exception("Error while update");
			}
		}
		

		
		
		
	}

	@Override
	public Page<Liasses> findByTypeliasse(TypeLiasses typeliasse, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByTypeliasse(typeliasse, PageRequest.of(page, size));
	}

	@Override
	public Liasses addLiasseForWorkflow(Liasses liasse) throws Exception {
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public Liasses addLiasseForWorkflowImportData(Liasses liasse) throws Exception {
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}//hjhjgkjhjhkjghjkhjkghjkhkhj
	}

	@Override
	public Liasses addLiasseForUser(Liasses liasse) throws Exception {
		liasse.setName(liasse.getName().toLowerCase());
		liasse.setSigle(liasse.getSigle().toUpperCase());
		if(liassesRepo.findByUseridAndName(liasse.getUserid(), liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findByUseridAndSigle(liasse.getUserid(), liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		try {
			return liassesRepo.save(liasse);
		} catch (Exception e) {
			throw new Exception("Error while create");
		}
	}

	@Override
	public Liasses addLiasseForUserImportData(Liasses liasse) throws Exception {
		liasse.setName(liasse.getName().toLowerCase());
		liasse.setSigle(liasse.getSigle().toUpperCase());
		if(liassesRepo.findByUseridAndName(liasse.getUserid(), liasse.getName())!=null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		if(liassesRepo.findByUseridAndSigle(liasse.getUserid(), liasse.getSigle())!=null) {
			throw new Exception("Liasses with sigle "+liasse.getSigle()+" already exist");
		}
		//jkhjkhkjlshfkjhsdkljfhsdjkfhksdj
		return liassesRepo.save(liasse);
	}

	@Override
	public Page<Liasses> findByNameLike(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByNameLikeAndArchiveTrue(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByNameLikeAndArchiveTrue(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByNameLikeAndArchiveFalse(String name, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByNameLikeAndArchiveFalse(name, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findBySigleLike(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findBySigleLike(sigle, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findBySigleLikeAndArchiveTrue(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return  liassesRepo.findBySigleLikeAndArchiveTrue(sigle, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findBySigleLikeAndArchiveFalse(String sigle, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findBySigleLikeAndArchiveFalse(sigle, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByTypeliasseAndArchiveTrue(TypeLiasses typeliasse, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByTypeliasseAndArchiveTrue(typeliasse, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByTypeliasseAndArchiveFalse(TypeLiasses typeliasse, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByTypeliasseAndArchiveFalse(typeliasse, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByArchiveFalse(int page, int size) {
		// TODO Auto-generated method stub
		return liassesRepo.findByArchiveFalse(PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByDateCreationAndArchiveFalse(Date dateCreation, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByDateCreationAndArchiveFalse(dateCreation, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByDateCreationBetweenAndArchiveFalse(Date date1, Date date2, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByDateCreationBetweenAndArchiveFalse(date1, date2, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByDateCreationAndArchiveTrue(Date dateCreation, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByDateCreationAndArchiveTrue(dateCreation, PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findByDateCreationBetweenAndArchiveTrue(Date date1, Date date2, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByDateCreationBetweenAndArchiveTrue(date1, date2, PageRequest.of(page, size));
	}
	

	@Override
	public void updateName(Liasses liasse) throws Exception {
		// TODO Auto-generated method stub
		if(liassesRepo.findByArchiveTrueAndIdliasse(liasse.getIdliasse())==null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		liassesRepo.save(liasse);
	}
	

	@Override
	public void archiveLiasse(Liasses liasse) throws Exception {
		// TODO Auto-generated method stub
		if(liassesRepo.findByArchiveTrueAndIdliasse(liasse.getIdliasse())==null) {
			throw new Exception("Liasse with name "+liasse.getName()+" already exist");
		}
		liasse.setArchive(true);
		liassesRepo.save(liasse);
	}

	
	@Override
	public Liasses findByIdliasse(Long id) throws Exception {
		// TODO Auto-generated method stub
		return liassesRepo.findByArchiveTrueAndIdliasse(id);
	}

	@Override
	public Page<Liasses> findByArchiveTrue(int page, int size) {
		// TODO Auto-generated method stub
		return liassesRepo.findByArchiveTrue(PageRequest.of(page, size));
	}

	@Override
	public Page<Liasses> findAllLiasses(int page, int size) {
		// TODO Auto-generated method stub
		return liassesRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public void createLiasseUser(Liasses liasse) throws Exception {
		// TODO Auto-generated method stub
		liasse = liassesRepo.save(liasse);
		String path = "LiasseN°"+liasse.getIdliasse();
		fileRessourceService.createFolderUser(liasse.getUserid(),path);
	}

	@Override
	public void createLiasseWorkFlow(Liasses liasse) throws Exception {
		// TODO Auto-generated method stub
		liasse = liassesRepo.save(liasse);
		fileRessourceService.createWorkFlowDir(
				"TypeLiasseN°"+liasse.getTypeliasse().getIdtypeliasse()+"/"+
				"YearN°"+liasse.getDateCreation().getYear()+"/"+
				"MonthN°"+liasse.getDateCreation().getMonth()+"/"+
				"WorkFlowN°"+liasse.getWorkflowid().getIdworkflows()+"/"+
				"LiasseN°"+liasse.getIdliasse());
	}*/

}
