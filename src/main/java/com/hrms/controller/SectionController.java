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
import com.hrms.model.Section;
import com.hrms.service.ModuleService;
import com.hrms.service.SectionService;
@Controller
public class SectionController {

	@Autowired
	SectionService sectionService;
	@Autowired
	private ModuleService moduleService;
	
	/**
	 * 
	 * Request mapping  section list data
	 * 
	 * @param model
	 * @param session
	 * @return
	 */	
@GetMapping("/sectionMaster")
public String sectionMaster(Model model,HttpSession session) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	List<Section> listSection = sectionService.getAllSections();
	model.addAttribute("listSection", listSection);
	String userCode= (String)session.getAttribute("username");
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
   	if (modules != null) {
   		model.addAttribute("modules", modules);
   	}
	session.setAttribute("imgUtil", new ImageUtil());
	session.setAttribute("username",session.getAttribute("username"));
	 return "sectionMaster";
	}

/**
 * Request Mapping save section Master
 * 
 * @param holiday
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveSection")
	public String saveSection(@ModelAttribute("section") Section section, Model model,RedirectAttributes redirectAttributes, HttpSession session) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	boolean isModuleExist = sectionService.checkSectionExists(section);	
	
	if (isModuleExist) {
		redirectAttributes.addFlashAttribute("message", "Section Code Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return"redirect:/sectionMaster";
	  
	}
	
	else{
			section.setInsBy((String) session.getAttribute("USER_NAME"));
			sectionService.addSection(section);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} 
	  return"redirect:/sectionMaster";

	}	
/**
 * Request Mapping fetching Id Base edit Section data
 * 
 * @param id
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = { "/editSection/{id}" })
public String editsection(@PathVariable("id") String id, Model model, HttpSession session) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	
	Section sectionEdit = sectionService.findSectionById(id);
	model.addAttribute("sectionEdit", sectionEdit);
	session.setAttribute("imgUtil", new ImageUtil());
	return "editSection";
}
/**
 * Request Mapping Update Section data
 * 
 * @param
 * @param model
 * @return
 */
@PostMapping("/updateSection")
public String updateSection(@ModelAttribute("sectupdate") Section d, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	
	d.setUpdBy((String) session.getAttribute("USER_NAME"));
	this.sectionService.updateSection(d);
	   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
	   redirectAttributes.addFlashAttribute("alertClass", "alert-success");   
	 return"redirect:/sectionMaster";
}
/**
 * 
 * @param Request Mapping Delete By Id Section Data
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = { "/deleteSection/{id}" })
public String deletesection(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	this.sectionService.removeSection(id);
	redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
	session.setAttribute("username", session.getAttribute("username"));
	 return"redirect:/sectionMaster";
}

}
