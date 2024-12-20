package com.pronix.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private List<BookingDTOWithCar> bookingDtoWithCars;
}
