package com.pronix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pronix.entity.Car;
import com.pronix.repository.CarRepository;
@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	private CarRepository repo;
	
	@Override
	public List<Car> showAll() {
		return repo.findAll();
	}

	@Override
	public Car byId(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public String add(Car car) {
		Car car1=repo.save(car);
		if(car1!=null)
			return "Car added successfully";
		else
			return "Failed to add Car";
	}

	@Override
	public String delete(Long id) {
		Optional<Car> car1=repo.findById(id);
		if(car1.isPresent())
		{
			repo.deleteById(id);
			return "Car deleted successfully";
		}
		return "Car Deletion failed";
	}

	@Override
	public String update(Long id, Car car) {
		Optional<Car> car1=repo.findById(id);
		if(car1.isPresent())
		{
			Car car2=car1.get();
			car2.setPricePerDay(car.getPricePerDay());;
			car2.setAvailabilityStatus(car.getAvailabilityStatus());
			repo.save(car2);
			return "Car Updated Successfully";
		}
		return "Car Updation Failed";
	}

}
