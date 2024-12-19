package com.pronix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pronix.entity.Booking;
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
    public Booking saveBooking(Booking booking) {
//    	booking.setUser(userService.byId(booking.getUser().getId()));
//    	booking.setCar(carService.byId(booking.getCar().getId()));
    	Booking save = bookingRepository.save(booking);
//    	save.setUser(userService.byId(booking.getUser().getId()));
//    	save.setCar(carService.byId(booking.getCar().getId()));
    	return save;
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Delete a booking by ID
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
