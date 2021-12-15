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
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
import com.hrms.service.ModuleService;
import com.hrms.service.ProgramService;
import com.hrms.service.RoleRepository;
import com.hrms.service.SubModuleService;

@Controller
public class ProgramController {
	
	@Autowired
	ProgramService programService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SubModuleService subModuleService;

	
	@Autowired RoleRepository roleService;
	
	/**
	 * Method to get Program  Result 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/program")
	public String program(@ModelAttribute Module module, Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		if (userCode!=null) {
			List<Program> listpPrograms = programService.getAllPrograms();
			model.addAttribute("listpPrograms", listpPrograms);
			List<Module> modulesList = moduleService.getActiveModules();
			model.addAttribute("modulesList", modulesList);
			List<SubModule> subModulesList = subModuleService.getActiveSubModules();
			model.addAttribute("subModulesList", subModulesList);
			session.setAttribute("imgUtil", new ImageUtil());
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			session.setAttribute("username", session.getAttribute("username"));
			return "program";
		}
		else
		{
		return "redirect:" + "./";	
		}
	}
	/**
	 * Method to Save Program 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveProgram")
	public String saveProgram1(@ModelAttribute("program1") Program program, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		boolean isSubModuleExist = programService.checkProgramExists(program);
		System.out.println("isSubModuleExists : "+ isSubModuleExist);
		if (isSubModuleExist) {
			redirectAttributes.addFlashAttribute("message", " Program Already exists !");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			  return"redirect:/program";
		}

		else {

			program.setInsertedBy((String) session.getAttribute("USER_NAME"));
			programService.addProgram(program);
			redirectAttributes.addFlashAttribute("message", " Record  has been saved successfully!!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			session.setAttribute("username", session.getAttribute("username"));
		}
		  return"redirect:/program";
	}
	/**
	 * Method to Edit Program 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editProgram/{id}" })
	public String editProgramdata(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		session.setAttribute("imgUtil", new ImageUtil());
		Program programEdit = programService.findProgramById(id);
		model.addAttribute("programEdit", programEdit);
		
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		
		List<SubModule>subModulesList=subModuleService.getActiveSubModules();
		model.addAttribute("subModulesList", subModulesList);

		session.setAttribute("username", session.getAttribute("username"));
		
		return "editProgram";
	}
	/**
	 * Method to update Program 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateProgram")
	public String updateProgram(@ModelAttribute("programupdate") Program program, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		program.setUpdatedBy((String) session.getAttribute("USER_NAME"));
		this.programService.updateProgram(program);
		redirectAttributes.addFlashAttribute("message", " Record  has been updated successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return"redirect:/program";
	}

	/**
	 * Method to Delete Program
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping(value = {"/deleteProgram/{id}"})
public String deleteprogram(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
{ if (session.getAttribute("username") == null) {
	return "redirect:" + "./";
}
	  this.programService.removeProgram(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		   
   session.setAttribute("username",session.getAttribute("username")); 
 
	  return"redirect:/program";

}


}
