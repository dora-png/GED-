package com.microservice.ged.service;

import com.microservice.ged.beans.RequestLogin;

public interface RequestLoginService {
	public RequestLogin getRequestLogin(String ipAdress, String account);
    public void saveRequestLogin(RequestLogin requestLogin);
    public void deleteRequestLogin(String ipAdress, String account);

}
