/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

/**
 *
 * @author Blingard
 */
@Entity
@Table(name = "postes")
public class Postes implements Serializable {


	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idposte", nullable = false)
    private Long idposte;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "active", nullable = false)
    private boolean active = true;
        
    @ManyToOne
	@JoinColumn(nullable = false)
	@JsonIncludeProperties(value = {"idstructure", "name", "sigle","active" })
	private Structures structure;

	@OneToMany(mappedBy = "posteSuperieur", fetch = FetchType.LAZY)
	//@JsonIncludeProperties(value = { "name" })
	private Set<Postes> posteSubalterne = new HashSet();

	@ManyToOne
	@JsonIncludeProperties(value = { "idposte", "name","active" })
	private Postes posteSuperieur;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;


	public Postes() {
		super();
	}


	public Postes(String name, String description, boolean active, Structures structure) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
		this.structure = structure;
	}


	public Long getIdposte() {
		return idposte;
	}


	public void setIdposte(Long idposte) {
		this.idposte = idposte;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public Structures getStructure() {
		return structure;
	}


	public void setStructure(Structures structure) {
		this.structure = structure;
	}


	public Set<Postes> getPosteSubalterne() {
		return posteSubalterne;
	}


	public void setPosteSubalterne(Set<Postes> posteSubalterne) {
		this.posteSubalterne = posteSubalterne;
	}


	public Postes getPosteSuperieur() {
		return posteSuperieur;
	}


	public void setPosteSuperieur(Postes posteSuperieur) {
		this.posteSuperieur = posteSuperieur;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
}
