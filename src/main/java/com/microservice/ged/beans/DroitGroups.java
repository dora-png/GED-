package com.microservice.ged.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name = "droitgroups")
public class DroitGroups implements Serializable {

		    private static final long serialVersionUID = 1L;
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    @Basic(optional = false)
		    @Column(name = "iddroitgroups")
		    private Long iddroitgroups;
		    
		    @ManyToOne
			private Droits droitId;
		    
		    @ManyToOne
			private GroupUser groupuserId;
		    
		    
		    @Column(name = "isactive")
		    private boolean isactive;
		    
		    
		    @Temporal(TemporalType.TIMESTAMP)
		    @Column(name = "dateCreation", nullable = false, updatable = false)
			@CreationTimestamp
			private Date dateCreation;


			public DroitGroups() {
				super();
			}


			public DroitGroups(Droits droitId, GroupUser groupuserId, boolean isactive) {
				super();
				this.droitId = droitId;
				this.groupuserId = groupuserId;
				this.isactive = isactive;
			}


			public GroupUser getGroupuserId() {
				return groupuserId;
			}


			public void setGroupuserId(GroupUser groupuserId) {
				this.groupuserId = groupuserId;
			}


			public boolean isIsactive() {
				return isactive;
			}


			public void setIsactive(boolean isactive) {
				this.isactive = isactive;
			}


			public Long getIddroitgroups() {
				return iddroitgroups;
			}


			public Droits getDroitId() {
				return droitId;
			}


			public Date getDateCreation() {
				return dateCreation;
			}


			@Override
			public String toString() {
				return "DroitGroups [iddroitgroups=" + iddroitgroups + ", droitId=" + droitId + ", groupuserId="
						+ groupuserId + ", isactive=" + isactive + ", dateCreation=" + dateCreation + "]";
			}
		    
		    
			
}
