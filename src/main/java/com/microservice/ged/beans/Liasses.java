package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "liasses")
public class Liasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idliasse")
    private Long idliasse;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "sigle", nullable = false, unique = true)
    private String sigle;
    
    @Column(name = "description", nullable = false)
    private String description;
    
	@Column(name = "datecreation", nullable = false, insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreation;
    
    @OneToMany(mappedBy = "liasse", fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "iddoc", "name", "extension", "path" })
	private List<Docs> docs;
    
    
    @ManyToOne
   	@JoinColumn(nullable = true)
   	//@JsonIncludeProperties(value = {"idworkflows", "name", "sigle" })
   	private WorkFlow workflowid;
    
    @ManyToOne
   	@JoinColumn(nullable = true)
   	//@JsonIncludeProperties(value = {"iduser", "name", "username" })
   	private Users userid;
    
    @ManyToOne
   	@JoinColumn(nullable = true)
   	@JsonIncludeProperties(value = {"idtypeliasse", "name", "sigle" })
   	private TypeLiasses typeliasse;

	/**
	 * 
	 */
	public Liasses() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param docs
	 * @param workflowid
	 * @param userid
	 * @param typeliasse
	 */
	public Liasses(String name, String sigle, String description, List<Docs> docs, WorkFlow workflowid, Users userid,
			TypeLiasses typeliasse) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.docs = docs;
		this.workflowid = workflowid;
		this.userid = userid;
		this.typeliasse = typeliasse;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param userid
	 */
	public Liasses(String name, String sigle, String description, Users userid) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.userid = userid;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param docs
	 * @param userid
	 */
	public Liasses(String name, String sigle, String description, List<Docs> docs, Users userid) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.docs = docs;
		this.userid = userid;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param docs
	 * @param workflowid
	 * @param typeliasse
	 */
	public Liasses(String name, String sigle, String description, List<Docs> docs, WorkFlow workflowid,
			TypeLiasses typeliasse) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.docs = docs;
		this.workflowid = workflowid;
		this.typeliasse = typeliasse;
	}

	/**
	 * @return the idliasse
	 */
	public Long getIdliasse() {
		return idliasse;
	}

	/**
	 * @param idliasse the idliasse to set
	 */
	public void setIdliasse(Long idliasse) {
		this.idliasse = idliasse;
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
	 * @return the sigle
	 */
	public String getSigle() {
		return sigle;
	}

	/**
	 * @param sigle the sigle to set
	 */
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the docs
	 */
	public List<Docs> getDocs() {
		return docs;
	}

	/**
	 * @param docs the docs to set
	 */
	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}

	/**
	 * @return the workflowid
	 */
	public WorkFlow getWorkflowid() {
		return workflowid;
	}

	/**
	 * @param workflowid the workflowid to set
	 */
	public void setWorkflowid(WorkFlow workflowid) {
		this.workflowid = workflowid;
	}

	/**
	 * @return the userid
	 */
	public Users getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Users userid) {
		this.userid = userid;
	}

	/**
	 * @return the typeliasse
	 */
	public TypeLiasses getTypeliasse() {
		return typeliasse;
	}

	/**
	 * @param typeliasse the typeliasse to set
	 */
	public void setTypeliasse(TypeLiasses typeliasse) {
		this.typeliasse = typeliasse;
	}
	
	
    
    

}