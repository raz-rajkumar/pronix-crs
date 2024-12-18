package com.pronix.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pronix.entity.User;
@Service
public interface IDefaultUserService extends UserDetailsService{
	public User save(User user);
}
