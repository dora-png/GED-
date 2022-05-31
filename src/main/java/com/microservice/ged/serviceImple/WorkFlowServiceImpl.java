package com.microservice.ged.serviceImple;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Liasses;
import com.microservice.ged.beans.LogPoste;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.repository.LiassesRepo;
import com.microservice.ged.repository.LogPosteRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.WorkFlowPosteRepo;
import com.microservice.ged.repository.WorkFlowRepo;
import com.microservice.ged.service.WorkFlowService;

@Transactional
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	
	@Autowired
	WorkFlowPosteRepo workFlowPosteRepo;
	
	@Autowired
	WorkFlowRepo workFlowRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	LiassesRepo liassesRepo;
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Autowired 
	LogPosteRepo logPosteRepo;

	@Override
	public Page<WorkFlow> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return workFlowRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<WorkFlow> searchByName(String name, int page, int size) {
		// TODO Auto-generated method stub
		return workFlowRepo.findByNameLike(name, PageRequest.of(page, size));
	}

	@Override
	public Page<WorkFlow> searchBySigle(String name, int page, int size) {
		// TODO Auto-generated method stub
		return workFlowRepo.findBySigleLike(name, PageRequest.of(page, size));
	}

	@Override
	public void add(WorkFlow workFlow, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(workFlowRepo.findByName(workFlow.getName())!=null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" already exist");
		}
		if(workFlowRepo.findBySigle(workFlow.getSigle())!=null) {
			throw new Exception("WorkFlow with sigle "+workFlow.getSigle()+" already exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="CWORKFLOW")
							hasRole = roles.isCreate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
									new Date(),
									"Creation worklow "+workFlow.getName(),
									logPosteUser.getUserId().getLogin(),
									logPosteUser.getPosteId().getName(),
									workFlow.getName(),
									"WORKFLOW");
							workFlowRepo.save(workFlow);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while create workFlow");
						}
					}else {
						throw new Exception("You dont have this right create workFlow");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
			
		}
		
	}

	@Override
	public void update(WorkFlow workFlow, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(workFlowRepo.findByIdworkflows(workFlow.getIdworkflows())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="UWORKFLOW")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update worklow "+workFlow.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								workFlow.getName(),
								"WORKFLOW");
							workFlowRepo.save(workFlow);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while update workFlow");
						}
					}else {
						throw new Exception("You dont have this right create workFlow");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}
	}

	@Override
	public void delete(WorkFlow workFlow, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(workFlowRepo.findByName(workFlow.getName())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		if(workFlowRepo.findBySigle(workFlow.getSigle())==null) {
			throw new Exception("WorkFlow with sigle "+workFlow.getSigle()+" not exist");
		}
		if(workFlowRepo.findByIdworkflows(workFlow.getIdworkflows())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="DWORKFLOW")
							hasRole = roles.isDelete();
					}
					if(hasRole) {
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Delete worklow "+workFlow.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								workFlow.getName(),
								"WORKFLOW");
							workFlowRepo.delete(workFlow);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while delete workFlow");
						}
					}else {
						throw new Exception("You dont have this right delete workFlow");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}
	}

	@Override
	public WorkFlow findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return workFlowRepo.findById(id).orElseThrow(() -> new Exception(""));
	}

	@Override
	public WorkFlow findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return workFlowRepo.findByName(name);
	}

	@Override
	public WorkFlow findBySigle(String sigle) throws Exception {
		// TODO Auto-generated method stub
		return workFlowRepo.findBySigle(sigle);
	}

	@Override
	public void addPosteToWorkFlow(WorkFlowPoste workFlowPoste, String posteName) throws Exception {
		// TODO Auto-generated method stub
				if(workFlowRepo.findByIdworkflows(workFlowPoste.getWorkflowId().getIdworkflows())==null) {
					throw new Exception("WorkFlow with name "+workFlowPoste.getWorkflowId().getName()+" not exist");
				}
				if(posteRepo.findByIdposte(workFlowPoste.getPosteId().getIdposte())==null) {
					throw new Exception("Poste with name "+workFlowPoste.getPosteId().getName()+" not exist");
				}
				Postes postes =  posteRepo.findByName(posteName);
				if(postes ==null) {
					throw new Exception("Votre poste ne vous permet pas cette action");
				}else {
					LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
					if(logPosteUser!=null) {
						if(logPosteUser.getUserId()!=null) {
							boolean  hasRole=false;
							for(Roles roles : postes.getRoles()) {
								if(roles.getName()=="UWORKFLOW")
									hasRole = roles.isUpdate();
							}
							if(hasRole) {
								
								try {
									LogPoste logPoste = new LogPoste(
										new Date(),
										"Update worklow "+workFlowPoste.getWorkflowId().getName(),
										logPosteUser.getUserId().getLogin(),
										logPosteUser.getPosteId().getName(),
										workFlowPoste.getWorkflowId().getName(),
										"WORKFLOW");
									workFlowPoste.setActive(true);
									workFlowPosteRepo.save(workFlowPoste);
									logPosteRepo.save(logPoste);
								} catch (Exception e) {
									throw new Exception("Error while update workFlow");
								}
							}else {
								throw new Exception("You dont have this right create workFlow");
							}
						}else {
							throw new Exception("Cet utilisateur ne peut effectuer cette action");
						}
					}else {
						throw new Exception("toto");
					}
				}
		
	}

	@Override
	public void removePosteToWorkFlow(WorkFlowPoste workFlowPoste, String posteName) throws Exception {
		// TODO Auto-generated method stub
				if(workFlowRepo.findByIdworkflows(workFlowPoste.getWorkflowId().getIdworkflows())==null) {
					throw new Exception("WorkFlow with name "+workFlowPoste.getWorkflowId().getName()+" not exist");
				}
				if(posteRepo.findByIdposte(workFlowPoste.getPosteId().getIdposte())==null) {
					throw new Exception("Poste with name "+workFlowPoste.getPosteId().getName()+" not exist");
				}
				Postes postes =  posteRepo.findByName(posteName);
				if(postes ==null) {
					throw new Exception("Votre poste ne vous permet pas cette action");
				}else {
					LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
					if(logPosteUser!=null) {
						if(logPosteUser.getUserId()!=null) {
							boolean  hasRole=false;
							for(Roles roles : postes.getRoles()) {
								if(roles.getName()=="UWORKFLOW")
									hasRole = roles.isUpdate();
							}
							if(hasRole) {
								
								try {
									LogPoste logPoste = new LogPoste(
										new Date(),
										"Update worklow "+workFlowPoste.getWorkflowId().getName(),
										logPosteUser.getUserId().getLogin(),
										logPosteUser.getPosteId().getName(),
										workFlowPoste.getWorkflowId().getName(),
										"WORKFLOW");
									workFlowPoste.setActive(false);
									workFlowPosteRepo.save(workFlowPoste);
									logPosteRepo.save(logPoste);
								} catch (Exception e) {
									throw new Exception("Error while update workFlow");
								}
							}else {
								throw new Exception("You dont have this right create workFlow");
							}
						}else {
							throw new Exception("Cet utilisateur ne peut effectuer cette action");
						}
					}else {
						throw new Exception("toto");
					}
				}
		
	}

	@Override
	public void addLiasseToWorkFlow(WorkFlow workFlow, String posteName) throws Exception {
		// TODO Auto-generated method stub
		if(workFlowRepo.findByIdworkflows(workFlow.getIdworkflows())==null) {
			throw new Exception("WorkFlow with name "+workFlow.getName()+" not exist");
		}
		Liasses liasse = liassesRepo.findByIdliasse(workFlow.getLiasses().get(0).getIdliasse());
		if(liasse==null) {
			throw new Exception("Liasse with name "+workFlow.getLiasses().get(0).getName()+" not exist");
		}
		Postes postes =  posteRepo.findByName(posteName);
		if(postes ==null) {
			throw new Exception("Votre poste ne vous permet pas cette action");
		}else {
			LogPosteUser logPosteUser= logPosteUserRepo.findByPosteIdAndDateFinIsNull(postes);
			if(logPosteUser!=null) {
				if(logPosteUser.getUserId()!=null) {
					boolean  hasRole=false;
					for(Roles roles : postes.getRoles()) {
						if(roles.getName()=="UWORKFLOW")
							hasRole = roles.isUpdate();
					}
					if(hasRole) {
						
						try {
							LogPoste logPoste = new LogPoste(
								new Date(),
								"Update worklow "+workFlow.getName(),
								logPosteUser.getUserId().getLogin(),
								logPosteUser.getPosteId().getName(),
								workFlow.getLiasses().get(0).getName(),
								"WORKFLOW");
							workFlow.getLiasses().add(workFlow.getLiasses().get(0));
							workFlowRepo.save(workFlow);
							logPosteRepo.save(logPoste);
						} catch (Exception e) {
							throw new Exception("Error while update workFlow");
						}
					}else {
						throw new Exception("You dont have this right create workFlow");
					}
				}else {
					throw new Exception("Cet utilisateur ne peut effectuer cette action");
				}
			}else {
				throw new Exception("toto");
			}
		}		
	}

}
