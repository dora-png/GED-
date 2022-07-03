package com.microservice.ged.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PosteRoleBean {
	private Long poste;
	private String role;
	
	/**
	 * 
	 */
	public PosteRoleBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the poste
	 */
	public Long getPoste() {
		return poste;
	}
	/**
	 * @param poste the poste to set
	 */
	public void setPoste(Long poste) {
		this.poste = poste;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
}
