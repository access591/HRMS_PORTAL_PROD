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


import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;

@Controller
public class PathDirectoryController {
	@Autowired private ModuleService moduleService;
	@Autowired private PathDirectoryServices pathDirectoryServices;
	@GetMapping("/pathDirectory")
	public String pathDirectory(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  	List<PathDirectory> listPathDirectory= pathDirectoryServices.getAllPathDirectory();
			model.addAttribute("listPathDirectory",listPathDirectory);
		return "pathDirectory";
	}
	
	
	
	@PostMapping("/savePathDirectory")
	public String savePathDirectory(@ModelAttribute("pathDirectory") PathDirectory pathDirectory, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	
		String insertedBY = (String) session.getAttribute("USER_NAME");
		pathDirectory.setInsBy(insertedBY);
		pathDirectoryServices.addPathDirectory(pathDirectory);
		   redirectAttributes.addFlashAttribute("message", "Record has been saved successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/pathDirectory";

	}
	
	@GetMapping(value = {"/editPathDirectory/{id}"})
	public String editPathDirectory(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		PathDirectory pathDirectoryEdit = pathDirectoryServices.findPathDirectoryById(id);
		model.addAttribute("pathDirectoryEdit", pathDirectoryEdit);
	    return "editPathDirectory";
	}
	/**
	 * Method to Update Path Directory
	 * @param model
	 * @param session
	 * @return
	 */	
	@PostMapping("/updatePathDirectory")
	public String updatePathDirectory(@ModelAttribute("pathDirectory")  PathDirectory pathDirectory, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
	 
		   try 
		   {
			   pathDirectory.setUpdBy((String) session.getAttribute("USER_NAME"));
			   this.pathDirectoryServices.updatePathDirectory(pathDirectory);   
			   redirectAttributes.addFlashAttribute("message", "Record   has been updated successfully! ");
			   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
	  	  
		   return"redirect:/pathDirectory";
	}
	
	
	
	
	@GetMapping(value = {"/deletePathDirectory/{id}"})
	public String deletePathDirectory(@PathVariable("id")long id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
	 { 
		  this.pathDirectoryServices.removePathDirectory(id);
		  redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully! ");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		    
		    return"redirect:/pathDirectory";
	}	
		
		
		
	
	

}
