package com.microservice.ged.beans;

public class LoginUser {
	private String login;
	private String pwd;
	
	public LoginUser(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void log(String userName, String pwd2) {
		// TODO Auto-generated method stub
		
	}
	
	

}
