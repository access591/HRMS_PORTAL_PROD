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
import com.hrms.model.Employee;
import com.hrms.model.Leave;
import com.hrms.model.LeaveGrant;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;

@Controller
public class LeaveGrantRegister {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	LeaveGrantRegisterService leaveGrantRegisterService;
	@GetMapping("/leaveGrant")
	public String leaveGrantRegister(Model model,HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		List<LeaveGrant> listLeaveGrant = leaveGrantRegisterService.getAllLeaveGrants();
		model.addAttribute("listLeaveGrant", listLeaveGrant);
		String userCode = (String) session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "leaveGrant";
		}
	
	@PostMapping("/saveLeaveGrant")
	public String saveLeaveGrant(@ModelAttribute("leaveGrant")LeaveGrant leaveGrant, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		leaveGrant.setInsBy((String) session.getAttribute("USER_NAME"));
		leaveGrantRegisterService.addLeaveGrant(leaveGrant);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));

		 return"redirect:/leaveGrant";

	}
	
	
	
	@GetMapping(value = {"/editLeaveGrant/{id}"})
	  public String editdesignation(@PathVariable("id")String id,  Model model,HttpSession session)
	   { 
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		LeaveGrant leaveGrantEdit = leaveGrantRegisterService.findLeaveGrantById(id);
		  model.addAttribute("leaveGrantEdit", leaveGrantEdit);
	
	      session.setAttribute("username",session.getAttribute("username")); 
	         return "/editLeaveGrant"; 
	  }
	
	
	@PostMapping("/updateLeaveGrant")
	public String updateLeaveGrant(@ModelAttribute("leaveGrant")LeaveGrant leaveGrant, Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	 
	      leaveGrant.setUpdBy((String)session.getAttribute("USER_NAME"));
		  this.leaveGrantRegisterService.updateLeaveGrant(leaveGrant);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		  return"redirect:/leaveGrant";
		
	}
	
	
	@GetMapping(value = {"/deleteLeaveGrant/{id}"})
	public String deleteLeaveGrant(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
	 { if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		  this.leaveGrantRegisterService.removeLeaveGrant(id);
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    session.setAttribute("username",session.getAttribute("username")); 
	    return"redirect:/leaveGrant";
	}



}
