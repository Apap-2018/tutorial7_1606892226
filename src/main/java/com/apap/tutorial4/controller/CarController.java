package com.apap.tutorial4.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	@Autowired
	private DealerService dealerService;
	
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable (value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> listCar = new ArrayList<CarModel>();
		listCar.add(new CarModel());
		dealer.setListCar(listCar);
		
		model.addAttribute("dealer",dealer);
		return "addCar";
		
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"save"})
	private String addCarSubmit(@PathVariable (value = "dealerId") Long dealerId, @ModelAttribute DealerModel dealer, Model model) {
		DealerModel dealerbyId = dealerService.getDealerDetailById(dealer.getId()).get();
		for (CarModel car: dealer.getListCar()) {
			car.setDealer(dealerbyId);
			carService.addCar(car);
		}
		
		
		return "add";
		
	}
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"addRow"})
	public String addRow(@ModelAttribute DealerModel dealer, BindingResult bindingResult, Model model) {
		if (dealer.getListCar() == null) {
            dealer.setListCar(new ArrayList<CarModel>());
        }
		dealer.getListCar().add(new CarModel());
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    dealer.getListCar().remove(rowId.intValue());
	    
	    model.addAttribute("dealer", dealer);
	    return "addCar";
	}
	
	

	
	@RequestMapping(value = "/car/delete", method = RequestMethod.POST)
	private String delete(@ModelAttribute DealerModel dealer, Model model) {
		for (CarModel car : dealer.getListCar()) {
			carService.deleteCar(car.getId());
		}
		return "delete" ;
		}

	@RequestMapping(value = "/car/edit/{dealerId}/{carId}", method = RequestMethod.GET)
	private String updateCar(
			@PathVariable(value = "dealerId") Long dealerId,
			@PathVariable(value = "carId") Long carId,
			Model model
			) {
		CarModel car = carService.getCarDetailById(carId).get();
		
		String brand = car.getBrand();
		String type = car.getType();
		Long price = car.getPrice();
		Integer amount = car.getAmount();

		model.addAttribute("dealerId", dealerId);
		model.addAttribute("carId", carId);
		model.addAttribute("brand", brand);
		model.addAttribute("type", type);
		model.addAttribute("price", price);
		model.addAttribute("amount", amount);
		return "update-car";
	}
	
	@RequestMapping(value = "/car/edit/{dealerId}/{carId}", method = RequestMethod.POST)
	private String updateCar(
			@PathVariable(value = "dealerId") Long dealerId,
			@PathVariable(value = "carId") Long carId,
			@ModelAttribute CarModel mobilTemp
			) {
		carService.editCar(mobilTemp, carId);
		return "update" ;
	}

}