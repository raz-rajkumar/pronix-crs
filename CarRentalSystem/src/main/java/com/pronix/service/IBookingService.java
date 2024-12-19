package com.pronix.service;

import java.util.List;
import java.util.Optional;

import com.pronix.entity.Booking;

public interface IBookingService {
	public Booking saveBooking(Booking booking);
	public List<Booking> getAllBookings();
	public Optional<Booking> getBookingById(Long id);
	public void deleteBooking(Long id);
}
