package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Holiday;
import com.hrms.model.MenuModule;
import com.hrms.service.HolidayService;
import com.hrms.service.ModuleService;

@Controller
public class HolidayController 
{

	
	@Autowired
	HolidayService holidayService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 
	 * Request mapping Holiday list data 
	 * @param model
	 * @param session
	 * @return
	 */
	
	@InitBinder("holidays")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dateOfHoliday",new CustomDateEditor(dateFormatter, true));
			
				
       
    }
	
	
	
	@GetMapping("/HolidayMaster")	
	public String holidayMaster(Model model,HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Holiday>listHoliday = holidayService.getAllHolidays();
		model.addAttribute("listHoliday", listHoliday); 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username",session.getAttribute("username"));
		return "HolidayMaster";
		}
	
	/**
	 * Request Mapping save Holiday Master
	 * @param holiday
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveHolidays")
	  public String saveHoliday(@ModelAttribute("holidays") Holiday holiday, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		if (holiday.getHolidayCode() == null) {
			holiday.setInsBy((String) session.getAttribute("USER_NAME"));
			holidayService.addHoliday(holiday);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			session.setAttribute("username", session.getAttribute("username"));
			session.setAttribute("imgUtil", new ImageUtil());
		}
			
			 return "redirect:/HolidayMaster";
	  }
	/**
	 * Request Mapping fetching  Id Base edit Holiday data 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editHoliday/{id}"})
	public String editHoliday(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

	Holiday holidayedit = holidayService.findHolidayById(id);
	model.addAttribute("Holidayedit", holidayedit);
	session.setAttribute("username",session.getAttribute("username")); 
	session.setAttribute("imgUtil", new ImageUtil());
	 return "editHoliday";
	 
	 }
	
	/**
	 * Request Mapping Update HOliday data 
	 * @param h
	 * @param model
	 * @return
	 */
	@PostMapping("/updateHoliday")
	public String updateHoliday(@ModelAttribute("holidays") Holiday holiday,HttpSession session, Model model,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		holiday.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.holidayService.updateHoliday(holiday);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		  return "redirect:/HolidayMaster";
	
	}
	
	/**
	 * 
	 * @param  Request Mapping  Delete By Id Holiday Data
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/deleteHoliday/{id}"})
	public String deleteHoliday(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
	 { 
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.holidayService.removeHoliday(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    session.setAttribute("username",session.getAttribute("username")); 
	    return "redirect:/HolidayMaster";
	}

	
}
