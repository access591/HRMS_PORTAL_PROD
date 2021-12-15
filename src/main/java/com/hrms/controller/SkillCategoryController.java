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
import com.hrms.model.SkillCategory;
import com.hrms.service.ModuleService;

import com.hrms.service.SkillCategoryService;
@Controller
public class SkillCategoryController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	SkillCategoryService skillCategoryService;

	/**
	 * Request Mapping Skill Cat Master 
	 * @param model
	 * @param session
	 * @return
	 */

	@GetMapping("/skillCategoryMaster")
	public String skillCategoryMaster(Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<SkillCategory> listSkillCategory = skillCategoryService.getAllSkillCategorys();
		model.addAttribute("listSkillCategory", listSkillCategory);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "skillCategoryMaster";
				}
/**
 * 
 * @param save Skill Category
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveSkillCategory")
	public String saveCity(@ModelAttribute("skillCategory") SkillCategory sc, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		sc.setInsBy((String) session.getAttribute("USER_NAME"));
		skillCategoryService.addSkillCategory(sc);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/skillCategoryMaster";
		
	}	
	
	/**
	 * 
	 * @param id find and set value update page 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editSkillCategory/{id}" })
	public String editSkillCategory(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
		
		SkillCategory skillCategoryEdit = skillCategoryService.findSkillCategoryById(id);
		model.addAttribute("skillCategoryEdit", skillCategoryEdit);
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "editSkillCategory";
	}
	/**
	 * 
	 * @param update Skill Category
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateSkillCategory")
	public String updateCity(@ModelAttribute("skillCategory") SkillCategory sc, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		sc.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.skillCategoryService.updateSkillCategory(sc);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
		return"redirect:/skillCategoryMaster";
	}
	
	/**
	 * delete skill cat.
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteSkillCategory/{id}" })
	public String deleteSkillCategory(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		this.skillCategoryService.removeSkillCategory(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/skillCategoryMaster";
		
	}

	
	
}
