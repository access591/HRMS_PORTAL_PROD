package com.hrms.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

import com.hrms.model.StaffPostingDuties;
import com.hrms.model.StaffPostingDutiesDetails;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.StaffPostingDutiesDetailsService;
import com.hrms.service.StaffPostingDutiesService;
import com.hrms.util.SaffPostingDutiesUtil;



@Controller
public class StaffPostingDutiesController {
	
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	StaffPostingDutiesService staffPostingDutiesService;
	@Autowired
	StaffPostingDutiesDetailsService staffPostingDutiesDetailsService;

	@GetMapping("/staffPostingDuties")
	public String staffPostingDuties(Model model,HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
			session.setAttribute("imgUtil", new ImageUtil());
		  List<StaffPostingDuties> listOfstaffDuties = staffPostingDutiesService.getAllStaffPostingDuties();
		  
		  List<SaffPostingDutiesUtil> listSaffPostingDutiesUtil=new ArrayList<>();
		  for (int i = 0; i < listOfstaffDuties.size(); i++) {
			  String empCode = listOfstaffDuties.get(i).getEmpCode().getEmpCode();
			  SaffPostingDutiesUtil listofutil=new SaffPostingDutiesUtil();
			  Employee employee = employeeService.findEmployeeById(empCode);
			    Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			    Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());
			    listofutil.setJobCode(listOfstaffDuties.get(i).getJobCode());
			    listofutil.setDeptName(department.getDeptName());
			    listofutil.setDesgName(designation.getDesgName());
			    listofutil.setEmpName(employee.getEmpName());
			    listSaffPostingDutiesUtil.add(listofutil);
			    model.addAttribute("listStaffDuties",listSaffPostingDutiesUtil);
			  
			  
			  
		  }
		  
		  
		  session.setAttribute("username",session.getAttribute("username"));
		  return"staffPostingDuties"; 
	}
	
	//@CrossOrigin
		@ResponseBody
	    @GetMapping("/viewEmployeeDetailsDuties/{id}")
	    public SaffPostingDutiesUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
			Employee e = employeeService.findEmployeeById(id);
			Department d = departmentService.findDepartmentById(e.getDepartment().getDepartmentCode());
			Designation des=designationService.findDesignationById(e.getDesignation().getDesgCode());
			return new SaffPostingDutiesUtil(e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getPayeeCode());
			
	      
	    }
		
		@PostMapping("/savestaffDuties")
		public String savestaffDuties(@ModelAttribute("saffPostingDuties") StaffPostingDuties staffduties, Model model,HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes) {
			if (session.getAttribute("username") == null) {
				return "redirect:" + "./";
			}
	
			
			String insertedBY = (String) session.getAttribute("USER_NAME");
			staffduties.setInsBy(insertedBY);
			this.staffPostingDutiesService.addStaffPostingDuties(staffduties);
			String jobCode=staffduties.getJobCode();
			   int flag = 0;
		   		int counter = 1;
				try {
					
					
					boolean insertStatusMR = false;
					counter = Integer.parseInt(request.getParameter("_cr"));
					System.out.println("counter::::::::::::::::::::" + counter);
					StaffPostingDutiesDetails spdd=new StaffPostingDutiesDetails();
				
					for (int i =0; i < counter; i++) 
					{
						System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						if(request.getParameter("jobDutie" + i) != null) {
							spdd.setJobDutie(request.getParameter("jobDutie" + i));
						} else {
							spdd.setJobDutie("" + i);
						}
						
	
						if(request.getParameter("fromDate" + i) != null) {
							
							String sDate1 = request.getParameter("fromDate" + i);
							Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
							spdd.setFromDate(date1);
							
						}
						if(request.getParameter("toDate" + i) != null) {
							
							String sDate1 = request.getParameter("toDate" + i);
							Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
							spdd.setToDate(date1);
							
						}
						
						if(request.getParameter("status" + i) != null) {
							spdd.setStatus(request.getParameter("status" + i));
						} else {
							spdd.setStatus("" + i);
						}
						staffduties.setJobCode(jobCode);
						
						spdd.setJobCode(staffduties);
						spdd.setEmpCode(staffduties.getEmpCode());	

						insertStatusMR= staffPostingDutiesDetailsService.addStaffPostingDutiesDetails(spdd);
						
						if (insertStatusMR) {
							System.out.println("Counter" + flag);
							flag++;

						}
						
					}
					
					
					if (flag > 0) {
						redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						
					} else {
						System.out.println("Enter into  failure part :");
						
					}
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
					
			

			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/staffPostingDuties";
			
		
		}
		
		
		
		@GetMapping(value = { "/editStaffPostingDuties/{id}" })
		public String editStaffPostingDuties(@PathVariable("id") String id, Model model, HttpSession session) {
			if (session.getAttribute("username") == null) {
				return "redirect:" + "./";
			}
			List<Employee> listEmployee = employeeService.getAllEmployees();
			  model.addAttribute("listEmployee", listEmployee);
			  
			  StaffPostingDuties staffPostingDutiesEdit = staffPostingDutiesService.staffPostingDutieById(id);
			model.addAttribute("staffPostingDutiesEdit", staffPostingDutiesEdit);
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", session.getAttribute("username"));
			 return "editStaffPostingDuties";
		}
		
		@PostMapping("/updateStaffDuties")
		public String updateStaffDuties(@ModelAttribute("staffPostingDutiesEdit")StaffPostingDuties staffduties, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
			if (session.getAttribute("username") == null) {
				return "redirect:" + "./";
			}

			this.staffPostingDutiesService.updateStaffPostingDuties(staffduties);
			session.setAttribute("username", session.getAttribute("username"));
			redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/staffPostingDuties";
			
		
		}
		
		
		
		/**
		 * Delete Staff
		 * @param id
		 * @param model
		 * @param session
		 * @return
		 */
			@GetMapping(value = { "/deleteStaffPostingDuties/{id}" })
			public String deleteStaffDuties(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
				if (session.getAttribute("username") == null) {
					return "redirect:" + "./";
				}
				this.staffPostingDutiesService.removestaffDuties(id);
				session.setAttribute("username", session.getAttribute("username"));
				redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				return "redirect:/staffPostingDuties";
			}
			
			
			@GetMapping(value = {"/reporStaffPostingDuties" })
			public String reporStaffPostingDuties(Model model, HttpSession session, HttpServletRequest request,
					HttpServletResponse response) {
				String val = null;
				 List<StaffPostingDuties> listOfstaffDuties = staffPostingDutiesService.getAllStaffPostingDuties();
				List<StaffPostingDutiesDetails>listDeatils=staffPostingDutiesDetailsService.getAllStaffpDetails();
				 session.setAttribute("username", session.getAttribute("username"));
				 List<SaffPostingDutiesUtil> dataList=new ArrayList<>();
			  	  List<SaffPostingDutiesUtil> v2=new ArrayList<>();
			  	  for(int j=0;j<listDeatils.size();j++)
			  		{
			  		SaffPostingDutiesUtil v=new SaffPostingDutiesUtil();
			  		String jobDuties = listDeatils.get(j).getJobDutie();  
			  		v.setJobDuties(jobDuties);
			  		 v2.add(v);
			  		}
				  for (int i = 0; i < listOfstaffDuties.size(); i++) {
					  
					  Format f = new SimpleDateFormat("dd-MMMM-yyyy");
					  String strDate = f.format(new Date());
					  String empCode = listOfstaffDuties.get(i).getEmpCode().getEmpCode();
					  SaffPostingDutiesUtil listofutil=new SaffPostingDutiesUtil();
					  Employee employee = employeeService.findEmployeeById(empCode);
					    Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
					    Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());
					    listofutil.setJobCode(listOfstaffDuties.get(i).getJobCode());
					    listofutil.setDeptName(department.getDeptName());
					    listofutil.setDesgName(designation.getDesgName());
					    listofutil.setEmpName(employee.getEmpName());
					    listofutil.setEmail(employee.getEmail());
					    listofutil.setMobileNumber(employee.getMobileNumber1());
					    listofutil.setAddress(employee.getCorrAdd1());
					    listofutil.setPositionCode(listOfstaffDuties.get(i).getPositionCode());
					    listofutil.setJobDuties(v2.get(i).getJobDuties());
					 
					    listofutil.setTimePeriod(strDate);
					  
					    dataList.add(listofutil);

	
				  }
				 
				String reportFileName = "";

				
				if (request.getParameter("_ex") != null) {
					val = request.getParameter("_ex");
					
					if (val.equals("P")) {
						System.out.println("heloo0000000000" + val);

						reportFileName = "staffPostingDuties_pdf";
						staffPostingDutiesService.staffPostingDutiesGenratepdf(request, response, reportFileName, dataList);
					} else if (val.equals("E")) {
						//reportFileName = "bankwisereport_XLS";
						//String filename = "bankwisereport";

					}
					
					
					
				}
				
				
				
				return null;

			}
		

			}
			


