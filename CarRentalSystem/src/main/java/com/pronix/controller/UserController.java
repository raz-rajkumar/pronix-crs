package com.pronix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pronix.config.JwtGeneratorValidator;
import com.pronix.dto.UserDTO;
import com.pronix.entity.User;
import com.pronix.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	JwtGeneratorValidator jwtGenVal;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping("/showAll")
	public List<UserDTO> showAllUsers() {
		return userService.showAll();
	}
	
	@GetMapping("/byId/{id}")
	public User userById(@PathVariable Long id)
	{
		return userService.byId(id);
	}
	
	
	@PostMapping("/registration")
	public String registerUser(@RequestBody User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userService.save(user);
	}
	
	
	@GetMapping("/genToken")
	public String generateJwtToken(@RequestBody User user)
	{
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
	return jwtGenVal.generateToken(authentication);
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
