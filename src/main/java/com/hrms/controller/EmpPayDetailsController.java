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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;

import com.hrms.model.Employee;
import com.hrms.model.EmployeePayDetail;
import com.hrms.model.MenuModule;
import com.hrms.model.MiscAllowance;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeePayDetailService;

import com.hrms.service.EmployeeService;
import com.hrms.service.MiscAllowanceDeductionService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmpPayDetailsController {

	
	@Autowired
	private ModuleService moduleService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private EmployeePayDetailService empPayDetailService;

	@Autowired
	DepartmentService departmentService;
	@Autowired
	MiscAllowanceDeductionService miscAllowanceDeductionService;

	@GetMapping("/empPayDetail")
	public String empPayDetail(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
		model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction);
		List<Employee> listEmployee = employeeService.getAllEmployees();
		List<EmployeePayDetail> listEmpPayDetail = empPayDetailService.getAllEmployeePayDetail();
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		if (listEmpPayDetail != null) {
		
			model.addAttribute("listEmpPayDetail", listEmpPayDetail);
		}

		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "empPayDetail";
	}

	@PostMapping("empPayDetail/{empCode}")
	@ResponseBody
	public Employee userInfo(@PathVariable("empCode") String empCode) {

		System.out.println("user info method : " + empCode);

		return employeeService.findEmployeeById(empCode);
	}



	@PostMapping("/saveemployepaydetail")
	public String saveEmployeePaydetail(@ModelAttribute("employeePayDetail") EmployeePayDetail employeePayDetail,HttpSession session, Model model,RedirectAttributes redirectAttributes) {

		System.out.println("save employe pay detail " + employeePayDetail);
		
		boolean isUserExists = empPayDetailService.isEmployeePayExists(employeePayDetail.getEmpCode());
		if(isUserExists) {
			System.out.println( " üser allready exists");
		}else {
			empPayDetailService.addEmployeePayDetail(employeePayDetail);
			System.out.println("user is not exists");
		}
		
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		return"redirect:/empPayDetail";
	}

	@GetMapping(value = { "/editEmpPayDetail/{empCode}" })
	public String editEmpPayDetail(@PathVariable("empCode") String empCode, Model model, HttpSession session) {
		System.out.println("edit emp page " + empCode);
		

		EmployeePayDetail employePay = empPayDetailService.findEmployeePayDetaildById(empCode);
		model.addAttribute("editEmpPayDetail", employePay);

		System.out.println(" pay employee : " + employePay.getEmpName());
		return "editEmpPay";
	}
}
