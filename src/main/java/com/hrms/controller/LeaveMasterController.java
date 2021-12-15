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
import com.hrms.model.Leave;
import com.hrms.model.MenuModule;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;


@Controller
public class LeaveMasterController {

	@Autowired
	LeaveService leaveService;
	@Autowired
	ModuleService moduleService;


	/**
	 * 
	 * Request mapping LeaveMaster list data 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/leaveMaster")
public String leaveMaster(Model model,HttpSession session) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	List<Leave> listLeave = leaveService.getAllLeaves();
	model.addAttribute("listLeave", listLeave);
	String userCode= (String)session.getAttribute("username");
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
	session.setAttribute("imgUtil", new ImageUtil());
	session.setAttribute("username",session.getAttribute("username"));
	 return "leaveMaster";
	}

/**
 * Request Mapping save Leave Master
 * @param holiday
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveLeave")
public String saveLeave(@ModelAttribute("leave") Leave leave, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	leave.setInsBy((String) session.getAttribute("USER_NAME"));
	leaveService.addLeave(leave);
	redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
	
	return "redirect:/leaveMaster";

}

/**
 * Request Mapping fetching  Id Base edit Leave Data 
 * @param id
 * @param model
 * @param session
 * @return
 */
  @GetMapping(value = {"/editLeave/{id}"})
  public String editleave(@PathVariable("id")String id,  Model model,HttpSession session)
	{
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		Leave leaveEdit = leaveService.findLeaveById(id);
		model.addAttribute("leaveEdit", leaveEdit);

		session.setAttribute("username", session.getAttribute("username"));
		return "editLeave";
	}
  /**
	 * Request Mapping Update Leave data 
	 * @param 
	 * @param model
	 * @return
	 */
  @PostMapping("/updateLeave")
  public String updateLeave(@ModelAttribute("leaveupdate") Leave d, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	  if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	  d.setUpdBy((String) session.getAttribute("USER_NAME"));
	  this.leaveService.updateLeave(d);
	   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
	   redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
	  return "redirect:/leaveMaster"; 
	 

  }
  /**
	 * 
	 * @param  Request Mapping  Delete By Id Leave Data
	 * @param model
	 * @param session
	 * @return
	 */
  @GetMapping(value = {"/deleteLeave/{id}"})
  public String deleteleave(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
   { 
	  this.leaveService.removeLeave(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		   

      session.setAttribute("username",session.getAttribute("username")); 
      return "redirect:/leaveMaster";
      

  }


}
