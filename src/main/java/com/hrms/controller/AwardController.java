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
import com.hrms.model.Award;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;

import com.hrms.service.AwardService;
@Controller
public class AwardController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private AwardService awardService;

/**
 * Request Mapping  Award Master 
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/awardMaster")
	public String awardMaster(Model model, HttpSession session) {
		List<Award> listAward = awardService.getAllAwards();
		model.addAttribute("listAward", listAward);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		if(session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "awardMaster";
	}
/**
 * 
 * @param  Save award master 
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveAward")
	public String saveAward(@ModelAttribute("award") Award award, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		boolean isAwardExist = awardService.checkAwardExists(award);
		if (isAwardExist) {
			redirectAttributes.addFlashAttribute("message", "Award Name Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
			 return"redirect:/awardMaster";
		}
		else
		{
	
		award.setInsBy((String) session.getAttribute("USER_NAME"));
		awardService.addAward(award);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		}
		 return"redirect:/awardMaster";
		
	}
/**
 *  find Record base on id and set to update Form
 * @param id
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/editAward/{id}" })
	public String editAward(@PathVariable("id") long id, Model model, HttpSession session) {

		Award awardEdit = awardService.findAwardById(id);
		model.addAttribute("awardEdit", awardEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return "editAward";
	}
	
	/**
	 * 
	 * @param update award master values 
	 * @param model
	 * @param session
	 * @return
	 */

	@PostMapping("/updateAward")
	public String updateAward(@ModelAttribute("award") Award award, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		award.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.awardService.updateAward(award);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		  return"redirect:/awardMaster";
	}
/**
 * Delete award master record 
 * @param id
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/deleteAward/{id}" })
	public String deleteAward(@PathVariable("id") long id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		this.awardService.removeAward(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		 return"redirect:/awardMaster";
	}

}
