package com.hrms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.EmployeeGradationExcel;
import com.hrms.ReportUtil;
import com.hrms.model.ApplicantInfo;
import com.hrms.model.Category;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.InterviewMaster;
import com.hrms.model.CommonUtil;
import com.hrms.model.MenuModule;
import com.hrms.reports.EmployeeJoiningLetter;
import com.hrms.reports.EmployeeOfferLetter;
import com.hrms.service.ApplicantInfoService;
import com.hrms.service.CategoryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeInformationReportController {

	
	@Autowired private ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired ReportUtil reportUtil;
	@Autowired EmployeeService employeeService;
	@Autowired EmployeeGradationExcel employeeGradationExcel;
	@Autowired CategoryService categoryService;
	@Autowired DesignationService designationService;
	@Autowired DepartmentService departmentService;
	@Autowired InterviewMasterService interviewMasterService;
	
	@Autowired ApplicantInfoService applicantInfoService;
	
	
	@Autowired EmployeeOfferLetter employeeOfferLetter;
	@Autowired EmployeeJoiningLetter employeeJoiningLetter;

//	Gradation / Employee controlller
	
	@GetMapping("/employeeInformation")
	public String employeeInformation(Model model, HttpSession session) {
		
		
		List<MenuModule> modules = moduleService.getAllModulesList(session.getAttribute("username").toString());
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory", listCategory);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "employeeInformation";
	}
	
	@ResponseBody
	@PostMapping("/employeeDetailPdf")
	public String employeeDetailPdf(@RequestParam("designation_name") String designation , Model model, 
			HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("designation is : "+ designation);
		String reportName = "EmployeeDetail";
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		
		
		System.out.println("list employee : "+ listEmployee);
		reportUtil.allEmployeeReport(request, response, reportName, listEmployee);
		return null;
	}
	
	
	
	@ResponseBody
	@GetMapping("getEmployeeByCategory/{categoryName}")
	public List<Employee> getEmployeeByCategory(@PathVariable("categoryName") String categoryCode) {
		
		System.out.println("category code : " + categoryCode);
		List<Employee> listEmployeeByCategory = null;
		if(categoryCode.equals("0")) {
			System.out.println(" hiiii");
			listEmployeeByCategory = employeeService.getAllEmployees();
		}
		else {
			listEmployeeByCategory = employeeService.getEmployeeByCategoryCode(categoryCode);
			
			
		}
		return listEmployeeByCategory;
	}
	
	@PostMapping("/createExcelsheet")
	public ResponseEntity<InputStreamResource> createExcelsheet(HttpServletRequest req, HttpServletResponse re) {
		
		
		
		
		String categoryType = req.getParameter("select_category");
		String employeeType = req.getParameter("employeeName");
		
		System.out.println("category type : " + categoryType);
		System.out.println("employee type : " + employeeType);
		
		List<Employee> listEmployee = null;
		List<Employee> listEmployee1 = new ArrayList<>();
		
		 if(categoryType.equals("") || categoryType==null) {
				System.out.println("all employee and category ");
				listEmployee = employeeService.getAllEmployees();
				
				listEmployee1.addAll(listEmployee);
				
			}
		else {
				System.out.println("else block ");
				//all employee of particular department
				if(employeeType.equals("") || employeeType == null) {
					System.out.println(" alll employee by category id");
					listEmployee = employeeService.getEmployeeByCategoryCode(categoryType);
					listEmployee1.addAll(listEmployee);
					
				}
				/* only one employee of particular department */
				else {
					System.out.println("one employee");
					Employee employee = employeeService.findEmployeeById(employeeType);
					listEmployee1.add(employee);
					
				}	
			}
		 System.out.println("resources size = "+listEmployee1.size());
		ByteArrayInputStream in = employeeGradationExcel.generateExcel(listEmployee1);
		
		 
		HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
		  "attachment ; filename=employee.xlsx");
		  
		  
		  return ResponseEntity.ok().headers(headers).body(new
		  InputStreamResource(in));
		
		
	}
	
	
	
	//Birth Report Controller
	
	@GetMapping("/birthAnniversary")
	public String viewBirthAniversaryUi(Model model, HttpSession session) {
		
		
		List<MenuModule> modules = moduleService.getAllModulesList(session.getAttribute("username").toString());
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		

		session.setAttribute("username", session.getAttribute("username"));

		return "birthAnniversary";
	}
	
	@PostMapping("/createbirtAnnReport")
	public String createBirthAniversaryUi(@RequestParam("month")String month,@RequestParam("reportType")String reportType,
											Model model, HttpSession session,HttpServletRequest req,
											HttpServletResponse response) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		System.out.println("report type value : "+reportType);
		System.out.println("month value : "+month);
		
		List<CommonUtil> em = new ArrayList<>();
		CommonUtil empl;
		List<Employee> employeeList ;
		
		if(month.equals("0")) {
			employeeList = employeeService.getAllEmployees();
			System.out.println("All case");
		}else {
			
			System.out.println("else block");
			employeeList = employeeService.findByDateOfJoiningMonth( Integer.parseInt(month));
		}
		
		if(reportType.equals("B")) {
			for(int i =0 ;i<employeeList.size();i++) {
				
				Department department = departmentService.findDepartmentById(employeeList.get(i).getDepartment().getDepartmentCode());
				
				empl = new CommonUtil(employeeList.get(i).getEmpCode(),employeeList.get(i).getEmpName(),
						department.getDeptName(),employeeList.get(i).getMartialStatus(),employeeList.get(i).getDoj());
				em.add(empl);
			}
			System.out.println("ëm size : "+ em.size());
			String reportFileName = "birtReport";
			reportUtil.birthAnniversaryReport(req, response, reportFileName,em);
		}else if(reportType.equals("A")) {
			for(int i =0 ;i<employeeList.size();i++) {
				Department department = departmentService.findDepartmentById(employeeList.get(i).getDepartment().getDepartmentCode());
				
				empl = new CommonUtil(employeeList.get(i).getEmpCode(),employeeList.get(i).getEmpName(),
						department.getDeptName(),employeeList.get(i).getMartialStatus(),employeeList.get(i).getDoj());
				em.add(empl);
			}
			System.out.println("ëm size : "+ em.size());
			String reportFileName = "Anniversary";
			reportUtil.birthAnniversaryReport(req, response, reportFileName,em);
		}
		
		
		
		session.setAttribute("username", session.getAttribute("username"));

		return "birthAnniversary";
	}
	
	
