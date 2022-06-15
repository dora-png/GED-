package com.microservice.ged.beans;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "docs")
public class Docs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddoc")
    private Long iddoc;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "extension", nullable = false)
    private String extension;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "path", nullable = false)
    private String path;
    
    @Column(name = "cantset", nullable = false)
    private String cantset;

    @ManyToOne
   	@JoinColumn(nullable = false)
   	@JsonIncludeProperties(value = {"idtypedoc", "name", "sigle" })
   	private TypeDocs typedoc;
    
    @ManyToOne
   	@JoinColumn(nullable = false)
   	@JsonIncludeProperties(value = {"idliasse", "name", "sigle" })
   	private Liasses liasse;

	/**
	 * 
	 */
	public Docs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param iddoc
	 */
	public Docs(Long iddoc) {
		super();
		this.iddoc = iddoc;
	}

	/**
	 * @param name
	 * @param extension
	 * @param description
	 * @param path
	 * @param cantset
	 * @param typedoc
	 * @param liasse
	 */
	public Docs(String name, String extension, String description, String path, String cantset, TypeDocs typedoc,
			Liasses liasse) {
		super();
		this.name = name;
		this.extension = extension;
		this.description = description;
		this.path = path;
		this.cantset = cantset;
		this.typedoc = typedoc;
		this.liasse = liasse;
	}

	/**
	 * @return the iddoc
	 */
	public Long getIddoc() {
		return iddoc;
	}

	/**
	 * @param iddoc the iddoc to set
	 */
	public void setIddoc(Long iddoc) {
		this.iddoc = iddoc;
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
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the cantset
	 */
	public String getCantset() {
		return cantset;
	}

	/**
	 * @param cantset the cantset to set
	 */
	public void setCantset(String cantset) {
		this.cantset = cantset;
	}

	/**
	 * @return the typedoc
	 */
	public TypeDocs getTypedoc() {
		return typedoc;
	}

	/**
	 * @param typedoc the typedoc to set
	 */
	public void setTypedoc(TypeDocs typedoc) {
		this.typedoc = typedoc;
	}

	/**
	 * @return the liasse
	 */
	public Liasses getLiasse() {
		return liasse;
	}

	/**
	 * @param liasse the liasse to set
	 */
	public void setLiasse(Liasses liasse) {
		this.liasse = liasse;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Docs))
			return false;
		Docs other = (Docs) obj;
		return Objects.equals(cantset, other.cantset) && Objects.equals(description, other.description)
				&& Objects.equals(extension, other.extension) && Objects.equals(iddoc, other.iddoc)
				&& Objects.equals(liasse, other.liasse) && Objects.equals(name, other.name)
				&& Objects.equals(path, other.path);
	}

	@Override
	public String toString() {
		return "Docs [iddoc=" + iddoc + ", name=" + name + ", extension=" + extension + ", description=" + description
				+ ", path=" + path + ", cantset=" + cantset + "]";
	}
    
    
}
