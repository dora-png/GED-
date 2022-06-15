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
@Table(name = "users")
public class Users {
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "id")
	 private Long iduser;
	 
	 @Column(name = "name", unique = true)
	 private String name;
	 
	 @Column(name = "username", unique = true, nullable = false)
	 private String username;
	 
	 @Column(name = "password", nullable = false)
	 private String password;
	 
	 
	 @Column(name = "status")
	 private boolean status = true;
	 
	 @Column(name = "datecreation", insertable = true, updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreationTimestamp
	 private Date dateCreation;

	/**
	 * 
	 */
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param username
	 * @param password
	 */
	public Users(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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
	 * @return the iduser
	 */
	public Long getIduser() {
		return iduser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCreation, iduser, name, password, status, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Users))
			return false;
		Users other = (Users) obj;
		return Objects.equals(dateCreation, other.dateCreation) && Objects.equals(iduser, other.iduser)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& status == other.status && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Users [iduser=" + iduser + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", status=" + status + ", dateCreation=" + dateCreation + "]";
	}

	


	 
	 
}
