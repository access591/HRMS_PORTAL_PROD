package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.EmpMonOvertime;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import org.springframework.web.bind.annotation.InitBinder;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.EmpMontOvertimeRegister;
import com.hrms.util.EmpMonOvertimeUtil;

@Controller
public class MonthlyOvertimeEvaluationController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmpMontOvertimeRegister empMontOvertimeRegister;
	@InitBinder("monthOverTimeRegister")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "oTimeMonth",new CustomDateEditor(dateFormatter, true));
			
				
       
    }
	@GetMapping("/monthlyOvertimeEvaluation")
	public String monthlyOvertimeEvaluation(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<EmpMonOvertime> listOverTimeR = empMontOvertimeRegister.getAllMontOvertimeRegister();

		List<EmpMonOvertimeUtil> listOverTimeEval = new ArrayList<>();
		for (int i = 0; i < listOverTimeR.size(); i++) {
			String empCode = listOverTimeR.get(i).getEmployee().getEmpCode();
			EmpMonOvertimeUtil ovTime = new EmpMonOvertimeUtil();
			session.setAttribute("imgUtil", new ImageUtil());

			Employee employee = employeeService.findEmployeeById(empCode);
			Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());

			ovTime.setEmpName(employee.getEmpName());
			ovTime.setDeptName(department.getDeptName());
			ovTime.setDesgName(designation.getDesgName());

			ovTime.setId(listOverTimeR.get(i).getId());
			ovTime.setoTimeMonth(listOverTimeR.get(i).getoTimeMonth());
			ovTime.setPayableAmt(listOverTimeR.get(i).getPayableAmt());
			listOverTimeEval.add(ovTime);
			model.addAttribute("overRegEval", listOverTimeEval);

		}
		return "monthlyOvertimeEvaluation";

	}

	// @CrossOrigin
	@ResponseBody
	@GetMapping("/viewMonthOverTimeRegisterBydepartment/{id}")
	public List<EmpMonOvertimeUtil> getLocalConvyenceById(@PathVariable(value = "id") String id, Model model,
			HttpSession session) {
		Department d = departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		List<EmpMonOvertimeUtil> lisOvertimeRegisterUtil = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			String empCode = e.get(i).getEmpCode();
			EmpMonOvertimeUtil lc = new EmpMonOvertimeUtil();
			Employee employee = employeeService.findEmployeeById(empCode);
			lc.setEmpCode(employee.getEmpCode());
			lc.setEmpName(employee.getEmpName());
			lisOvertimeRegisterUtil.add(lc);
		}
		return lisOvertimeRegisterUtil;

	}

	@ResponseBody
	@GetMapping("/viewMonthOverTimeRegisterByEmployee/{id}")
	public List<EmpMonOvertimeUtil> viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id, Model model,
			HttpSession session) {

		List<Employee> employee = employeeService.findByempCode(id);
		List<EmpMonOvertimeUtil> lisOver = new ArrayList<>();

		for (int i = 0; i < employee.size(); i++) {

			EmpMonOvertimeUtil listEmp = new EmpMonOvertimeUtil();
			Designation designation = designationService.findDesignationById(employee.get(i).getDesignation().getDesgCode());
			listEmp.setDesgName(designation.getDesgName());
			listEmp.setEmpCode(employee.get(i).getEmpCode());
			listEmp.setEmpName(employee.get(i).getEmpName());
			lisOver.add(listEmp);

		}

		return lisOver;

	}

	@PostMapping("/saveOvertimeEvaluation")
	public String saveOvertimeEvaluation(@ModelAttribute("monthOverTimeRegister") EmpMonOvertime overtimeEval , Model model,
			HttpSession session, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
	String empCode=overtimeEval.getEmployee().getEmpCode();
	System.out.println(":::::::::::::::::::::::::::::::::::::::::"+empCode);
	
		boolean isModuleExist = empMontOvertimeRegister.checkMonthOverTimeExists(overtimeEval.getEmployee().getEmpCode());	
		
		if (isModuleExist) {
			
			       redirectAttributes.addFlashAttribute("message", "Rights are already assigned to user!!");
				   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				return"redirect:/monthlyOvertimeEvaluation";
		}
		else 
		{
		String insertedBY = (String) session.getAttribute("USER_NAME");
		overtimeEval.setInsBy(insertedBY);
		this.empMontOvertimeRegister.addMontOvertimeRegister(overtimeEval);
		   redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/monthlyOvertimeEvaluation";
		}
	}

	//
	@GetMapping(value = { "/deleteMonthOverTime/{id}" })
	public String deleteMonthOverTime(@PathVariable("id") long id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		empMontOvertimeRegister.removeMonthOverTimeRegister(id);
		session.setAttribute("username", session.getAttribute("username"));
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		return "redirect:/monthlyOvertimeEvaluation";
	}

}
