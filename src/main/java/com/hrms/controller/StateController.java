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
import com.hrms.model.Country;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.service.CountryService;
import com.hrms.service.ModuleService;
import com.hrms.service.StateService;

@Controller
public class StateController {
	
	@Autowired
	CountryService countryService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	StateService stateService;

	/**
	 * Request mapping State Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/stateMaster")
	public String stateMaster(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		if(userCode!=null) {
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Country> listCountry = countryService.getActiveCountrys();
		model.addAttribute("listCountry", listCountry);
		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "stateMaster";
		}
		else
		{
			  return "redirect:" + "./";	
		}
	}

	/**
	 * 
	 * @param  Save state Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveState")
	public String saveState(@ModelAttribute("state") State state, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	
		state.setInsBy((String) session.getAttribute("USER_NAME"));
		stateService.addState(state);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		List<State> listState =stateService.getAllStates();
		model.addAttribute("listState", listState);
		
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/stateMaster";
		

	}
	/**
	 * 
	 * @param id find unique record edit 
	 * @param model
	 * @param session
	 * @return
	 */
	
	@GetMapping(value = { "/editState/{id}" })
	public String editState(@PathVariable("id") String id, Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);
		session.setAttribute("imgUtil", new ImageUtil());
		State stateEdit = stateService.findStateById(id);
		model.addAttribute("stateEdit", stateEdit);
		session.setAttribute("username", session.getAttribute("username"));
		
		return "editState";
	}
	
	/**
	 * 
	 * @param upadte state master 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateState")
	public String updateState(@ModelAttribute("city") State c, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		c.setUpdBy((String) session.getAttribute("USER_NAME"));
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		this.stateService.updateState(c);
		return"redirect:/stateMaster";
		
	}
	
	/**
	 * 
	 * @param id delete record base on id 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteState/{id}" })
	public String deleteState(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.stateService.removeState(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/stateMaster";
	}
}

