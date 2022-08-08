package com.microservice.ged.utils;

public class ProfileStructureBean {
	private Long idStructure;
	private String name;
	private String sigle;
	
	public ProfileStructureBean(Long idStructure, String name, String sigle) {
		super();
		this.idStructure = idStructure;
		this.name = name;
		this.sigle = sigle;
	}

	public ProfileStructureBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdStructure() {
		return idStructure;
	}

	public void setIdStructure(Long idStructure) {
		this.idStructure = idStructure;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	@Override
	public String toString() {
		return "ProfileStructureBean [idStructure=" + idStructure + ", name=" + name + ", sigle=" + sigle + "]";
	}
	
	

}
