package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Employee;

import com.hrms.model.LtaRequest;
import com.hrms.util.LtaRequestUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LtaApprovalService;
import com.hrms.service.LtaRequestService;
import com.hrms.service.ModuleService;
@Controller
public class LtaApprovalController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	LtaApprovalService ltaApprovalService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	LtaRequestService ltaRequestService ;
	
	@GetMapping("/ltaApproval")
	public String ltaApproval(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	
		String userCode = (String) session.getAttribute("username");
		
		List<LtaRequest>listLtaApproval=ltaApprovalService.getAllLtaApproval();
		
		List<LtaRequestUtil> listOfLtaApproval = new ArrayList<>();
		
		for (int i = 0; i < listLtaApproval.size(); i++) {
			String empCode = listLtaApproval.get(i).getEmpCode().getEmpCode();
			
			
			LtaRequestUtil lreq=new LtaRequestUtil();
			Employee employee = employeeService.findEmployeeById(empCode);
			Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			lreq.setLtaCode(listLtaApproval.get(i).getLtaCode());
			lreq.setEmpName(employee.getEmpName());
			lreq.setDeptName(department.getDeptName());
			lreq.setAppDate(listLtaApproval.get(i).getAppDate());
			lreq.setWhenAvailed(listLtaApproval.get(i).getWhenAvailed());
			lreq.setRemarks(listLtaApproval.get(i).getRemarks());
			listOfLtaApproval.add(lreq);
			
		}
		
		model.addAttribute("listLtaApproval",listOfLtaApproval );
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "ltaApproval";
	}
	
	@GetMapping("approvedLtaRequest/{id}")
	public String approvedLtaRequest(@PathVariable("id") String id) {
		ltaApprovalService.approvedLtaRequestById(id);
		return "redirect:/ltaApproval";
	}
	@GetMapping(value = { "/viewLtaRequest/{id}" })
	public String viewLtaRequest(@PathVariable("id") String id, Model model, HttpSession session) {
		List<Employee> empp = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", empp);

		 LtaRequest ltaRequestEdit =	ltaRequestService.findByIdLta(id);
		  model.addAttribute("ltaRequestEdit", ltaRequestEdit);

		return "viewLtaRequest";
	}

}
