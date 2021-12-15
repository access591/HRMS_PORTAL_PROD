package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.service.LeaveDetailService;



import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ReportUtil;
import com.hrms.model.Employee;
import com.hrms.model.LocalConvyenceDetail;
import com.hrms.model.LtaRequest;
import com.hrms.model.MenuModule;
import com.hrms.reports.LocalClaimReport;
import com.hrms.reports.LtaReport;
import com.hrms.reports.TourClaimReport;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.LocalConvyenceDetailService;
import com.hrms.service.LtaRequestService;
import com.hrms.service.ModuleService;
import com.hrms.service.TourPlanService;
import com.hrms.util.LtaReportUtil;

@Controller
public class ReportCommonController {

	@Autowired LeaveRequestService leaveRequestService;
	@Autowired LeaveGrantRegisterService leaveGrantService;
	@Autowired EmployeeService employeeService;
	@Autowired LeaveService leaveService;
	@Autowired LeaveDetailService leaveDetailService;
	@Autowired DesignationService designationService;
	@Autowired ModuleService moduleService;
	@Autowired LtaRequestService ltaRequestService;
	@Autowired LocalConvyenceDetailService localConvyenceDetailService;
	@Autowired LocalClaimReport localClaimReport;
	
	@Autowired TourPlanService tourPlanService;
	
	@Autowired TourClaimReport tourClaimReport;
	
	@Autowired LtaReport ltaReport;
	

	@Autowired
	ReportUtil reportUtil;
	
	@InitBinder("ltaRequest")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "leaveFrom",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "leaveTo",
                new CustomDateEditor(dateFormatter, true));
        
       
    }

	
	
	
	@ResponseBody
	@PostMapping("/employeereport")
	public void employeeReport(HttpServletRequest request,HttpServletResponse response) {
		
		
		String reportName = "EmployeeReport";	
		List<Employee> listEmployee = employeeService.getAllEmployees();
				
		reportUtil.allEmployeeReport(request, response, reportName, listEmployee);
		
	}
	

	
	
	
	
	
//LOCAL CLAIM REORT
	
	@GetMapping("localclaimPage")
	public String localClaimReport(Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		
		return "LocalClaimReport"; 
	}
	
	@PostMapping("localClaimReport")
	public String createLocalClaimReport(@RequestParam("empName") String empName, Model model,HttpSession session,
			HttpServletRequest req,HttpServletResponse res) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		System.out.println("empName is : " + empName);
		
		List<LocalConvyenceDetail> listLocalConvyenceDetail;
		if(empName.equals("ALL")) {
			listLocalConvyenceDetail = localConvyenceDetailService.findLocalConvyenceDetailByEmpCode(empName);
		}
		else {
			listLocalConvyenceDetail = localConvyenceDetailService.findAllLocalConvyenceDetail();
		}
		
		
		localClaimReport.localClaimReport(res, req, listLocalConvyenceDetail);
		return null;
		
	}
	
	
//LTA REPORTS
	
	@GetMapping("ltaReport")  
	public String ltaReportPage(Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		try {
			List<LtaRequest> listLtaRequest = ltaRequestService.getAllDistinctLtaRequest();
			model.addAttribute("listLtaRequest", listLtaRequest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("ltaRequest", new LtaRequest());
		return "LtaReport";
	}
	
	@PostMapping("createLtaReport")  
	public String createLtReport(@ModelAttribute("ltaRequest") LtaRequest ltaRequest , Model model,HttpSession session,
			HttpServletRequest req,HttpServletResponse res) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		List<LtaRequest> listLtarequest = null; 
		List<LtaReportUtil> listLtaReport = new ArrayList<>(); 
		
		if(!ltaRequest.getEmpCode().getEmpCode().equals("ALL")) {
			listLtarequest = ltaRequestService.findLtaByFromLeaveDateToLeave(ltaRequest.getLeaveFrom(), 
					ltaRequest.getLeaveTo(), ltaRequest.getEmpCode().getEmpCode());
			
			LtaReportUtil ltaReportUtil;
			
			for(LtaRequest lt : listLtarequest) {
				
				ltaReportUtil = new LtaReportUtil(lt.getEmpCode().getEmpCode(),lt.getEmpCode().getEmpName(),
						lt.getAppDate(),lt.getEligibilityDate(),lt.getLeaveAvailed(),lt.getLeaveFrom(),
						lt.getLeaveTo());
				listLtaReport.add(ltaReportUtil);
			}
		}
		else {
			listLtarequest = ltaRequestService.findAllLtaByFromLeaveDateToLeave(ltaRequest.getLeaveFrom(), 
					ltaRequest.getLeaveTo());
			
			LtaReportUtil ltaReportUtil;
			
			for(LtaRequest lt : listLtarequest) {
				
				ltaReportUtil = new LtaReportUtil(lt.getEmpCode().getEmpCode(),lt.getEmpCode().getEmpName(),
						lt.getAppDate(),lt.getEligibilityDate(),lt.getLeaveAvailed(),lt.getLeaveFrom(),
						lt.getLeaveTo());
				listLtaReport.add(ltaReportUtil);
			}
		}
		
		ltaReport.ltaReport(res, req, listLtaReport,ltaRequest.getLeaveFrom(),ltaRequest.getLeaveTo());
		
		
		return null;
	}
	
	
	

}
