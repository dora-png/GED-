package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "typeliasses")
public class TypeLiasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtypeliasse")
    private Long idtypeliasse;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "sigle", nullable = false, unique = true)
    private String sigle;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "typeliasse", fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "idliasse",  "name", "sigle" })
	private Set<Liasses> liasses;

	/**
	 * 
	 */
	public TypeLiasses() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idtypeliasse
	 */
	public TypeLiasses(Long idtypeliasse) {
		super();
		this.idtypeliasse = idtypeliasse;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param liasses
	 */
	public TypeLiasses(String name, String sigle, String description, Set<Liasses> liasses) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.liasses = liasses;
	}

	/**
	 * @return the idtypeliasse
	 */
	public Long getIdtypeliasse() {
		return idtypeliasse;
	}

	/**
	 * @param idtypeliasse the idtypeliasse to set
	 */
	public void setIdtypeliasse(Long idtypeliasse) {
		this.idtypeliasse = idtypeliasse;
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
	 * @return the liasses
	 */
	public Set<Liasses> getLiasses() {
		return liasses;
	}

	/**
	 * @param liasses the liasses to set
	 */
	public void setLiasses(Set<Liasses> liasses) {
		this.liasses = liasses;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TypeLiasses))
			return false;
		TypeLiasses other = (TypeLiasses) obj;
		return Objects.equals(description, other.description) && Objects.equals(idtypeliasse, other.idtypeliasse)
				&& Objects.equals(liasses, other.liasses) && Objects.equals(name, other.name)
				&& Objects.equals(sigle, other.sigle);
	}

	@Override
	public String toString() {
		return "TypeLiasses [idtypeliasse=" + idtypeliasse + ", name=" + name + ", sigle=" + sigle + ", description="
				+ description + "]";
	}

}
