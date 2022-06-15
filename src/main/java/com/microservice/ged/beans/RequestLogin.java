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

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

    public RequestLogin(String ipAdress, String account, int count) {
        this.ipAdress = ipAdress;
        this.account = account;
        this.count = count;
    }


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ipAdress
	 */
	public String getIpAdress() {
		return ipAdress;
	}

	/**
	 * @param ipAdress the ipAdress to set
	 */
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, count, date, id, ipAdress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RequestLogin))
			return false;
		RequestLogin other = (RequestLogin) obj;
		return Objects.equals(account, other.account) && count == other.count && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && Objects.equals(ipAdress, other.ipAdress);
	}

	@Override
	public String toString() {
		return "RequestLogin [id=" + id + ", ipAdress=" + ipAdress + ", account=" + account + ", count=" + count
				+ ", date=" + date + "]";
	}
    
    
}
