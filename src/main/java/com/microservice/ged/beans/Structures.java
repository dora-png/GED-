/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

/**
 *
 * @author Blingard
 */
@Entity
@Table(name = "structures")
public class Structures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstructure")
    private Long idstructure;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "sigle", nullable = false, unique = true)
    private String sigle;
    
    @Column(name = "color"/*, unique = true*/, nullable = false)
    private String color;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "active", nullable = false)
    private boolean active = true;
        
    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
	@JsonIncludeProperties(value = { "idposte", "name", "active", "posteSubalterne", "posteSuperieur"})
	private Set<Postes> postes;
    
    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
   	@JsonIncludeProperties(value = { "idprofiles", "name", "currentUser", "typeprofil", "status", "dateCreation"})
   	private Set<Profiles> profiles;
    
	@ManyToOne
	@JsonIncludeProperties(value = { "idstructure", "name", "sigle" })
	private Structures structureSuperieur;

	@OneToMany
	//@JsonIncludeProperties(value = { "idstructure", "name", "sigle" })
	private Set<Structures> sousStructure = new HashSet();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;
	

	/**
	 * 
	 */
	public Structures() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Structures(String name, String sigle, String color, String description) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.color = color;
		this.description = description;
	}


	public Long getIdstructure() {
		return idstructure;
	}


	public void setIdstructure(Long idstructure) {
		this.idstructure = idstructure;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSigle() {
		return sigle;
	}


	public void setSigle(String sigle) {
		this.sigle = sigle;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Set<Postes> getPostes() {
		return postes;
	}


	public void setPostes(Set<Postes> postes) {
		this.postes = postes;
	}


	public Structures getStructureSuperieur() {
		return structureSuperieur;
	}


	public void setStructureSuperieur(Structures structureSuperieur) {
		this.structureSuperieur = structureSuperieur;
	}


	public Set<Structures> getSousStructure() {
		return sousStructure;
	}


	public void setSousStructure(Set<Structures> sousStructure) {
		this.sousStructure = sousStructure;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public Set<Profiles> getProfiles() {
		return profiles;
	}


	public void setProfiles(Set<Profiles> profiles) {
		this.profiles = profiles;
	}
	
	
	
}
