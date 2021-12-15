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

import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.ActivityService;
import com.hrms.ImageUtil;
import com.hrms.model.Activities;
@Controller
public class ActivityController {

	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ActivityService activityService;

	/**
	 * Request Mapping Activity Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/actMaster")
	public String actMaster(Model model, HttpSession session) {
		List<Activities> listActivities = activityService.getAllActivitys();
		model.addAttribute("listActivities", listActivities);
		
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		return "actMaster";
	}
	/**
	 * 
	 * @param  Save  Activity
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveActivity")
	public String saveActivity(@ModelAttribute("activity")Activities activities, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		activities.setInsBy((String) session.getAttribute("USER_NAME"));
		activityService.addActivity(activities);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		
		return"redirect:/actMaster";
		

	}
	/**
	 * Edit Find Single Record And Set Form of update Activity Form
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editActivity/{id}" })
	public String editActivity(@PathVariable("id") String id, Model model, HttpSession session) {

		Activities activityEdit = activityService.findActivityById(id);
		model.addAttribute("activityEdit", activityEdit);

		
		return "editActivity";
		}
	/**
	 * 
	 * @param Update Activity 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateActivity")
	public String updateActivity(@ModelAttribute("activity")Activities activities, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		activities.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.activityService.updateActivity(activities);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/actMaster";
		
	}
	/**
	 * 
	 * @param id  delete base on id Activity
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteActivity/{id}" })
	public String deleteActivity(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		this.activityService.removeActivity(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/actMaster";
		
	}

}
