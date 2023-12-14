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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

/**
 *
 * @author Blingard
 */
@Entity
@Table(name = "droits")
public class Droits implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddroit", nullable = false)
    private Long iddroit;
    
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    @Column(name = "description", unique = true, nullable = false, updatable = false)
    private String description;
    
    @Column(name = "method", nullable = false, updatable = false)
    private String method;
    
    @Column(name = "uri", unique = true, nullable = false, updatable = false)
    private String uri;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;

	public Droits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Droits(String name, String description, String method, String uri) {
		super();
		this.name = name;
		this.description = description;
		this.method = method;
		this.uri = uri;
	}

	public Long getIddroit() {
		return iddroit;
	}

	public void setIddroit(Long iddroit) {
		this.iddroit = iddroit;
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "Droits [iddroit=" + iddroit + ", name=" + name + ", description=" + description + ", method=" + method
				+ ", uri=" + uri + ", dateCreation=" + dateCreation + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCreation, description, iddroit, method, name, uri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Droits other = (Droits) obj;
		return Objects.equals(dateCreation, other.dateCreation) && Objects.equals(description, other.description)
				&& Objects.equals(iddroit, other.iddroit) && Objects.equals(method, other.method)
				&& Objects.equals(name, other.name) && Objects.equals(uri, other.uri);
	}
    
    


}
