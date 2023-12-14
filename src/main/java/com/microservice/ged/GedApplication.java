package com.microservice.ged;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
import com.microservice.ged.repository.DroitsRepo;
import com.microservice.ged.service.AppUserService;
import com.microservice.ged.service.GroupProfileService;
import com.microservice.ged.service.GroupUserService;
import com.microservice.ged.service.LogPosteUserService;
import com.microservice.ged.service.PosteService;
import com.microservice.ged.service.ProfilesService;
import com.microservice.ged.service.StructureService;
import com.microservice.ged.service.WorkFlowService;
import com.microservice.ged.utils.GroupProfilesBean;
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
	
	@Value("${spring.datasource.username}")
	String port;
	
	@Bean
	CommandLineRunner start(
			GroupProfileService groupProfileService, 
			DroitsRepo droitsRepo,
			ProfilesService profilesService,
			StructureService structureService,
			PosteService posteService,
			LogPosteUserService logPosteUserService,
			GroupUserService groupUserService,
			WorkFlowService workFlowService,
			AppUserService appUserService
			) {
		return arg -> {
			if(!port.equals("sa")) {
				Structures maire = new Structures(
						"Mairie de la ville", 
						"MV",
						"blue", 
						"");
				structureService.add(maire);
				Postes maireP = new Postes("Maire de la ville", "description", structureService.findByName(maire.getName()));
				posteService.addPoste(maireP);
				Postes maireA1 = new Postes("Adjoint N°1 au Maire de la ville", "description", structureService.findByName(maire.getName()));
				maireA1.setPosteSuperieur(posteService.findPosteByName(maireP.getName()));
				posteService.addSubPoste(maireA1);
				Postes maireA2 = new Postes("Adjoint N°2 au Maire de la ville", "description", structureService.findByName(maire.getName()));
				maireA2.setPosteSuperieur(posteService.findPosteByName(maireP.getName()));
				posteService.addSubPoste(maireA2);
				Postes maireA3 = new Postes("Adjoint N°3 au Maire de la ville", "description", structureService.findByName(maire.getName()));
				maireA3.setPosteSuperieur(posteService.findPosteByName(maireP.getName()));
				posteService.addSubPoste(maireA3);
				Postes maireA4 = new Postes("Adjoint N°4 au Maire de la ville", "description", structureService.findByName(maire.getName()));
				maireA4.setPosteSuperieur(posteService.findPosteByName(maireP.getName()));
				posteService.addSubPoste(maireA4);

				Structures servicesRattache = new Structures(
						"Services Rattahes aupres du Maire de la ville", 
						"SRMV",
						"blue", 
						"");
				servicesRattache.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(servicesRattache);
				Postes ct = new Postes("Conseiller Technique N°1", "description", structureService.findByName(servicesRattache.getName()));
				posteService.addPoste(ct);
				Postes ct1 = new Postes("Conseiller Technique N°2", "description", structureService.findByName(servicesRattache.getName()));
				ct1.setPosteSuperieur(posteService.findPosteByName(ct.getName()));
				posteService.addSubPoste(ct1);
				Postes ct2 = new Postes("Conseiller Technique N°3", "description", structureService.findByName(servicesRattache.getName()));
				ct2.setPosteSuperieur(posteService.findPosteByName(ct.getName()));
				posteService.addSubPoste(ct2);
				
				Structures cabinetMairie = new Structures(
						"Cabinet du Maire de la Ville", 
						"CMV",
						"blue", 
						"");
				cabinetMairie.setStructureSuperieur(structureService.findByName(servicesRattache.getName()));
				structureService.addSubStructures(cabinetMairie);
				Postes att = new Postes("Attaché (CMV)", "description", structureService.findByName(cabinetMairie.getName()));
				posteService.addPoste(att);
				Postes cea1 = new Postes("Chargé d'Etude Assistant N°1 (CMV)", "description", structureService.findByName(cabinetMairie.getName()));
				cea1.setPosteSuperieur(posteService.findPosteByName(att.getName()));
				posteService.addSubPoste(cea1);
				Postes cea2 = new Postes("Chargé d'Etude Assistant N°2 (CMV)", "description", structureService.findByName(cabinetMairie.getName()));
				cea2.setPosteSuperieur(posteService.findPosteByName(att.getName()));
				posteService.addSubPoste(cea2);
				
				Structures structuresCCRP = new Structures(
						"Cellule de Communication et des Relations Publiques", 
						"CCRP",
						"blue", 
						"Placé sous l'autorité d'un Chef de Cellule, la Cellule de Communication et des Relations Publiques est chargé : \\r\n ");
				
				structuresCCRP.setStructureSuperieur(structureService.findByName(servicesRattache.getName()));
				structureService.addSubStructures(structuresCCRP);
				Postes postesCCRP = new Postes("Chef de Cellule (CCRP)", "description", structureService.findByName(structuresCCRP.getName()));
				posteService.addPoste(postesCCRP);
				Postes postesCCRP1 = new Postes("Chargé d'Etude Assistants N°1 (CCRP)", "description", structureService.findByName(structuresCCRP.getName()));
				postesCCRP1.setPosteSuperieur(posteService.findPosteByName(postesCCRP.getName()));
				posteService.addSubPoste(postesCCRP1);
				Postes postesCCRP2 = new Postes("Chargé d'Etude Assistants N°2 (CCRP)", "description", structureService.findByName(structuresCCRP.getName()));
				postesCCRP2.setPosteSuperieur(posteService.findPosteByName(postesCCRP.getName()));
				posteService.addSubPoste(postesCCRP2);
				Postes postesCCRP3 = new Postes("Chargé d'Etude Assistants N°3 (CCRP)", "description", structureService.findByName(structuresCCRP.getName()));
				postesCCRP3.setPosteSuperieur(posteService.findPosteByName(postesCCRP.getName()));
				posteService.addSubPoste(postesCCRP3);

				Structures structuresRADIO = new Structures(
						"Radio Nkul-Ongola", 
						"RNO",
						"blue", 
						"Placé sous l'autorité d'un Chef dantenne, la Cellule de Communication et des Relations Publiques est chargé : \\r\n ");
				
				structuresRADIO.setStructureSuperieur(structureService.findByName(structuresCCRP.getName()));
				structureService.addSubStructures(structuresRADIO);
				Postes postesRADIO = new Postes("Chef d'Antenne", "description", structureService.findByName(structuresRADIO.getName()));
				posteService.addPoste(postesRADIO);
				
				Structures structuresDSR = new Structures(
						"Division du Suivi et de la Relance", 
						"DSRI",
						"blue", 
						"Placé sous l'autorité d'un chef de Division, la division des Inspections est chargé : \\r\n ");
				
				structuresDSR.setStructureSuperieur(structureService.findByName(servicesRattache.getName()));
				structureService.addSubStructures(structuresDSR);
				Postes postesDSR = new Postes("Chef de Division (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				posteService.addPoste(postesDSR);
				Postes postesDSR1 = new Postes("Chargé d'Etude N°1 (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				postesDSR1.setPosteSuperieur(posteService.findPosteByName(postesDSR.getName()));
				posteService.addSubPoste(postesDSR1);
				Postes postesDSR2 = new Postes("Chargé d'Etude N°2 (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				postesDSR2.setPosteSuperieur(posteService.findPosteByName(postesDSR.getName()));
				posteService.addSubPoste(postesDSR2);
				Postes postesDSR3 = new Postes("Chargé d'Etude N°3 (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				postesDSR3.setPosteSuperieur(posteService.findPosteByName(postesDSR.getName()));
				posteService.addSubPoste(postesDSR3);
				Postes postesDSR11 = new Postes("Chargé d'Etude Assistant N°1 (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				postesDSR11.setPosteSuperieur(posteService.findPosteByName(postesDSR1.getName()));
				posteService.addSubPoste(postesDSR11);
				Postes postesDSR21 = new Postes("Chargé d'Etude Assistant N°2 (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				postesDSR21.setPosteSuperieur(posteService.findPosteByName(postesDSR2.getName()));
				posteService.addSubPoste(postesDSR21);
				Postes postesDSR31 = new Postes("Chargé d'Etude Assistant N°3 (DSRI)", "description", structureService.findByName(structuresDSR.getName()));
				postesDSR31.setPosteSuperieur(posteService.findPosteByName(postesDSR3.getName()));
				posteService.addSubPoste(postesDSR31);
				

				Structures structuresDI = new Structures(
						"Division des Inspections", 
						"DI",
						"blue", 
						"Placé sous l'autorité d'un chef de Division, la division des Inspections est chargé : \\r\n ");
				
				structuresDI.setStructureSuperieur(structureService.findByName(servicesRattache.getName()));
				structureService.addSubStructures(structuresDI);
				Postes postesDI = new Postes("Chef de Division (DI)", "description", structureService.findByName(structuresDI.getName()));
				posteService.addPoste(postesDI);
				Postes postesDI1 = new Postes("Inspecteurs de Service N°1", "description", structureService.findByName(structuresDI.getName()));
				postesDI1.setPosteSuperieur(posteService.findPosteByName(postesDI.getName()));
				posteService.addSubPoste(postesDI1);
				Postes postesDI2 = new Postes("Inspecteurs de Service N°2", "description", structureService.findByName(structuresDI.getName()));
				postesDI2.setPosteSuperieur(posteService.findPosteByName(postesDI.getName()));
				posteService.addSubPoste(postesDI2);
				
				
				Structures structuresCM = new Structures(
						"Service de la Comptabilité-Matieres", 
						"SCM",
						"blue", 
						"Placé sous l'autorité d'un chef de Service, le Service de la Comptabilité-Matieres est chargé : \\r\n ");
				
				structuresCM.setStructureSuperieur(structureService.findByName(servicesRattache.getName()));
				structureService.addSubStructures(structuresCM);
				Postes postesCS = new Postes("Chef de Service (SCM)", "description", structureService.findByName(structuresCM.getName()));
				posteService.addPoste(postesCS);
				Postes postes1 = new Postes("Comptable des matieres Consomptibles", "description", structureService.findByName(structuresCM.getName()));
				postes1.setPosteSuperieur(posteService.findPosteByName(postesCS.getName()));
				posteService.addSubPoste(postes1);
				Postes postes2 = new Postes("Comptable des Materiels et Equipements", "description", structureService.findByName(structuresCM.getName()));
				postes2.setPosteSuperieur(posteService.findPosteByName(postesCS.getName()));
				posteService.addSubPoste(postes2);
				Postes postes3 = new Postes("Comptable des Biens Immeubles", "description", structureService.findByName(structuresCM.getName()));
				postes3.setPosteSuperieur(posteService.findPosteByName(postesCS.getName()));
				posteService.addSubPoste(postes3);
				Structures secretariatParticulier = new Structures(
						"Secretariats Particuliers des Adjoints au Maire de la Ville", 
						"SPAMV",
						"blue", 
						"");
				secretariatParticulier.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(secretariatParticulier);
				Postes spa = new Postes("Chef de Secretariat Particulier", "description", structureService.findByName(secretariatParticulier.getName()));
				posteService.addPoste(spa);
				
				
				Structures secretariatGeneral = new Structures(
						"Secretariats Gènéral", 
						"SG",
						"blue", 
						"");
				secretariatGeneral.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(secretariatGeneral);
				Postes sg = new Postes("Secretaire General (SG)", "description", structureService.findByName(secretariatGeneral.getName()));
				posteService.addPoste(sg);
				Postes ceSG = new Postes("Chargé d'Etudes (SG)", "description", structureService.findByName(secretariatGeneral.getName()));
				ceSG.setPosteSuperieur(posteService.findPosteByName(sg.getName()));
				posteService.addSubPoste(ceSG);
				Postes cea1SG = new Postes("Chargé d'Etudes Assistant N°1 (SG)", "description", structureService.findByName(secretariatGeneral.getName()));
				cea1SG.setPosteSuperieur(posteService.findPosteByName(ceSG.getName()));
				posteService.addSubPoste(cea1SG);
				Postes cea2SG = new Postes("Chargé d'Etudes Assistant N°2 (SG)", "description", structureService.findByName(secretariatGeneral.getName()));
				cea2SG.setPosteSuperieur(posteService.findPosteByName(ceSG.getName()));
				posteService.addSubPoste(cea2SG);
				
				Structures serviceCourrier = new Structures(
						"Service du Courrier Central", 
						"SCC",
						"blue", 
						"");
				serviceCourrier.setStructureSuperieur(structureService.findByName(secretariatGeneral.getName()));
				structureService.addSubStructures(serviceCourrier);
				Postes postesSCC = new Postes("Chef de Service(SCC)", "description", structureService.findByName(serviceCourrier.getName()));
				posteService.addPoste(postesSCC);
				Postes postesSCC1 = new Postes("Bureau du Courrier Confidentiel", "description", structureService.findByName(serviceCourrier.getName()));
				postesSCC1.setPosteSuperieur(posteService.findPosteByName(postesSCC.getName()));
				posteService.addSubPoste(postesSCC1);
				Postes postesSCC2 = new Postes("Bureau du Courrier Arrivée", "description", structureService.findByName(serviceCourrier.getName()));
				postesSCC2.setPosteSuperieur(posteService.findPosteByName(postesSCC.getName()));
				posteService.addSubPoste(postesSCC2);
				Postes postesCC3 = new Postes("Bureau du Courrier Depart", "description", structureService.findByName(serviceCourrier.getName()));
				postesCC3.setPosteSuperieur(posteService.findPosteByName(postesSCC.getName()));
				posteService.addSubPoste(postesCC3);
				
				Structures serviceEtatCivil = new Structures(
						"Service de l'Etat Civil", 
						"SEC",
						"blue", 
						"");
				serviceEtatCivil.setStructureSuperieur(structureService.findByName(secretariatGeneral.getName()));
				structureService.addSubStructures(serviceEtatCivil);
				Postes postesSEC = new Postes("Chef de Service(SEC)", "description", structureService.findByName(serviceEtatCivil.getName()));
				posteService.addPoste(postesSEC);
				Postes postesSCEC1 = new Postes("Bureau des Naissances", "description", structureService.findByName(serviceEtatCivil.getName()));
				postesSCEC1.setPosteSuperieur(posteService.findPosteByName(postesSEC.getName()));
				posteService.addSubPoste(postesSCEC1);
				Postes postesCEC2 = new Postes("Bureau des Mariages", "description", structureService.findByName(serviceEtatCivil.getName()));
				postesCEC2.setPosteSuperieur(posteService.findPosteByName(postesSEC.getName()));
				posteService.addSubPoste(postesCEC2);
				Postes postesCEC3 = new Postes("Bureau des Deces", "description", structureService.findByName(serviceEtatCivil.getName()));
				postesCEC3.setPosteSuperieur(posteService.findPosteByName(postesSEC.getName()));
				posteService.addSubPoste(postesCEC3);
				Postes postesCEC4 = new Postes("Bureau des Archives d'Etat Civil", "description", structureService.findByName(serviceEtatCivil.getName()));
				postesCEC4.setPosteSuperieur(posteService.findPosteByName(postesSEC.getName()));
				posteService.addSubPoste(postesCEC4);
				
				Structures serviceDoc = new Structures(
						"Service de la Documentation et des Archives", 
						"SDA",
						"blue", 
						"");
				serviceDoc.setStructureSuperieur(structureService.findByName(secretariatGeneral.getName()));
				structureService.addSubStructures(serviceDoc);
				Postes postesSDA = new Postes("Chef de Service(SDA)", "description", structureService.findByName(serviceDoc.getName()));
				posteService.addPoste(postesSDA);
				Postes postesSDA1 = new Postes("Bureau de la Documentation", "description", structureService.findByName(serviceDoc.getName()));
				postesSDA1.setPosteSuperieur(posteService.findPosteByName(postesSDA.getName()));
				posteService.addSubPoste(postesSDA1);
				Postes postesSDA2 = new Postes("Bureau des Archives", "description", structureService.findByName(serviceDoc.getName()));
				postesSDA2.setPosteSuperieur(posteService.findPosteByName(postesSDA.getName()));
				posteService.addSubPoste(postesSDA2);


				Structures serviceCTI = new Structures(
						"Cellule de la Traduction et de l'Interpretariat", 
						"CTI",
						"blue", 
						"");
				serviceCTI.setStructureSuperieur(structureService.findByName(secretariatGeneral.getName()));
				structureService.addSubStructures(serviceCTI);
				Postes postesCTI = new Postes("Chef de Service(CTI)", "description", structureService.findByName(serviceCTI.getName()));
				posteService.addPoste(postesCTI);
				Postes postesCTI1 = new Postes("Chargé d'Etude Assistant N°1 (CTI)", "description", structureService.findByName(serviceCTI.getName()));
				postesCTI1.setPosteSuperieur(posteService.findPosteByName(postesCTI.getName()));
				posteService.addSubPoste(postesCTI1);
				Postes postesCTI2 = new Postes("Chargé d'Etude Assistant N°2 (CTI)", "description", structureService.findByName(serviceCTI.getName()));
				postesCTI2.setPosteSuperieur(posteService.findPosteByName(postesCTI.getName()));
				posteService.addSubPoste(postesCTI2);
				Postes postesCTI3 = new Postes("Chargé d'Etude Assistant N°3 (CTI)", "description", structureService.findByName(serviceCTI.getName()));
				postesCTI3.setPosteSuperieur(posteService.findPosteByName(postesCTI.getName()));
				posteService.addSubPoste(postesCTI3);
				Postes postesCTI4 = new Postes("Chargé d'Etude Assistant N°4 (CTI)", "description", structureService.findByName(serviceCTI.getName()));
				postesCTI4.setPosteSuperieur(posteService.findPosteByName(postesCTI.getName()));
				posteService.addSubPoste(postesCTI4);
				
				Structures serviceCSI = new Structures(
						"Cellule des Systéme d'Information", 
						"CSI",
						"blue", 
						"");
				serviceCSI.setStructureSuperieur(structureService.findByName(secretariatGeneral.getName()));
				structureService.addSubStructures(serviceCSI);
				Postes postesCSI = new Postes("Chef de Cellule (CSI)", "description", structureService.findByName(serviceCSI.getName()));
				posteService.addPoste(postesCSI);
				Postes postesCSI1 = new Postes("Chargé d'Etude Assistant N°1 (CSI)", "description", structureService.findByName(serviceCSI.getName()));
				postesCSI1.setPosteSuperieur(posteService.findPosteByName(postesCSI.getName()));
				posteService.addSubPoste(postesCSI1);
				Postes postesCSI2 = new Postes("Chargé d'Etude Assistant N°2 (CSI)", "description", structureService.findByName(serviceCSI.getName()));
				postesCSI2.setPosteSuperieur(posteService.findPosteByName(postesCSI.getName()));
				posteService.addSubPoste(postesCSI2);
				Postes postesCSI3 = new Postes("Chargé d'Etude Assistant N°3 (CSI)", "description", structureService.findByName(serviceCSI.getName()));
				postesCSI3.setPosteSuperieur(posteService.findPosteByName(postesCSI.getName()));
				posteService.addSubPoste(postesCSI3);
				
				Structures serviceDAJC = new Structures(
						"Division des Affaires Juridiques et du Contentieux", 
						"DAJC",
						"blue", 
						"");
				serviceDAJC.setStructureSuperieur(structureService.findByName(secretariatGeneral.getName()));
				structureService.addSubStructures(serviceDAJC);
				Postes postesDAJC = new Postes("Chef de Division(DAJC)", "description", structureService.findByName(serviceDAJC.getName()));
				posteService.addPoste(postesDAJC);
				
				Structures serviceCER = new Structures(
						"Cellule des Etudes et de la Reglementation", 
						"CER",
						"blue", 
						"");
				serviceCER.setStructureSuperieur(structureService.findByName(serviceDAJC.getName()));
				structureService.addSubStructures(serviceCER);
				Postes postesCER = new Postes("Chef de Cellule(CER)", "description", structureService.findByName(serviceCER.getName()));
				posteService.addPoste(postesCER);
				Postes postesCER1 = new Postes("Chargé d'Etude Assistant N°1 (CER)", "description", structureService.findByName(serviceCER.getName()));
				postesCER1.setPosteSuperieur(posteService.findPosteByName(postesCER.getName()));
				posteService.addSubPoste(postesCER1);
				Postes postesCER2 = new Postes("Chargé d'Etude Assistant N°2 (CER)", "description", structureService.findByName(serviceCER.getName()));
				postesCER2.setPosteSuperieur(posteService.findPosteByName(postesCER.getName()));
				posteService.addSubPoste(postesCER2);

				Structures serviceCC = new Structures(
						"Cellule du Contentieux", 
						"CC",
						"blue", 
						"");
				serviceCC.setStructureSuperieur(structureService.findByName(serviceDAJC.getName()));
				structureService.addSubStructures(serviceCC);
				Postes postesCC = new Postes("Chef de Cellule(CC)", "description", structureService.findByName(serviceCC.getName()));
				posteService.addPoste(postesCC);
				Postes postesCC1 = new Postes("Chargé d'Etude Assistant N°1 (CC)", "description", structureService.findByName(serviceCC.getName()));
				postesCC1.setPosteSuperieur(posteService.findPosteByName(postesCC.getName()));
				posteService.addSubPoste(postesCC1);
				Postes postesCC2 = new Postes("Chargé d'Etude Assistant N°2 (CC)", "description", structureService.findByName(serviceCC.getName()));
				postesCC2.setPosteSuperieur(posteService.findPosteByName(postesCC.getName()));
				posteService.addSubPoste(postesCC2);
				
				
				
				
				Structures duacv = new Structures(
						"Direction de l'Urbanisme, de l'Architecture et du Cadre de Vie", 
						"DUACV",
						"blue", 
						"");
				duacv.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(duacv);
				Postes dirtduacv = new Postes("Directeur (DUACV)", "description", structureService.findByName(duacv.getName()));
				posteService.addPoste(dirtduacv);
				
				Structures cepp = new Structures(
						"Cellules des Etudes, de la Planification et de la Prospective", 
						"CEPP",
						"blue", 
						"");
				cepp.setStructureSuperieur(structureService.findByName(duacv.getName()));
				structureService.addSubStructures(cepp);
				Postes postecepp = new Postes("Chef de Cellule (CEPP)", "description", structureService.findByName(cepp.getName()));
				posteService.addPoste(postecepp);
				Postes postecepp1 = new Postes("Chargé d'Etude Assistant N°1 (CEPP)", "description", structureService.findByName(cepp.getName()));
				postecepp1.setPosteSuperieur(posteService.findPosteByName(postecepp.getName()));
				posteService.addSubPoste(postecepp1);
				Postes postecepp2 = new Postes("Chargé d'Etude Assistant N°2 (CEPP)", "description", structureService.findByName(cepp.getName()));
				postecepp2.setPosteSuperieur(posteService.findPosteByName(postecepp.getName()));
				posteService.addSubPoste(postecepp2);
				
				Structures ou = new Structures(
						"Observatoire Urbain", 
						"OU",
						"blue", 
						"");
				ou.setStructureSuperieur(structureService.findByName(cepp.getName()));
				structureService.addSubStructures(ou);
				Postes posteou = new Postes("Observateur", "description", structureService.findByName(ou.getName()));
				posteService.addPoste(posteou);
				
				Structures sdua = new Structures(
						"Sous-Direction de l'Urbanisme et de l'Architecture", 
						"SDUA",
						"blue", 
						"");
				sdua.setStructureSuperieur(structureService.findByName(duacv.getName()));
				structureService.addSubStructures(sdua);
				Postes postesdua = new Postes("Sous-Directeur (SDUA)", "description", structureService.findByName(sdua.getName()));
				posteService.addPoste(postesdua);
				
				Structures safd = new Structures(
						"Service des Affaires Fonciéres et Domaniales", 
						"SAFD",
						"blue", 
						"");
				safd.setStructureSuperieur(structureService.findByName(sdua.getName()));
				structureService.addSubStructures(safd);
				Postes postessafd = new Postes("Chef Service (SAFD)", "description", structureService.findByName(safd.getName()));
				posteService.addPoste(postessafd);
				Postes postesafd1 = new Postes("Bureau des Affaires Domaniales", "description", structureService.findByName(safd.getName()));
				postesafd1.setPosteSuperieur(posteService.findPosteByName(postessafd.getName()));
				posteService.addSubPoste(postesafd1);
				Postes postesafd2 = new Postes("Bureau des Affaires Fonciéres", "description", structureService.findByName(safd.getName()));
				postesafd2.setPosteSuperieur(posteService.findPosteByName(postessafd.getName()));
				posteService.addSubPoste(postesafd2);
				Postes postesafd3 = new Postes("Bureau du Cadastre", "description", structureService.findByName(safd.getName()));
				postesafd3.setPosteSuperieur(posteService.findPosteByName(postessafd.getName()));
				posteService.addSubPoste(postesafd3);
				
				Structures smut = new Structures(
						"Service de la Mobilité Urbaine et des Transports", 
						"SMUT",
						"blue", 
						"");
				smut.setStructureSuperieur(structureService.findByName(sdua.getName()));
				structureService.addSubStructures(smut);
				Postes postessmut = new Postes("Chef Service (SMUT)", "description", structureService.findByName(smut.getName()));
				posteService.addPoste(postessmut);

				Structures suh = new Structures(
						"Service de l'Urbanisme et de l'Habitat", 
						"SUH",
						"blue", 
						"");
				suh.setStructureSuperieur(structureService.findByName(sdua.getName()));
				structureService.addSubStructures(suh);
				Postes postesuh = new Postes("Chef Service (SUH)", "description", structureService.findByName(suh.getName()));
				posteService.addPoste(postesuh);
				Postes postesuh1 = new Postes("Bureau du Controle des Normes Urbaines", "description", structureService.findByName(suh.getName()));
				postesuh1.setPosteSuperieur(posteService.findPosteByName(postesuh.getName()));
				posteService.addSubPoste(postesuh1);
				Postes postesuh2 = new Postes("Bureau de l'Adressage et du Jalonnement", "description", structureService.findByName(suh.getName()));
				postesuh2.setPosteSuperieur(posteService.findPosteByName(postesuh.getName()));
				posteService.addSubPoste(postesuh2);
				Postes postesuh3 = new Postes("Bureau de la Cartographie et de la Photogrammétrie", "description", structureService.findByName(suh.getName()));
				postesuh3.setPosteSuperieur(posteService.findPosteByName(postesuh.getName()));
				posteService.addSubPoste(postesuh3);
				Postes postesuh4 = new Postes("Bureau de la Gestion des Cimetieres", "description", structureService.findByName(suh.getName()));
				postesuh4.setPosteSuperieur(posteService.findPosteByName(postesuh.getName()));
				posteService.addSubPoste(postesuh4);
				
				Structures sdedd = new Structures(
						"Sous-Direction de l'Environnement et du Developpemnt Durable", 
						"SDEDD",
						"blue", 
						"");
				sdedd.setStructureSuperieur(structureService.findByName(duacv.getName()));
				structureService.addSubStructures(sdedd);
				Postes postessedd = new Postes("Sous-Directeur (SDEDD)", "description", structureService.findByName(sdedd.getName()));
				posteService.addPoste(postessedd);
				
				Structures spedd = new Structures(
						"Service de la Promotion de l'Environnement et du Developpement Durable", 
						"SPEDD",
						"blue", 
						"");
				spedd.setStructureSuperieur(structureService.findByName(sdedd.getName()));
				structureService.addSubStructures(spedd);
				Postes postespedd = new Postes("Chef Service (SPEDD)", "description", structureService.findByName(spedd.getName()));
				posteService.addPoste(postespedd);
				
				Structures shsgvd = new Structures(
						"Service de l'Hygiene, de la Salubrité, de la Gestion et de la Valorisation des Dechets", 
						"SHSGVD",
						"blue", 
						"");
				shsgvd.setStructureSuperieur(structureService.findByName(sdedd.getName()));
				structureService.addSubStructures(shsgvd);
				Postes posteshsgvd = new Postes("Chef Service (SHSGVD)", "description", structureService.findByName(shsgvd.getName()));
				posteService.addPoste(posteshsgvd);
				Postes posteshsgvd1 = new Postes("Bureau de la Prophylaxie", "description", structureService.findByName(shsgvd.getName()));
				posteshsgvd1.setPosteSuperieur(posteService.findPosteByName(posteshsgvd.getName()));
				posteService.addSubPoste(posteshsgvd1);
				Postes posteshsgvd2 = new Postes("Bureau de la Promotion de la Culture Citoyenne de Proprete", "description", structureService.findByName(shsgvd.getName()));
				posteshsgvd2.setPosteSuperieur(posteService.findPosteByName(posteshsgvd.getName()));
				posteService.addSubPoste(posteshsgvd2);
				Postes posteshsgvd3 = new Postes("Bureau du Ramassage et de la Gestion des Ordures Ménagéres", "description", structureService.findByName(shsgvd.getName()));
				posteshsgvd3.setPosteSuperieur(posteService.findPosteByName(posteshsgvd.getName()));
				posteService.addSubPoste(posteshsgvd3);

				Structures sea = new Structures(
						"Service de l'Eau et de l'Assainissement", 
						"SEA",
						"blue", 
						"");
				sea.setStructureSuperieur(structureService.findByName(sdedd.getName()));
				structureService.addSubStructures(sea);
				Postes postesea = new Postes("Chef Service (SEA)", "description", structureService.findByName(sea.getName()));
				posteService.addPoste(postesea);
				Postes postesea1 = new Postes("Bureau des Eaux Usées", "description", structureService.findByName(sea.getName()));
				postesea1.setPosteSuperieur(posteService.findPosteByName(postesea.getName()));
				posteService.addSubPoste(postesea1);
				Postes postesea2 = new Postes("Bureau du Drainage des Eaux Pluvieuses", "description", structureService.findByName(sea.getName()));
				postesea2.setPosteSuperieur(posteService.findPosteByName(postesea.getName()));
				posteService.addSubPoste(postesea2);
				Postes postesea3 = new Postes("Bureau dee Eaux Potables", "description", structureService.findByName(sea.getName()));
				postesea3.setPosteSuperieur(posteService.findPosteByName(postesea.getName()));
				posteService.addSubPoste(postesea3);
				
				Structures ddie = new Structures(
						"Direction du Developpement des Infrastructures et des Equipements", 
						"DDIE",
						"blue", 
						"");
				ddie.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(ddie);
				Postes dirtddie = new Postes("Directeur (DDIE)", "description", structureService.findByName(ddie.getName()));
				posteService.addPoste(dirtddie);
				
				Structures crdp = new Structures(
						"Cellules de la Recherche et du Developpement Participatifs Etudes", 
						"CRDP",
						"blue", 
						"");
				crdp.setStructureSuperieur(structureService.findByName(ddie.getName()));
				structureService.addSubStructures(crdp);
				Postes postecrdp = new Postes("Chef de Cellule (CRDP)", "description", structureService.findByName(crdp.getName()));
				posteService.addPoste(postecrdp);
				Postes postecrdp1 = new Postes("Chargé d'Etude Assistant N°1 (CRDP)", "description", structureService.findByName(crdp.getName()));
				postecrdp1.setPosteSuperieur(posteService.findPosteByName(postecrdp.getName()));
				posteService.addSubPoste(postecrdp1);
				Postes postecrdp2 = new Postes("Chargé d'Etude Assistant N°2 (CRDP)", "description", structureService.findByName(crdp.getName()));
				postecrdp2.setPosteSuperieur(posteService.findPosteByName(postecrdp.getName()));
				posteService.addSubPoste(postecrdp2);
				
				Structures sdeiu = new Structures(
						"Sous-Direction des Constructions et de l'Entretien des Infrastructures Urbaines", 
						"SDCEIU",
						"blue", 
						"");
				sdeiu.setStructureSuperieur(structureService.findByName(ddie.getName()));
				structureService.addSubStructures(sdeiu);
				Postes postesdeiu = new Postes("Sous-Directeur (SDCEIU)", "description", structureService.findByName(sdeiu.getName()));
				posteService.addPoste(postesdeiu);
				
				Structures svrd = new Structures(
						"Service de la Voirie et des Reseaux Divers", 
						"SVRD",
						"blue", 
						"");
				svrd.setStructureSuperieur(structureService.findByName(sdeiu.getName()));
				structureService.addSubStructures(svrd);
				Postes postesvrd = new Postes("Chef Service (SVRD)", "description", structureService.findByName(svrd.getName()));
				posteService.addPoste(postesvrd);
				
				Structures sep = new Structures(
						"Service de l'Eclairage Public", 
						"SEP",
						"blue", 
						"");
				sep.setStructureSuperieur(structureService.findByName(sdeiu.getName()));
				structureService.addSubStructures(sep);
				Postes postesep = new Postes("Chef Service (SEP)", "description", structureService.findByName(sep.getName()));
				posteService.addPoste(postesep);
				
				Structures sba = new Structures(
						"Service des Batiments", 
						"SBA",
						"blue", 
						"");
				sba.setStructureSuperieur(structureService.findByName(sdeiu.getName()));
				structureService.addSubStructures(sba);
				Postes postesba = new Postes("Chef Service (SBA)", "description", structureService.findByName(sba.getName()));
				posteService.addPoste(postesba);
				
				Structures sde = new Structures(
						"Sous-Direction des Equipements", 
						"SDE",
						"blue", 
						"");
				sde.setStructureSuperieur(structureService.findByName(ddie.getName()));
				structureService.addSubStructures(sde);
				Postes postesde = new Postes("Sous-Directeur (SDE)", "description", structureService.findByName(sde.getName()));
				posteService.addPoste(postesde);
				
				Structures gm = new Structures(
						"Garage Muncipal", 
						"GM",
						"blue", 
						"");
				gm.setStructureSuperieur(structureService.findByName(sde.getName()));
				structureService.addSubStructures(gm);
				Postes postegm = new Postes("Chef de Garage (GM)", "description", structureService.findByName(gm.getName()));
				posteService.addPoste(postegm);
				Postes postegm1 = new Postes("Bureau de la Mécanique", "description", structureService.findByName(gm.getName()));
				postegm1.setPosteSuperieur(posteService.findPosteByName(postegm.getName()));
				posteService.addSubPoste(postegm1);
				Postes postegm2 = new Postes("Bureau de l'Electricité Automobile", "description", structureService.findByName(gm.getName()));
				postegm2.setPosteSuperieur(posteService.findPosteByName(postegm.getName()));
				posteService.addSubPoste(postegm2);
				Postes postegm3 = new Postes("Bureau de la Tôlerie", "description", structureService.findByName(gm.getName()));
				postegm3.setPosteSuperieur(posteService.findPosteByName(postegm.getName()));
				posteService.addSubPoste(postegm3);
				
				Structures scl = new Structures(
						"Service du Carburant et des Lubrifiants", 
						"SCL",
						"blue", 
						"");
				scl.setStructureSuperieur(structureService.findByName(sde.getName()));
				structureService.addSubStructures(scl);
				Postes postescl = new Postes("Chef Service (SCL)", "description", structureService.findByName(scl.getName()));
				posteService.addPoste(postescl);
				Postes postescl1 = new Postes("Bureau Carburant", "description", structureService.findByName(scl.getName()));
				postescl1.setPosteSuperieur(posteService.findPosteByName(postescl.getName()));
				posteService.addSubPoste(postescl1);
				Postes postescl2 = new Postes("Bureau Lubrifiant", "description", structureService.findByName(scl.getName()));
				postescl2.setPosteSuperieur(posteService.findPosteByName(postescl.getName()));
				posteService.addSubPoste(postescl2);
				
				Structures sai = new Structures(
						"Service des Ateliers Industriels", 
						"SAI",
						"blue", 
						"");
				sai.setStructureSuperieur(structureService.findByName(sde.getName()));
				structureService.addSubStructures(sai);
				Postes postesai = new Postes("Chef Service (SAI)", "description", structureService.findByName(sai.getName()));
				posteService.addPoste(postesai);
				Postes postesai1 = new Postes("Centrale d'Enrobés", "description", structureService.findByName(sai.getName()));
				postesai1.setPosteSuperieur(posteService.findPosteByName(postesai.getName()));
				posteService.addSubPoste(postesai1);
				Postes postesai2 = new Postes("Atelier Menuiserie Bois", "description", structureService.findByName(sai.getName()));
				postesai2.setPosteSuperieur(posteService.findPosteByName(postesai.getName()));
				posteService.addSubPoste(postesai2);
				Postes postesai3 = new Postes("Atelier Menuiserie Métal", "description", structureService.findByName(sai.getName()));
				postesai3.setPosteSuperieur(posteService.findPosteByName(postesai.getName()));
				posteService.addSubPoste(postesai3);
				Postes postesai4 = new Postes("Atelier Prefabrication en Béton", "description", structureService.findByName(sai.getName()));
				postesai4.setPosteSuperieur(posteService.findPosteByName(postesai.getName()));
				posteService.addSubPoste(postesai4);
				Postes postesai5 = new Postes("Atelier Maintenance et Reparation", "description", structureService.findByName(sai.getName()));
				postesai5.setPosteSuperieur(posteService.findPosteByName(postesai.getName()));
				posteService.addSubPoste(postesai5);

				Structures djev = new Structures(
						"Direction des Jardins et des Espaces Verts", 
						"DJEV",
						"blue", 
						"");
				djev.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(djev);
				Postes dirtdjev = new Postes("Directeur (DJEV)", "description", structureService.findByName(djev.getName()));
				posteService.addPoste(dirtdjev);
				
				Structures sdpj = new Structures(
						"Sous-Direction des Parcs et Jardins", 
						"SDPJ",
						"blue", 
						"");
				sdpj.setStructureSuperieur(structureService.findByName(djev.getName()));
				structureService.addSubStructures(sdpj);
				Postes postesdpj = new Postes("Sous-Directeur (SDPJ)", "description", structureService.findByName(sdpj.getName()));
				posteService.addPoste(postesdpj);
				
				Structures scapj = new Structures(
						"Service de la Création et de l'Aménagement des Parcs et Jardins", 
						"SCAPJ",
						"blue", 
						"");
				scapj.setStructureSuperieur(structureService.findByName(sdpj.getName()));
				structureService.addSubStructures(scapj);
				Postes postescapj = new Postes("Chef Service (SCAPJ)", "description", structureService.findByName(scapj.getName()));
				posteService.addPoste(postescapj);
				Postes postescapj1 = new Postes("Bureau d'Aménagement et de la Gestion des Parcs et Jardins", "description", structureService.findByName(scapj.getName()));
				postescapj1.setPosteSuperieur(posteService.findPosteByName(postescapj.getName()));
				posteService.addSubPoste(postescapj1);
				Postes postescapj2 = new Postes("Bureau de Création des Parcs et Jardins", "description", structureService.findByName(scapj.getName()));
				postescapj2.setPosteSuperieur(posteService.findPosteByName(postescapj.getName()));
				posteService.addSubPoste(postescapj2);
				
				Structures sevpj = new Structures(
						"Service de l'Entretient et de la Valorisation des Parcs et Jardins", 
						"SEVPJ",
						"blue", 
						"");
				sevpj.setStructureSuperieur(structureService.findByName(sdpj.getName()));
				structureService.addSubStructures(sevpj);
				Postes postesevpj = new Postes("Chef Service (SEVPJ)", "description", structureService.findByName(sevpj.getName()));
				posteService.addPoste(postesevpj);
				Postes postesevpj1 = new Postes("Bureau de la Valorisation des Parcs et Jardins", "description", structureService.findByName(sevpj.getName()));
				postesevpj1.setPosteSuperieur(posteService.findPosteByName(postesevpj.getName()));
				posteService.addSubPoste(postesevpj1);
				Postes postesevpj2 = new Postes("Bureau de l'Aménagement et de la gestion des Parcs et Jardins", "description", structureService.findByName(sevpj.getName()));
				postesevpj2.setPosteSuperieur(posteService.findPosteByName(postesevpj.getName()));
				posteService.addSubPoste(postesevpj2);			

				Structures sdfc = new Structures(
						"Sous-Direction des Fôrets Communautaires", 
						"SDFC",
						"blue", 
						"");
				sdfc.setStructureSuperieur(structureService.findByName(djev.getName()));
				structureService.addSubStructures(sdfc);
				Postes postesdfc = new Postes("Sous-Directeur (SDFC)", "description", structureService.findByName(sdfc.getName()));
				posteService.addPoste(postesdfc);
				
				Structures scafc = new Structures(
						"Service de la Création et de l'Aménagement des Fôrets Communautaires", 
						"SCAFC",
						"blue", 
						"");
				scafc.setStructureSuperieur(structureService.findByName(sdfc.getName()));
				structureService.addSubStructures(scafc);
				Postes postescafc = new Postes("Chef Service (SCAFC)", "description", structureService.findByName(scafc.getName()));
				posteService.addPoste(postescafc);
				Postes postescafc1 = new Postes("Bureau d'Aménagement et de la Gestion des Fôrets Communautaires", "description", structureService.findByName(scafc.getName()));
				postescafc1.setPosteSuperieur(posteService.findPosteByName(postescafc.getName()));
				posteService.addSubPoste(postescafc1);
				Postes postescafc2 = new Postes("Bureau de Création des Fôrets Communautaires", "description", structureService.findByName(scafc.getName()));
				postescafc2.setPosteSuperieur(posteService.findPosteByName(postescafc.getName()));
				posteService.addSubPoste(postescafc2);
				
				Structures sevfc = new Structures(
						"Service de l'Entretient et de la Valorisation des Fôrets Communautaires", 
						"SEVFC",
						"blue", 
						"");
				sevfc.setStructureSuperieur(structureService.findByName(sdfc.getName()));
				structureService.addSubStructures(sevfc);
				Postes postesevfc = new Postes("Chef Service (SEVFC)", "description", structureService.findByName(sevfc.getName()));
				posteService.addPoste(postesevfc);
				Postes postesevfc1 = new Postes("Bureau de la Valorisation des Forets Communautaires", "description", structureService.findByName(sevfc.getName()));
				postesevfc1.setPosteSuperieur(posteService.findPosteByName(postesevfc.getName()));
				posteService.addSubPoste(postesevfc1);
				Postes postesevfc2 = new Postes("Bureau de l'Aménagement et de la gestion des Fôrets Communautaires", "description", structureService.findByName(sevfc.getName()));
				postesevfc2.setPosteSuperieur(posteService.findPosteByName(postesevfc.getName()));
				posteService.addSubPoste(postesevfc2);
				
				Structures ctpha = new Structures(
						"Centre des Techniques et Productions Horticoles et Arboricoles", 
						"CTPHA",
						"blue", 
						"");
				ctpha.setStructureSuperieur(structureService.findByName(djev.getName()));
				structureService.addSubStructures(ctpha);
				Postes postectpha = new Postes("Chef de Centre Directeur (CTPHA)", "description", structureService.findByName(ctpha.getName()));
				posteService.addPoste(postectpha);

				Structures dacs = new Structures(
						"Direction des Affaires, Culturelles et Sportives", 
						"DACS",
						"blue", 
						"");
				dacs.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(dacs);
				Postes dirtdacs = new Postes("Directeur (DACS)", "description", structureService.findByName(dacs.getName()));
				posteService.addPoste(dirtdacs);
				
				Structures sdaspf = new Structures(
						"Sous-Direction des Affaires Sociales et de la Promotion de la Femme", 
						"SDASPF",
						"blue", 
						"");
				sdaspf.setStructureSuperieur(structureService.findByName(dacs.getName()));
				structureService.addSubStructures(sdaspf);
				Postes postesdaspf = new Postes("Sous-Directeur (SDASPF)", "description", structureService.findByName(sdaspf.getName()));
				posteService.addPoste(postesdaspf);
				
				Structures spas = new Structures(
						"Service de Promotion de l'Accés à la Sante", 
						"SPAS",
						"blue", 
						"");
				spas.setStructureSuperieur(structureService.findByName(sdaspf.getName()));
				structureService.addSubStructures(spas);
				Postes postespas = new Postes("Chef Service (SPAS)", "description", structureService.findByName(spas.getName()));
				posteService.addPoste(postespas);
				Postes postespas1 = new Postes("Bureau de l'Education à la Santé", "description", structureService.findByName(spas.getName()));
				postespas1.setPosteSuperieur(posteService.findPosteByName(postespas.getName()));
				posteService.addSubPoste(postespas1);
				Postes postespas2 = new Postes("Bureau de Suivi des Formations Hospitaliéres", "description", structureService.findByName(spas.getName()));
				postespas2.setPosteSuperieur(posteService.findPosteByName(postespas.getName()));
				posteService.addSubPoste(postespas2);
				
				Structures sas = new Structures(
						"Service des Affaires Sociales", 
						"SAS",
						"blue", 
						"");
				sas.setStructureSuperieur(structureService.findByName(sdaspf.getName()));
				structureService.addSubStructures(sas);
				Postes postesas = new Postes("Chef Service (SAS)", "description", structureService.findByName(sas.getName()));
				posteService.addPoste(postesas);
				Postes postesas1 = new Postes("Bureau de la Solidarité Communautaire", "description", structureService.findByName(sas.getName()));
				postesas1.setPosteSuperieur(posteService.findPosteByName(postesas.getName()));
				posteService.addSubPoste(postesas1);
				Postes postesas2 = new Postes("Bureau du Partenariat avec le Service Sociale Local", "description", structureService.findByName(sas.getName()));
				postesas2.setPosteSuperieur(posteService.findPosteByName(postesas.getName()));
				posteService.addSubPoste(postesas2);
				
				Structures spf = new Structures(
						"Service de la Promotion de la Femme", 
						"SPF",
						"blue", 
						"");
				spf.setStructureSuperieur(structureService.findByName(sdaspf.getName()));
				structureService.addSubStructures(spf);
				Postes postespf = new Postes("Chef Service (SPF)", "description", structureService.findByName(spf.getName()));
				posteService.addPoste(postespf);
				Postes postespf1 = new Postes("Bureau de la Formation", "description", structureService.findByName(spf.getName()));
				postespf1.setPosteSuperieur(posteService.findPosteByName(postespf.getName()));
				posteService.addSubPoste(postespf1);
				Postes postespf2 = new Postes("Bureau de la Promotion Economique et Sociale Suivi des Formations Hospitaliéres", "description", structureService.findByName(spf.getName()));
				postespf2.setPosteSuperieur(posteService.findPosteByName(postespf.getName()));
				posteService.addSubPoste(postespf2);		
				
				Structures sdact = new Structures(
						"Sous-Direction des Arts, de la Culture et du Tourisme", 
						"SDACT",
						"blue", 
						"");
				sdact.setStructureSuperieur(structureService.findByName(dacs.getName()));
				structureService.addSubStructures(sdact);
				Postes postesdact = new Postes("Sous-Directeur (SDACT)", "description", structureService.findByName(sdact.getName()));
				posteService.addPoste(postesdact);
				
				Structures sacpll = new Structures(
						"Service des Arts, de la Culture et de la Promotion des Langues Locales", 
						"SACPLL",
						"blue", 
						"");
				sacpll.setStructureSuperieur(structureService.findByName(sdact.getName()));
				structureService.addSubStructures(sacpll);
				Postes postesacpll = new Postes("Chef Service (SACPLL)", "description", structureService.findByName(sacpll.getName()));
				posteService.addPoste(postesacpll);
				Postes postesacpll1 = new Postes("Bureau des Ars et Littérature", "description", structureService.findByName(sacpll.getName()));
				postesacpll1.setPosteSuperieur(posteService.findPosteByName(postesacpll.getName()));
				posteService.addSubPoste(postesacpll1);
				Postes postesacpll2 = new Postes("Bureau de la Culture et de Promotion des Langues Locales", "description", structureService.findByName(sacpll.getName()));
				postesacpll2.setPosteSuperieur(posteService.findPosteByName(postesacpll.getName()));
				posteService.addSubPoste(postesacpll2);
				
				Structures st = new Structures(
						"Service du Tourisme", 
						"ST",
						"blue", 
						"");
				st.setStructureSuperieur(structureService.findByName(sdact.getName()));
				structureService.addSubStructures(st);
				Postes postest = new Postes("Chef Service (ST)", "description", structureService.findByName(st.getName()));
				posteService.addPoste(postest);
				Postes postest1 = new Postes("Bureau du Tourisme", "description", structureService.findByName(st.getName()));
				postest1.setPosteSuperieur(posteService.findPosteByName(postest.getName()));
				posteService.addSubPoste(postest1);
				Postes postest2 = new Postes("Bureau des Autres Loisirs", "description", structureService.findByName(st.getName()));
				postest2.setPosteSuperieur(posteService.findPosteByName(postest.getName()));
				posteService.addSubPoste(postest2);
				
				Structures sdejs = new Structures(
						"Sous-Direction de l'Education, de la Jeunesse et des Sports", 
						"SDEJS",
						"blue", 
						"");
				sdejs.setStructureSuperieur(structureService.findByName(dacs.getName()));
				structureService.addSubStructures(sdejs);
				Postes postesdejs = new Postes("Sous-Directeur (SDEJS)", "description", structureService.findByName(sdejs.getName()));
				posteService.addPoste(postesdejs);
				
				Structures ssse = new Structures(
						"Service de Suivi du Systéme Educatif", 
						"SSSE",
						"blue", 
						"");
				ssse.setStructureSuperieur(structureService.findByName(sdejs.getName()));
				structureService.addSubStructures(ssse);
				Postes postessse = new Postes("Chef Service (SSSE)", "description", structureService.findByName(ssse.getName()));
				posteService.addPoste(postessse);
				Postes postessse1 = new Postes("Bureau de Suivi du Secteur Educatif Public","description", structureService.findByName(ssse.getName()));
				postessse1.setPosteSuperieur(posteService.findPosteByName(postessse.getName()));
				posteService.addSubPoste(postessse1);
				Postes postessse2 = new Postes("Bureau de Suivi du Secteur Educatif Privé", "description", structureService.findByName(ssse.getName()));
				postessse2.setPosteSuperieur(posteService.findPosteByName(postessse.getName()));
				posteService.addSubPoste(postessse2);
				
				Structures sfipj = new Structures(
						"Service de la Formation et de l'Insertion Professionnelles des Jeunes", 
						"SFIPJT",
						"blue", 
						"");
				sfipj.setStructureSuperieur(structureService.findByName(sdejs.getName()));
				structureService.addSubStructures(sfipj);
				Postes postesfipj = new Postes("Chef Service (SFIPJT)", "description", structureService.findByName(sfipj.getName()));
				posteService.addPoste(postesfipj);
				Postes postesfipj1 = new Postes("Bureau de la Formation aux Métiers", "description", structureService.findByName(sfipj.getName()));
				postesfipj1.setPosteSuperieur(posteService.findPosteByName(postesfipj.getName()));
				posteService.addSubPoste(postesfipj1);
				Postes postesfipj2 = new Postes("Bureau de la Promotion des Incubateurs d'Entreprises", "description", structureService.findByName(sfipj.getName()));
				postesfipj2.setPosteSuperieur(posteService.findPosteByName(postesfipj.getName()));
				posteService.addSubPoste(postesfipj2);

				Structures saps = new Structures(
						"Service des Activités Physiques et Sportives", 
						"SAPS",
						"blue", 
						"");
				saps.setStructureSuperieur(structureService.findByName(sdejs.getName()));
				structureService.addSubStructures(saps);
				Postes postesaps = new Postes("Chef Service (SAPS)", "description", structureService.findByName(saps.getName()));
				posteService.addPoste(postesaps);
				Postes postesaps1 = new Postes("Bureau des Infrastructures Sportives", "description", structureService.findByName(saps.getName()));
				postesaps1.setPosteSuperieur(posteService.findPosteByName(postesaps.getName()));
				posteService.addSubPoste(postesaps1);
				Postes postesaps2 = new Postes("Bureau des Activités Physiques et Sportives", "description", structureService.findByName(saps.getName()));
				postesaps2.setPosteSuperieur(posteService.findPosteByName(postesaps.getName()));
				posteService.addSubPoste(postesaps2);
				
				Structures daefb = new Structures(
						"Direction des Affaires Economiques, Finaciéres et du Budget", 
						"DAEFB",
						"blue", 
						"");
				daefb.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(daefb);
				Postes dirtdaefb = new Postes("Directeur (DAEFB)", "description", structureService.findByName(daefb.getName()));
				posteService.addPoste(dirtdaefb);
				
				Structures caec = new Structures(
						"Cellule des Affaires Economique et de la Coopération", 
						"CAEC",
						"blue", 
						"");
				caec.setStructureSuperieur(structureService.findByName(daefb.getName()));
				structureService.addSubStructures(caec);
				Postes postecaec = new Postes("Sous-Directeur (CAEC)", "description", structureService.findByName(caec.getName()));
				posteService.addPoste(postecaec);
				Postes postecaec1 = new Postes("Chargé d'Etude Assistant N°1 (CAEC)", "description", structureService.findByName(caec.getName()));
				postecaec1.setPosteSuperieur(posteService.findPosteByName(postecaec.getName()));
				posteService.addSubPoste(postecaec1);
				Postes postecaec2 = new Postes("Chargé d'Etude Assistant N°2 (CAEC)", "description", structureService.findByName(caec.getName()));
				postecaec2.setPosteSuperieur(posteService.findPosteByName(postecaec.getName()));
				posteService.addSubPoste(postecaec2);
				
				Structures sdmrf = new Structures(
						"Sous-Direction de la Mobilisation des Ressources Financiéres", 
						"SDMRF",
						"blue", 
						"");
				sdmrf.setStructureSuperieur(structureService.findByName(daefb.getName()));
				structureService.addSubStructures(sdmrf);
				Postes postesdmrf = new Postes("Sous-Directeur (SDMRF)", "description", structureService.findByName(sdmrf.getName()));
				posteService.addPoste(postesdmrf);
				
				Structures smrf = new Structures(
						"Service de la Mobilisation des Recettes Fiscales", 
						"SMRF",
						"blue", 
						"");
				smrf.setStructureSuperieur(structureService.findByName(sdmrf.getName()));
				structureService.addSubStructures(smrf);
				Postes postesmrf = new Postes("Chef Service (SMRF)", "description", structureService.findByName(smrf.getName()));
				posteService.addPoste(postesmrf);
				Postes postesmrf1 = new Postes("Bureau de Suivi des Recettes Fiscales", "description", structureService.findByName(smrf.getName()));
				postesmrf1.setPosteSuperieur(posteService.findPosteByName(postesmrf.getName()));
				posteService.addSubPoste(postesmrf1);
				Postes postesmrf2 = new Postes("Bureau de Suivi des Recettes des Taxes Communales", "description", structureService.findByName(smrf.getName()));
				postesmrf2.setPosteSuperieur(posteService.findPosteByName(postesmrf.getName()));
				posteService.addSubPoste(postesmrf2);
				Postes postesmrf3 = new Postes("Bureau du Fichier des Contribuables et des Contrôles", "description", structureService.findByName(smrf.getName()));
				postesmrf3.setPosteSuperieur(posteService.findPosteByName(postesmrf.getName()));
				posteService.addSubPoste(postesmrf3);
				Postes postesmrf4 = new Postes("Bureau de la Documentation Fiscale et d'Analyse de la Fiscalité", "description", structureService.findByName(smrf.getName()));
				postesmrf4.setPosteSuperieur(posteService.findPosteByName(postesmrf.getName()));
				posteService.addSubPoste(postesmrf4);
				
				Structures smrnf = new Structures(
						"Service de la Mobilisation des Recettes Non Fiscales", 
						"SMRNF",
						"blue", 
						"");
				smrnf.setStructureSuperieur(structureService.findByName(sdmrf.getName()));
				structureService.addSubStructures(smrnf);
				Postes postesmrnf = new Postes("Chef Service (SMRNF)", "description", structureService.findByName(smrnf.getName()));
				posteService.addPoste(postesmrnf);
				Postes postesmrnf1 = new Postes("Bureau du Fichier des Exloitants Suivi des Recettes Fiscales", "description", structureService.findByName(smrnf.getName()));
				postesmrnf1.setPosteSuperieur(posteService.findPosteByName(postesmrnf.getName()));
				posteService.addSubPoste(postesmrnf1);
				Postes postesmrnf2 = new Postes("Bureau de Suivi des Recettes Non Fiscales", "description", structureService.findByName(smrnf.getName()));
				postesmrnf2.setPosteSuperieur(posteService.findPosteByName(postesmrnf.getName()));
				posteService.addSubPoste(postesmrnf2);
				
				
				
				Structures sdbc = new Structures(
						"Sous-Direction du Budget et de la Comptabilité", 
						"SDBC",
						"blue", 
						"");
				sdbc.setStructureSuperieur(structureService.findByName(daefb.getName()));
				structureService.addSubStructures(sdbc);
				Postes postesdbc = new Postes("Sous-Directeur (SDBC)", "description", structureService.findByName(sdbc.getName()));
				posteService.addPoste(postesdbc);
				
				Structures sb = new Structures(
						"Service du Budget", 
						"SB",
						"blue", 
						"");
				sb.setStructureSuperieur(structureService.findByName(sdbc.getName()));
				structureService.addSubStructures(sb);
				Postes postesb = new Postes("Chef Service (SB)", "description", structureService.findByName(sb.getName()));
				posteService.addPoste(postesb);
				Postes postesb1 = new Postes("Bureau des Engagements", "description", structureService.findByName(sb.getName()));
				postesb1.setPosteSuperieur(posteService.findPosteByName(postesb.getName()));
				posteService.addSubPoste(postesb1);
				Postes postesb2 = new Postes("Bureau de l'Ordonnancement et de la Liquidation", "description", structureService.findByName(sb.getName()));
				postesb2.setPosteSuperieur(posteService.findPosteByName(postesb.getName()));
				posteService.addSubPoste(postesb2);
				Postes postesb3 = new Postes("Bureau de la Comptabilé Budgétaire et de l'Archivage", "description", structureService.findByName(sb.getName()));
				postesb3.setPosteSuperieur(posteService.findPosteByName(postesb.getName()));
				posteService.addSubPoste(postesb3);
				
				Structures scac = new Structures(
						"Service de la Comptabilité d'Analyse des Coûts", 
						"SCAC",
						"blue", 
						"");
				scac.setStructureSuperieur(structureService.findByName(sdbc.getName()));
				structureService.addSubStructures(scac);
				Postes postescac = new Postes("Chef Service (SCAC)", "description", structureService.findByName(scac.getName()));
				posteService.addPoste(postescac);
				
				Structures sdmgp = new Structures(
						"Sous-Direction des Moyens Généraux et du Patrimoine", 
						"SDMGP",
						"blue", 
						"");
				sdmgp.setStructureSuperieur(structureService.findByName(daefb.getName()));
				structureService.addSubStructures(sdmgp);
				Postes postesdmgp = new Postes("Sous-Directeur (SDMGP)", "description", structureService.findByName(sdmgp.getName()));
				posteService.addPoste(postesdmgp);
				Structures sag = new Structures(
						"Service des Affaires Generales", 
						"SAG",
						"blue", 
						"");
				sag.setStructureSuperieur(structureService.findByName(sdmgp.getName()));
				structureService.addSubStructures(sag);
				Postes postesag = new Postes("Chef Service (SAG)", "description", structureService.findByName(sag.getName()));
				posteService.addPoste(postesag);
				Postes postesag1 = new Postes("Bureau de l'Entretient st de la Maintenance d'Equipement Mobiliers et Immobiliers", "description", structureService.findByName(sag.getName()));
				postesag1.setPosteSuperieur(posteService.findPosteByName(postesag.getName()));
				posteService.addSubPoste(postesag1);
				Postes postesag2 = new Postes("Bureau des Assurences des Biens Meubles et Immeubles", "description", structureService.findByName(sag.getName()));
				postesag2.setPosteSuperieur(posteService.findPosteByName(postesag.getName()));
				posteService.addSubPoste(postesag2);			
				Structures saad = new Structures(
						"Service des Achats et Approvisionnements Divers", 
						"SAAD",
						"blue", 
						"");
				saad.setStructureSuperieur(structureService.findByName(sdmgp.getName()));
				structureService.addSubStructures(saad);
				Postes postesaad = new Postes("Chef Service (SAAD)", "description", structureService.findByName(saad.getName()));
				posteService.addPoste(postesaad);
				Postes postesaad1 = new Postes("Bureau de l'Evaluation des Besoins Généraux", "description", structureService.findByName(saad.getName()));
				postesaad1.setPosteSuperieur(posteService.findPosteByName(postesaad.getName()));
				posteService.addSubPoste(postesaad1);
				Postes postesaad2 = new Postes("Bureau des Approvisionnements", "description", structureService.findByName(saad.getName()));
				postesaad2.setPosteSuperieur(posteService.findPosteByName(postesaad.getName()));
				posteService.addSubPoste(postesaad2);
				
				Structures sdmp = new Structures(
						"Sous-Direction des Marchés Publics", 
						"SDMP",
						"blue", 
						"");
				sdmp.setStructureSuperieur(structureService.findByName(daefb.getName()));
				structureService.addSubStructures(sdmp);
				Postes postesdmp = new Postes("Sous-Directeur (SDMP)", "description", structureService.findByName(sdmp.getName()));
				posteService.addPoste(postesdmp);
				Structures smp = new Structures(
						"Service des Marchés Publics", 
						"SMP",
						"blue", 
						"");
				smp.setStructureSuperieur(structureService.findByName(sdmp.getName()));
				structureService.addSubStructures(smp);
				Postes postesmp = new Postes("Chef Service (SMP)", "description", structureService.findByName(smp.getName()));
				posteService.addPoste(postesmp);
				Postes postesmp1 = new Postes("Bureau de la Preparation des Appels d'Offres (SMP)", "description", structureService.findByName(smp.getName()));
				postesmp1.setPosteSuperieur(posteService.findPosteByName(postesmp.getName()));
				posteService.addSubPoste(postesmp1);
				Postes postesmp2 = new Postes("Bureau de Suivi de l'Execution des Marchés (SMP)", "description", structureService.findByName(smp.getName()));
				postesmp2.setPosteSuperieur(posteService.findPosteByName(postesmp.getName()));
				posteService.addSubPoste(postesmp2);
				
				Structures sfam = new Structures(
						"Service des Fournitures et Autres Marchés", 
						"SFAM",
						"blue", 
						"");
				sfam.setStructureSuperieur(structureService.findByName(sdmp.getName()));
				structureService.addSubStructures(sfam);
				Postes postesfam = new Postes("Chef Service (SFAM)", "description", structureService.findByName(sfam.getName()));
				posteService.addPoste(postesfam);
				Postes postesfam1 = new Postes("Bureau de la Preparation des Appels d'Offres (SFAM)", "description", structureService.findByName(sfam.getName()));
				postesfam1.setPosteSuperieur(posteService.findPosteByName(postesfam.getName()));
				posteService.addSubPoste(postesfam1);
				Postes postesfam2 = new Postes("Bureau de Suivi de l'Execution des Marchés (SFAM)", "description", structureService.findByName(sfam.getName()));
				postesfam2.setPosteSuperieur(posteService.findPosteByName(postesfam.getName()));
				posteService.addSubPoste(postesfam2);
				
				Structures dbou = new Structures(
						"Direction du Bon Ordre Urbain", 
						"DBOU",
						"blue", 
						"");
				dbou.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(dbou);
				Postes dirtdbou = new Postes("Directeur (DBOU)", "description", structureService.findByName(dbou.getName()));
				posteService.addPoste(dirtdbou);
				
				Structures sa = new Structures(
						"Service des Amendes", 
						"SA",
						"blue", 
						"");
				sa.setStructureSuperieur(structureService.findByName(dbou.getName()));
				structureService.addSubStructures(sa);
				Postes postesa = new Postes("Chef de Service (SA)", "description", structureService.findByName(sa.getName()));
				posteService.addPoste(postesa);
				Postes postesa1 = new Postes("Bureau de la Comptabilité des Emissions (SA)", "description", structureService.findByName(sa.getName()));
				postesa1.setPosteSuperieur(posteService.findPosteByName(postesa.getName()));
				posteService.addSubPoste(postesa1);
				Postes postesa2 = new Postes("Bureau de la Comptabilite des Recettes (SA)", "description", structureService.findByName(sa.getName()));
				postesa2.setPosteSuperieur(posteService.findPosteByName(postesa.getName()));
				posteService.addSubPoste(postesa2);
				
				Structures sdlcdu = new Structures(
						"Sous-Direction de la Lutte Contre les Desordres Urbains", 
						"SDLDU",
						"blue", 
						"");
				sdlcdu.setStructureSuperieur(structureService.findByName(dbou.getName()));
				structureService.addSubStructures(sdlcdu);
				Postes postesdlcdu = new Postes("Sous-Directeur (SDLDU)", "description", structureService.findByName(sdlcdu.getName()));
				posteService.addPoste(postesdlcdu);
				Structures blcduplp = new Structures(
						"Brigade de Luttre Contre les Desordres Urbains dans les Places et Lieux Publics", 
						"BLCDUPLP",
						"blue", 
						"");
				blcduplp.setStructureSuperieur(structureService.findByName(sdlcdu.getName()));
				structureService.addSubStructures(blcduplp);
				Postes posteblcduplp = new Postes("Chef de Brigade (BLCDUPLP)", "description", structureService.findByName(blcduplp.getName()));
				posteService.addPoste(posteblcduplp);			
				Structures blcddpc = new Structures(
						"Brigade de Luttre Contre les Désordres sur le Domaine Public et les Propriétés Communales", 
						"BLCDDPC",
						"blue", 
						"");
				blcddpc.setStructureSuperieur(structureService.findByName(sdlcdu.getName()));
				structureService.addSubStructures(blcddpc);
				Postes posteblcddpc = new Postes("Chef de Brigade (BLCDDPC)", "description", structureService.findByName(blcddpc.getName()));
				posteService.addPoste(posteblcddpc);
				
				Structures sdccovp = new Structures(
						"Sous-Direction du Controle de la Circulation et de l'Occupation de la Voie Publique", 
						"SDCCOVP",
						"blue", 
						"");
				sdccovp.setStructureSuperieur(structureService.findByName(dbou.getName()));
				structureService.addSubStructures(sdccovp);
				Postes postesdccovp = new Postes("Sous-Directeur (SDCCOVP)", "description", structureService.findByName(sdccovp.getName()));
				posteService.addPoste(postesdccovp);
				Structures bs = new Structures(
						"Brigade de Stationnement", 
						"BS",
						"blue", 
						"");
				bs.setStructureSuperieur(structureService.findByName(sdccovp.getName()));
				structureService.addSubStructures(bs);
				Postes postebs = new Postes("Chef de Brigade (BS)", "description", structureService.findByName(bs.getName()));
				posteService.addPoste(postebs);
				Structures sfm = new Structures(
						"Service de la Fourriére Municipale", 
						"SFM",
						"blue", 
						"");
				sfm.setStructureSuperieur(structureService.findByName(sdccovp.getName()));
				structureService.addSubStructures(sfm);
				Postes postesfm = new Postes("Chef Service (SFM)", "description", structureService.findByName(sfm.getName()));
				posteService.addPoste(postesfm);
				Postes postesfm1 = new Postes("Bureau des Interventions (SFM)", "description", structureService.findByName(sfm.getName()));
				postesfm1.setPosteSuperieur(posteService.findPosteByName(postesfm.getName()));
				posteService.addSubPoste(postesfm1);
				Postes postesfm2 = new Postes("Bureau du Materiel en Transit (SFM)", "description", structureService.findByName(sfm.getName()));
				postesfm2.setPosteSuperieur(posteService.findPosteByName(postesfm.getName()));
				posteService.addSubPoste(postesfm2);
				Postes postesfm3 = new Postes("Bureau du Materiel Déplacé (SFM)", "description", structureService.findByName(sfm.getName()));
				postesfm3.setPosteSuperieur(posteService.findPosteByName(postesfm.getName()));
				posteService.addSubPoste(postesfm3);

				Structures sdlcimtv = new Structures(
						"Sous-Direction de Lutte Contre l'Insalubrité Morale et les Troubles de Voisinage", 
						"DSLCIMTV",
						"blue", 
						"");
				sdlcimtv.setStructureSuperieur(structureService.findByName(dbou.getName()));
				structureService.addSubStructures(sdlcimtv);
				Postes postesdlcimtv = new Postes("Sous-Directeur (DSLCIMTV)", "description", structureService.findByName(sdlcimtv.getName()));
				posteService.addPoste(postesdlcimtv);
				Structures srequetes = new Structures(
						"Service des Requêtes", 
						"SRQ",
						"blue", 
						"");
				srequetes.setStructureSuperieur(structureService.findByName(sdlcimtv.getName()));
				structureService.addSubStructures(srequetes);
				Postes postesrequetes = new Postes("Chef Service (SRQ)", "description", structureService.findByName(srequetes.getName()));
				posteService.addPoste(postesrequetes);
				Structures srecherches = new Structures(
						"Service des Recherches", 
						"SRH",
						"blue", 
						"");
				srecherches.setStructureSuperieur(structureService.findByName(sdlcimtv.getName()));
				structureService.addSubStructures(srecherches);
				Postes postesrecherches = new Postes("Chef Service (SRH)", "description", structureService.findByName(srecherches.getName()));
				posteService.addPoste(postesrecherches);
				
				Structures sdlt = new Structures(
						"Sous-Direction de la Logistique et des Transmissions", 
						"SDLT",
						"blue", 
						"");
				sdlt.setStructureSuperieur(structureService.findByName(dbou.getName()));
				structureService.addSubStructures(sdlt);
				Postes postesdlt = new Postes("Sous-Directeur (SDLT)", "description", structureService.findByName(sdlt.getName()));
				posteService.addPoste(postesdlt);
				Structures sgm = new Structures(
						"Service de Gestions Du Materiel", 
						"SGM",
						"blue", 
						"");
				sgm.setStructureSuperieur(structureService.findByName(sdlt.getName()));
				structureService.addSubStructures(sgm);
				Postes postesgm = new Postes("Chef Service (SGM)", "description", structureService.findByName(sgm.getName()));
				posteService.addPoste(postesgm);
				Postes postesgm1 = new Postes("Bureau des Equipements (SGM)", "description", structureService.findByName(sgm.getName()));
				postesgm1.setPosteSuperieur(posteService.findPosteByName(postesgm.getName()));
				posteService.addSubPoste(postesgm1);
				Postes postesgm2 = new Postes("Bureau de Gestion du Parc Automobile (SGM)", "description", structureService.findByName(sgm.getName()));
				postesgm2.setPosteSuperieur(posteService.findPosteByName(postesgm.getName()));
				posteService.addSubPoste(postesgm2);
				Structures sta = new Structures(
						"Service des Transmissions", 
						"STA",
						"blue", 
						"");
				sta.setStructureSuperieur(structureService.findByName(sdlt.getName()));
				structureService.addSubStructures(sta);
				Postes postesta = new Postes("Chef Service (STA)", "description", structureService.findByName(sta.getName()));
				posteService.addPoste(postesta);
				Postes postesta1 = new Postes("Poste de Commandement Radio (STA)", "description", structureService.findByName(sta.getName()));
				postesta1.setPosteSuperieur(posteService.findPosteByName(postesta.getName()));
				posteService.addSubPoste(postesta1);
				Postes postesta2 = new Postes("Bureau de la Maintenance (STA)", "description", structureService.findByName(sta.getName()));
				postesta2.setPosteSuperieur(posteService.findPosteByName(postesta.getName()));
				posteService.addSubPoste(postesta2);
				
				Structures drh = new Structures(
						"Direction des Ressources Humaines", 
						"DRH",
						"blue", 
						"");
				drh.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(drh);
				Postes dirtdrh = new Postes("Directeur (DRH)", "description", structureService.findByName(drh.getName()));
				posteService.addPoste(dirtdrh);
				
				
				Structures sdarh = new Structures(
						"Sous-Direction de l'Administration des Ressources Humaines", 
						"SDARH",
						"blue", 
						"");
				sdarh.setStructureSuperieur(structureService.findByName(drh.getName()));
				structureService.addSubStructures(sdarh);
				Postes postesdarh = new Postes("Sous-Directeur (SDARH)", "description", structureService.findByName(sdarh.getName()));
				posteService.addPoste(postesdarh);
				Structures sap = new Structures(
						"Service de l'Administration du Personnel", 
						"SAP",
						"blue", 
						"");
				sap.setStructureSuperieur(structureService.findByName(sdarh.getName()));
				structureService.addSubStructures(sap);
				Postes postesap = new Postes("Chef Service (SAP)", "description", structureService.findByName(sap.getName()));
				posteService.addPoste(postesap);
				Postes postesap1 = new Postes("Bureau de Gestions de la Carriére des Personnels (SAP)", "description", structureService.findByName(sap.getName()));
				postesap1.setPosteSuperieur(posteService.findPosteByName(postesap.getName()));
				posteService.addSubPoste(postesap1);
				Postes postesap2 = new Postes("Bureau de l'Assurance Santé et du Suivi Médical (SAP)", "description", structureService.findByName(sap.getName()));
				postesap2.setPosteSuperieur(posteService.findPosteByName(postesap.getName()));
				posteService.addSubPoste(postesap2);
				Postes postesap3 = new Postes("Bureau de l'Action Sociale et Solidaire (SAP)", "description", structureService.findByName(sap.getName()));
				postesap3.setPosteSuperieur(posteService.findPosteByName(postesap.getName()));
				posteService.addSubPoste(postesap3);
				Structures ss = new Structures(
						"Service de la Solde", 
						"SS",
						"blue", 
						"");
				ss.setStructureSuperieur(structureService.findByName(sdarh.getName()));
				structureService.addSubStructures(ss);
				Postes postess = new Postes("Chef Service (SS)", "description", structureService.findByName(ss.getName()));
				posteService.addPoste(postess);
				Postes postess1 = new Postes("Bureau de Gestions de la Solde des Personnels (SS)", "description", structureService.findByName(ss.getName()));
				postess1.setPosteSuperieur(posteService.findPosteByName(postess.getName()));
				posteService.addSubPoste(postess1);
				Postes postess2 = new Postes("Bureau du Ficher de la Solde (SS)", "description", structureService.findByName(ss.getName()));
				postess2.setPosteSuperieur(posteService.findPosteByName(postess.getName()));
				posteService.addSubPoste(postess2);
				
				
				Structures sddrh = new Structures(
						"Sous-Direction du Developpement des Ressources Humaines", 
						"SDDRH",
						"blue", 
						"");
				sddrh.setStructureSuperieur(structureService.findByName(drh.getName()));
				structureService.addSubStructures(sddrh);
				Postes postesddrh = new Postes("Sous-Directeur (SDDRH)", "description", structureService.findByName(sddrh.getName()));
				posteService.addPoste(postesddrh);
				Structures sgpec = new Structures(
						"Service de Géstion Prévisionnelle des Effecifs et des Competances", 
						"SGPEC",
						"blue", 
						"");
				sgpec.setStructureSuperieur(structureService.findByName(sddrh.getName()));
				structureService.addSubStructures(sgpec);
				Postes postesgpec = new Postes("Chef Service (SGPEC)", "description", structureService.findByName(sgpec.getName()));
				posteService.addPoste(postesgpec);
				
				Structures sfp = new Structures(
						"Service de la Formation et du Perfectionnement", 
						"SFP",
						"blue", 
						"");
				sfp.setStructureSuperieur(structureService.findByName(sddrh.getName()));
				structureService.addSubStructures(sfp);
				Postes postesfp = new Postes("Chef Service (SFP)", "description", structureService.findByName(sfp.getName()));
				posteService.addPoste(postesfp);
				Postes postesfp1 = new Postes("Bureau de la Solde Formation (SFP)", "description", structureService.findByName(sfp.getName()));
				postesfp1.setPosteSuperieur(posteService.findPosteByName(postesfp.getName()));
				posteService.addSubPoste(postesfp1);
				Postes postesfp2 = new Postes("Bureau du Perfectionnement (SFP)", "description", structureService.findByName(sfp.getName()));
				postesfp2.setPosteSuperieur(posteService.findPosteByName(postesfp.getName()));
				posteService.addSubPoste(postesfp2);
				
				

				Structures gu = new Structures(
						"Guichet Unique", 
						"GU",
						"blue", 
						"");
				gu.setStructureSuperieur(structureService.findByName(maire.getName()));
				structureService.addSubStructures(gu);
				Postes dirtgu = new Postes("Coordonateur (GU)", "description", structureService.findByName(gu.getName()));
				posteService.addPoste(dirtgu);
				Postes poste1 = new Postes("Instructeur N°1 (GU)", "description", structureService.findByName(gu.getName()));
				poste1.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste1);
				Postes poste2 = new Postes("Instructeur N°2 (GU)", "description", structureService.findByName(gu.getName()));
				poste2.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste2);
				Postes poste3 = new Postes("Instructeur N°3 (GU)", "description", structureService.findByName(gu.getName()));
				poste3.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste3);
				Postes poste4 = new Postes("Instructeur N°4 (GU)", "description", structureService.findByName(gu.getName()));
				poste4.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste4);
				Postes poste5 = new Postes("Instructeur N°5 (GU)", "description", structureService.findByName(gu.getName()));
				poste5.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste5);
				Postes poste6 = new Postes("Instructeur N°6 (GU)", "description", structureService.findByName(gu.getName()));
				poste6.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste6);
				Postes poste11 = new Postes("Repesentant des Administrations Competents N°1 (GU)", "description", structureService.findByName(gu.getName()));
				poste11.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste11);
				Postes poste22 = new Postes("Repesentant des Administrations Competents N°2 (GU)", "description", structureService.findByName(gu.getName()));
				poste22.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste22);
				Postes poste33 = new Postes("Repesentant des Administrations Competents N°3 (GU)", "description", structureService.findByName(gu.getName()));
				poste33.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste33);
				Postes poste333 = new Postes("Repesentant du Receveur Municipal (GU)", "description", structureService.findByName(gu.getName()));
				poste333.setPosteSuperieur(posteService.findPosteByName(dirtgu.getName()));
				posteService.addSubPoste(poste333);
				
				Structures pd = new Structures(
						"Plateforme de Dématerialisation", 
						"PD",
						"blue", 
						"");
				pd.setStructureSuperieur(structureService.findByName(gu.getName()));
				structureService.addSubStructures(pd);
				Postes dirtpd = new Postes("Administrateur (PD)", "description", structureService.findByName(pd.getName()));
				posteService.addPoste(dirtpd);
				
				
				
				
				
				Droits ap12 = new Droits("CPROF", "Creer un profile utilisateur", "post","/profile/user");
				Droits ap22 = new Droits("LPROF", "Lister un profile utilisateur", "get","/profile/all");
				Droits ap29 = new Droits("LUAPROF", "Lister les users ldap a affecte a un profile utilisateur", "get","/profile/list_User");
				Droits ap28 = new Droits("STPROF", "Structure du profile utilisateur", "get","/profile/current_S1231tructurebyd");
				Droits ap23 = new Droits("FPROF", "Rechercher un profile utilisateur", "get","/profile/search-by-name");
				Droits ap27 = new Droits("FIDPROF", "Information sur un profile utilisateur", "get","/profile/find_by_id");
				Droits ap24 = new Droits("USPROF", "Modifier le status d'un profile utilisateur", "post","/profile/set_StaTus");
				Droits ap25 = new Droits("UNPROF", "Modifier le nom d'un profile utilisateur", "post","/profile/set_namekjk2132123");
				Droits ap26 = new Droits("UUPROF", "Modifier l'utilisateur ldap du profile", "post","/profile/set_userkjk2132123");
				Droits ap30 = new Droits("CUSTRUCTURE", "Creer et modifier une structure", "post","/structures/update");
				Droits ap31 = new Droits("RSTRUCTURE", "Lister les structures", "get","/structures/all");
				Droits ap32 = new Droits("DSTRUCTURE", "Supprimer une structure", "delete","/structures");
				Droits ap33 = new Droits("CUPOSTE", "Creer et modifier un poste", "post","/postes/update");
				Droits ap34 = new Droits("RPOSTE", "Lister les postes", "get","/postes/all");
				Droits ap35 = new Droits("DPOSTE", "Supprimer une poste", "delete","/postes");
				Droits ap36 = new Droits("ADMIN", "Administration", "admin","/**");

				droitsRepo.save(ap36);
				droitsRepo.save(ap12);
				droitsRepo.save(ap22);
				droitsRepo.save(ap29);
				droitsRepo.save(ap28);
				droitsRepo.save(ap23);
				droitsRepo.save(ap24);
				droitsRepo.save(ap27);
				droitsRepo.save(ap25);
				droitsRepo.save(ap26);
				droitsRepo.save(ap30);
				droitsRepo.save(ap31);
				droitsRepo.save(ap32);
				droitsRepo.save(ap33);
				droitsRepo.save(ap34);
				droitsRepo.save(ap35);
				
				
				
				GroupUser groupUser = new GroupUser("GroupUser name", "GroupUser sigle", true);
				List<Long> ids = List.of(
						new Long(1), 
						new Long(2), 
						new Long(3), 
						new Long(4),
						new Long(5),
						new Long(6),
						new Long(7),
						new Long(8),
						new Long(9),
						new Long(10),
						new Long(11),
						new Long(12),
						new Long(13),
						new Long(14));
				groupUserService.saveGroupUser(groupUser, ids);	
				
				GroupUser groupUser1 = new GroupUser("GroupUser 1", "GroupUser sigle 1", true);
				groupUserService.saveGroupUser(groupUser1, ids);		
				
				GroupUser groupUser2 = new GroupUser("GroupUser 2", "GroupUser sigle 2", true);
				groupUserService.saveGroupUser(groupUser2, ids);		
				
				GroupUser groupUser3 = new GroupUser("GroupUser 3", "GroupUser sigle 3", true);
				groupUserService.saveGroupUser(groupUser3, ids);		
				
				GroupUser groupUser4 = new GroupUser("GroupUser 4", "GroupUser sigle 4", true);
				groupUserService.saveGroupUser(groupUser4, ids);		
				
				GroupUser groupUser5 = new GroupUser("GroupUser 5", "GroupUser sigle 5", true);
				groupUserService.saveGroupUser(groupUser5, ids);		
				
				GroupUser groupUser6 = new GroupUser("GroupUser 6", "GroupUser sigle 6", true);
				groupUserService.saveGroupUser(groupUser6, ids);		
				
				GroupUser groupUser7 = new GroupUser("GroupUser 7", "GroupUser sigle 7", true);
				groupUserService.saveGroupUser(groupUser7, ids);		
				
				GroupUser groupUser8 = new GroupUser("GroupUser 8", "GroupUser sigle 8", true);
				groupUserService.saveGroupUser(groupUser8, ids);		
				
				GroupUser groupUser9 = new GroupUser("GroupUser 9", "GroupUser sigle 9", true);
				groupUserService.saveGroupUser(groupUser9, ids);		
				
				GroupUser groupUser10 = new GroupUser("GroupUser 10", "GroupUser sigle 10", true);
				groupUserService.saveGroupUser(groupUser10, ids);			
				
				Profiles profiles = new Profiles("Maire","Bob Hamilton", TypeUser.INTERN_ACTOR, true);
				profilesService.add(profiles, structureService.findByName(maire.getName()).getIdstructure(),groupUserService.findGroupByName(groupUser.getName()).getIdgroupes());
				
				Profiles profile1 = new Profiles("Secretaire Generale","Joe Smeth", TypeUser.INTERN_ACTOR, true);
				profilesService.add(profile1, structureService.findByName(maire.getName()).getIdstructure(),groupUserService.findGroupByName(groupUser.getName()).getIdgroupes());
				
				Profiles profile2 = new Profiles("Secretaire","Mouse Jerry", TypeUser.INTERN_ACTOR, true);
				profilesService.add(profile2, structureService.findByName(maire.getName()).getIdstructure(),groupUserService.findGroupByName(groupUser.getName()).getIdgroupes());
				
				Profiles profile3 = new Profiles("Adjoint N°1","slash guy", TypeUser.INTERN_ACTOR, true);
				profilesService.add(profile3, structureService.findByName(maire.getName()).getIdstructure(),groupUserService.findGroupByName(groupUser.getName()).getIdgroupes());

				Profiles profile4 = new Profiles("Adjoint N°2","Ben Alex", TypeUser.INTERN_ACTOR, true);
				profilesService.add(profile4, structureService.findByName(maire.getName()).getIdstructure(),groupUserService.findGroupByName(groupUser.getName()).getIdgroupes());
				
			}
			
			System.err.println("Port: "+port);
		};
	}

}
