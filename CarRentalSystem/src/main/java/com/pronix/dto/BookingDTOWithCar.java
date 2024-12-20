package com.pronix.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTOWithCar {
	private Long id;
	private Date pickupDate;
    private Date returnDate;
    private Double totalPrice;
    private String status;
    private CarDTO carDTO;
}
