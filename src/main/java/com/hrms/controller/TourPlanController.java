package com.hrms.controller;



import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.City;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;

import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;

import com.hrms.model.TourPlanDetails;
import com.hrms.service.CityService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TourPlanDetailService;
import com.hrms.service.TourPlanService;

@Controller
public class TourPlanController {
	

	@Autowired
	DepartmentService departmentService;
	
	@Autowired 
	ModuleService moduleService;
	@Autowired 
	 EmployeeService employeeService;
	@Autowired
    DesignationService designationService;
	@Autowired 
	TourPlanService tourPlanService;
	@Autowired
	TourPlanDetailService tourPlanDetailService;
	@Autowired CityService cityService;
	
	@InitBinder("tourPlan")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "tourPlanDate",new CustomDateEditor(dateFormatter, true));
       
    }
	
	@InitBinder("tourPlanEdit")
    public void customizeBindingedit (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "tourPlanDate",new CustomDateEditor(dateFormatter, true));
       
    }

	@GetMapping("/tourPlan")
	public String tourPlan(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<TourPlan> listTourPlan=tourPlanService.getAllTourPlan();
		model.addAttribute("ListTourPlan", listTourPlan);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		List<City> cityList =cityService.getAllCities();
		model.addAttribute("cityList",cityList);
	
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "tourPlan";
	}  
	
	
	@PostMapping("/saveTourPlan")
	public String saveTourPlan(@ModelAttribute("tourPlan")TourPlan tourPlan, Model model, HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String insertedBY = (String) session.getAttribute("USER_NAME");
		
		TourPlanDetails tourPlanDetail=new TourPlanDetails();
		tourPlan.setApprovalStatus("N");
		tourPlan.setInsBy(insertedBY);
		tourPlan.setInsBy(insertedBY);
		tourPlanService.addTourPlan(tourPlan);
		 int flag = 0;
	   		int counter = 1;
			try {
				
				
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				
			
				for (int i =0; i < counter; i++) 
				{
					System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					if (request.getParameter("startPlace" + i) != null) {
						tourPlanDetail.setStartPlace(request.getParameter("startPlace" + i));
					} else {
						tourPlanDetail.setStartPlace("" + i);
					}
					
					if(request.getParameter("endPlace" + i) != null) {
						tourPlanDetail.setEndPlace(request.getParameter("endPlace" + i));
					} else {
						tourPlanDetail.setEndPlace("" + i);
					}
					
				
					if(request.getParameter("fromDate" + i) != null) {
						
						String sDate1 = request.getParameter("fromDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						tourPlanDetail.setFromDate(date1);
						
					}
					
					if(request.getParameter("toDate" + i) != null) {
						String sDate1 = request.getParameter("toDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						tourPlanDetail.setToDate(date1);
					} 
					
					
					if(request.getParameter("purpose" + i) != null) {
						tourPlanDetail.setPurpose(request.getParameter("purpose" + i));
					} else {
						tourPlanDetail.setPurpose("" + i);
					}
					tourPlan.setTourPlanId(tourPlan.getTourPlanId());
					tourPlan.setTourPlanDate(tourPlan.getTourPlanDate());
					tourPlanDetail.setTourPlanId(tourPlan);
					tourPlanDetail.setTourPlanDate(tourPlan);
					
					insertStatusMR= tourPlanDetailService.addTourPlanDetail(tourPlanDetail);
					
					if (insertStatusMR) {
						System.out.println("Counter" + flag);
						flag++;

					}
					
				}
				
				
				if (flag > 0) {
					session.setAttribute("Message", "Data added successfully.");
					redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				} else {
					System.out.println("Enter into  failure part :");
					
				}

			
			} catch (Exception e) {
				
				
				e.printStackTrace();
			}
		
		
		
		
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/tourPlan";
	}
	
	@GetMapping(value = {"/editTourPlan/{id}"})
	public String editTourPlan(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		session.setAttribute("imgUtil", new ImageUtil());
	
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		TourPlan tourPlanEdit =	tourPlanService.findByIdTourPlan(id);
		  model.addAttribute("tourPlanEdit", tourPlanEdit);

	   
	    return "editTourPlan";
	}

	@PostMapping("/updateTourPlan")
	  public String updateTourPlan(@ModelAttribute("tourPlanEdit") TourPlan tourPlan, Model model, HttpSession session,RedirectAttributes redirectAttributes){
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		tourPlan.setApprovalStatus("N");
		this.tourPlanService.updateTourPlan(tourPlan);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/tourPlan";
	  }

	@GetMapping(value = { "/deleteTourPlan/{id}" })
public String deleteTourPlan(@PathVariable("id") String id, Model model,
		HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	try {
		
		tourPlanService.removeTourPlan(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/tourPlan";
	} catch (Exception e) {
		e.printStackTrace();
	}
	redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
	redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	return "redirect:/tourPlan";
}
}
