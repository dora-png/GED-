package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{
	Users findByLogin(String login);
	Users findByName(String name);
	Users findByIduser(Long iduser);
	Page<Users> findByNameLike(String name, Pageable pageable);
	Page<Users> findByLoginLike(String name, Pageable pageable);

}
