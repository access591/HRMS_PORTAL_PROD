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
import com.hrms.model.LocalConvyence;
import com.hrms.util.LocalConvyenceUtil;
import com.hrms.model.MenuModule;

import com.hrms.service.ConveyanceApprovalService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LocalConvyenceService;
import com.hrms.service.ModuleService;

@Controller
public class ConveyanceApprovalController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	private ConveyanceApprovalService conveyanceApprovalService;
	@Autowired
	LocalConvyenceService  localConvyenceService;

	@GetMapping("/conveyanceApproval")
	public String conveyanceApproval(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<LocalConvyence> listOfLoc = conveyanceApprovalService.getAllLocalConveyance();
		List<LocalConvyenceUtil> listLocalConvyenceUtil = new ArrayList<>();
		for (int i = 0; i < listOfLoc.size(); i++) {

			String empCode = listOfLoc.get(i).getEmpCode().getEmpCode();
			LocalConvyenceUtil lc = new LocalConvyenceUtil();
			Employee employee = employeeService.findEmployeeById(empCode);
			Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());

			lc.setLocalConvId(listOfLoc.get(i).getLocalConvId());
			lc.setDeptName(department.getDeptName());
			lc.setDesgName(designation.getDesgName());
			lc.setEmpName(employee.getEmpName());
			lc.setTotalPas((listOfLoc.get(i).getTotalPas()));

			listLocalConvyenceUtil.add(lc);
		}
		model.addAttribute("listLocalConveyance", listLocalConvyenceUtil);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "conveyanceApproval";
	}

	@GetMapping("approvedLocalConv/{id}")
	public String approvedLocalConv(@PathVariable("id") String id) {
		conveyanceApprovalService.approvedByLocalConvyenceId(id);
		return "redirect:/conveyanceApproval";
	}

	@GetMapping(value = { "/viewLocalConv/{id}" })
	public String viewLocalConv(@PathVariable("id") String id, Model model, HttpSession session) {
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);

		LocalConvyence localConvyenceEdit = localConvyenceService.findByIdLocalConvyence(id);
		model.addAttribute("localConvyenceEdit", localConvyenceEdit);

		return "viewLocalConv";
	}

}

