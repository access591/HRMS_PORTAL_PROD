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
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.Loan;
import com.hrms.model.LoanApplication;
import com.hrms.util.LoanApplicationUtil;

import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LoanApprovalService;
import com.hrms.service.LoanMaterService;
import com.hrms.service.LoanRequestService;
import com.hrms.service.ModuleService;
@Controller
public class LoanApprovalController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LoanApprovalService loanApprovalService;
	@Autowired
	LoanMaterService loanMaterService;
	@Autowired
	LoanRequestService loanRequestService;
	
	@GetMapping("/loanApproval")
	public String loanApproval(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		List<LoanApplication> listOfLoanApp = loanApprovalService.getAllLoanApproval();
		List<LoanApplicationUtil>listofloanApproval= new ArrayList<>();
		for (int i = 0; i < listOfLoanApp.size(); i++) {
			String empCode = listOfLoanApp.get(i).getEmpCode().getEmpCode();
			LoanApplicationUtil lc=new LoanApplicationUtil();
			
			String loanCode=listOfLoanApp.get(i).getLoanCode().getLoanCode();
			Loan loan=loanMaterService.findLoanById(loanCode);
			Employee employee = employeeService.findEmployeeById(empCode);
			Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());
			
			lc.setDeptName(department.getDeptName());
			lc.setDesgName(designation.getDesgName());
			lc.setEmpName(employee.getEmpName());
			lc.setEmpPayCode(employee.getPayeeCode());
			lc.setEmpCode(employee.getEmpCode());
			lc.setLoanName(loan.getLoanName());
			lc.setAppNo(listOfLoanApp.get(i).getAppNo());
			lc.setAppDate(listOfLoanApp.get(i).getAppDate());
			lc.setAmountRequired(listOfLoanApp.get(i).getAmountRequired());
			lc.setEffScheduleDate(listOfLoanApp.get(i).getEffScheduleDate());
		
			listofloanApproval.add(lc);
			model.addAttribute("listofloanApproval", listofloanApproval);
			
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "/loanApproval";
	}
	@GetMapping("approvedLoanRequest/{id}")
	public String approvedLoanRequest(@PathVariable("id") String id) {
		loanApprovalService.approvedLoanRequestById(id);
		return "redirect:/loanApproval";
	}
	
	@GetMapping(value = { "/viewLoanRequest/{id}" })
	public String viewLoanRequest(@PathVariable("id") String id, Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> em = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", em);
		List<Loan> listLoan = loanMaterService.getAllLoans();
		model.addAttribute("listLoan", listLoan);
		LoanApplication loanRequestEdit =	loanRequestService.findByIdLoanReq(id);
		  model.addAttribute("loanRequestEdit", loanRequestEdit);
	   

		return "viewLoanRequest";
	}

	
	
}
