package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "groupprofile")
public class GroupProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgroupprofile")
    private Long idgroupprofile;
    
    @ManyToOne
	private Profiles profileId;
    
    @ManyToOne
	private GroupUser groupuserId;
    
    
    @Column(name = "isactive")
    private boolean isactive;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false, updatable = false)
	@CreationTimestamp
	private Date dateCreation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateEnd")
	private Date dateEnd;


	public GroupProfile() {
		super();
	}


	public GroupProfile(Profiles profileId, GroupUser groupuserId, boolean isactive) {
		super();
		this.profileId = profileId;
		this.groupuserId = groupuserId;
		this.isactive = isactive;
	}


	public Long getIdgroupprofile() {
		return idgroupprofile;
	}


	public void setIdgroupprofile(Long idgroupprofile) {
		this.idgroupprofile = idgroupprofile;
	}


	public Profiles getProfileId() {
		return profileId;
	}


	public void setProfileId(Profiles profileId) {
		this.profileId = profileId;
	}


	public GroupUser getGroupuserId() {
		return groupuserId;
	}


	public void setGroupuserId(GroupUser groupuserId) {
		this.groupuserId = groupuserId;
	}


	public boolean isIsactive() {
		return isactive;
	}


	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public Date getDateEnd() {
		return dateEnd;
	}


	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	
}
