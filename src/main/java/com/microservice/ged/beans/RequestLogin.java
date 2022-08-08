package com.microservice.ged.beans;

import java.util.Date;
import java.util.Objects;

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
@Table(name = "requestlogin")
public class RequestLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ipAdress", nullable = false)
    private String ipAdress;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

    
    
    public RequestLogin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RequestLogin(String ipAdress, String account) {
        this.ipAdress = ipAdress;
        this.account = account;
    }



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIpAdress() {
		return ipAdress;
	}


	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}


	public Boolean getActive() {
		return this.active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, account, date, id, ipAdress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RequestLogin))
			return false;
		RequestLogin other = (RequestLogin) obj;
		return Objects.equals(active, other.active) && account == other.account && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && Objects.equals(ipAdress, other.ipAdress);
	}

	@Override
	public String toString() {
		return "RequestLogin [id=" + id + ", ipAdress=" + ipAdress + ", active=" + active + ", account=" + account
				+ ", date=" + date + "]";
	}
    
    
}
