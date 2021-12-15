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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.OvertimeRegister;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.OvertimeRegisterService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.util.OvertimeRegisterUtil;

@Controller
public class OvertimeRegisterController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;

	@Autowired
	OvertimeRegisterService overtimeRegisterService;

	@InitBinder("overTimeRegister")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "overTimeDate", new CustomDateEditor(dateFormatter, true));
	}

	@GetMapping("/overtimeRegister")
	public String overtimeRegister(Model model, HttpSession session) {
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
		session.setAttribute("imgUtil", new ImageUtil());
		List<OvertimeRegister> listOverTimeR = overtimeRegisterService.getAllOvertimeRegister();
		List<OvertimeRegisterUtil> listOverTime = new ArrayList<>();
		for (int i = 0; i < listOverTimeR.size(); i++) {

			String empCode = listOverTimeR.get(i).getEmployee().getEmpCode();
			OvertimeRegisterUtil ovTime = new OvertimeRegisterUtil();

			Employee employee = employeeService.findEmployeeById(empCode);
			Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());

			ovTime.setEmpName(employee.getEmpName());
			ovTime.setDeptName(department.getDeptName());
			ovTime.setDesgName(designation.getDesgName());

			ovTime.setId(listOverTimeR.get(i).getId());
			ovTime.setOverTimeDate(listOverTimeR.get(i).getOverTimeDate());
			/* ovTime.setEsiYn(listOverTimeR.get(i).getEsiYn()); */
			ovTime.setOverTime(listOverTimeR.get(i).getOverTime());
			ovTime.setRemarks(listOverTimeR.get(i).getRemarks());
			ovTime.setTimeIN(listOverTimeR.get(i).getTimeIN());
			ovTime.setTimeOut(listOverTimeR.get(i).getTimeOut());
			listOverTime.add(ovTime);
			model.addAttribute("overReg", listOverTime);

		}

		return "overtimeRegister";

	}

	// @CrossOrigin
	@ResponseBody
	@GetMapping("/viewOverTimeRegisterBydepartment/{id}")
	public List<OvertimeRegisterUtil> getLocalConvyenceById(@PathVariable(value = "id") String id, Model model,
			HttpSession session) {
		Department d = departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		List<OvertimeRegisterUtil> lisOvertimeRegisterUtil = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			String empCode = e.get(i).getEmpCode();
			OvertimeRegisterUtil lc = new OvertimeRegisterUtil();
			Employee employee = employeeService.findEmployeeById(empCode);
			lc.setEmpCode(employee.getEmpCode());
			lc.setEmpName(employee.getEmpName());
			lisOvertimeRegisterUtil.add(lc);
		}
		return lisOvertimeRegisterUtil;

	}

	@ResponseBody
	@GetMapping("/viewOverTimeRegisterByEmployee/{id}")
	public OvertimeRegisterUtil viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id, Model model,
			HttpSession session) {

		Employee employee = employeeService.findEmployeeById(id);
		String empCode = employee.getEmpCode();
		System.out.println(">>>>>>>>xxxxxx>>>>>>>>>>>>>>>>>>" + empCode);
		OvertimeRegisterUtil listEmp = new OvertimeRegisterUtil();

		Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());
		listEmp.setDesgName(designation.getDesgName());
		listEmp.setEmpCode(employee.getEmpCode());
		listEmp.setEmpName(employee.getEmpName());

		return listEmp;

	}

	@PostMapping("/saveOverTimeRegister")
	public String saveAttendenceRegister(@ModelAttribute("overTimeRegister") OvertimeRegister overReg, Model model,RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {
		String insertedBY = (String) session.getAttribute("USER_NAME");
		overReg.setInsBy(insertedBY);

		this.overtimeRegisterService.addOvertimeRegister(overReg);
		  redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/overtimeRegister";
	}

	@GetMapping(value = { "/deleteOverTime/{id}" })
	public String deleteOverTime(@PathVariable("id") long id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		overtimeRegisterService.removeOverTimeRegister(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/overtimeRegister";
	}
}
