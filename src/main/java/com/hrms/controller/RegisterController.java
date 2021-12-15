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

import com.hrms.model.Register;
import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.RegisterService;
@Controller
public class RegisterController {
	
	
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	RegisterService registerService;

/**
 * Request Mapping Register Master
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/registerMaster")
	public String registerMaster(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Register> listRegister = registerService.getAllRegisters();
		model.addAttribute("listRegister", listRegister);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));

		return  "registerMaster";
	}
/**
 * 
 * @param  Save register Master 
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveRegister")
	public String saveRegister(@ModelAttribute("register") Register register, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		boolean isRegisterExist = registerService.checkRegisterExists(register);
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		if (isRegisterExist) {
			redirectAttributes.addFlashAttribute("message", "Register Name Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			
			  return"redirect:/registerMaster";
		} else {
	
			register.setInsBy((String) session.getAttribute("USER_NAME"));
			registerService.addRegister(register);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		}
		  return"redirect:/registerMaster";

	}
/**
 *  
 * @param id  find Record and Update
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/editRegister/{id}" })
	public String editRegister(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	

		Register registerEdit = registerService.findRegisterById(id);
		model.addAttribute("registerEdit", registerEdit);
		session.setAttribute("imgUtil", new ImageUtil());
		return "editRegister";
	}
/**
 * Update Register Master 
 * @param r
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/updateRegister")
	public String updateRegister(@ModelAttribute("register") Register r, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	
		r.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.registerService.updateRegister(r);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/registerMaster";
	}
/**
 * Delete Register Master 
 * @param id
 * @param model
 * @param session
 * @return
 */
	
	@GetMapping(value = { "/deleteRegister/{id}" })
	public String deleteRegister(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.registerService.removeRegister(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/registerMaster";
	}

}
