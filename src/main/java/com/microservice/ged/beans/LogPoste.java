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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "logposte")
public class LogPoste implements Serializable {


	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogposte", nullable = false)
    private Long idlogposte;
    
	 @Column(name = "datecreation", nullable = false, insertable = true, updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreationTimestamp
	private Date dateCreation;
    
    @Column(name = "actiondo",nullable = false)
	private String actiondo;
    
    @Column(name = "loginuser",nullable = false)
	private String loginuser;
    
    @Column(name = "postename",nullable = false)
	private String postename;
    
    @Column(name = "objectname",nullable = false)
	private String objectname;
    
    @Column(name = "typeobject",nullable = false)
	private String type;
    
    @Column(name = "status",columnDefinition="INT DEFAULT '1'")
	private int status;

	/**
	 * 
	 */
	public LogPoste() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idlogposte
	 */
	public LogPoste(Long idlogposte) {
		super();
		this.idlogposte = idlogposte;
	}

	/**
	 * @param dateCreation
	 * @param actiondo
	 * @param loginuser
	 * @param postename
	 * @param objectname
	 * @param type
	 */
	public LogPoste(String actiondo, String loginuser, String postename, String objectname,
			String type) {
		super();
		this.actiondo = actiondo;
		this.loginuser = loginuser;
		this.postename = postename;
		this.objectname = objectname;
		this.type = type;
	}

	/**
	 * @return the idlogposte
	 */
	public Long getIdlogposte() {
		return idlogposte;
	}

	/**
	 * @param idlogposte the idlogposte to set
	 */
	public void setIdlogposte(Long idlogposte) {
		this.idlogposte = idlogposte;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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
	 * @return the loginuser
	 */
	public String getLoginuser() {
		return loginuser;
	}

	/**
	 * @param loginuser the loginuser to set
	 */
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

	/**
	 * @return the postename
	 */
	public String getPostename() {
		return postename;
	}

	/**
	 * @param postename the postename to set
	 */
	public void setPostename(String postename) {
		this.postename = postename;
	}

	/**
	 * @return the objectname
	 */
	public String getObjectname() {
		return objectname;
	}

	/**
	 * @param objectname the objectname to set
	 */
	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actiondo, dateCreation, idlogposte, loginuser, objectname, postename, status, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LogPoste))
			return false;
		LogPoste other = (LogPoste) obj;
		return Objects.equals(actiondo, other.actiondo) && Objects.equals(dateCreation, other.dateCreation)
				&& Objects.equals(idlogposte, other.idlogposte) && Objects.equals(loginuser, other.loginuser)
				&& Objects.equals(objectname, other.objectname) && Objects.equals(postename, other.postename)
				&& status == other.status && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "LogPoste [idlogposte=" + idlogposte + ", dateCreation=" + dateCreation + ", actiondo=" + actiondo
				+ ", loginuser=" + loginuser + ", postename=" + postename + ", objectname=" + objectname + ", type="
				+ type + ", status=" + status + "]";
	}

    
   
}
