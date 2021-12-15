package com.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.Loan;
import com.hrms.model.LoanApplication;
import com.hrms.util.LoanApplicationUtil;
import com.hrms.model.LoanSchedule;

import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LoanMaterService;
import com.hrms.service.LoanRequestService;
import com.hrms.service.LoanTrackingService;
import com.hrms.service.ModuleService;

@Controller
public class LoanTrackingController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LoanRequestService loanRequestService;
	@Autowired
	LoanMaterService loanMaterService;
	@Autowired
	LoanTrackingService loanTrackingService;
	
	@GetMapping("/loanTracking")
	public String loanTracking(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		
			List<Loan> listLoan = loanMaterService.getAllLoans();
			
			
			model.addAttribute("listLoan", listLoan);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		List<LoanApplication> listloanApplication=loanRequestService.getAllRequest();
		
		for (int i = 0; i < listloanApplication.size(); i++) {
			String empCode = listloanApplication.get(i).getAppNo();
			List<LoanApplication> loanApp=loanRequestService.findByApprovalLoan(empCode);
			model.addAttribute("listloanApplication", loanApp);
		   }
	
	
		
		 return "/loanTracking";
	}

	
	
	@ResponseBody
    @GetMapping("/viewLoanTracking/{id}")
    public LoanApplicationUtil  viewLoanTracking(@PathVariable(value = "id") String id) {
		
		LoanApplication loanApp=loanRequestService.findByIdLoanReq(id);
		String empCode=loanApp.getEmpCode().getEmpCode();
		
		Employee e = employeeService.findEmployeeById(empCode);
		Department d = departmentService.findDepartmentById(e.getDepartment().getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignation().getDesgCode());
		LoanApplicationUtil l=new LoanApplicationUtil(e.getEmpCode(),
					e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getPayeeCode());
		
		l.setEmpCode(empCode);
		l.setLoanCode(loanApp.getLoanCode().getLoanCode());
		l.setAppDate(loanApp.getAppDate());
		l.setEffScheduleDate(loanApp.getEffScheduleDate());
		
		l.setAmountRequired(loanApp.getAmountRequired());
		l.setAmountSanctioned(loanApp.getAmountSanctioned());
		l.setApprovalStatus(loanApp.getApprovalStatus());
        return l;
    }
	
	
	@PostMapping("/saveLoanTracking")
	public String saveLoanTracking(@ModelAttribute("localtrack")LoanApplicationUtil loanTrackUtil, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		LoanSchedule ls=new LoanSchedule();
		LoanApplication loanRequest = new LoanApplication();
		loanRequest.setAppNo(loanTrackUtil.getAppNo());
		loanRequest.setNoOfInstallments(loanTrackUtil.getNoOfInstallments());
		loanRequest.setChequeNo(loanTrackUtil.getChequeNo());
		loanRequest.setChequeDate(loanTrackUtil.getChequeDate());
		loanRequest.setChequeAmount(loanTrackUtil.getChequeAmount());
		loanRequest.setBankName(loanTrackUtil.getBankName());
		loanRequest.setInterestRate(loanTrackUtil.getInterestRate());
		this.loanRequestService.updateLoanRequest(loanRequest);
		String appNo=loanTrackUtil.getAppNo();
		Date appDate=loanTrackUtil.getAppDate();
		int flag = 0;
   		int counter = 1;
		try {
			
			
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr"));
			System.out.println("counter::::::::::::::::::::" + counter);
			
		
			for (int i =0; i < counter; i++) 
			{
				System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
				
				
				if(request.getParameter("instNo" + i) != null) {
					  
					  String inso=request.getParameter("instNo" + i);
					  int inst=Integer.parseInt(inso);
					  ls.setInstNo(inst);
				  } 
				
				if(request.getParameter("instDate" + i) != null) {
					  
					  String sDate1=request.getParameter("instDate" + i);
					  Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
                      ls.setInstDate(date1);
					 
				  } 

				if(request.getParameter("instAmt" + i) != null) {
					  
					  String insa=request.getParameter("instAmt" + i);
					  int ina=Integer.parseInt(insa);
					  ls.setInstAmt(ina);
				  } 
				
				if(request.getParameter("instPaid" + i) != null) {
					  
					  String insa=request.getParameter("instPaid" + i);
					  int ina=Integer.parseInt(insa);
					  ls.setInstPaid(ina);
				  } 
				
		
				if(request.getParameter("instPaidDate" + i) != null) {
					  
					  String sDate2=request.getParameter("instPaidDate" + i);
					  Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);  
                    ls.setInstPaidDate(date1);
					 
				  } 

				loanRequest.setAppNo(appNo);
				loanRequest.setAppDate(appDate);
				
				ls.setAppNo(loanRequest);
				ls.setAppDate(loanRequest);
				
				insertStatusMR= loanTrackingService.addLoanSchhedule(ls);
				
				if (insertStatusMR) {
					System.out.println("Counter" + flag);
					flag++;

				}
				
			}
			
			
			if (flag > 0) {
				session.setAttribute("Message", "Data added successfully.");
				
			} else {
				System.out.println("Enter into  failure part :");
				
			}

		
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
	
	
	
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/loanTracking";
		
	}
	
}



