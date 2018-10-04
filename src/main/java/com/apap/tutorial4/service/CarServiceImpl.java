
package com.apap.tutorial4.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.repository.CarDb;


@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}
	
	@Override
	public void deleteCar(long carId) {
		// TODO Auto-generated method stub
		carDb.deleteById(carId);
	}
	@Override
	public Optional<CarModel> getCarDetailById(Long id) {
		// TODO Auto-generated method stub
		return carDb.findById(id);
	}

	@Override
	public void editCar(CarModel newCar, Long id) {
		// TODO Auto-generated method stub
		CarModel updatedCar = carDb.getOne(id);
		updatedCar.setAmount(newCar.getAmount());
		updatedCar.setBrand(newCar.getBrand());
		updatedCar.setPrice(newCar.getPrice());
		updatedCar.setType(newCar.getType());
		carDb.save(updatedCar);
	}
}