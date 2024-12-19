package com.pronix.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pronix.config.JwtGeneratorValidator;
import com.pronix.dto.JwtResponse;
import com.pronix.dto.UserDTO;
import com.pronix.entity.User;
import com.pronix.security.service.IDefaultUserService;
import com.pronix.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	IDefaultUserService defaultUser;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	JwtGeneratorValidator jwtGenVal;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/showAll")
	public List<UserDTO> showAllUsers() {
		return userService.showAll();
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/byId/{id}")
	public User userById(@PathVariable Long id)
	{
		return userService.byId(id);
	}
	
	
	@PostMapping("/userRegistration")
	public ResponseEntity<Object> userRegisterUser(@RequestBody UserDTO userDto) {
		User users =  defaultUser.save(userDto);
		if (users.equals(null))
			return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, userDto);
		else
			return generateRespose("User saved successfully : " + users.getId(), HttpStatus.OK, users);
	}
	
	
	@PostMapping("/adminRegistration")
	public ResponseEntity<Object> adminRegisterUser(@RequestBody UserDTO userDto) {
		userDto.setRole("ADMIN");
		User users =  defaultUser.save(userDto);
		if (users.equals(null))
			return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, userDto);
		else
			return generateRespose("User saved successfully : " + users.getId(), HttpStatus.OK, users);
	}
	
	
	@GetMapping("/genToken")
	public JwtResponse generateJwtToken(@RequestBody User user)
	{
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	return new JwtResponse(userService.findByEmail(user.getEmail()),jwtGenVal.generateToken(authentication));
	}
	
	@PutMapping("update/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public String updateUser(@PathVariable Long id, @RequestBody User user)
	{
		return userService.update(id, user);
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id)
	{
		return userService.delete(id);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/welcomeAdmin")
	public String welcome() {
		return "WelcomeAdmin";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/welcomeUser")
	public String welcomeUser() {
		return "WelcomeUSER";
	}
	
	
	
	public ResponseEntity<Object> generateRespose(String message, HttpStatus st, Object responseobj) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("meaasge", message);
		map.put("Status", st.value());
		map.put("data", responseobj);

		return new ResponseEntity<Object>(map, st);
	}
	
}
