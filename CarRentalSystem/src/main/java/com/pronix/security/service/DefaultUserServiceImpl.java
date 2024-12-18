package com.pronix.security.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pronix.entity.Role;
import com.pronix.entity.User;
import com.pronix.repository.RoleRepository;
import com.pronix.repository.UserRepository;
@Service
public class DefaultUserServiceImpl implements IDefaultUserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByEmail(username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}
	
	
	@Override
	public User save(User user) {
		Role role = new Role();
		if(user.getRoles().equals("USER"))
		  role = roleRepo.findByRole("ROLE_USER");
		else if(user.getRoles().equals("ADMIN"))
		 role = roleRepo.findByRole("ROLE_ADMIN");
		User user1 = new User();
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setEmail(user.getEmail());		
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		user1.setRole(role);
		
		return userRepo.save(user1);
	}

}
