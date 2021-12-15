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
import com.hrms.model.MiscAllowance;
import com.hrms.service.MiscAllowanceDeductionService;
import com.hrms.service.ModuleService;


@Controller
public class MiscAllowancesDeductionController 
{

	@Autowired
	MiscAllowanceDeductionService miscAllowanceDeductionService;
	@Autowired
	private ModuleService moduleService;


	/**
	 * 
	 * Request mapping MiscAllowanceDeduction  list data
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/miscAllowances")
public String miscAllowances(Model model,HttpSession session) 
{
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	 List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
		model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "miscAllowances";

}
/**
 * Request Mapping save AllowncesDeduction
 * 
 * @param holiday
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveAllowncesDeduction")
 public String saveAllowncesDeduction(@ModelAttribute("miscAllowance") MiscAllowance miscAllowance, Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { 
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	 
	miscAllowance.setInsBy((String) session.getAttribute("USER_NAME"));
	miscAllowanceDeductionService.addMiscAllowanceDeduction(miscAllowance);
	redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	session.setAttribute("username", session.getAttribute("username"));
	return "redirect:/miscAllowances";
	

 
 }
/**
 * Request Mapping fetching Id Base AllowanceDeduction  Loan data
 * 
 * @param id
 * @param model
 * @param session
 * @return
 */

@GetMapping(value = {"/editAllowanceDeduction/{id}"})
public String editAllowncesDeduction(@PathVariable("id")String id,  Model model,HttpSession session)
 { if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}

	MiscAllowance editMiscAllowance = miscAllowanceDeductionService.findMiscAllowanceDeductionById(id);
	model.addAttribute("editMiscAllowance", editMiscAllowance);
	session.setAttribute("imgUtil", new ImageUtil());
	session.setAttribute("username", session.getAttribute("username"));
	return "editAllowanceDeduction";
}
/**
 * 
 * @param Request Mapping update AllowanceDeduction Data
 * @param model
 * @param session
 * @return
 */
@PostMapping("/updateAllowanceDeduction")
public String updateAllowncesDeduction(@ModelAttribute("miscAllowanceUpdate") MiscAllowance miscAllowance, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}

	miscAllowance.setUpdBy((String) session.getAttribute("USER_NAME"));
	this.miscAllowanceDeductionService.updateMiscAllowanceDeduction(miscAllowance);
	redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	return "redirect:/miscAllowances";
	
}
/**
 * 
 * @param Request Mapping Delete By Id AllowanceDeduction Data
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/deleteAllowanceDeduction/{id}"})
public String deleteAllowncesDeduction(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	this.miscAllowanceDeductionService.removeMiscAllowanceDeduction(id);
	redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	session.setAttribute("username", session.getAttribute("username"));
	return "redirect:/miscAllowances";
 
}




}
