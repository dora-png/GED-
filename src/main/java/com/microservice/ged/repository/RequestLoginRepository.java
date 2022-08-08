package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.RequestLogin;

@Repository
public interface RequestLoginRepository extends JpaRepository<RequestLogin, Long> {
    public List<RequestLogin> findByAccountAndActiveTrue(String account);
    public Page<RequestLogin> findByAccount(String account, Pageable pageable);
    public Page<RequestLogin> findAll(Pageable pageable);
    public List<RequestLogin> findByIpAdressAndAccountAndActiveTrue(String ipAdress, String account);
}
