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

import com.hrms.model.ImageGallery;

import com.hrms.model.MenuModule;
import com.hrms.service.ImageGalleryService;
import com.hrms.service.ModuleService;

@Controller
public class ImageGalleryController {
	
	@Autowired ModuleService  moduleService;
	@Autowired  ImageGalleryService imageGalleryService;
	
	@GetMapping("/imageGallery")
	public String acts(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  	List<ImageGallery> listImageGallery= imageGalleryService.getAllImageGallery();
			model.addAttribute("listImageGallery",listImageGallery);
		return "imageGallery";
	}

	
	
	
	@PostMapping("/saveImageGallery")
	public String saveEservices(@ModelAttribute("imageGallery") ImageGallery imageGallery, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	
		String insertedBY = (String) session.getAttribute("USER_NAME");
		
		imageGallery.setInsBy(insertedBY);
		imageGalleryService.addImageGallery(imageGallery);
		 redirectAttributes.addFlashAttribute("message", "Record  has been saved  successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/imageGallery";

	}
	
	/**
	 * Method to Edit Image Gallery
	 * @param model
	 * @param session
	 * @return
	 */	
	@GetMapping(value = {"/editImageGallery/{id}"})
	public String editImageGallery(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		ImageGallery imageGalleryEdit = imageGalleryService.findImageGalleryById(id);
		model.addAttribute("imageGalleryEdit", imageGalleryEdit);
	    return "editimageGallery";
	}
	/**
	 * Method to Update Image Gallery
	 * @param model
	 * @param session
	 * @return
	 */	
	@PostMapping("/updateImageGallery")
	public String updateImageGallery(@ModelAttribute("imageGallery")  ImageGallery imageGallery, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
	 
		   try 
		   {
			   imageGallery.setUpdBy((String) session.getAttribute("USER_NAME"));
			   this.imageGalleryService.updateImageGallery(imageGallery);   
			   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
	  	  
		   return"redirect:/imageGallery";
	}

	/**
	 * Method to Delete Image Gallery
	 * @param model
	 * @param session
	 * @return
	 */	
	@GetMapping(value = {"/deleteImageGallery/{id}"})
	public String deleteImageGallery(@PathVariable("id")long id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
	 { 
		  this.imageGalleryService.removeImageGallery(id);
		  redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully! ");
		  redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		  return"redirect:/imageGallery";
	}	
		
		
		

}
