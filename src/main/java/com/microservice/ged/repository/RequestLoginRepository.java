package com.microservice.ged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.RequestLogin;

@Repository
public interface RequestLoginRepository extends JpaRepository<RequestLogin, Long> {
    public RequestLogin findByAccount(String account);
    public RequestLogin findByIpAdressAndAccount(String ipAdress, String account);
}
