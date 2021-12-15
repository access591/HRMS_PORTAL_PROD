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
import com.hrms.model.City;
import com.hrms.model.Insurance;
import com.hrms.model.MenuModule;
import com.hrms.service.CityService;
import com.hrms.service.InsuranceService;
import com.hrms.service.ModuleService;

@Controller
public class InsuranceController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	InsuranceService insuranceService;
	@Autowired
	CityService cityService;
	
	@GetMapping("/insuranceMaster")
	public String insuranceMaster(Model model,HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		List<Insurance> listInsurance = insuranceService.getAllInsurances();
		model.addAttribute("listInsurance", listInsurance);
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		  return "insuranceMaster";
		}
	
	@PostMapping("/saveInsurance")
	public String saveInsurance(@ModelAttribute("insurance") Insurance insurance, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		insurance.setInsBy((String) session.getAttribute("USER_NAME"));
		insuranceService.addInsurance(insurance);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));

		return"redirect:/insuranceMaster";

	}
	@GetMapping(value = {"/editInsurance/{id}"})
	public String editInsurance(@PathVariable("id")String id,  Model model,HttpSession session)
	 { if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	 List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		  Insurance insuranceEdit = insuranceService.findInsuranceById(id);
		  model.addAttribute("insuranceEdit", insuranceEdit);
		  session.setAttribute("imgUtil", new ImageUtil());
	    session.setAttribute("username",session.getAttribute("username")); 
	       return "/editInsurance"; 
	}
	@PostMapping("/updateInsurance")
	public String updateInsurance(@ModelAttribute("updinsurance") Insurance insurance, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		String id=insurance.getInsCode();
	
		insurance.setInsCode(id);
		insurance.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.insuranceService.updateInsurance(insurance);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
		return"redirect:/insuranceMaster";
	}
	
	@GetMapping(value = { "/deleteInsurance/{id}" })
	public String deleteInsurance(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.insuranceService.removeInsurance(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/insuranceMaster";
	}

	
}
