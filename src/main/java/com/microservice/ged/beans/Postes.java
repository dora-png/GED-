/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
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
    
    @Column(name = "name",unique = true, nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "niveau")
    private int niveau;
        
    @ManyToOne
	@JoinColumn(nullable = false)
	@JsonIncludeProperties(value = {"idstructure", "name", "sigle" })
	private Structures structure;

	@OneToMany(mappedBy = "posteSuperieur", fetch = FetchType.LAZY)
	//@JsonIncludeProperties(value = { "name" })
	private Set<Postes> posteSubalterne = new HashSet();

	@ManyToOne
	@JsonIncludeProperties(value = { "name" })
	private Postes posteSuperieur;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "idgroupes", "name", "roleslistes"})
	private Set<GroupUser> groupslistes;

	/**
	 * 
	 */
	public Postes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    /**
	 * @param idposte
	 * @param name
	 * @param description
	 * @param niveau
	 * @param structure
	 * @param posteSubalterne
	 * @param posteSuperieur
	 * @param posteemploye
	 * @param roles
	 */
	public Postes(Long idposte, String name, String description, int niveau, Structures structure,
			Set<Postes> posteSubalterne, Postes posteSuperieur, Set<GroupUser> groupslistes) {
		super();
		this.idposte = idposte;
		this.name = name;
		this.description = description;
		this.niveau = niveau;
		this.structure = structure;
		this.posteSubalterne = posteSubalterne;
		this.posteSuperieur = posteSuperieur;
		this.groupslistes = groupslistes;
	}


	/**
	 * @param idposte
	 */
	public Postes(Long idposte) {
		super();
		this.idposte = idposte;
	}

	/**
	 * @param name
	 * @param description
	 * @param niveau
	 * @param structure
	 * @param posteSuperieur
	 */
	public Postes(String name, String description, Structures structure) {
		super();
		this.name = name;
		this.description = description;
		this.structure = structure;
	}

	/**
	 * @return the idposte
	 */
	public Long getIdposte() {
		return idposte;
	}

	/**
	 * @param idposte the idposte to set
	 */
	public void setIdposte(Long idposte) {
		this.idposte = idposte;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the niveau
	 */
	public Integer getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the structure
	 */
	public Structures getStructure() {
		return structure;
	}

	/**
	 * @param structure the structure to set
	 */
	public void setStructure(Structures structure) {
		this.structure = structure;
	}

	/**
	 * @return the posteSubalterne
	 */
	public Set<Postes> getPosteSubalterne() {
		return posteSubalterne;
	}

	/**
	 * @param posteSubalterne the posteSubalterne to set
	 */
	public void setPosteSubalterne(Set<Postes> posteSubalterne) {
		this.posteSubalterne = posteSubalterne;
	}

	/**
	 * @return the posteSuperieur
	 */
	public Postes getPosteSuperieur() {
		return posteSuperieur;
	}

	/**
	 * @param posteSuperieur the posteSuperieur to set
	 */
	public void setPosteSuperieur(Postes posteSuperieur) {
		this.posteSuperieur = posteSuperieur;
	}

	/**
	 * @return the groupslistes
	 */
	public Set<GroupUser> getGroupslistes() {
		return groupslistes;
	}

	/**
	 * @param groupslistes the groupslistes to set
	 */
	public void setGroupslistes(Set<GroupUser> groupslistes) {
		this.groupslistes = groupslistes;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}



	
}
