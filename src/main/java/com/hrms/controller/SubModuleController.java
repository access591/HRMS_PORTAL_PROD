package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;




import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.SubModule;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;
import com.hrms.service.SubModuleService;

@Controller
public class SubModuleController {
	
	
	@Autowired
	SubModuleService subModuleService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * Method to get SubModule Result 	
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/subModule")
	public String submodulePage(Model model,HttpSession session) {
		
	String userCode= (String)session.getAttribute("username");
	if (userCode!=null) {
	  List<SubModule> listSubModule = subModuleService.getAllSubModules();
		model.addAttribute("listSubModule", listSubModule);
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
	
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "subModule";
	}
	else 
	{
		  return "redirect:" + "./";
	}
	}


/**
 * Method to Save  SubModule 	
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveSubModule")
	public String saveSubModule(@ModelAttribute("SubModule1") SubModule subModule, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		boolean isSubModuleExist = subModuleService.checkSubModuleExists(subModule);
		 
		  if (isSubModuleExist)
		  { redirectAttributes.addFlashAttribute("message"," Sub Module Already exists !  ");
		  redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		  
		  return"redirect:/subModule";
		}
		  
		  else {
			  boolean isSeqExist =subModuleService.checkSubModuleSeqExists(subModule);
		  
		  if(isSeqExist) 
		  {
			  redirectAttributes.addFlashAttribute("message", "Sequence No Already exists !");
		      redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		      return"redirect:/subModule";
		  }else {
			 subModule.setInsertedBySubModule((String) session.getAttribute("USER_NAME"));
			 subModuleService.addSubModule(subModule);
			 redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully! ");
			 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			 session.setAttribute("username", session.getAttribute("username"));
	
			 return"redirect:/subModule";
			 } 

			 } 
	}
	
	/**
	 * Method to Edit  SubModule 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editSubModule/{id}" })
	public String editsubmodule(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}  
	
		SubModule subModuleEdit = subModuleService.findSubModuleById(id);
		model.addAttribute("subModuleEdit", subModuleEdit);

		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "editSubModule";
	}

	/**
	 * Method to update  SubModule 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateSubModule")
	public String updatesubmodule(@ModelAttribute("submoduleupdate") SubModule subModule, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		subModule.setUpdateBySubModule((String) session.getAttribute("USER_NAME"));
		this.subModuleService.updateSubModule(subModule);
		 redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		 return"redirect:/subModule";

	}

	/**
	 * Method to Delete  SubModule 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteSubModule/{id}" })
	public String deletesubmodule(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		this.subModuleService.removeSubModule(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		 return"redirect:/subModule";
	}
	
	
	@ResponseBody
	@GetMapping("/viewModuleBySubModuleCode/{id}")
	public List<SubModule> getSubModuleByModule(@PathVariable(value = "id") String id, Model model, HttpSession session) {
		List<SubModule> e =subModuleService.findSubModuleByModuleCode(id);
		List<SubModule> lisSubModule = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			SubModule st = new SubModule();
			st.setSubModuleCode(e.get(i).getSubModuleCode());
			st.setSubModuleName(e.get(i).getSubModuleName());
			lisSubModule.add(st);
		}
		return lisSubModule;

	}

}
