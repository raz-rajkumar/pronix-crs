package com.pronix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // Primary key for Car
    private String make;
    private String model;
    private Integer year;
    private String category;
    private Double pricePerDay;
    private String availabilityStatus;

    @OneToMany(mappedBy = "car")  // One car can have multiple bookings
    private List<Booking> bookings;  // List of bookings associated with the car
}
