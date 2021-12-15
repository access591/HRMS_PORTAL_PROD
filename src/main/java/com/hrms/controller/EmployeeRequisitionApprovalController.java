package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.helper.Message1;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeRequisitionApprovalController {

	@Autowired ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	@Autowired DepartmentService departmentService;
	
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		session.setAttribute("username", session.getAttribute("username"));
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
	}
	
	
	@GetMapping("employeeRequisitionApproval")
	public String employeeRequisitionApproval(@ModelAttribute("employeeRequisition")EmployeeRequisition employeeRequisition ,
			Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
	
		List<EmployeeRequisition> listEmployeeReq = employeeRequisitionService.getAllPendingEmployeeRequisition();
	
		model.addAttribute("listCommonUtil", listEmployeeReq);

		//session.setAttribute("imgUtil", new ImageUtil());
		return "EmployeeRequisitionApproval"; 
		
		
	}
	
	
	@GetMapping("approveRequisition/{id}/{status}")
	public String approveRequisition(@PathVariable("id") String reqCode,@PathVariable("status") String approvalStatus,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		try {
			employeeRequisitionService.approvedByReqCodeAndStatus(reqCode,approvalStatus);
			
			if(approvalStatus.equals("Y")) {
				redirectAttributes.addFlashAttribute("message", "Employee Requisition has been Approved");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
			}else if(approvalStatus.equals("C")) {
				redirectAttributes.addFlashAttribute("message", "Employee Requisition has been Cancelled");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return "redirect:/employeeRequisitionApproval";
	}
	
	
	
}
