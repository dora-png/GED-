package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appuser")

public class Appusers implements Serializable {
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "id")
	    private Long iduser;
	    @Column(name = "name", columnDefinition = "VARCHAR(60) CHECK (name IN ('ROOT')")
	    private String name;
	    @Column(name = "login", columnDefinition = "string CHECK (login IN ('root')")
	    private String login;
	    @Column(name = "password")
	    private String password;
		/**
		 * 
		 */
		public Appusers() {
			super();
			// TODO Auto-generated constructor stub
		}
		/**
		 * @param iduser
		 */
		public Appusers(Long iduser) {
			super();
			this.iduser = iduser;
		}
		/**
		 * @param name
		 * @param login
		 * @param password
		 */
		public Appusers(String name, String login, String password) {
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
		@Override
		public int hashCode() {
			return Objects.hash(iduser, login, name, password);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Appusers))
				return false;
			Appusers other = (Appusers) obj;
			return Objects.equals(iduser, other.iduser) && Objects.equals(login, other.login)
					&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
		}
	    
	    

}
