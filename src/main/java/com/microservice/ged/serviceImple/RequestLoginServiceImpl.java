package com.microservice.ged.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.RequestLogin;
import com.microservice.ged.repository.RequestLoginRepository;
import com.microservice.ged.service.RequestLoginService;

@Transactional
@Service
public class RequestLoginServiceImpl implements RequestLoginService{
    @Autowired
    private RequestLoginRepository requestLoginRepository;


    @Override
    public RequestLogin getRequestLogin(String ipAdress, String account) {
        return requestLoginRepository.findByIpAdressAndAccount(ipAdress,account);
    }

    @Override
    public void saveRequestLogin(RequestLogin requestLogin) {
        requestLoginRepository.save(requestLogin);
    }

    @Override
    public void deleteRequestLogin(String ipAdress, String account) {
        requestLoginRepository.delete(requestLoginRepository.findByIpAdressAndAccount(ipAdress,account));
    }




}

