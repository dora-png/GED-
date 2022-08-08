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

    public Long getIddroit() {
		return iddroit;
	}


	public void setIddroit(Long iddroit) {
		this.iddroit = iddroit;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddroit", nullable = false)
    private Long iddroit;
    
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    
    @Column(name = "abbr", unique = true, nullable = false, updatable = false)
    private String abbr;

    @Column(name = "create",columnDefinition = "boolean default false")
    private boolean create;

    @Column(name = "read",columnDefinition = "boolean default false")
    private boolean read;

    @Column(name = "update",columnDefinition = "boolean default false")
    private boolean update;

    @Column(name = "delete",columnDefinition = "boolean default false")
    private boolean delete;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;


	public Droits() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Droits(String name, String abbr, boolean create, boolean read, boolean update, boolean delete) {
		super();
		this.name = name;
		this.abbr = abbr;
		this.create = create;
		this.read = read;
		this.update = update;
		this.delete = delete;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbbr() {
		return abbr;
	}

	public boolean isCreate() {
		return create;
	}


	public void setCreate(boolean create) {
		this.create = create;
	}


	public boolean isRead() {
		return read;
	}


	public void setRead(boolean read) {
		this.read = read;
	}


	public boolean isUpdate() {
		return update;
	}


	public void setUpdate(boolean update) {
		this.update = update;
	}


	public boolean isDelete() {
		return delete;
	}


	public void setDelete(boolean delete) {
		this.delete = delete;
	}


	public Long getIdDroit() {
		return iddroit;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	@Override
	public int hashCode() {
		return Objects.hash(abbr, create, dateCreation, delete, iddroit, name, read, update);
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
		return Objects.equals(abbr, other.abbr) && create == other.create
				&& Objects.equals(dateCreation, other.dateCreation) && delete == other.delete
				&& Objects.equals(iddroit, other.iddroit) && Objects.equals(name, other.name) && read == other.read
				&& update == other.update;
	}


	@Override
	public String toString() {
		return "Droits [idDroit=" + iddroit + ", name=" + name + ", abbr=" + abbr + ", create=" + create + ", read="
				+ read + ", update=" + update + ", delete=" + delete + ", dateCreation=" + dateCreation + "]";
	}
    
  


}
