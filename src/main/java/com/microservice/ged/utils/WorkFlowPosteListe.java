package com.microservice.ged.utils;

import java.util.Objects;

import com.microservice.ged.beans.Postes;

public class WorkFlowPosteListe {
	private int  index;
	private Long  idPoste;
	private Long  idWorkFlow;
	private boolean exist;
	/**
	 * 
	 */
	public WorkFlowPosteListe() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param index
	 * @param idPoste
	 * @param idWorkFlow
	 * @param exist
	 */
	public WorkFlowPosteListe(int index, Long idPoste, Long idWorkFlow, boolean exist) {
		super();
		this.index = index;
		this.idPoste = idPoste;
		this.idWorkFlow = idWorkFlow;
		this.exist = exist;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the idPoste
	 */
	public Long getIdPoste() {
		return idPoste;
	}
	/**
	 * @param idPoste the idPoste to set
	 */
	public void setIdPoste(Long idPoste) {
		this.idPoste = idPoste;
	}
	/**
	 * @return the idWorkFlow
	 */
	public Long getIdWorkFlow() {
		return idWorkFlow;
	}
	/**
	 * @param idWorkFlow the idWorkFlow to set
	 */
	public void setIdWorkFlow(Long idWorkFlow) {
		this.idWorkFlow = idWorkFlow;
	}
	/**
	 * @return the exist
	 */
	public boolean isExist() {
		return exist;
	}
	/**
	 * @param exist the exist to set
	 */
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	@Override
	public int hashCode() {
		return Objects.hash(exist, idPoste, idWorkFlow, index);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof WorkFlowPosteListe))
			return false;
		WorkFlowPosteListe other = (WorkFlowPosteListe) obj;
		return exist == other.exist && Objects.equals(idPoste, other.idPoste)
				&& Objects.equals(idWorkFlow, other.idWorkFlow) && index == other.index;
	}
	@Override
	public String toString() {
		return "WorkFlowPosteListe [index=" + index + ", idPoste=" + idPoste + ", idWorkFlow=" + idWorkFlow + ", exist="
				+ exist + "]";
	}


	

}
