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
import com.hrms.model.Country;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.CountryService;

@Controller
public class CountryController {
	
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	CountryService countryService;

	/**
	 * Request mapping country master page
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/countryMaster")
	public String countryMaster(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		if (userCode!=null) {
			session.setAttribute("imgUtil", new ImageUtil());
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			List<Country> listCountry = countryService.getAllCountrys();
			model.addAttribute("listCountry", listCountry);
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", session.getAttribute("username"));
			return "countryMaster";
		}
		else
		{
			  return "redirect:" + "./";
		}
	}

	/**
	 * 
	 * @param save  country 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveCountry")
	public String saveCountry(@ModelAttribute("country") Country country, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		
	
		country.setInsBy((String) session.getAttribute("USER_NAME"));
		countryService.addCountry(country);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);

		return"redirect:/countryMaster";

	}
	/**
	 * 
	 * @param id edit record base on id 
	 * @param model
	 * @param session
	 * @return
	 */
	
	@GetMapping(value = { "/editCountry/{id}" })
	public String editCountry(@PathVariable("id") String id, Model model, HttpSession session) {

	
		Country countryEdit = countryService.findCountryById(id);
		model.addAttribute("countryEdit", countryEdit);
	

		return "editCountry";
	}

	/**
	 * 
	 * @param upadte record request mapping 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateCountry")
	public String updateCountry(@ModelAttribute("country") Country country, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		country.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.countryService.updateCountry(country);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/countryMaster";
	}
	
	/**
	 * Delete record base on id 
	 * @param id 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteCountry/{id}" })
	public String deleteCountry(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		this.countryService.removeCountry(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		   
		return"redirect:/countryMaster";
	}
}
