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
import com.hrms.model.AboutDept;
import com.hrms.model.MenuModule;
import com.hrms.service.AboutDeptService;
import com.hrms.service.ModuleService;

@Controller
public class AboutDeptController {
	@Autowired private ModuleService moduleService;
	@Autowired private  AboutDeptService aboutDeptService;
	
	@GetMapping("/aboutDept")
	public String aboutDept(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<AboutDept> listAboutDept = aboutDeptService.getAllAboutsDepts();
		model.addAttribute("listAboutDept", listAboutDept);
		
		
		return "aboutDepartment";
	}

@PostMapping("/saveAboutdept")
public String aboutDept(@ModelAttribute("aboutDept") AboutDept aboutDept, Model model,RedirectAttributes redirectAttributes,HttpSession session)
{
	
	aboutDeptService.addAboutDept(aboutDept);
	aboutDept.setInsBy((String) session.getAttribute("USER_NAME"));
	redirectAttributes.addFlashAttribute("message", "Record has been  saved successfully! ");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	return"redirect:/aboutDept";
}



/**
 * Method to Edit About Department 
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/editAboutDept/{id}"})
public String editAboutDept(@PathVariable("id")long id,  Model model,HttpSession session)
 { 

	
	AboutDept aboutDeptEdit = aboutDeptService.findAboutDeptById(id);
	  model.addAttribute("aboutDeptEdit", aboutDeptEdit);

   
    return "editAboutDept";
}
/**
 * Method to Update About Department 
 * @param model
 * @param session
 * @return
 */	
@PostMapping("/updateAboutDept")
public String updateAboutDept(@ModelAttribute("aboutDept")  AboutDept aboutDept, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
 
	   try 
	   {
		   aboutDept.setUpdBy((String) session.getAttribute("USER_NAME"));
		   this.aboutDeptService.updateAboutDept(aboutDept);   
		   redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	} 
	   	catch (Exception e) 
	   	{
	System.out.println("exception "+e);
	}
  	  
	   return"redirect:/aboutDept";
}

/**
 * Method to Delete  About Department 
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/deleteAboutDept/{id}"})
public String deletedepartment(@PathVariable("id")long id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { 
	  this.aboutDeptService.removeAboutDept(id);
	  redirectAttributes.addFlashAttribute("message", " Record has been deleted  successfully! ");
	  redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	    
	    return"redirect:/aboutDept";
}

}
