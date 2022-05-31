/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Blingard
 */
@Entity
@Table(name = "roles")
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idroles", nullable = false)
    private Long idroles;
    
    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String name;

    @Column(name = "create",columnDefinition = "boolean default false")
    private boolean create;

    @Column(name = "read",columnDefinition = "boolean default false")
    private boolean read;

    @Column(name = "update",columnDefinition = "boolean default false")
    private boolean update;

    @Column(name = "delete",columnDefinition = "boolean default false")
    private boolean delete;

	/**
	 * 
	 */
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param create
	 * @param read
	 * @param update
	 * @param delete
	 */
	public Roles(String name, boolean create, boolean read, boolean update, boolean delete) {
		super();
		this.name = name;
		this.create = create;
		this.read = read;
		this.update = update;
		this.delete = delete;
	}

	/**
	 * @return the idroles
	 */
	public Long getIdroles() {
		return idroles;
	}

	/**
	 * @param idroles the idroles to set
	 */
	public void setIdroles(Long idroles) {
		this.idroles = idroles;
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
	 * @return the create
	 */
	public boolean isCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public void setCreate(boolean create) {
		this.create = create;
	}

	/**
	 * @return the read
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(boolean read) {
		this.read = read;
	}

	/**
	 * @return the update
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * @return the delete
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public int hashCode() {
		return Objects.hash(create, delete, idroles, name, read, update);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Roles))
			return false;
		Roles other = (Roles) obj;
		return create == other.create && delete == other.delete && Objects.equals(idroles, other.idroles)
				&& Objects.equals(name, other.name) && read == other.read && update == other.update;
	}

	@Override
	public String toString() {
		return "Roles [idroles=" + idroles + ", name=" + name + ", create=" + create + ", read=" + read + ", update="
				+ update + ", delete=" + delete + "]";
	}


}
