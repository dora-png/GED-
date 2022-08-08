package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="sharedoc")
public class ShareDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsharedoc")
    private Long idsharedoc;
    
    @Column(name = "loginuser")
    private String loginUser;
    
    @Column(name = "itemshare")
    private Long itemShare;
    
    @Column(name = "typeshare", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TypeShare typeShare;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreation", nullable = false)
	@CreationTimestamp
	private Date dateCreation;

    @Column(name = "active", nullable = false)
    private boolean active = true;

	public ShareDoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShareDoc(String loginUser, Long itemShare, TypeShare typeShare) {
		super();
		this.loginUser = loginUser;
		this.itemShare = itemShare;
		this.typeShare = typeShare;
	}

	public Long getIdsharedoc() {
		return idsharedoc;
	}

	public void setIdsharedoc(Long idsharedoc) {
		this.idsharedoc = idsharedoc;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public Long getItemShare() {
		return itemShare;
	}

	public void setItemShare(Long itemShare) {
		this.itemShare = itemShare;
	}

	public TypeShare getTypeShare() {
		return typeShare;
	}

	public void setTypeShare(TypeShare typeShare) {
		this.typeShare = typeShare;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, dateCreation, idsharedoc, itemShare, loginUser, typeShare);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShareDoc other = (ShareDoc) obj;
		return active == other.active && Objects.equals(dateCreation, other.dateCreation)
				&& Objects.equals(idsharedoc, other.idsharedoc) && Objects.equals(itemShare, other.itemShare)
				&& Objects.equals(loginUser, other.loginUser) && typeShare == other.typeShare;
	}

	@Override
	public String toString() {
		return "ShareDoc [idsharedoc=" + idsharedoc + ", loginUser=" + loginUser + ", itemShare=" + itemShare
				+ ", typeShare=" + typeShare + ", dateCreation=" + dateCreation + ", active=" + active + "]";
	}
    
    
        
}
