package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    
    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "idroles", "name"})
	private Set<Roles> roleslistes;
    

    @ManyToMany(fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "idposte", "name"})
	private Set<Postes> posteslistes;

	/**
	 * 
	 */
	public GroupUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idgroupes
	 */
	public GroupUser(Long idgroupes) {
		super();
		this.idgroupes = idgroupes;
	}

	/**
	 * @param name
	 * @param roleslistes
	 */
	public GroupUser(String name, Set<Roles> roleslistes) {
		super();
		this.name = name;
		this.roleslistes = roleslistes;
	}

	/**
	 * @param name
	 */
	public GroupUser(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the idgroupes
	 */
	public Long getIdgroupes() {
		return idgroupes;
	}

	/**
	 * @param idgroupes the idgroupes to set
	 */
	public void setIdgroupes(Long idgroupes) {
		this.idgroupes = idgroupes;
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
	 * @return the roleslistes
	 */
	public Set<Roles> getRoleslistes() {
		return roleslistes;
	}

	/**
	 * @param roleslistes the roleslistes to set
	 */
	public void setRoleslistes(Set<Roles> roleslistes) {
		this.roleslistes = roleslistes;
	}

	/**
	 * @return the posteslistes
	 */
	public Set<Postes> getPosteslistes() {
		return posteslistes;
	}

	/**
	 * @param posteslistes the posteslistes to set
	 */
	public void setPosteslistes(Set<Postes> posteslistes) {
		this.posteslistes = posteslistes;
	}

	@Override
	public String toString() {
		return "GroupUser [idgroupes=" + idgroupes + ", name=" + name + ", roleslistes=" + roleslistes
				+ ", posteslistes=" + posteslistes + "]";
	}
	
	

	
    
    
}
