package com.microservice.ged.utils;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosteEmployeBean {

	private Long id;
	private Date dateAffectation;
	private Date dateDepart;
	private int employeid;
	private long poste;
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
	/**
	 * @return the dateAffectation
	 */
	public Date getDateAffectation() {
		return dateAffectation;
	}
	/**
	 * @param dateAffectation the dateAffectation to set
	 */
	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}
	/**
	 * @return the dateDepart
	 */
	public Date getDateDepart() {
		return dateDepart;
	}
	/**
	 * @param dateDepart the dateDepart to set
	 */
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
	/**
	 * @return the employeid
	 */
	public int getEmployeid() {
		return employeid;
	}
	/**
	 * @param employeid the employeid to set
	 */
	public void setEmployeid(int employeid) {
		this.employeid = employeid;
	}
	/**
	 * @return the poste
	 */
	public long getPoste() {
		return poste;
	}
	/**
	 * @param poste the poste to set
	 */
	public void setPoste(long poste) {
		this.poste = poste;
	}
	

}
