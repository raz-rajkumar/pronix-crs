package com.pronix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pronix.entity.Car;
import com.pronix.service.ICarService;




@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private ICarService carService;
	
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("showAll")
	public List<Car> showAll()
	{
		List<Car> showAllCars = carService.showAll();
		return showAllCars;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/byId/{id}")
	public Car showById(@PathVariable Long id) {
		return carService.byId(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public String addCar(@RequestBody Car car) {		
		return carService.add(car);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("update/{id}")
	public String updateCar(@PathVariable Long id, @RequestBody Car car) {		
		return carService.update(id, car);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteCar(@PathVariable Long id)
	{
		return carService.delete(id);
	}
	
}
