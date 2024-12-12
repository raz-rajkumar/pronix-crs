package com.pronix.service;

import java.util.List;

import com.pronix.dto.UserDTO;
import com.pronix.entity.User;


public interface IUserService {
	public List<UserDTO> showAll();
	public User byId(Long id);
	public String save(User user);
	public String update(Long id, User user);
	public String delete(Long id);
}
