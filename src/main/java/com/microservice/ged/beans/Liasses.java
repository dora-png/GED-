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
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "sigle", nullable = false)
    private String sigle;
    
    @Column(name = "description")
    private String description;
    
	@Column(name = "datecreation", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreation;
    
	@Column(name = "archive", nullable = false)
    private boolean archive = false;
	
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
   	private Profiles profileid;
    
    @ManyToOne
   	@JoinColumn(nullable = true)
   	@JsonIncludeProperties(value = {"idtypeliasse", "name", "sigle" })
   	private TypeLiasses typeliasse;

    
    
	public Liasses() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Liasses(String name, String sigle, String description) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
	}



	public Long getIdliasse() {
		return idliasse;
	}

	public void setIdliasse(Long idliasse) {
		this.idliasse = idliasse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public List<Docs> getDocs() {
		return docs;
	}

	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}

	public WorkFlow getWorkflowid() {
		return workflowid;
	}

	public void setWorkflowid(WorkFlow workflowid) {
		this.workflowid = workflowid;
	}

	public TypeLiasses getTypeliasse() {
		return typeliasse;
	}

	public void setTypeliasse(TypeLiasses typeliasse) {
		this.typeliasse = typeliasse;
	}



	public Profiles getProfileid() {
		return profileid;
	}



	public void setProfileid(Profiles profileid) {
		this.profileid = profileid;
	}

    
}