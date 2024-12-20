package com.pronix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pronix.dto.BookingDTOWithCar;
import com.pronix.dto.UserDTO;
import com.pronix.dto.UserViewDTO;
import com.pronix.entity.Booking;
import com.pronix.entity.User;
import com.pronix.repository.UserRepository;
@Service
public class UserServiceImpl implements IUserService {
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired 
	private BookingServiceImpl bookingService;
	
	@Override
	public List<UserDTO> showAll() {
	    return repo.findAll().stream().map(user -> {
	        UserDTO userDto = new UserDTO();
	        userDto.setId(user.getId());
	        userDto.setFirstName(user.getFirstName());
	        userDto.setLastName(user.getLastName());
	        userDto.setEmail(user.getEmail());
	        return userDto;  
	    }).collect(Collectors.toList());  
	}


	@Override
	public UserViewDTO byId(Long id) {
		
		UserViewDTO userView=new UserViewDTO();
		User user=repo.findById(id).get();
		userView.setId(id);
		userView.setFirstName(user.getFirstName());
		userView.setLastName(user.getLastName());
		userView.setEmail(user.getEmail());
		userView.setBookingDtoWithCars(null);
		
		List<BookingDTOWithCar> withCar=new ArrayList<BookingDTOWithCar>();
		
		List<Booking> bookedList=user.getBookings();
		
		for(Booking b: bookedList)
		{
			BookingDTOWithCar bookingDto=new BookingDTOWithCar();
			bookingDto.setId(b.getId());
			bookingDto.setPickupDate(b.getPickupDate());
			bookingDto.setReturnDate(b.getReturnDate());
			bookingDto.setTotalPrice(b.getTotalPrice());
			bookingDto.setStatus(b.getStatus());
			bookingDto.setCarDTO(bookingService.getCarDto(b.getCar().getId()));
			withCar.add(bookingDto);
		}
		userView.setBookingDtoWithCars(withCar);
		return userView;
	}
	
	
	@Override
	public User findByEmail(String email)
	{
		return repo.findByEmail(email);
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


	@Override
	public ResponseEntity<Object> save(UserDTO userDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
