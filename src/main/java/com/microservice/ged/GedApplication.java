package com.microservice.ged;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.RolesRepo;

@SpringBootApplication
public class GedApplication {

	public static void main(String[] args) {
		SpringApplication.run(GedApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(
			AppUserRepo userRepo, 
			RolesRepo approlerepository
			) {
		return arg -> {
			//Appusers appusers = new Appusers("ROOT", "root","1234");
			//userRepo.save(appusers);
			//Structures s1 = new Structures("Cabinet du Maire", "CM", "Service du maire", 0, null, null, null);
			//Structures s2 = new Structures("Service Courrier", "SC", "Gestion du courrier", 1, null, s1, null);
			//Structures s3 = new Structures("Service Archivage", "SA", "Gestion des archive", 1, null, s2, null);
			//List<Structures> ls1 = new ArrayList<Structures>();
			//ls1.add(s2);
			//ls1.add(s3);
			//s1.setSousStructure(ls1);
			//List<Structures> ls2 = new ArrayList<Structures>();
			//ls2.add(s3);
			//s2.setSousStructure(ls2);

			//Postes p1 = new Postes( "Maire", "le poste du maire", 0, s1, null);
			// Employe e = employeRepository.findEmployeById(id);

			//Postes p2 = new Postes("Directeur Courrier", "Directeur courier", 1, s2, null);

			//PosteEmploye pe1 = new PosteEmploye(null, new Date(), null, 1, p1);

			//PosteEmploye pe2 = new PosteEmploye(null, new Date(), null, 2, p2);

			Roles ap1 = new Roles("CWORKFLOW", true, false, false, false);
			Roles ap2 = new Roles("RWORKFLOW", false, true, false, false);
			Roles ap3 = new Roles("UWORKFLOW", false, false, true, false);
			Roles ap4 = new Roles("DWORKFLOW", false, false, false, true);
			approlerepository.save(ap1);
			approlerepository.save(ap2);
			approlerepository.save(ap3);
			approlerepository.save(ap4);

			Roles ap12 = new Roles("CUSER", true, false, false, false);
			Roles ap22 = new Roles("RUSER", false, true, false, false);
			Roles ap32 = new Roles("UUSER", false, false, true, false);
			Roles ap42 = new Roles("DUSER", false, false, false, true);
			approlerepository.save(ap12);
			approlerepository.save(ap22);
			approlerepository.save(ap32);
			approlerepository.save(ap42);

			Roles ap13 = new Roles("CTYPELIASSE", true, false, false, false);
			Roles ap23 = new Roles("RTYPELIASSE", false, true, false, false);
			Roles ap33 = new Roles("UTYPELIASSE", false, false, true, false);
			Roles ap43 = new Roles("DTYPELIASSE", false, false, false, true);
			approlerepository.save(ap13);
			approlerepository.save(ap23);
			approlerepository.save(ap33);
			approlerepository.save(ap43);

			Roles ap14 = new Roles("CTYPEDOC", true, false, false, false);
			Roles ap24 = new Roles("RTYPEDOC", false, true, false, false);
			Roles ap34 = new Roles("UTYPEDOC", false, false, true, false);
			Roles ap44 = new Roles("DTYPEDOC", false, false, false, true);
			approlerepository.save(ap14);
			approlerepository.save(ap24);
			approlerepository.save(ap34);
			approlerepository.save(ap44);

			Roles ap15 = new Roles("CSTRUCTURE", true, false, false, false);
			Roles ap25 = new Roles("RSTRUCTURE", false, true, false, false);
			Roles ap35 = new Roles("USTRUCTURE", false, false, true, false);
			Roles ap45 = new Roles("DSTRUCTURE", false, false, false, true);
			approlerepository.save(ap15);
			approlerepository.save(ap25);
			approlerepository.save(ap35);
			approlerepository.save(ap45);

			Roles ap16 = new Roles("RROLE", false, true, false, false);
			approlerepository.save(ap16);
			Roles ap26 = new Roles("PRINT", false, true, false, false);
			approlerepository.save(ap26);

			Roles ap17 = new Roles("CPOSTE", true, false, false, false);
			Roles ap27 = new Roles("RPOSTE", false, true, false, false);
			Roles ap37 = new Roles("UPOSTE", false, false, true, false);
			Roles ap47 = new Roles("DPOSTE", false, false, false, true);
			approlerepository.save(ap17);
			approlerepository.save(ap27);
			approlerepository.save(ap37);
			approlerepository.save(ap47);

			Roles ap18 = new Roles("CLIASSE", true, false, false, false);
			Roles ap28 = new Roles("RLIASSE", false, true, false, false);
			Roles ap38 = new Roles("ULIASSE", false, false, true, false);
			Roles ap48 = new Roles("DLIASSE", false, false, false, true);
			approlerepository.save(ap18);
			approlerepository.save(ap28);
			approlerepository.save(ap38);
			approlerepository.save(ap48);

			Roles ap19 = new Roles("CDOC", true, false, false, false);
			Roles ap29 = new Roles("RDOC", false, true, false, false);
			Roles ap39 = new Roles("UDOC", false, false, true, false);
			Roles ap49 = new Roles("DDOC", false, false, false, true);
			approlerepository.save(ap19);
			approlerepository.save(ap29);
			approlerepository.save(ap39);
			approlerepository.save(ap49);
			

			/*Roles ap18 = new Roles("CLIASSE", true, false, false, false);
			Roles ap28 = new Roles("RLIASSE", false, true, false, false);
			Roles ap38 = new Roles("ULIASSE", false, false, true, false);
			Roles ap48 = new Roles("DLIASSE", false, false, false, true);
			approlerepository.save(ap18);
			approlerepository.save(ap28);
			approlerepository.save(ap38);
			approlerepository.save(ap48);*/
			
			
			//structureRepository.save(s1);
			//structureRepository.save(s2);
			//structureRepository.save(s3);
			
			//postRepository.save(p1);
			//p2.getRoles().add(ap1);
			//p2.getRoles().add(ap2);
			//p2.getRoles().add(ap3);
			//postRepository.save(p2);
			
			//posteemployerepository.save(pe1);
			//posteemployerepository.save(pe2);

		};
	}

}
