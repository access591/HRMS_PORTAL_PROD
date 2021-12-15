package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.hrms.helper.Message1;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.Leave;
import com.hrms.model.LeaveGrant;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;

import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;

import com.hrms.service.UserService;
import com.hrms.util.LeaveUtil;

@Controller
public class LeaveRequestController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveRequestService leaveRequestService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	UserService userService;
	@Autowired
	LeaveGrantRegisterService leaveGrantRegisterService;
	@Autowired
	LeaveDetailService leaveDetailService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	LeaveGrantRegisterService leaveGrantService;

	@Autowired
	private ModuleService moduleService;

	@InitBinder("leaveRequest")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "applyDate", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "toDate", new CustomDateEditor(dateFormatter, true));

		binder.registerCustomEditor(Date.class, "fromDate", new CustomDateEditor(dateFormatter, true));

	}

	@ModelAttribute
	public void commonData(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");

		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		session.setAttribute("username", userCode);

	}

	@GetMapping("/leaveRequest")
	public String empPayDetail(@ModelAttribute("leaveRequest") LeaveRequest leaveRequest, Model model,
			HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> listEmployee = employeeService.getAllEmployees();

		try {
			List<Department> departmentList = departmentService.getAllDepartments();
			if (departmentList != null) {
				model.addAttribute("departmentList", departmentList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Leave> listLeave = leaveService.getAllLeaves();
		if (listLeave != null) {
			model.addAttribute("listLeave", listLeave);
		}

		List<LeaveRequest> listLeaveRequest = leaveRequestService.getAllLeaves();
		if (listLeaveRequest != null) {
			model.addAttribute("listRequest", listLeaveRequest);
		}

		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}

		return "leaveRequest";
	}

	@PostMapping("/saveLeaveRequest")
	public String saveLeaveRequest(@Valid @ModelAttribute("leaveRequest") LeaveRequest leaveRequest,
			HttpSession session, RedirectAttributes redirectAttributes) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {
			leaveRequestService.addLeave(leaveRequest);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}

		return "redirect:/leaveRequest";
	}

	@GetMapping(value = { "/deleteLeaveRequest/{id}" })
	public String deleteActivity(@PathVariable("id") Long id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {
			this.leaveRequestService.removeLeaveRequest(id);
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message1("Something went wrong", "alert-primary"));
		}

		return "redirect:/leaveRequest";

	}

	@ResponseBody
	@GetMapping("/employee/leaveGrant/{id}")
	public LeaveUtil getEmployeeLeaveGrant(@PathVariable(value = "id") String empCode, Model model,
			HttpSession session) {

		System.out.println("get Employeee ;eave hrant ");
		List<LeaveGrant> lvGrant = leaveGrantService.findLeaveGrantByEmpCode(empCode);
		
		LeaveUtil leaveUtil = new LeaveUtil();
		leaveUtil.setLeaveGrants(lvGrant);

		int totalLeaveGranted = 0;
		int leaveAvailed = 0;
		int takenLeave = 0;

		if (lvGrant.size() > 0) {

			for (LeaveGrant lv : lvGrant) {
				totalLeaveGranted = totalLeaveGranted + lv.getNoOfLeavesGranted();
				leaveAvailed = leaveAvailed + lv.getLeaveAvailed();
			}
			takenLeave = totalLeaveGranted - leaveAvailed;

			System.out.println("taken leave :" + takenLeave);
			
		}
		LeaveGrant l = new LeaveGrant();
		l.setLeaveAvailed(leaveAvailed);
		l.setNoOfLeavesGranted(totalLeaveGranted);
		l.setTakenLeave(String.valueOf(takenLeave));
		
		leaveUtil.setLv(l);
		return leaveUtil;
	}

}
