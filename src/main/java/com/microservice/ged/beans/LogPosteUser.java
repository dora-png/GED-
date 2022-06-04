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
    
    @Column(name = "actiondo",nullable = false)
	private String actiondo;
    
    @ManyToOne
	@JsonIncludeProperties(value = {"idposte", "name", "description" })
	private Postes posteId;
    
    @ManyToOne
	@JsonIncludeProperties(value = {"iduser", "name", "surname", "login" })
	private Users userId;

	/**
	 * 
	 */
	public LogPosteUser() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param actiondo
	 * @param posteId
	 * @param userId
	 */
	public LogPosteUser(String actiondo, Postes posteId, Users userId) {
		super();
		this.actiondo = actiondo;
		this.posteId = posteId;
		this.userId = userId;
	}

	/**
	 * @return the idloguserposte
	 */
	public Long getIdloguserposte() {
		return idloguserposte;
	}

	/**
	 * @param idloguserposte the idloguserposte to set
	 */
	public void setIdloguserposte(Long idloguserposte) {
		this.idloguserposte = idloguserposte;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the actiondo
	 */
	public String getActiondo() {
		return actiondo;
	}

	/**
	 * @param actiondo the actiondo to set
	 */
	public void setActiondo(String actiondo) {
		this.actiondo = actiondo;
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
	 * @return the userId
	 */
	public Users getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Users userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actiondo, dateDebut, dateFin, idloguserposte, posteId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LogPosteUser))
			return false;
		LogPosteUser other = (LogPosteUser) obj;
		return Objects.equals(actiondo, other.actiondo) && Objects.equals(dateDebut, other.dateDebut)
				&& Objects.equals(dateFin, other.dateFin) && Objects.equals(idloguserposte, other.idloguserposte)
				&& Objects.equals(posteId, other.posteId) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "LogPosteUser [idloguserposte=" + idloguserposte + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", actiondo=" + actiondo + ", posteId=" + posteId + ", userId=" + userId + "]";
	}
	
    
    
}
