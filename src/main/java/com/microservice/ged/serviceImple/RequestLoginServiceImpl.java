package com.microservice.ged.serviceImple;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<RequestLogin> getRequestLogin(String ipAdress, String account) {
        return requestLoginRepository.findByAccountAndActiveTrue(account);
    }

    @Override
    public void saveRequestLogin(RequestLogin requestLogin) {
        requestLoginRepository.save(requestLogin);
    }

    @Override
    public void deleteRequestLogin(String ipAdress, String account) {
    	List<RequestLogin> getRequestLogin =  requestLoginRepository.findByAccountAndActiveTrue(account);
    	if(!getRequestLogin.isEmpty()) {
    		getRequestLogin.forEach((requestLogin)->{
    			requestLogin.setActive(false);
    			requestLoginRepository.save(requestLogin);
    		});
    	}
    }

	@Override
	public Page<RequestLogin> getAllRequestLogin(int page, int size) {
		// TODO Auto-generated method stub
		return requestLoginRepository.findAll(PageRequest.of(page, size,Sort.by("id").descending()));
	}

	@Override
	public Page<RequestLogin> getAllRequestLoginUser(String account, int page, int size) {
		// TODO Auto-generated method stub
		return requestLoginRepository.findByAccount(account, PageRequest.of(page, size,Sort.by("id").descending()));
	}




}

