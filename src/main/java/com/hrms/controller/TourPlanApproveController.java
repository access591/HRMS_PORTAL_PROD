package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;

import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TourPlanApprovalService;
import com.hrms.service.TourPlanDetailService;
import com.hrms.service.TourPlanService;

@Controller
public class TourPlanApproveController {
	@Autowired 
	ModuleService moduleService;
	@Autowired 
	 EmployeeService employeeService;
	@Autowired
    DesignationService designationService;
	@Autowired 
	TourPlanService tourPlanService;
	@Autowired
	TourPlanDetailService tourPlanDetailService;
	@Autowired
	DepartmentService departmentService;

	@Autowired TourPlanApprovalService tourPlanApprovalService;
	
	@GetMapping("/tourPlanApproval")
	public String tourPlanApproval(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<TourPlan> listTourPlan=tourPlanApprovalService.getAllTourPlan();
		model.addAttribute("ListTourPlan", listTourPlan);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return"tourPlanApproval";
		
	}
	
	@GetMapping("approvedTourPlan/{id}")
	public String approvedTourPlan(@PathVariable("id") String id) {
		tourPlanApprovalService.approvedByTourPlanId(id);
		return "redirect:/tourPlanApproval";
	}
	
	
	@GetMapping(value = {"/viewTourPlan/{id}"})
	public String viewTourPlan(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		
	
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		TourPlan tourPlanEdit =	tourPlanService.findByIdTourPlan(id);
		  model.addAttribute("tourPlanEdit", tourPlanEdit);

	   
	    return "viewTourPlan";
	}
	
	
}
