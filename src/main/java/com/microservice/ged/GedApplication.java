package com.microservice.ged;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Structures;
import com.microservice.ged.beans.Users;
import com.microservice.ged.beans.WorkFlow;
import com.microservice.ged.beans.WorkFlowPoste;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.repository.StructureRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.service.UserService;
import com.microservice.ged.service.WorkFlowService;
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
			AppUserService userRepo, 
			RolesRepo approlerepository,
			UserService userService,
			StructureService structureService,
			PosteService posteService,
			LogPosteUserService logPosteUserService,
			GroupUserService groupUserService,
			WorkFlowService workFlowService
			) {
		return arg -> {
			Appusers appusers = new Appusers("ROOT", "root","1234");
			userRepo.addUser(appusers);
			
			GroupUser groupUser = new GroupUser("Group 1");
			
			Roles ap1 = new Roles("CWORKFLOW", true, false, false, false);
			Roles ap2 = new Roles("RWORKFLOW", false, true, false, false);
			Roles ap3 = new Roles("UWORKFLOW", true, true, true, false);
			Roles ap4 = new Roles("DWORKFLOW", true, true, true, true);
			ap1 = approlerepository.save(ap1);
			approlerepository.save(ap2);
			approlerepository.save(ap3);
			approlerepository.save(ap4);
			groupUser.getRoleslistes().add(ap1);
			Roles ap12 = new Roles("CUSER", true, false, false, false);
			Roles ap22 = new Roles("RUSER", false, true, false, false);
			Roles ap32 = new Roles("UUSER", true, true, true, false);
			Roles ap42 = new Roles("DUSER", true, true, true, true);
			approlerepository.save(ap12);
			approlerepository.save(ap22);
			approlerepository.save(ap32);
			approlerepository.save(ap42);
			
			
			Roles ap13 = new Roles("CTYPELIASSE", true, false, false, false);
			Roles ap23 = new Roles("RTYPELIASSE", false, true, false, false);
			Roles ap33 = new Roles("UTYPELIASSE", true, true, true, false);
			Roles ap43 = new Roles("DTYPELIASSE", true, true, true, true);
			approlerepository.save(ap13);
			approlerepository.save(ap23);
			approlerepository.save(ap33);
			approlerepository.save(ap43);

			Roles ap14 = new Roles("CTYPEDOC", true, false, false, false);
			Roles ap24 = new Roles("RTYPEDOC", false, true, false, false);
			Roles ap34 = new Roles("UTYPEDOC", true, true, true, false);
			Roles ap44 = new Roles("DTYPEDOC", true, true, true, true);
			approlerepository.save(ap14);
			approlerepository.save(ap24);
			approlerepository.save(ap34);
			approlerepository.save(ap44);

			Roles ap15 = new Roles("CSTRUCTURE", true, false, false, false);
			Roles ap25 = new Roles("RSTRUCTURE", false, true, false, false);
			Roles ap35 = new Roles("USTRUCTURE", true, true, true, false);
			Roles ap45 = new Roles("DSTRUCTURE", true, true, true, true);
			approlerepository.save(ap15);
			approlerepository.save(ap25);
			approlerepository.save(ap35);
			approlerepository.save(ap45);

			Roles ap16 = new Roles("RROLE", false, true, false, false);
			approlerepository.save(ap16);
			Roles ap26 = new Roles("PRINT", true, true, true, true);
			approlerepository.save(ap26);

			Roles ap17 = new Roles("CPOSTE", true, false, false, false);
			Roles ap27 = new Roles("RPOSTE", false, true, false, false);
			Roles ap37 = new Roles("UPOSTE", true, true, true, false);
			Roles ap47 = new Roles("DPOSTE", true, true, true, true);
			approlerepository.save(ap17);
			approlerepository.save(ap27);
			approlerepository.save(ap37);
			approlerepository.save(ap47);

			Roles ap18 = new Roles("CLIASSE", true, false, false, false);
			Roles ap28 = new Roles("RLIASSE", false, true, false, false);
			Roles ap38 = new Roles("ULIASSE", true, true, true, false);
			Roles ap48 = new Roles("DLIASSE", true, true, true, true);
			approlerepository.save(ap18);
			approlerepository.save(ap28);
			approlerepository.save(ap38);
			approlerepository.save(ap48);

			Roles ap19 = new Roles("CDOC", true, false, false, false);
			Roles ap29 = new Roles("RDOC", false, true, false, false);
			Roles ap39 = new Roles("UDOC", true, true, true, false);
			Roles ap49 = new Roles("DDOC", true, true, true, true);
			approlerepository.save(ap19);
			approlerepository.save(ap29);
			approlerepository.save(ap39);
			approlerepository.save(ap49);
			


			Users users = new Users("Administrator", "admin", "1234");
			userService.add(users);
			//GroupUser groupUser = new GroupUser(null)
			Structures structures = new Structures("Mairie", "MRIE", "description");
			structureService.add(structures);
			Structures structure = new Structures("Cabinet", "CM", "description");
			Postes postes = new Postes("Maire", "description", structures);
			posteService.addPoste(postes);
			logPosteUserService.add(postes, users);
			
			
			Users uses = new Users("Administratore", "admine", "1234");
			userService.add(uses);
			
			Postes poste = new Postes("SGP", "description", structures);
			//Postes poste1 = new Postes("Poste1", "description", structures);
			/*posteService.addPoste(poste );
			posteService.addPoste(poste1 );	*/		
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
			workFlowService.addPosteToWorkFlow(workFlowPosteListe);
			
		};
	}

}
