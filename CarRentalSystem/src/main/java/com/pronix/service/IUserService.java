package com.pronix.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pronix.dto.UserDTO;
import com.pronix.entity.User;


public interface IUserService {
	public List<UserDTO> showAll();
	public User byId(Long id);
	public User findByEmail(String email);
	public ResponseEntity<Object> save(UserDTO userDto);
	public String update(Long id, User user);
	public String delete(Long id);
	String save(User user);
}
