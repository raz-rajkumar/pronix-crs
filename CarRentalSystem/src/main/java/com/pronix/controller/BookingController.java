package com.pronix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pronix.entity.Booking;
import com.pronix.service.IBookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/booking")
public class BookingController {
	
	
	@Autowired
	IBookingService bookingService;
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/showAll")
	public List<Booking> showAllBookings()
	{
		return bookingService.getAllBookings();
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/bookCar")
	public Booking carBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping("/byId/{id}")
	public Booking bookingById(@PathVariable Long id) {
		return bookingService.getBookingById(id).get();
	}
	
}
