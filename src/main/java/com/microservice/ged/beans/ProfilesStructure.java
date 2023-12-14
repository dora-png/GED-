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
@Table(name = "profilestructure")
public class ProfilesStructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofilestructure")
    private Long idprofilestructure;
    
    @ManyToOne
	private Profiles profilesId;
    
    @ManyToOne
	private Structures structureId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateSend", nullable = false, updatable = false)
	@CreationTimestamp
	private Date dateSend;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateEnd")
	private Date dateEnd;

	public ProfilesStructure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfilesStructure(Profiles profilesId, Structures structureId) {
		super();
		this.profilesId = profilesId;
		this.structureId = structureId;
	}

	public Long getIdprofilestructure() {
		return idprofilestructure;
	}

	public void setIdprofilestructure(Long idprofilestructure) {
		this.idprofilestructure = idprofilestructure;
	}

	public Profiles getProfilesId() {
		return profilesId;
	}

	public void setProfilesId(Profiles profilesId) {
		this.profilesId = profilesId;
	}

	public Structures getStructureId() {
		return structureId;
	}

	public void setStructureId(Structures structureId) {
		this.structureId = structureId;
	}

	public Date getDateSend() {
		return dateSend;
	}

	public void setDateSend(Date dateSend) {
		this.dateSend = dateSend;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
    
    
	
}

