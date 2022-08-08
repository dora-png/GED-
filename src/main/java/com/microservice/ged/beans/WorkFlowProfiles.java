package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "workflowprofile")
public class WorkFlowProfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idworkflowprofile")
    private Long idworkflowprofile;
    
    @ManyToOne
	private Profiles profilesId;
    
    @ManyToOne
	//@JsonIncludeProperties(value = {"idworkflows", "name", "sigle"})
	private WorkFlow workflowId;
    
    @Column(name = "level", nullable = false)
    private int level;
    
    @Column(name = "isactive")
    private boolean isactive;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false, updatable = false)
	@CreationTimestamp
	private Date dateCreation;
    
    
	public Date getDateCreation() {
		return dateCreation;
	}


	public WorkFlowProfiles() {
		super();
		// TODO Auto-generated constructor stub
	}


	public WorkFlowProfiles(Profiles profilesId, WorkFlow workflowId, int level, boolean isactive) {
		super();
		this.profilesId = profilesId;
		this.workflowId = workflowId;
		this.level = level;
		this.isactive = isactive;
	}


	public Long getIdworkflowposte() {
		return idworkflowprofile;
	}


	public void setIdworkflowposte(Long idworkflowprofile) {
		this.idworkflowprofile = idworkflowprofile;
	}


	public Profiles getProfilesId() {
		return profilesId;
	}


	public void setProfilesId(Profiles profilesId) {
		this.profilesId = profilesId;
	}


	public WorkFlow getWorkflowId() {
		return workflowId;
	}


	public void setWorkflowId(WorkFlow workflowId) {
		this.workflowId = workflowId;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public boolean isIsactive() {
		return isactive;
	}


	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
}
