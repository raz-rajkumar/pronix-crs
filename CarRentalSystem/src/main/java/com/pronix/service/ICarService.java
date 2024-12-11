package com.pronix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pronix.entity.Car;

@Service
public interface ICarService {
	public List<Car> showAll();
	public Car byId(Long id);
	public String add(Car car);
	public String delete(Long id);
	public String update(Long id, Car car);
}
