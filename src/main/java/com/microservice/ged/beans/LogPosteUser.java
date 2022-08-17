package com.microservice.ged.beans;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "loguserposte")
public class LogPosteUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idloguserposte", nullable = false)
    private Long idloguserposte;
    
	 @Column(name = "datecreation", nullable = false, insertable = true, updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreationTimestamp
	private Date dateDebut;
    
    @Column(name = "datefin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;
    
    @ManyToOne
	@JsonIncludeProperties(value = {"idposte", "name", "description", "structure" })
	private Postes posteId;
    
    @ManyToOne
	private Profiles ldaplogin;

	public LogPosteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogPosteUser(Postes posteId, Profiles ldaplogin) {
		super();
		this.posteId = posteId;
		this.ldaplogin = ldaplogin;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Long getIdloguserposte() {
		return idloguserposte;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Postes getPosteId() {
		return posteId;
	}

	public Profiles getLdaplogin() {
		return ldaplogin;
	}

	@Override
	public String toString() {
		return "LogPosteUser [idloguserposte=" + idloguserposte + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", posteId=" + posteId + ", ldaplogin=" + ldaplogin + "]";
	}

	
    
}
