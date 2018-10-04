package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.CarModel;

public interface CarService {
	
	void addCar (CarModel car);
	void deleteCar(long carId);


}
