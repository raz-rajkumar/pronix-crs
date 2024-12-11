package com.pronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pronix.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
