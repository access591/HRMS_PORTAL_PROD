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
import com.hrms.model.MenuModule;
import com.hrms.service.CategoryService;
import com.hrms.service.ModuleService;


@Controller
public class CategoryController {

	
	@Autowired 
	ModuleService moduleService; 


	@Autowired CategoryService categoryService;
	
	
	@GetMapping("/categoryMaster")
	public String categoryMaster(Model model,HttpSession httpSession) {
	
		String userCode = (String) httpSession.getAttribute("username");
		if (httpSession.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory" ,listCategory);
		}
		httpSession.setAttribute("imgUtil", new ImageUtil());
		httpSession.setAttribute("username", userCode);
		return"categoryMaster";
	}
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category ,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		boolean checkCategory = categoryService.chaeckCategoryExistOrNot(category);
		if (checkCategory) {
			redirectAttributes.addFlashAttribute("message", "Category Name Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			 
			  return"redirect:/categoryMaster";
		} else {
			category.setInsBy((String) session.getAttribute("USER_NAME"));
		    categoryService.addCategory(category);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
			  return"redirect:/categoryMaster";
		}
	}

	@GetMapping(value = { "/editCategory/{id}" })
	public String editCategory(@PathVariable("id") String id, Model model, HttpSession session) {


		Category categoryEdit = categoryService.findCategoryByCatId(id);
		model.addAttribute("categoryEdit", categoryEdit);
	

		return "editCategory";
	}

	/**
	 * 
	 * @param upadte Request MappingCategory
	 * @param model
	 * @return
	 */
	@PostMapping("/updateCategory")
	public String updateCategory(@ModelAttribute("category") Category category, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		category.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.categoryService.updateCategory(category);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
		  return"redirect:/categoryMaster";
	}

	/**
	 * 
	 * @param delete  Request mapping Category
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteCategory/{id}" })
	public String deleteCategory(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		this.categoryService.removeCategory(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		  return"redirect:/categoryMaster";
	}

	
	
	
}
