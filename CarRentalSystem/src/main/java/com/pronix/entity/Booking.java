package com.pronix.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for Booking

    @ManyToOne(fetch = FetchType.LAZY)  // Define Many-to-One relationship with User
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key column in Booking table
    @JsonBackReference("user-booking")
    private User user;  // Foreign key to User entity

    @ManyToOne(fetch = FetchType.LAZY)  // Define Many-to-One relationship with Car
    @JoinColumn(name = "car_id", nullable = false)  // Foreign key column in Booking table
    @JsonBackReference("car-booking")
    private Car car;  // Foreign key to Car entity

    private Date pickupDate;
    private Date returnDate;
    private Double totalPrice;
    private String status;
}