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
@Table(name = "trace")
public class Trace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private Long id;

    @Column(name = "username", nullable = false)
	private String username;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
	@CreationTimestamp
	private Date date;

    @Column(name = "uri", nullable = false)
	private String uri;

    @Column(name = "url", nullable = false)
	private String url;

    @Column(name = "ip", nullable = false)
	private String ip;

    @Column(name = "method", nullable = false)
	private String method;

    @Column(name = "response", nullable = false)
	private int response;

	public Trace(String username, String uri, String url, String ip, String method, int response) {
		this.username = username;
		this.uri = uri;
		this.url = url;
		this.ip = ip;
		this.method = method;
		this.response = response;
	}

	/**
	 * 
	 */
	public Trace() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the response
	 */
	public int getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(int response) {
		this.response = response;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, ip, method, response, uri, url, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Trace))
			return false;
		Trace other = (Trace) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(ip, other.ip)
				&& Objects.equals(method, other.method) && response == other.response && Objects.equals(uri, other.uri)
				&& Objects.equals(url, other.url) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Trace [id=" + id + ", username=" + username + ", date=" + date + ", uri=" + uri + ", url=" + url
				+ ", ip=" + ip + ", method=" + method + ", response=" + response + "]";
	}
	
	

}
