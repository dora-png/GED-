package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.ged.beans.Appusers;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Users;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.LogPosteUserRepo;
import com.microservice.ged.repository.PosteRepo;
import com.microservice.ged.repository.RolesRepo;
import com.microservice.ged.repository.UserRepo;
import com.microservice.ged.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AppUserRepo appUserRepo; 
	
	@Autowired
	RolesRepo rolesRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		return new User(user.getUsername(),user.getPassword(),authorities);
	}


	@Override
	public Page<Users> defaultList(int page, int size) throws Exception {
		return userRepo.findAll(PageRequest.of(page, size,Sort.by("iduser").descending()));
	}

	@Override
	public Page<Users> findAll(int page, int size) throws Exception {
		return userRepo.findAll(PageRequest.of(page, size,Sort.by("iduser").descending()));
	}

	@Override
	public Page<Users> searchByName(String name, int page, int size) throws Exception {
		return userRepo.findByNameContaining(name, PageRequest.of(page, size,Sort.by("iduser").descending()));
	}

	@Override
	public Page<Users> searchByLogin(String login, int page, int size) throws Exception {
		return userRepo.findByUsernameContaining(login, PageRequest.of(page, size,Sort.by("iduser").descending()));
	}

	@Override
	public void add(Users users) throws Exception {
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()) );
		userRepo.save(users);
	
		
	}

	@Override
	public void update(Users users) throws Exception {


		userRepo.save(users);
	}

	@Override
	public String getPwd(Long posteId, Users users) throws Exception {
		Users user = userRepo.findByIduser(users.getIduser());
		if(user==null) {
			throw new Exception("User dont exist");
		}
		return user.getPassword();
	}

	@Override
	public void changePwd(Long posteId, Users users, String newPdb) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Users users) throws Exception {
				userRepo.delete(users);
	}

	@Override
	public Users findById(Long id) throws Exception {
		return userRepo.findByIduser(id);
	}

	@Override
	public Users findByName(String name) throws Exception {
		return userRepo.findByName(name);
	}

	@Override
	public Users findByUsername(String login) {
		return userRepo.findByUsername(login);
	}

	@Override
	public void save(Users users) throws Exception {
		// TODO Auto-generated method stub
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()) );
		userRepo.save(users);
	}

	@Override
	public void setStatus(Users users) {
		// TODO Auto-generated method stub
		
	}

}
