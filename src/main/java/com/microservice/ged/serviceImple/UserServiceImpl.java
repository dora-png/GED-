package com.microservice.ged.serviceImple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.LogPosteUser;
import com.microservice.ged.beans.Postes;
import com.microservice.ged.beans.Roles;
import com.microservice.ged.beans.Users;
import com.microservice.ged.repository.AppUserRepo;
import com.microservice.ged.repository.GroupUserRepo;
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
	GroupUserRepo groupUserRepo;
	
	@Autowired
	PosteRepo posteRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Autowired
	LogPosteUserRepo logPosteUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepo.findByUsername(username);
		Appusers appusers = null;
		Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
		if(user==null) {
			appusers = appUserRepo.findByLogin(username);
			if(appusers==null) {
				throw new UsernameNotFoundException("User not found");
			}
			List<Roles> roles =  rolesRepo.findAll();
			roles.forEach(
					(role)->{
						if(!authorities.contains(role.getName())) {
							authorities.add(new SimpleGrantedAuthority(role.getName()));
						}
					}
				 );
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new User(appusers.getLogin(),appusers.getPassword(),authorities);
		}else {
			LogPosteUser logPosteUser =  logPosteUserRepo.findByUserIdAndDateFinIsNull(user);
			if(logPosteUser!=null) {
				Set<Postes> postes = new HashSet<>();
				postes.add(logPosteUser.getPosteId());
				List<GroupUser> groupUserList = groupUserRepo.findByPosteslistesIn(postes);
				groupUserList.forEach(
						(groupUser)->{
							groupUser.getRoleslistes().forEach(
									(role)->{
										if(!authorities.contains(role.getName())) {
											authorities.add(new SimpleGrantedAuthority(role.getName()));
										}
									}
							);
						}
				);
			}else{
				throw new UsernameNotFoundException("User "+user.getUsername() +" don't have poste contact administrator to have poste");
			}
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new User(user.getUsername(),user.getPassword(),authorities);			
		}
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


	@Override
	public Page<Users> listUserToAffect(Long id, int page, int size) throws Exception {
		// TODO Auto-generated method stub
		Set<Long> ids = new HashSet<>();
		ids.add(id);
		return userRepo.findByIduserNotIn(ids, PageRequest.of(page, size,Sort.by("iduser").descending()));
	}

}
