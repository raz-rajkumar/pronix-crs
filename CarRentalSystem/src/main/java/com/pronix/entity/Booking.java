package com.pronix.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to User
    @JsonBackReference("user-booking")
    private User user;  // A booking is linked to one user

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)  // Foreign key to Car
    @JsonBackReference("car-booking")
    private Car car;  // A booking is linked to one car

    private Date pickupDate;
    private Date returnDate;
    private Double totalPrice;
    private String status;
}
