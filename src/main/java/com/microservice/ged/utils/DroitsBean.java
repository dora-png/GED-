package com.microservice.ged.utils;

import java.util.Date;


public class DroitsBean {
    private Long iddroit;
    private String name;
    private String abbr;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;
	private Date dateCreation;
	private boolean typeDroit;
	
	public DroitsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DroitsBean(Long iddroit, String name, String abbr, boolean create, boolean read, boolean update,
			boolean delete, Date dateCreation, boolean typeDroit) {
		super();
		this.iddroit = iddroit;
		this.name = name;
		this.abbr = abbr;
		this.create = create;
		this.read = read;
		this.update = update;
		this.delete = delete;
		this.dateCreation = dateCreation;
		this.typeDroit = typeDroit;
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

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isTypeDroit() {
		return typeDroit;
	}

	public void setTypeDroit(boolean typeDroit) {
		this.typeDroit = typeDroit;
	}
	
	
	
}