//EMPLOYEE JOINING / OFFER LETTER REPORT 
	
	@GetMapping("/joining_offerLetter")
	public String employeeJoiningReport(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<InterviewMaster> interviewMasterList = interviewMasterService.getFinalSelection();
				
		if(interviewMasterList != null) {
			model.addAttribute("employeeList", interviewMasterList);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "joining_offerLetter"; 
	}
	
	
	@PostMapping("/createJoinOfferLetter")
	public String createJoiningOfferLetter(@RequestParam("empName")String empCode,@RequestParam("reportType")String reportType,
											Model model, HttpSession session,HttpServletRequest req,
											HttpServletResponse response) throws IOException {
		
		

		System.out.println("report type value : "+reportType);
		System.out.println("employee code value : "+empCode); 
		
		
		
		try {
			
			ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(empCode);
			
			String reportFileName = null;
			if(reportType.equals("J")) {
				reportFileName = "joiningLetter";
				//reportUtil.employeeJoiningLetter2(req, response, reportFileName, em);
				employeeJoiningLetter.employeeJoiningLetter(req, response, reportFileName,applicantInfo);
			}
			else {
				reportFileName = "OfferLetter";
				employeeOfferLetter.employeeOfferLetter(req, response, reportFileName,applicantInfo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		session.setAttribute("username", session.getAttribute("username"));

		return null;
	}
}
