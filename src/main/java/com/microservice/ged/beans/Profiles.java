package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "profiles")
public class Profiles {
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idprofile")
	 private Long idprofiles;
	 
	 @Column(name = "name")
	 private String name;
	 
	 @Column(name = "currentuser", updatable = false, nullable = false)
	 private String currentUser;
	 
	 @Column(name = "typeprofil", updatable = false, nullable = false)
	 private TypeUser typeprofil;
	 	 	 
	 @Column(name = "status")
	 private boolean status;
	 
	 @ManyToOne
	 @JoinColumn(nullable = false)
	 @JsonIncludeProperties(value = {"idstructure", "name", "sigle","active", "color" })
	 private Structures structure;
	 
	 @Column(name = "datecreation", insertable = true, updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreationTimestamp
	 private Date dateCreation;

	public Profiles() {
		super();
	}

	public Profiles(String name, String currentUser, TypeUser typeprofil, boolean status) {
		super();
		this.name = name;
		this.currentUser = currentUser;
		this.typeprofil = typeprofil;
		this.status = status;
	}

	public Long getIdProfiles() {
		return idprofiles;
	}

	public void setIdProfiles(Long idProfiles) {
		this.idprofiles = idProfiles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public TypeUser getTypeprofil() {
		return typeprofil;
	}

	public void setTypeprofil(TypeUser typeprofil) {
		this.typeprofil = typeprofil;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Structures getStructure() {
		return structure;
	}

	public void setStructure(Structures structure) {
		this.structure = structure;
	}
	
}
