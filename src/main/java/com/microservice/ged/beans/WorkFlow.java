package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

/**
 *
 * @author Blingard
 */
@Entity
@Table(name = "workflows")
public class WorkFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idworkflows")
    private Long idworkflows;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "sigle", nullable = false, unique = true)
    private String sigle;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "workflowid", fetch = FetchType.LAZY)
	@JsonIncludeProperties(value = { "idliasse", "name", "description" })
	private List<Liasses> liasses;
    
    @OneToMany(fetch = FetchType.LAZY)
	@JsonIncludeProperties(value = { "idtypedoc", "name", "sigle" })
	private List<TypeDocs> typeDocs;

	/**
	 * 
	 */
	public WorkFlow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idworkflows
	 */
	public WorkFlow(Long idworkflows) {
		super();
		this.idworkflows = idworkflows;
	}

	/**
	 * @param name
	 * @param sigle
	 * @param description
	 * @param liasses
	 * @param typeDocs
	 */
	public WorkFlow(String name, String sigle, String description, List<Liasses> liasses,
			List<TypeDocs> typeDocs) {
		super();
		this.name = name;
		this.sigle = sigle;
		this.description = description;
		this.liasses = liasses;
		this.typeDocs = typeDocs;
	}

	/**
	 * @return the idworkflows
	 */
	public Long getIdworkflows() {
		return idworkflows;
	}

	/**
	 * @param idworkflows the idworkflows to set
	 */
	public void setIdworkflows(Long idworkflows) {
		this.idworkflows = idworkflows;
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
	 * @return the liasses
	 */
	public List<Liasses> getLiasses() {
		return liasses;
	}

	/**
	 * @param liasses the liasses to set
	 */
	public void setLiasses(List<Liasses> liasses) {
		this.liasses = liasses;
	}

	/**
	 * @return the typeDocs
	 */
	public List<TypeDocs> getTypeDocs() {
		return typeDocs;
	}

	/**
	 * @param typeDocs the typeDocs to set
	 */
	public void setTypeDocs(List<TypeDocs> typeDocs) {
		this.typeDocs = typeDocs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, idworkflows, liasses, name, sigle, typeDocs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof WorkFlow))
			return false;
		WorkFlow other = (WorkFlow) obj;
		return Objects.equals(description, other.description) && Objects.equals(idworkflows, other.idworkflows)
				&& Objects.equals(liasses, other.liasses) && Objects.equals(name, other.name)
				&& Objects.equals(sigle, other.sigle) && Objects.equals(typeDocs, other.typeDocs);
	}

	@Override
	public String toString() {
		return "WorkFlow [idworkflows=" + idworkflows + ", name=" + name + ", sigle=" + sigle + ", description="
				+ description + "]";
	}

	

}
