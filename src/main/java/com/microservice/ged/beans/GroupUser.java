package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

/**
*
* @author Blingard
*/
@Entity
@Table(name = "groupesuser")
public class GroupUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgroupes", nullable = false)
    private Long idgroupes;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "sigle", nullable = false)
    private String sigle; 
        
    @Column(name = "status")
    private Boolean status;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;

	public GroupUser() {
		super();
	}

	public GroupUser(String name, String sigle, Boolean status) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getIdgroupes() {
		return idgroupes;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	

    public void setIdgroupes(Long idgroupes) {
		this.idgroupes = idgroupes;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	


}
