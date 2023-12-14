package com.microservice.ged.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.RequestLogin;

public interface RequestLoginService {
	public List<RequestLogin> getRequest(String account);
	public List<RequestLogin> getRequestLogin(String ipAdress, String account);
	public Page<RequestLogin> getAllRequestLoginUser(String account, int page, int size);
	public Page<RequestLogin> getAllRequestLogin(int page, int size);
    public void saveRequestLogin(RequestLogin requestLogin);
    public void deleteRequestLogin(String ipAdress, String account);

}
