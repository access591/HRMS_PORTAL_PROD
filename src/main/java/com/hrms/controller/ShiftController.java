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
import com.hrms.model.Shift;
import com.hrms.service.ModuleService;
import com.hrms.service.ShiftService;

@Controller
public class ShiftController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	ShiftService shiftService;



	/**
	 * Request Mapping Shift Master
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/shiftMaster")
	public String shiftMaster(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Shift> listShift = shiftService.getAllShifts();
		model.addAttribute("listShift", listShift);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));

		return "shiftMaster";
	}

	/**
	 * save Record Shift Master Request Mapping
	 * 
	 * @param shift
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveShift")
	public String saveShift(@ModelAttribute("shift") Shift shift, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	
		shift.setInsBy((String) session.getAttribute("USER_NAME"));
		shiftService.addShift(shift);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));

		
		
		return"redirect:/shiftMaster";
		
	}

	/**
	 * Find Record And View Value on Update Shift master Page
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editShift/{id}" })
	public String editShift(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	
		session.setAttribute("imgUtil", new ImageUtil());
		Shift shiftEdit = shiftService.findShiftById(id);
		model.addAttribute("shiftEdit", shiftEdit);
		session.setAttribute("username", session.getAttribute("username"));

		
		return"editShift";
	
	}

	/**
	 * Update Record Shift Master
	 * 
	 * @param s
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateShift")
	public String updateShift(@ModelAttribute("shift") Shift s, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		s.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.shiftService.updateShift(s);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");   

		return"redirect:/shiftMaster";
	}

	/**
	 * delete Shift Master Record
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteShift/{id}" })
	public String deleteShift(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.shiftService.removeShift(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/shiftMaster";
	}

}
