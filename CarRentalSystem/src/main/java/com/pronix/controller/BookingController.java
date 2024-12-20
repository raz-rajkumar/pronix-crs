package com.pronix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pronix.dto.BookingDTOWithUserCar;
import com.pronix.entity.Booking;
import com.pronix.service.IBookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/booking")
public class BookingController {
	
	
	@Autowired
	IBookingService bookingService;
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/showAll")
	public List<BookingDTOWithUserCar> showAllBookings()
	{
		return bookingService.getAllBookings();
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/bookCar")
	public BookingDTOWithUserCar carBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/byId/{id}")
	public BookingDTOWithUserCar bookingById(@PathVariable Long id) {
		return bookingService.getBookingById(id);
	}
	
	@PutMapping("/update/{id}")
	public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
		Booking book=bookingService.updateBooking(id, booking);
		if(book!=null)
			return book;
		
		return booking;
	}
	
}
