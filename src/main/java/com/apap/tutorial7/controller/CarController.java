package com.apap.tutorial7.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	@Autowired
	private DealerService dealerService;
	
	
//	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
//	private String add(@PathVariable (value = "dealerId") Long dealerId, Model model) {
//		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
//		ArrayList<CarModel> listCar = new ArrayList<CarModel>();
//		listCar.add(new CarModel());
//		dealer.setListCar(listCar);
//
//		model.addAttribute("dealer",dealer);
//		return "addCar";
//
//	}
//
//	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"save"})
//	private String addCarSubmit(@PathVariable (value = "dealerId") Long dealerId, @ModelAttribute DealerModel dealer, Model model) {
//		DealerModel dealerbyId = dealerService.getDealerDetailById(dealer.getId()).get();
//		for (CarModel car: dealer.getListCar()) {
//			car.setDealer(dealerbyId);
//			carService.addCar(car);
//		}
//
//
//		return "add";
//
//	}
//	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"addRow"})
//	public String addRow(@ModelAttribute DealerModel dealer, BindingResult bindingResult, Model model) {
//		if (dealer.getListCar() == null) {
//            dealer.setListCar(new ArrayList<CarModel>());
//        }
//		dealer.getListCar().add(new CarModel());
//
//		model.addAttribute("dealer", dealer);
//		return "addCar";
//	}
//
//	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
//	public String removeRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
//	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//	    dealer.getListCar().remove(rowId.intValue());
//
//	    model.addAttribute("dealer", dealer);
//	    return "addCar";
//	}
//
//
//
//
//	@RequestMapping(value = "/car/delete", method = RequestMethod.POST)
//	private String delete(@ModelAttribute DealerModel dealer, Model model) {
//		for (CarModel car : dealer.getListCar()) {
//			carService.deleteCar(car.getId());
//		}
//		return "delete" ;
//		}
//
//	@RequestMapping(value = "/car/edit/{dealerId}/{carId}", method = RequestMethod.GET)
//	private String updateCar(
//			@PathVariable(value = "dealerId") Long dealerId,
//			@PathVariable(value = "carId") Long carId,
//			Model model
//			) {
//		CarModel car = carService.getCarDetailById(carId).get();
//
//		String brand = car.getBrand();
//		String type = car.getType();
//		Long price = car.getPrice();
//		Integer amount = car.getAmount();
//
//		model.addAttribute("dealerId", dealerId);
//		model.addAttribute("carId", carId);
//		model.addAttribute("brand", brand);
//		model.addAttribute("type", type);
//		model.addAttribute("price", price);
//		model.addAttribute("amount", amount);
//		return "update-car";
//	}
//
//	@RequestMapping(value = "/car/edit/{dealerId}/{carId}", method = RequestMethod.POST)
//	private String updateCar(
//			@PathVariable(value = "dealerId") Long dealerId,
//			@PathVariable(value = "carId") Long carId,
//			@ModelAttribute CarModel mobilTemp
//			) {
//		carService.editCar(mobilTemp, carId);
//		return "update" ;
//	}

	@DeleteMapping(value = "/delete/{carId}")
	private String deleteCar(@PathVariable("carId") long carId, Model model) {
		carService.deleteCar(carId);
		return "Success hapus car";
	}

	@PostMapping(value = "/add")
	private String addCarSubmit(@RequestBody CarModel car) {
		carService.addCar(car);
		return "Success tambah car";
	}

	@GetMapping(value = "/{id}")
	private CarModel viewCar(@PathVariable("id") long id) {
		CarModel car = carService.getCarDetailById(id).get();
		return car;
	}

	@GetMapping(value = "/all")
	private List<CarModel> viewAllCar() {
		List<CarModel> listCar = carService.getAll();
		for (CarModel car: listCar) {
			car.setDealer(null);
		}
		return listCar;
	}

	@PutMapping(value = "{id}")
	private String updateCarSubmit(
			@PathVariable(value = "id") long id,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "amount", required = false) String amount,
			@RequestParam(value = "dealerID", required = false) String dealerID
	){
		CarModel car = carService.getCarDetailById(id).get();
		if (car == null) {
			return "Couldn't find your car";
		}
		if (!(brand == null)) {
			car.setBrand(brand);
		}
		if (!(type == null)) {
			car.setType(type);
		}
		if (!(price == null)) {
			car.setPrice(Long.valueOf(price));
		}
		if (!(amount == null)) {
			car.setAmount(Integer.parseInt(amount));
		}
		if (!(dealerID == null)) {
			DealerModel dealer = dealerService.getDealerDetailById(Long.valueOf(dealerID)).get();
			car.setDealer(dealer);
		}
		carService.editCar(car, id);
		return "car update success";
	}


}