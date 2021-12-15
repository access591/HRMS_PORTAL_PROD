package com.hrms.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.ImageUtil;
import com.hrms.ReportUtil;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TourClaim;
import com.hrms.model.TourPlan;
import com.hrms.reports.LocalClaimReport;
import com.hrms.reports.LtaReport;
import com.hrms.reports.TourClaimReport;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.LocalConvyenceDetailService;
import com.hrms.service.LtaRequestService;
import com.hrms.service.ModuleService;
import com.hrms.service.TourClaimService;
import com.hrms.service.TourPlanService;

@Controller
public class ReimbursementReportController {

	@Autowired
	LeaveRequestService leaveRequestService;
	@Autowired
	LeaveGrantRegisterService leaveGrantService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	LeaveDetailService leaveDetailService;
	@Autowired
	DesignationService designationService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	LtaRequestService ltaRequestService;
	@Autowired
	LocalConvyenceDetailService localConvyenceDetailService;
	@Autowired
	LocalClaimReport localClaimReport;
	@Autowired
	TourPlanService tourPlanService;
	@Autowired
	TourClaimReport tourClaimReport;
	@Autowired
	LtaReport ltaReport;
	@Autowired
	ReportUtil reportUtil;
	
	
	@Autowired TourClaimService tourClaimService;

//1.TOUR CLAIM REPORT	

	@GetMapping("tourclaimPage")
	public String tourClaimReport(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		model.addAttribute("object", new TourPlan());
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", userCode);
		return "tourClaimReports"; 
	}

	@PostMapping("generateTourClaim")
	public String genrateTourClaim(@ModelAttribute("object") TourPlan tourPlan, HttpSession session,
			@RequestParam("empName") String empCode, HttpServletRequest req, HttpServletResponse res,
			@RequestParam("fromDate") Date fromDate , @RequestParam("toDate") Date toDate) {

		String userCode = (String) session.getAttribute("username");
		System.out.println("employee type/name : " + empCode);
		
		if (!empCode.equals("ALL")) {
			List<TourClaim> tourClaim = tourClaimService.findTourClaimByEmpCodeBetweenDate(empCode,fromDate,toDate);
			
			tourClaimReport.tourClaimReport(res, req, tourClaim);

			

		}else {
			System.out.println("else block?  ");
			
			List<TourClaim> tourClaim = tourClaimService.getAllTourClaimBetweenDate(fromDate, toDate);
			tourClaimReport.tourClaimReport(res, req, tourClaim);
		}

		session.setAttribute("username", userCode);
		return "redirect:/tourclaimPage";

	}

}
