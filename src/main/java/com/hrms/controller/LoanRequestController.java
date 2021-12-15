package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.hrms.model.Loan;
import com.hrms.model.LoanApplication;
import com.hrms.util.LoanApplicationUtil;

import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LoanMaterService;
import com.hrms.service.LoanRequestService;
import com.hrms.service.ModuleService;

@Controller
public class LoanRequestController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	LoanMaterService loanMaterService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LoanRequestService loanRequestService;
	
	@InitBinder("LoanRequest")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "appDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "effScheduleDate", new CustomDateEditor(dateFormatter, true));
       
    }
	
	
	
	
	@GetMapping("/loanRequest")
	public String loanApplication(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		List<Loan> listLoan = loanMaterService.getAllLoans();
		model.addAttribute("listLoan", listLoan);
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<LoanApplication> listloanApplication=loanRequestService.getAllRequest();
		model.addAttribute("listloanApplication", listloanApplication);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "/loanRequest";
	}
	
	

	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeDetailsLoan/{id}")
    public LoanApplicationUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartment().getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignation().getDesgCode());
		 return new LoanApplicationUtil(e.getEmpName(),d.getDeptName(),
				des.getDesgName()
				,e.getPayeeCode());
		
      
    }

	
	@PostMapping("/saveLoanRequest")
	public String saveLoanRequest(@ModelAttribute("LoanRequest")LoanApplicationUtil loanAppUtil, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		LoanApplication loanRequest = new LoanApplication();
		Employee emp = new Employee();
		emp.setEmpCode(loanAppUtil.getEmpCode());
		loanRequest.setEmpCode(emp);

		Loan lo = new Loan();
		lo.setLoanCode(loanAppUtil.getLoanCode());
		loanRequest.setLoanCode(lo);
		String insertedBY = (String) session.getAttribute("USER_NAME");
		loanRequest.setInsBy(insertedBY);
		loanRequest.setApprovalStatus("N");

		loanRequest.setAppDate(loanAppUtil.getAppDate());

		loanRequest.setLoanType(loanAppUtil.getLoanType());

		loanRequest.setEffScheduleDate(loanAppUtil.getEffScheduleDate());

		loanRequest.setAmountRequired(loanAppUtil.getAmountRequired());

		loanRequest.setAmountSanctioned(loanAppUtil.getAmountSanctioned());

		loanRequest.setLoanStatus(loanAppUtil.getLoanStatus());
	

		this.loanRequestService.addLoanRequest(loanRequest);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/loanRequest";

	}
	@GetMapping(value = {"/editLoanRequest/{id}"})
	public String editLoanRequest(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> em = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", em);
		List<Loan> listLoan = loanMaterService.getAllLoans();
		model.addAttribute("listLoan", listLoan);
		LoanApplication loanRequestEdit =	loanRequestService.findByIdLoanReq(id);
		  model.addAttribute("loanRequestEdit", loanRequestEdit);
	   
	    return "editLoanRequest";
	}
	
	
	
	@PostMapping("/updateLoanRequest")
	public String updateLoanRequest(@ModelAttribute("LoanRequest") LoanApplicationUtil loanAppUtil, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	try {
		
		LoanApplication loanRequest = new LoanApplication();
		
		Loan lo = new Loan();
		lo.setLoanCode(loanAppUtil.getLoanCode());
		loanRequest.setLoanCode(lo);
		loanRequest.setAppDate(loanAppUtil.getAppDate());
		loanRequest.setLoanType(loanAppUtil.getLoanType());
		loanRequest.setEffScheduleDate(loanAppUtil.getEffScheduleDate());
		loanRequest.setAmountRequired(loanAppUtil.getAmountRequired());
		loanRequest.setAmountSanctioned(loanAppUtil.getAmountSanctioned());
		loanRequest.setLoanStatus(loanAppUtil.getLoanStatus());
		loanRequest.setAppNo(loanAppUtil.getAppNo());
		
	   this.loanRequestService.updateLoanRequest(loanRequest);
	   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
	   redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		return "redirect:/loanRequest";
			
	} catch (Exception e) {
	
		System.out.println(" "+e);
	}
	return "redirect:/loanRequest";

	
	}
	
	@GetMapping(value = {"/deleteLoanRequest/{id}"})
	public String deleteLoanRequest(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		try {
			this.loanRequestService.removeLoanRequest(id);
			
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/loanRequest";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Record  has been Maaped Other Table!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			e.printStackTrace();
		}
		return "redirect:/loanRequest";
	}

}
