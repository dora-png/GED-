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
@Table(name = "typedocs")
public class TypeDocs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtypedoc")
    private Long idtypedoc;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "sigle", nullable = false, unique = true)
    private String sigle;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "typedoc", fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "iddoc", "name", "extension" })
	private Set<Docs> docs;

	/**
	 * 
	 */
	public TypeDocs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idtypedoc
	 */
	public TypeDocs(Long idtypedoc) {
		super();
		this.idtypedoc = idtypedoc;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param docs
	 */
	public TypeDocs(String name, String sigle, String description, Set<Docs> docs) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.docs = docs;
	}

	/**
	 * @return the idtypedoc
	 */
	public Long getIdtypedoc() {
		return idtypedoc;
	}

	/**
	 * @param idtypedoc the idtypedoc to set
	 */
	public void setIdtypedoc(Long idtypedoc) {
		this.idtypedoc = idtypedoc;
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
	 * @return the docs
	 */
	public Set<Docs> getDocs() {
		return docs;
	}

	/**
	 * @param docs the docs to set
	 */
	public void setDocs(Set<Docs> docs) {
		this.docs = docs;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TypeDocs))
			return false;
		TypeDocs other = (TypeDocs) obj;
		return Objects.equals(description, other.description) && Objects.equals(docs, other.docs)
				&& Objects.equals(idtypedoc, other.idtypedoc) && Objects.equals(name, other.name)
				&& Objects.equals(sigle, other.sigle);
	}

	@Override
	public String toString() {
		return "TypeDocs [idtypedoc=" + idtypedoc + ", name=" + name + ", sigle=" + sigle + ", description="
				+ description + "]";
	}
    
    

}
