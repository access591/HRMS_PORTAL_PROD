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
import com.hrms.model.Category;
import com.hrms.model.Designation;
import com.hrms.model.MenuModule;
import com.hrms.service.CategoryService;
import com.hrms.service.DesignationService;
import com.hrms.service.ModuleService;


@Controller
public class DesignationController {
	
	@Autowired
	DesignationService designationService;
	@Autowired
	private ModuleService moduleService;

	@Autowired 
	CategoryService categoryService;
	/**
	 * 
	 * @param Request mapping of list Designation data
	 * @param session
	 * @return
	 */
	@GetMapping("/designationMaster")
	public String designationMaster(Model model, HttpSession session) {
	
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		List<Category> listCategory = categoryService.getActiveCategory();
		model.addAttribute("listCategory" ,listCategory);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "designationMaster";
	}

	/**
	 * 
	 * @param Add                Request mapping Designation
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/saveDesignation")
	public String saveDesignation(@ModelAttribute("designation")Designation designation, Model model,
			RedirectAttributes redirectAttributes,HttpSession session) {
	    	
		designation.setInsBy((String) session.getAttribute("USER_NAME"));
		boolean isModuleExist = designationService.checkDesignationExists(designation);

		if (isModuleExist) {
			redirectAttributes.addFlashAttribute("message", "Designation already exist!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			  return"redirect:/designationMaster";
			 
		} else {
			designationService.addDesignation(designation);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			List<Designation> listDesignation = designationService.getAllDesignations();
			model.addAttribute("listDesignation", listDesignation);
			session.setAttribute("imgUtil", new ImageUtil());
		}
		  return"redirect:/designationMaster";

	}

	/**
	 * 
	 * @param Request Mapping Of edit Designation
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editDesignation/{id}" })
	public String editdesignation(@PathVariable("id") String id, Model model, HttpSession session) {
	
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory" ,listCategory);
		Designation designationEdit = designationService.findDesignationById(id);
		model.addAttribute("designationEdit", designationEdit);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "editDesignation";
	}

	/**
	 * 
	 * @param upadte Request Mapping Designation
	 * @param model
	 * @return
	 */
	@PostMapping("/updateDesignation")
	public String updateDesignation(@ModelAttribute("desiupdate")Designation designation, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully !");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		designation.setUpdBy((String) session.getAttribute("USER_NAME"));
		
		this.designationService.updateDesignation(designation);

		  return"redirect:/designationMaster";
	}

	/**
	 * 
	 * @param delete  Request mapping Designation
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteDesignation/{id}" })
	public String deletedesignation(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		session.setAttribute("imgUtil", new ImageUtil());
		this.designationService.removeDesignation(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		  return"redirect:/designationMaster";
	}

}
