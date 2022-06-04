/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
	private List<Postes> posteSubalterne = new ArrayList<>();

	@ManyToOne
	@JsonIncludeProperties(value = { "name" })
	private Postes posteSuperieur;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "rolles")
	
	private List<Roles> roles = new ArrayList<Roles>();

	/**
	 * @return the roles
	 */
	public List<Roles> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

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
			List<Postes> posteSubalterne, Postes posteSuperieur, List<Roles> roles) {
		super();
		this.idposte = idposte;
		this.name = name;
		this.description = description;
		this.niveau = niveau;
		this.structure = structure;
		this.posteSubalterne = posteSubalterne;
		this.posteSuperieur = posteSuperieur;
		this.roles = roles;
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
	public List<Postes> getPosteSubalterne() {
		return posteSubalterne;
	}

	/**
	 * @param posteSubalterne the posteSubalterne to set
	 */
	public void setPosteSubalterne(List<Postes> posteSubalterne) {
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

	
	@Override
	public int hashCode() {
		return Objects.hash(description, idposte, name, niveau, posteSubalterne, posteSuperieur, structure);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Postes))
			return false;
		Postes other = (Postes) obj;
		return Objects.equals(description, other.description) && Objects.equals(idposte, other.idposte)
				&& Objects.equals(name, other.name) && Objects.equals(niveau, other.niveau)
				&& Objects.equals(posteSubalterne, other.posteSubalterne)
				&& Objects.equals(posteSuperieur, other.posteSuperieur);
	}

	
}
