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
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;


@Controller
public class ModuleController {

	@Autowired
	ModuleService moduleService;

	/**
	 * Method to get Module Result 	
	 * @param model
	 * @param session
	 * @return
	 */
	
	@GetMapping("/module")
	public String module(@ModelAttribute Module module, Model model, HttpSession session) {
		List<Module> modules1 = moduleService.getModules();
		model.addAttribute("modules1", modules1);
		String userCode= (String)session.getAttribute("username");
		if (userCode!=null) {
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", session.getAttribute("username"));
			return "module";
		}
		else
		{
			  return "redirect:" + "./";
			
		}
	}

	/**
	 * Method to Save Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveModule")
	  public String saveModule(@ModelAttribute("module") Module module, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}	
		boolean isModuleExist = moduleService.checkModuleExists(module);
		
		if (isModuleExist) {
			    redirectAttributes.addFlashAttribute("message", "Record already exists !  ");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			    return"redirect:/module";
		}
		else 
		{
			boolean isSeqExist = moduleService.checkModuleSeqExists(module);
			
			if(isSeqExist) {
				redirectAttributes.addFlashAttribute("message", "Sequence No already exists !  ");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			
			    return"redirect:/module";
			
			}else {
				module.setInsertedBy((String) session.getAttribute("USER_NAME"));
				moduleService.addModule(module); 
				session.setAttribute("username",session.getAttribute("username"));
				 redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
				   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				return"redirect:/module";
			}			
		}
	  }
	/**
	 * Method to Edit Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editModule/{id}"})
	public String editModule(@PathVariable("id")String id,  Model model,HttpSession session)
	 { if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		   
			String userCode= (String)session.getAttribute("username");
			if (userCode!=null) {
			Module moduleEdit = moduleService.findModuleById(id);
			model.addAttribute("moduleEdit", moduleEdit);
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", session.getAttribute("username"));
			return "editModule";
		}
			else
			{
				 return "redirect:" + "./";
			}
	}
	/**
	 * Method to Edit Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateModule")
	public String updateModule(@ModelAttribute("moduleupdate") Module m, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
		  m.setUpdateBy((String) session.getAttribute("USER_NAME"));
		  this.moduleService.updateModule(m);
		  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		  redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		  return"redirect:/module";
		
	}
	/**
	 * Method to Delete Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/deleteModule/{id}"})
	public String deleteModule(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
	 { if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		  this.moduleService.removeModule(id);
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
	    session.setAttribute("username",session.getAttribute("username")); 
	    return"redirect:/module";
	}
	
}
