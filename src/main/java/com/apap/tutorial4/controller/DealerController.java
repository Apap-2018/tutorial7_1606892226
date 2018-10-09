
package com.apap.tutorial4.controller;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;


@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;

	@Autowired
	private CarService carService;

	@RequestMapping("/")
	private String home() {
		return "home";
	}
	@RequestMapping(value ="/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}


	@RequestMapping(value ="/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}

	@RequestMapping(value="/dealer/view", method = RequestMethod.GET)
	private String viewDealer(@RequestParam(value = "dealerId", required = true) Long dealerId, Model model) {
		DealerModel listDealer = dealerService.getDealerDetailById(dealerId).get();
		String alamat = listDealer.getAlamat();
		String no_telp = listDealer.getNoTelp();
		Long id = listDealer.getId();
		List <CarModel> car_list = listDealer.getListCar();
		Collections.sort(car_list, new SortCar());

		model.addAttribute("dealer",listDealer);
		model.addAttribute("dealerID", id);
		model.addAttribute("alamat", alamat);
		model.addAttribute("no_telp", no_telp);
		model.addAttribute("car_list", car_list);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/delete", method = RequestMethod.GET)
	private String deleteDealer(@RequestParam(value = "dealerId") Long dealerId) {
		dealerService.deleteDealer(dealerId);
		return "delete-dealer";
	}
	
	@RequestMapping("/dealer/view-all")
	private String home(Model model) {
		model.addAttribute("dealer_list", dealerService.getAllDealer());
		return "view-dealer-all";
	}
	
	class SortCar implements Comparator <CarModel>{
		  public int compare(CarModel a, CarModel b) {
		   return (int) (a.getPrice() - b.getPrice());
		  }
	}
		 
}
	





