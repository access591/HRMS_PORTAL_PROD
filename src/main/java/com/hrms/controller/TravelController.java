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
import com.hrms.model.MenuModule;
import com.hrms.model.Travel;
import com.hrms.service.CityService;
import com.hrms.service.ModuleService;
import com.hrms.service.TravelService;

@Controller
public class TravelController {

	

	@Autowired
	private ModuleService moduleService;
	@Autowired
	TravelService travelService;
	@Autowired
	CityService cityService;
/**
 * Request  Mapping Travel Details 
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/travelDetails")
	public String travelDetailsMaster(Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		List<Travel> listTravel = travelService.getAllTravels();
		model.addAttribute("listTravel", listTravel);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		 List<City> cityList =cityService.getAllCities();
		 model.addAttribute("cityMaster", cityList);

		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "travelDetails";
	}
/**
 * 
 * @param  Save record  travel
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveTravel")
	public String saveTravel(@ModelAttribute("travel") Travel travel, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		travel.setInsBy((String) session.getAttribute("USER_NAME"));
		travelService.addTravel(travel);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/travelDetails";
		

	}
	/**
	 * 
	 * @param id find record base on id 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editTravel/{id}"})
	public String editTravel(@PathVariable("id") String id, Model model, HttpSession session) {
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		Travel travelEdit = travelService.findTravelById(id);
		model.addAttribute("travelEdit",travelEdit);
		List<City> cityList =cityService.getAllCities();
		model.addAttribute("cityMaster", cityList);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		
		return "editTravel";
	}
	/**
	 * 
	 * @param update record Travel Detail 
	 * @param model
	 * @return
	 */
	@PostMapping("/updateTravel")
	public String updateTravel(@ModelAttribute("travel")Travel travel, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		travel.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.travelService.updateTravel(travel);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/travelDetails";
	}
/**
 * 
 * @param id Delete Record Travel Details 
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/deleteTravel/{id}" })
	public String deleteTravel(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		this.travelService.removeTravel(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/travelDetails";
	}

}
