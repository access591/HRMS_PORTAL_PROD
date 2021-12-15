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
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TrainingRequisition;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TrainingRequistionService;

@Controller
public class TrainingRequisitionApproval {

	@Autowired
	private ModuleService moduleService;
	@Autowired TrainingRequistionService trainingRequistionService;
	@Autowired DepartmentService departmentService;
	@Autowired EmployeeService employeeService;

	@GetMapping("/trainingRequisitionApproval")
	public String trainingRequisitionPage(@ModelAttribute("trainingRequisition")TrainingRequisition trainingRequisition,
			Model model, HttpSession session) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<TrainingRequisition> listTrainingRequisition = trainingRequistionService.findTrainingRequisitionByStatusYAndC();
		if(listTrainingRequisition != null) {
			model.addAttribute("listTrainingRequisition", listTrainingRequisition);
		}
		return "trainingRequisitionApproval";

	}
	
	@GetMapping("approveTrainingRequisition/{id}/{status}")
	public String approveTrainingRequisition(@PathVariable("id") String trReqCode,@PathVariable("status") String approvalStatus
			,HttpSession session,RedirectAttributes redirectAttributes) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		try {
			trainingRequistionService.trainingRequisitionApproval(trReqCode, approvalStatus);
			
			redirectAttributes.addFlashAttribute("message", "Record   has been Approved!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
			
			
			
		}catch(Exception e) {
			e.printStackTrace();

		
			redirectAttributes.addFlashAttribute("message", "Something went wrong!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		
		}
		
		return "redirect:/trainingRequisitionApproval";
	}
	
	
	
	
	@GetMapping("viewTrainingRequisition/{id}")
	public String viewTrainingRequisition(@PathVariable("id") String trReqCode,@ModelAttribute("trainingRequisition")TrainingRequisition trainingRequisition
			,Model model,HttpSession session) {
	
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		List<Department> listDepartment = departmentService.getAllDepartments();
		if (listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		
		
		TrainingRequisition tr = trainingRequistionService.findById(trReqCode);
		if(tr != null) {
			model.addAttribute("trainingRequisition", tr);
		}
		return "viewTrainingRequisition";
	}	
}
