package com.pronix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pronix.entity.User;
import com.pronix.service.UserServiceImpl;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/showAll")
	public List<User> showAllUsers() {
		return userService.showAll();
	}
	
	@GetMapping("/byId/{id}")
	public User userById(@PathVariable Long id)
	{
		return userService.byId(id);
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody User user) {
		return userService.save(user);
	}
	
	
	@PutMapping("update/{id}")
	public String updateUser(@PathVariable Long id, @RequestBody User user)
	{
		return userService.update(id, user);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id)
	{
		return userService.delete(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
