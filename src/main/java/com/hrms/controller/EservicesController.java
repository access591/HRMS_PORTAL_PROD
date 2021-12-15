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

import com.hrms.model.Eservices;
import com.hrms.model.MenuModule;

import com.hrms.service.EservicesService;
import com.hrms.service.ModuleService;

@Controller
public class EservicesController {
	
	@Autowired
	ModuleService moduleService;
	@Autowired  EservicesService eservicesService;
	@GetMapping("/eservices")
	public String eservices(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Eservices> listEservices = eservicesService.getAllEservices();
		model.addAttribute("listEservices", listEservices);
		return "eservices";

	}
	
	
	@PostMapping("/saveEservices")
	public String saveEservices(@ModelAttribute("eServices") Eservices eServices, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	
		String insertedBY = (String) session.getAttribute("USER_NAME");
		eServices.setInsBy(insertedBY);
		eservicesService.addEservices(eServices);
		redirectAttributes.addFlashAttribute("message", "Record has been saved  successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/eservices";

	}

/**
 * Method to Edit E Services 
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/editEservices/{id}"})
public String editEservices(@PathVariable("id")long id,  Model model,HttpSession session)
 { 
	Eservices eServicesEdit = eservicesService.findEservicesById(id);
	model.addAttribute("eServicesEdit", eServicesEdit);
    return "editEservices";
}
/**
 * Method to Update E Services 
 * @param model
 * @param session
 * @return
 */	
@PostMapping("/updateEservices")
public String updateEservices(@ModelAttribute("eServices")  Eservices eServices, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
 
	   try 
	   {
		   eServices.setUpdBy((String) session.getAttribute("USER_NAME"));
		   this.eservicesService.updateEservices(eServices);   
		   redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	} 
	   	catch (Exception e) 
	   	{
	System.out.println("exception "+e);
	}
  	  
	   return"redirect:/eservices";
}

/**
 * Method to Delete  E Services 
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/deleteEservices/{id}"})
public String deleteEservices(@PathVariable("id")long id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { 
	  this.eservicesService.removeEservices(id);
	  redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully! ");
	  redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	  return"redirect:/eservices";
}	
	
	
	
	
	
}
