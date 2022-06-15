package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "workflowposte")
public class WorkFlowPoste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idworkflowposte")
    private Long idworkflowposte;
    
    @ManyToOne
	@JsonIncludeProperties(value = {"idposte", "name", "description" })
	private Postes posteId;
    
    @ManyToOne
	@JsonIncludeProperties(value = {"idworkflows", "name", "sigle"})
	private WorkFlow workflowId;
    
    @Column(name = "level", nullable = false)
    private int level;
    
    @Column(name = "isactive")
    private boolean isactive;

	/**
	 * 
	 */
	public WorkFlowPoste() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idworkflowposte
	 */
	public WorkFlowPoste(Long idworkflowposte) {
		super();
		this.idworkflowposte = idworkflowposte;
	}

	/**
	 * @param posteId
	 * @param workflowId
	 * @param level
	 * @param active
	 */
	public WorkFlowPoste(Postes posteId, WorkFlow workflowId, int level) {
		super();
		this.posteId = posteId;
		this.workflowId = workflowId;
		this.level = level;
	}

	/**
	 * @return the idworkflowposte
	 */
	public Long getIdworkflowposte() {
		return idworkflowposte;
	}

	/**
	 * @param idworkflowposte the idworkflowposte to set
	 */
	public void setIdworkflowposte(Long idworkflowposte) {
		this.idworkflowposte = idworkflowposte;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the isactive
	 */
	public boolean isIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	/**
	 * @return the posteId
	 */
	public Postes getPosteId() {
		return posteId;
	}

	/**
	 * @return the workflowId
	 */
	public WorkFlow getWorkflowId() {
		return workflowId;
	}

	
}
