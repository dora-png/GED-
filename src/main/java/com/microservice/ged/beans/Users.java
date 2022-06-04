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
public class Users implements Serializable {
	 private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "id",nullable = false)
	 private Long iduser;
	 
	 @Column(name = "name", unique = true, nullable = false)
	 private String name;
	 
	 @Column(name = "login", unique = true, nullable = false)
	 private String login;
	 
	 @Column(name = "password", nullable = false)
	 private String password;
	 
	 @Column(name = "datecreation", nullable = false, insertable = true, updatable = false)
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
	 * @param iduser
	 */
	public Users(Long iduser) {
		super();
		this.iduser = iduser;
	}

	/**
	 * @param name
	 * @param surname
	 * @param login
	 * @param password
	 */
	public Users(String name, String login, String password) {
		super();
		this.name = name;
		this.login = login;
		this.password = password;
	}

	/**
	 * @return the iduser
	 */
	public Long getIduser() {
		return iduser;
	}

	/**
	 * @param iduser the iduser to set
	 */
	public void setIduser(Long iduser) {
		this.iduser = iduser;
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public int hashCode() {
		return Objects.hash(dateCreation, iduser, login, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Users))
			return false;
		Users other = (Users) obj;
		return Objects.equals(dateCreation, other.dateCreation) && Objects.equals(iduser, other.iduser)
				&& Objects.equals(login, other.login) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Users [iduser=" + iduser + ", name=" + name +", login=" + login
				+ ", password=" + password + ", dateCreation=" + dateCreation + "]";
	}
	 
	 
	 
}
