package com.microservice.ged;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.microservice.ged.beans.Appusers;
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
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.service.UserService;
import com.microservice.ged.service.WorkFlowService;

@SpringBootApplication
public class GedApplication {

	public static void main(String[] args) {
		SpringApplication.run(GedApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(
			AppUserRepo userRepo, 
			RolesRepo approlerepository,
			UserService userService,
			StructureService structureService,
			PosteService posteService,
			LogPosteUserService logPosteUserService,
			WorkFlowService workFlowService
			) {
		return arg -> {
			Appusers appusers = new Appusers("ROOT", "root","1234");
			userRepo.save(appusers);
			
			Roles ap1 = new Roles("CWORKFLOW", true, false, false, false);
			Roles ap2 = new Roles("RWORKFLOW", false, true, false, false);
			Roles ap3 = new Roles("UWORKFLOW", true, true, true, false);
			Roles ap4 = new Roles("DWORKFLOW", true, true, true, true);
			approlerepository.save(ap1);
			approlerepository.save(ap2);
			approlerepository.save(ap3);
			approlerepository.save(ap4);

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
			userService.add(users,appusers.getLogin());
			Structures structures = new Structures("Mairie", "MRIE", "description");
			structureService.add(structures, appusers.getLogin());
			Structures structure = new Structures("Cabinet du Maire", "CM", "description");
			Postes postes = new Postes("Maire", "description", structures);
			postes.getRoles().add(ap1);
			postes.getRoles().add(ap2);
			postes.getRoles().add(ap3);
			postes.getRoles().add(ap4);
			postes.getRoles().add(ap12);
			postes.getRoles().add(ap22);
			postes.getRoles().add(ap32);
			postes.getRoles().add(ap42);
			postes.getRoles().add(ap13);
			postes.getRoles().add(ap23);
			postes.getRoles().add(ap33);
			postes.getRoles().add(ap43);
			postes.getRoles().add(ap14);
			postes.getRoles().add(ap24);
			postes.getRoles().add(ap34);
			postes.getRoles().add(ap44);
			postes.getRoles().add(ap15);
			postes.getRoles().add(ap25);
			postes.getRoles().add(ap35);
			postes.getRoles().add(ap45);
			postes.getRoles().add(ap16);
			postes.getRoles().add(ap26);
			postes.getRoles().add(ap17);
			postes.getRoles().add(ap27);
			postes.getRoles().add(ap37);
			postes.getRoles().add(ap47);
			postes.getRoles().add(ap18);
			postes.getRoles().add(ap28);
			postes.getRoles().add(ap38);
			postes.getRoles().add(ap48);
			postes.getRoles().add(ap19);
			postes.getRoles().add(ap29);
			postes.getRoles().add(ap39);
			postes.getRoles().add(ap49);
			posteService.add(postes, appusers.getLogin());
			logPosteUserService.add(postes, users, appusers.getLogin());
			structureService.add(structure, postes.getName());			
			Postes poste = new Postes("SGP", "description", structures);
			Postes poste1 = new Postes("Poste1", "description", structures);
			posteService.add(poste, postes.getName());
			posteService.add(poste1, postes.getName());			
			postes.getPosteSubalterne().add(poste);
			poste.getPosteSubalterne().add(poste1);			
			posteService.addSubPoste(postes.getName(),postes);
			posteService.addSubPoste(postes.getName(),poste);
			structureService.addSubStructures(postes.getName(), structures, structure);
			WorkFlow workFlow = new WorkFlow("workFlow ", "workFlow", "description");
			workFlowService.add(workFlow, postes.getName());
			WorkFlow workFlow1 = new WorkFlow("workFlow 1", "workFlow1", "description");
			workFlowService.add(workFlow1, postes.getName());
			WorkFlow workFlow2 = new WorkFlow("workFlow 2", "workFlow2", "description");
			workFlowService.add(workFlow2, postes.getName());

			WorkFlow workFlow3 = new WorkFlow("workFlow3 ", "workFlow3", "description");
			workFlowService.add(workFlow3, postes.getName());
			WorkFlow workFlow4 = new WorkFlow("workFlow 4", "workFlow4", "description");
			workFlowService.add(workFlow4, postes.getName());
			WorkFlow workFlow5 = new WorkFlow("workFlow 5", "workFlow5", "description");
			workFlowService.add(workFlow5, postes.getName());

			WorkFlow workFlow6 = new WorkFlow("workFlow6 ", "workFlow6", "description");
			workFlowService.add(workFlow6, postes.getName());
			WorkFlow workFlow7 = new WorkFlow("workFlow 7", "workFlow7", "description");
			workFlowService.add(workFlow7, postes.getName());
			WorkFlow workFlow8 = new WorkFlow("workFlow 8", "workFlow8", "description");
			workFlowService.add(workFlow8, postes.getName());

			WorkFlow workFlow9 = new WorkFlow("workFlow9 ", "workFlow9", "description");
			workFlowService.add(workFlow9, postes.getName());
			WorkFlow workFlow10 = new WorkFlow("workFlow 10", "workFlow10", "description");
			workFlowService.add(workFlow10, postes.getName());
			WorkFlow workFlow11 = new WorkFlow("workFlow 11", "workFlow11", "description");
			workFlowService.add(workFlow11, postes.getName());
			WorkFlowPoste workFlowPoste = new WorkFlowPoste(poste,workFlow,0);
			WorkFlowPoste workFlowPoste1 = new WorkFlowPoste(poste1,workFlow,1);
			workFlowService.addPosteToWorkFlow(workFlowPoste, postes.getName());
			workFlowService.addPosteToWorkFlow(workFlowPoste1, postes.getName());
			
		};
	}

}
