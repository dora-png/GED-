package com.microservice.ged.utils;

import java.util.ArrayList;
import java.util.List;

public class OrganigramStructure {
	private String name;
	final private String cssClass="ngx-org-ceo'";
	final private String image ="../../../../../assets/node.svg";
	private String title;
	private List<OrganigramStructure> childs = new ArrayList<>();
	
	/**
	 * 
	 */
	public OrganigramStructure() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the childs
	 */
	public List<OrganigramStructure> getChilds() {
		return childs;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<OrganigramStructure> childs) {
		this.childs = childs;
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
}
