package com.pronix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlyDTO {
	private Long id; // Primary key for User
	private String firstName;
	private String lastName;
	private String email;
}

