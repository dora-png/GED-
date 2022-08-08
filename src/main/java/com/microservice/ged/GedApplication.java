package com.microservice.ged;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Profiles;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.beans.TypeUser;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.repository.StructureRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.DroitProfilesServices;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.StructureService;
//import com.microservice.ged.service.UserService;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.GroupProfilesBean;
import com.microservice.ged.utils.ProfilesDroitBean;
import com.microservice.ged.utils.WorkFlowPosteListe;

@SpringBootApplication
public class GedApplication {

	public static void main(String[] args) {
		SpringApplication.run(GedApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner start(
			GroupProfileService groupProfileService, 
			DroitsRepo droitsRepo,
			ProfilesService profilesService,
			StructureService structureService,
			PosteService posteService,
			LogPosteUserService logPosteUserService,
			DroitProfilesServices droitProfilesServices,
			GroupUserService groupUserService,
			WorkFlowService workFlowService
			) {
		return arg -> {
			
			Droits ap12 = new Droits("CUUSER",  "CUUSER",  true, true, true, false);
			Droits ap22 = new Droits("RUSER", "RUSER", false, true, false, false);
			Droits ap42 = new Droits("DUSER", "DUSER", true, true, true, true);
			droitsRepo.save(ap12);
			droitsRepo.save(ap22);
			droitsRepo.save(ap42);
			
			
			Droits ap13 = new Droits("CUTYPELIASSE",  "CUTYPELIASSE",  true, true, true, false);
			Droits ap23 = new Droits("RTYPELIASSE", "RTYPELIASSE", false, true, false, false);
			Droits ap43 = new Droits("DTYPELIASSE", "DTYPELIASSE", true, true, true, true);
			droitsRepo.save(ap13);
			droitsRepo.save(ap23);
			droitsRepo.save(ap43);

			Droits ap14 = new Droits("CUTYPEDOC",  "CUTYPEDOC",  true, true, true, false);
			Droits ap24 = new Droits("RTYPEDOC", "RTYPEDOC", false, true, false, false);
			Droits ap44 = new Droits("DTYPEDOC", "DTYPEDOC", true, true, true, true);
			droitsRepo.save(ap14);
			droitsRepo.save(ap24);
			droitsRepo.save(ap44);

			Droits ap15 = new Droits("CUSTRUCTURE",  "CUSTRUCTURE",  true, true, true, false);
			Droits ap25 = new Droits("RSTRUCTURE", "RSTRUCTURE", false, true, false, false);
			Droits ap45 = new Droits("DSTRUCTURE", "DSTRUCTURE", true, true, true, true);
			droitsRepo.save(ap15);
			droitsRepo.save(ap25);
			droitsRepo.save(ap45);

			Droits ap16 = new Droits("RROLE", "RROLE", false, true, false, false);
			droitsRepo.save(ap16);
			Droits ap26 = new Droits("PRINT", "PRINT", true, true, true, true);
			droitsRepo.save(ap26);

			Droits ap17 = new Droits("CUPOSTE",  "CUPOSTE",  true, true, true, false);
			Droits ap27 = new Droits("RPOSTE", "RPOSTE", false, true, false, false);
			Droits ap47 = new Droits("DPOSTE", "DPOSTE", true, true, true, true);
			droitsRepo.save(ap17);
			droitsRepo.save(ap27);
			droitsRepo.save(ap47);

			Droits ap18 = new Droits("CULIASSE",  "CULIASSE",  true, true, true, false);
			Droits ap28 = new Droits("RLIASSE", "RLIASSE", false, true, false, false);
			Droits ap48 = new Droits("DLIASSE", "DLIASSE", true, true, true, true);
			droitsRepo.save(ap18);
			droitsRepo.save(ap28);
			droitsRepo.save(ap48);

			Droits ap19 = new Droits("CUDOC",  "CUDOC",  true, true, true, false);
			Droits ap29 = new Droits("RDOC", "RDOC", false, true, false, false);
			Droits ap49 = new Droits("DDOC", "DDOC", true, true, true, true);
			droitsRepo.save(ap19);
			droitsRepo.save(ap29);
			droitsRepo.save(ap49);
			
			Profiles profiles = new Profiles("Maire",null, TypeUser.INTERN_ACTOR, true);
			profilesService.add(profiles);
			Profiles profiless = new Profiles("Maires",null, TypeUser.EXTERN_ACTOR, true);
			profilesService.add(profiless);
			
			GroupUser groupUser = new GroupUser("String name", "String sigle", "String couleur", true);
			groupUserService.saveGroupUser(groupUser);
			List<ProfilesDroitBean> profilesDroitBeanList = new ArrayList<>();
			profilesDroitBeanList.add(new ProfilesDroitBean(new Long(1), new Long(1)));
			profilesDroitBeanList.add(new ProfilesDroitBean(new Long(2), new Long(1)));
			profilesDroitBeanList.add(new ProfilesDroitBean(new Long(3), new Long(1)));
			droitProfilesServices.addDroitToProfiles(profilesDroitBeanList);
			List<GroupProfilesBean> groupProfilesBeanList = new ArrayList<>();
			groupProfilesBeanList.add(new GroupProfilesBean(new Long(1), new Long(1)));	
			groupProfilesBeanList.add(new GroupProfilesBean(new Long(2), new Long(1)));			
			groupProfileService.addGroupToProfiles(groupProfilesBeanList);
			
			/*Appusers appusers = new Appusers("ROOT", "root","1234");
			userRepo.addUser(appusers);
			
			GroupUser groupUser = new GroupUser("Group 1");
			
			droitsRepo ap1 = new Roles("CUWORKFLOW", true, true, true, false);
			Roles ap2 = new Roles("RWORKFLOW", false, true, false, false);
			Roles ap4 = new Roles("DWORKFLOW", true, true, true, true);
			approlerepository.save(ap1);
			ap2 = approlerepository.save(ap2);
			approlerepository.save(ap4);
			groupUser.getRoleslistes().add(ap2);
			Roles ap12 = new Roles("CUUSER",  true, true, true, false);
			Roles ap22 = new Roles("RUSER", false, true, false, false);
			Roles ap42 = new Roles("DUSER", true, true, true, true);
			approlerepository.save(ap12);
			approlerepository.save(ap22);
			approlerepository.save(ap42);
			
			
			Roles ap13 = new Roles("CUTYPELIASSE",  true, true, true, false);
			Roles ap23 = new Roles("RTYPELIASSE", false, true, false, false);
			Roles ap43 = new Roles("DTYPELIASSE", true, true, true, true);
			approlerepository.save(ap13);
			approlerepository.save(ap23);
			approlerepository.save(ap43);

			Roles ap14 = new Roles("CUTYPEDOC",  true, true, true, false);
			Roles ap24 = new Roles("RTYPEDOC", false, true, false, false);
			Roles ap44 = new Roles("DTYPEDOC", true, true, true, true);
			approlerepository.save(ap14);
			approlerepository.save(ap24);
			approlerepository.save(ap44);

			Roles ap15 = new Roles("CUSTRUCTURE",  true, true, true, false);
			Roles ap25 = new Roles("RSTRUCTURE", false, true, false, false);
			Roles ap45 = new Roles("DSTRUCTURE", true, true, true, true);
			approlerepository.save(ap15);
			approlerepository.save(ap25);
			approlerepository.save(ap45);

			Roles ap16 = new Roles("RROLE", false, true, false, false);
			approlerepository.save(ap16);
			Roles ap26 = new Roles("PRINT", true, true, true, true);
			approlerepository.save(ap26);

			Roles ap17 = new Roles("CUPOSTE",  true, true, true, false);
			Roles ap27 = new Roles("RPOSTE", false, true, false, false);
			Roles ap47 = new Roles("DPOSTE", true, true, true, true);
			approlerepository.save(ap17);
			approlerepository.save(ap27);
			approlerepository.save(ap47);

			Roles ap18 = new Roles("CULIASSE",  true, true, true, false);
			Roles ap28 = new Roles("RLIASSE", false, true, false, false);
			Roles ap48 = new Roles("DLIASSE", true, true, true, true);
			approlerepository.save(ap18);
			approlerepository.save(ap28);
			approlerepository.save(ap48);

			Roles ap19 = new Roles("CUDOC",  true, true, true, false);
			Roles ap29 = new Roles("RDOC", false, true, false, false);
			Roles ap49 = new Roles("DDOC", true, true, true, true);
			approlerepository.save(ap19);
			approlerepository.save(ap29);
			approlerepository.save(ap49);
			


			Users users = new Users("Administrator", "admin", "1234");
			userService.add(users);
			//GroupUser groupUser = new GroupUser(null)
			Structures structures = new Structures("Mairie", "MRIE", "description");
			structureService.add(structures);
			Structures structure = new Structures("Cabinet", "CM", "description");
			Postes postes = new Postes("Maire", "description", structures);
			posteService.addPoste(postes);
			postes = posteService.findPosteByName(postes.getName());
			logPosteUserService.add(postes, users);
			Postes postee = new Postes("Poste 1", "description", structures);
			postee.setPosteSuperieur(postes);
			posteService.addPoste(postee);
			
			
			Users uses = new Users("Administratore", "admine", "1234");
			userService.add(uses);
			
			Postes poste = new Postes("SGP", "description", structures);
			//Postes poste1 = new Postes("Poste1", "description", structures);
			//posteService.addPoste(poste );
			///posteService.addPoste(poste1 );			
			//poste.getPosteSubalterne().add(poste1);			
			//posteService.addSubPoste( postes);
			//posteService.addSubPoste( poste);
			groupUser.getPosteslistes().add(postes);
			groupUserService.saveGroupUser(groupUser);
			
			
			
			poste = posteService.findPosteByName(poste.getName());
			WorkFlow workFlow = new WorkFlow("workFlow ", "workFlow", "description");
			workFlowService.add(workFlow);
			workFlow = workFlowService.findByName(workFlow.getName());
			WorkFlow workFlow1 = new WorkFlow("workFlow 1", "wow1", "description");
			workFlowService.add(workFlow1);
			WorkFlow workFlow2 = new WorkFlow("workFlow 2", "wor2", "description");
			workFlowService.add(workFlow2);

			WorkFlow workFlow3 = new WorkFlow("workFlow3 ", "wor3", "description");
			workFlowService.add(workFlow3);
			WorkFlow workFlow4 = new WorkFlow("workFlow 4", "wor4", "description");
			workFlowService.add(workFlow4);
			WorkFlow workFlow5 = new WorkFlow("workFlow 5", "wor5", "description");
			workFlowService.add(workFlow5);

			WorkFlow workFlow6 = new WorkFlow("workFlow6 ", "wor6", "description");
			workFlowService.add(workFlow6);
			WorkFlow workFlow7 = new WorkFlow("workFlow 7", "wor7", "description");
			workFlowService.add(workFlow7);
			WorkFlow workFlow8 = new WorkFlow("workFlow 8", "wor8", "description");
			workFlowService.add(workFlow8);

			WorkFlow workFlow9 = new WorkFlow("workFlow9 ", "wor9", "description");
			workFlowService.add(workFlow9);
			WorkFlow workFlow10 = new WorkFlow("workFlow 10", "wo10", "description");
			workFlowService.add(workFlow10);
			WorkFlow workFlow11 = new WorkFlow("workFlow 11", "wo11", "description");
			workFlowService.add(workFlow11);
			List<WorkFlowPosteListe> workFlowPosteListe = new ArrayList<>();
			//workFlowPosteListe.add(new WorkFlowPosteListe(0, poste.getIdposte(), workFlow.getIdworkflows(), true));
			//workFlowPosteListe.add(new WorkFlowPosteListe(1, poste1.getIdposte(), workFlow.getIdworkflows(), true));
			//workFlowService.addPosteToWorkFlow(workFlowPosteListe);
			*/
		};
	}

}
