package com.microservice.ged.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosteBean {

	private Long id;
	private String titre;
	private String description;
	private long structure;
	private long posteSuperieur;
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
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
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
	 * @return the structure
	 */
	public long getStructure() {
		return structure;
	}
	/**
	 * @param structure the structure to set
	 */
	public void setStructure(long structure) {
		this.structure = structure;
	}
	/**
	 * @return the posteSuperieur
	 */
	public long getPosteSuperieur() {
		return posteSuperieur;
	}
	/**
	 * @param posteSuperieur the posteSuperieur to set
	 */
	public void setPosteSuperieur(long posteSuperieur) {
		this.posteSuperieur = posteSuperieur;
	}
	

}
