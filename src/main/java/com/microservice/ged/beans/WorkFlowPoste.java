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
	@JsonIncludeProperties(value = {"idworkflow", "name", "sigle"})
	private WorkFlow workflowId;
    
    @Column(name = "level", nullable = false)
    private int level;
    
    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active = true;

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
	 * @return the posteId
	 */
	public Postes getPosteId() {
		return posteId;
	}

	/**
	 * @param posteId the posteId to set
	 */
	public void setPosteId(Postes posteId) {
		this.posteId = posteId;
	}

	/**
	 * @return the workflowId
	 */
	public WorkFlow getWorkflowId() {
		return workflowId;
	}

	/**
	 * @param workflowId the workflowId to set
	 */
	public void setWorkflowId(WorkFlow workflowId) {
		this.workflowId = workflowId;
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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, idworkflowposte, level, posteId, workflowId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof WorkFlowPoste))
			return false;
		WorkFlowPoste other = (WorkFlowPoste) obj;
		return active == other.active && Objects.equals(idworkflowposte, other.idworkflowposte) && level == other.level
				&& Objects.equals(posteId, other.posteId) && Objects.equals(workflowId, other.workflowId);
	}

	@Override
	public String toString() {
		return "WorkFlowPoste [idworkflowposte=" + idworkflowposte + ", posteId=" + posteId + ", workflowId="
				+ workflowId + ", level=" + level + ", active=" + active + "]";
	}
    
    
    
}
