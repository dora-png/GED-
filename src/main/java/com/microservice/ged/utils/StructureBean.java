package com.microservice.ged.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureBean {

	private Long id;
	private String titre;
	private String description;
	private long structureSuperieur;
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
	 * @return the structureSuperieur
	 */
	public long getStructureSuperieur() {
		return structureSuperieur;
	}
	/**
	 * @param structureSuperieur the structureSuperieur to set
	 */
	public void setStructureSuperieur(long structureSuperieur) {
		this.structureSuperieur = structureSuperieur;
	}
	

}
