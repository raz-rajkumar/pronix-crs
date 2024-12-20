package com.pronix.service;

import java.util.List;

import com.pronix.dto.BookingDTOWithUserCar;
import com.pronix.entity.Booking;

public interface IBookingService {
	public BookingDTOWithUserCar saveBooking(Booking booking);
	public List<BookingDTOWithUserCar> getAllBookings();
	public BookingDTOWithUserCar getBookingById(Long id);
	public String deleteBooking(Long id);
	public Booking updateBooking(Long id, Booking booking);
//	public List<Booking> findByUserId(Long id);
//	public List<Booking> findByCarId(Long id);
}
