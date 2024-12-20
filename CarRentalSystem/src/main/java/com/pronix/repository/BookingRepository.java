package com.pronix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pronix.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	public List<Booking> findByUserId(Long id);
	public List<Booking> findByCarId(Long id);
}
