package com.pronix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pronix.dto.BookingDTOWithUserCar;
import com.pronix.dto.CarDTO;
import com.pronix.dto.UserOnlyDTO;
import com.pronix.dto.UserViewDTO;
import com.pronix.entity.Booking;
import com.pronix.entity.Car;
import com.pronix.entity.User;
import com.pronix.repository.BookingRepository;

@Service
public class BookingServiceImpl implements IBookingService {
	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICarService carService;
	
    // Save or update a booking
    public BookingDTOWithUserCar saveBooking(Booking booking) {
    	Booking save = bookingRepository.save(booking);
    	BookingDTOWithUserCar booked=new BookingDTOWithUserCar();
    	booked.setId(save.getId());
    	booked.setPickupDate(save.getPickupDate());
    	booked.setReturnDate(save.getReturnDate());
    	booked.setTotalPrice(save.getTotalPrice());
    	booked.setStatus(save.getStatus());
    	booked.setUserOnlyDTO(getUserDto(save.getUser().getId()));
    	booked.setCarDTO(getCarDto(save.getCar().getId()));
    	return booked;
    }

    // Get all bookings
    public List<BookingDTOWithUserCar> getAllBookings() {
    	List<BookingDTOWithUserCar> bookedList= new ArrayList<BookingDTOWithUserCar>();
    	List<Booking> all = bookingRepository.findAll();
    	for(Booking b:all)
    	{
    		BookingDTOWithUserCar booked=new BookingDTOWithUserCar();
    		booked.setId(b.getId());
        	booked.setPickupDate(b.getPickupDate());
        	booked.setReturnDate(b.getReturnDate());
        	booked.setTotalPrice(b.getTotalPrice());
        	booked.setStatus(b.getStatus());
        	booked.setUserOnlyDTO(getUserDto(b.getUser().getId()));
        	booked.setCarDTO(getCarDto(b.getCar().getId()));
        	bookedList.add(booked);
    	}
        return bookedList;
    }

    // Get booking by ID
    public BookingDTOWithUserCar getBookingById(Long id) {
    	Booking save = bookingRepository.findById(id).get();
    	BookingDTOWithUserCar booked=new BookingDTOWithUserCar();
    	booked.setId(save.getId());
    	booked.setPickupDate(save.getPickupDate());
    	booked.setReturnDate(save.getReturnDate());
    	booked.setTotalPrice(save.getTotalPrice());
    	booked.setStatus(save.getStatus());
    	booked.setUserOnlyDTO(getUserDto(save.getUser().getId()));
    	booked.setCarDTO(getCarDto(save.getCar().getId()));
    	return booked;
    }

    // Delete a booking by ID
    public String deleteBooking(Long id) {
        bookingRepository.deleteById(id);
        return "Booking Deleted";
    }

	@Override
	public Booking updateBooking(Long id, Booking booking) {
		Optional<Booking> book1=bookingRepository.findById(id);
		if(book1.isPresent())
		{
			Booking book2=book1.get();
			if(booking.getPickupDate()!=null)
				book2.setPickupDate(booking.getPickupDate());
			if(booking.getReturnDate()!=null)
				book2.setReturnDate(booking.getReturnDate());
			if(booking.getCar().getId()!=null)
				book2.setCar(booking.getCar());
			
			
			return bookingRepository.save(book2);
			
		}
		return null;
	}
	
	
	public UserOnlyDTO getUserDto(Long id)
	{
		UserOnlyDTO user=new UserOnlyDTO();
		UserViewDTO user1=userService.byId(id);
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		user.setEmail(user1.getEmail());
		user.setId(id);
		return user;
	}
	public CarDTO getCarDto(Long id)
	{
		CarDTO car=new CarDTO();
		Car car1=carService.byId(id);
		car.setId(id);
		car.setMake(car1.getMake());
		car.setModel(car1.getModel());
		car.setYear(car1.getYear());
		return car;
	}
}

