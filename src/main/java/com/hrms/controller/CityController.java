package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.City;
import com.hrms.model.Country;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.service.CityService;
import com.hrms.service.CountryService;
import com.hrms.service.ModuleService;

import com.hrms.service.StateService;


@Controller
public class CityController {
	
	@Autowired
	CountryService countryService;
	@Autowired
	StateService stateService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	CityService cityService;

/**
 * 
 * Request mapping City Master 
 * @param model
 * @param session
 * @return
 */
	@RequestMapping(value="/cityMaster")
    public String productsRedirect(HttpServletRequest request, Model model, HttpSession
    		  session){
		String userCode = (String) session.getAttribute("username"); 
		
		if (userCode!=null) {
			session.setAttribute("imgUtil", new ImageUtil());
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			List<Country> listCountry = countryService.getActiveCountrys();
			model.addAttribute("listCountry", listCountry);
			List<State> listState = stateService.getActiveStates();
			model.addAttribute("listState", listState);
			List<City> cityList =cityService.getAllCities();
			model.addAttribute("cityList",cityList);
			return "cityMaster";
		}
		else
		{
    return "redirect:" + "./";
		}
    }
  

	/**
	 * save Cities  request mapping city master 
	 * @param city
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") City city, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		
	
		city.setInsBy((String) session.getAttribute("USER_NAME"));
		session.setAttribute("imgUtil", new ImageUtil());
		cityService.addCity(city);
		List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		session.setAttribute("username", session.getAttribute("username"));
		 redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return"redirect:/cityMaster";

	}

	/**
	 * Get Single Record And Edit Record 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editCity/{id}" })
	public String editCity(@PathVariable("id") String id, Model model, HttpSession session) {
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);
		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);
		City cityEdit = cityService.findCityById(id);
		model.addAttribute("cityEdit", cityEdit);
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "editCity";
	}
	/**
	 * Request Mapping  update City 
	 * @param c
	 * @param model
	 * @param session
	 * @return
	 */
	
	@PostMapping("/updateCity")
	public String updateCity(@ModelAttribute("city") City city, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		city.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.cityService.updateCity(city);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/cityMaster";
	}
	
	/**
	 * Delete City record 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteCity/{id}" })
	public String deleteCity(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		
		try {
			this.cityService.removeCity(id);
			session.setAttribute("username", session.getAttribute("username"));
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
			return"redirect:/cityMaster";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Mapping Other pages !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			e.printStackTrace();
		}
		return"redirect:/cityMaster";
	}
	
	
}
