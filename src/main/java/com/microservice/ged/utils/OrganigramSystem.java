package com.microservice.ged.utils;

import java.util.ArrayList;
import java.util.List;

public class OrganigramSystem {
	private Long id;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	final private String cssClass="ngx-org-ceo'";
	final private String image ="../../../../../assets/node.svg";
	private String title;
	private List<OrganigramSystem> childs = new ArrayList<>();
	
	/**
	 * 
	 */
	public OrganigramSystem() {
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
	public List<OrganigramSystem> getChilds() {
		return childs;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<OrganigramSystem> childs) {
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
