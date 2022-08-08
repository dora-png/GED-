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
@Table(name = "droitprofile")
public class DroitProfiles  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddroitprofile")
    private Long iddroitprofile;
    
    @ManyToOne
	private Droits droitId;
    
    @ManyToOne
	private Profiles profileId;
    
    
    @Column(name = "isactive")
    private boolean isactive;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false, updatable = false)
	@CreationTimestamp
	private Date dateCreation;


	public DroitProfiles() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DroitProfiles(Droits droitId, Profiles profileId, boolean isactive) {
		super();
		this.droitId = droitId;
		this.profileId = profileId;
		this.isactive = isactive;
	}


	public Long getIddroitprofile() {
		return iddroitprofile;
	}


	public void setIddroitprofile(Long iddroitprofile) {
		this.iddroitprofile = iddroitprofile;
	}


	public Droits getDroitId() {
		return droitId;
	}


	public void setDroitId(Droits droitId) {
		this.droitId = droitId;
	}


	public Profiles getProfileId() {
		return profileId;
	}


	public void setProfileId(Profiles profileId) {
		this.profileId = profileId;
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
	
	

}
