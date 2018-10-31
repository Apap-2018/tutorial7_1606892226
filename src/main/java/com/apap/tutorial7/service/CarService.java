package com.apap.tutorial7.service;

import java.util.*;

import com.apap.tutorial7.model.CarModel;

public interface CarService {
	
	void addCar (CarModel car);
	void deleteCar(long carId);
	Optional<CarModel> getCarDetailById(Long id);
	void editCar(CarModel newCar, Long id);
	List<CarModel> getAll();




}
