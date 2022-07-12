package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Roles> roleslistes = new ArrayList<>();
    

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Postes> posteslistes = new ArrayList<>();

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
	public GroupUser(String name, List<Roles> roleslistes) {
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
	public List<Roles> getRoleslistes() {
		return roleslistes;
	}

	/**
	 * @param roleslistes the roleslistes to set
	 */
	public void setRoleslistes(List<Roles> roleslistes) {
		this.roleslistes = roleslistes;
	}

	/**
	 * @return the posteslistes
	 */
	public List<Postes> getPosteslistes() {
		return posteslistes;
	}

	/**
	 * @param posteslistes the posteslistes to set
	 */
	public void setPosteslistes(List<Postes> posteslistes) {
		this.posteslistes = posteslistes;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	

	
    
    
}
