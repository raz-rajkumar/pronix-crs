package com.pronix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pronix.dto.UserDTO;
import com.pronix.entity.User;
import com.pronix.repository.UserRepository;
@Service
public class UserServiceImpl implements IUserService {
	
	
	@Autowired
	private UserRepository repo;

	@Override
	public List<UserDTO> showAll() {
		
		
		return repo.findAll().parallelStream().map(user->{
			UserDTO userDto=new UserDTO();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
		});
	}

	@Override
	public User byId(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public String save(User user) {
		User user1=repo.save(user);
		if(user1!=null)
			return "User added successfully";
		else
			return "Failed to add user";
	}

	@Override
	public String update(Long id, User user) {
		Optional<User> user1=repo.findById(id);
		if(user1.isPresent())
		{
			User user2=user1.get();
			user2.setFirstName(user.getFirstName());
			user2.setLastName(user.getLastName());
			user2.setEmail(user.getEmail());
			repo.save(user2);
			return "User Updated Successfully";
		}
		return "User Updation Failed";
	}

	@Override
	public String delete(Long id) {
		Optional<User> user1=repo.findById(id);
		if(user1.isPresent())
		{
			repo.deleteById(id);
			return "User deleted successfully";
		}
		return "User Deletion failed";
	}

}
