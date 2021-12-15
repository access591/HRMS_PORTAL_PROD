package com.hrms.controller;


import java.util.List;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Bank;
import com.hrms.model.City;
import com.hrms.model.Country;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.service.BankService;
import com.hrms.service.CityService;
import com.hrms.service.CountryService;
import com.hrms.service.ModuleService;
import com.hrms.service.StateService;


@Controller
public class BankController {

	@Autowired
	BankService bankService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;

/**
 * Method to get Bank Result 	
 * @param model
 * @param session
 * @return
 */
@GetMapping("/bankMaster")
public String bankMaster(Model model,HttpSession session) {

	List<Bank> listBank = bankService.getAllBanks();
	model.addAttribute("listBank", listBank);
	String userCode= (String)session.getAttribute("username");
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
	  List<City>cityList =cityService.getAllCities();
	  model.addAttribute("CityList", cityList);
	  List<State> listState = stateService.getAllStates();
	  model.addAttribute("listState", listState);
	  List<Country> listCountry = countryService.getAllCountrys();
	 model.addAttribute("listCountry", listCountry);
	session.setAttribute("imgUtil", new ImageUtil());
	session.setAttribute("username",session.getAttribute("username"));
	return "bankMaster";
	
	}

/**
 * Method to Save Bank Details
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveBank")
public String saveBank(@ModelAttribute("bank") Bank bank, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	bank.setInsBy((String) session.getAttribute("USER_NAME"));
	bankService.addBank(bank);
	List<Bank> listBank = bankService.getAllBanks();
	model.addAttribute("listBank", listBank);
	redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
	return "redirect:/bankMaster";

}

/**
 * Method to Edit Bank Details
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/editBank/{id}"})
public String editbank(@PathVariable("id") String id, Model model, HttpSession session) {

	Bank bankEdit = bankService.findBankById(id);
	model.addAttribute("bankEdit", bankEdit);
	session.setAttribute("username", session.getAttribute("username"));
	return "editBank";
}
/**
 * Method to Update Bank Details
 * @param model
 * @param session
 * @return
 */
@PostMapping("/updateBank")
public String updateBank(@ModelAttribute("bankupdate") Bank bank, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	bank.setUpdBy((String) session.getAttribute("USER_NAME"));
	this.bankService.updateBank(bank);
	   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
	   redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
	return "redirect:/bankMaster";
}
/**
 * Method to Delete Bank Details
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/deleteBank/{id}"})
public String deletebank(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { 
	  
	 this.bankService.removeBank(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	 session.setAttribute("username",session.getAttribute("username")); 
	 return"redirect:/bankMaster";
}


	


}
