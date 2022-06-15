/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.ged.beans;

import java.io.Serializable;
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
    
    @Column(name = "description", nullable = false)
    private String description;
        
    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
	@JsonIncludeProperties(value = { "idposte", "name", "niveau" })
	private Set<Postes> postes;
    
	@ManyToOne
	@JsonIncludeProperties(value = { "idstructure", "name", "sigle" })
	private Structures structureSuperieur;

	@OneToMany
	//@JsonIncludeProperties(value = { "idstructure", "name", "sigle" })
	private Set<Structures> sousStructure = new HashSet();

	/**
	 * 
	 */
	public Structures() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idstructure
	 */
	public Structures(Long idstructure) {
		super();
		this.idstructure = idstructure;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 */
	public Structures(String name, String sigle, String description) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
	}

	/**
	 * @return the idstructure
	 */
	public Long getIdstructure() {
		return idstructure;
	}

	/**
	 * @param idstructure the idstructure to set
	 */
	public void setIdstructure(Long idstructure) {
		this.idstructure = idstructure;
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
	 * @return the sigle
	 */
	public String getSigle() {
		return sigle;
	}

	/**
	 * @param sigle the sigle to set
	 */
	public void setSigle(String sigle) {
		this.sigle = sigle;
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
	 * @return the postes
	 */
	public Set<Postes> getPostes() {
		return postes;
	}

	/**
	 * @param postes the postes to set
	 */
	public void setPostes(Set<Postes> postes) {
		this.postes = postes;
	}

	/**
	 * @return the structureSuperieur
	 */
	public Structures getStructureSuperieur() {
		return structureSuperieur;
	}

	/**
	 * @param structureSuperieur the structureSuperieur to set
	 */
	public void setStructureSuperieur(Structures structureSuperieur) {
		this.structureSuperieur = structureSuperieur;
	}

	/**
	 * @return the sousStructure
	 */
	public Set<Structures> getSousStructure() {
		return sousStructure;
	}

	/**
	 * @param sousStructure the sousStructure to set
	 */
	public void setSousStructure(Set<Structures> sousStructure) {
		this.sousStructure = sousStructure;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Structures))
			return false;
		Structures other = (Structures) obj;
		return Objects.equals(description, other.description) && Objects.equals(idstructure, other.idstructure)
				&& Objects.equals(name, other.name) && Objects.equals(postes, other.postes)
				&& Objects.equals(sigle, other.sigle) && Objects.equals(sousStructure, other.sousStructure)
				&& Objects.equals(structureSuperieur, other.structureSuperieur);
	}

	@Override
	public String toString() {
		return "Structures [idstructure=" + idstructure + ", name=" + name + ", sigle=" + sigle + ", description="
				+ description + "]";
	}

	
}
