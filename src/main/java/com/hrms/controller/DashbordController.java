package com.hrms.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.model.Category;
import com.hrms.model.Employee;
import com.hrms.model.InterviewMaster;
import com.hrms.model.MenuModule;
import com.hrms.model.OrderIssueTracking;
import com.hrms.model.TrackallEnquiries;
import com.hrms.model.UserEntity;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.BudgetProvisionService;
import com.hrms.service.CategoryService;
import com.hrms.service.EmployeeService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;
import com.hrms.service.OrderIssueTrackingService;
import com.hrms.service.TrackallEnquiriesService;
import com.hrms.service.UserService;
import com.hrms.util.PaiChart;

@Controller
public class DashbordController {

	@Autowired
	UserService userService;
	@Autowired
	private ModuleService moduleService;
	
	@Autowired EmployeeService employeeService;
	@Autowired BudgetProvisionService budgetProvisionService;
	
	@Autowired InterviewMasterService interviewMasterService;
	@Autowired AttendenceRegisterService attendenceRegisterService;
	@Autowired CategoryService categoryService;
	@Autowired OrderIssueTrackingService orderIssueTrackingService;
	@Autowired TrackallEnquiriesService trackallEnquiriesService; 
	
	

	@GetMapping("/getDashBoardData")
	public String getDashBoardData(Model model,HttpSession session) {
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		return "redirect:/";

	}
	
	@GetMapping("/home")
	public String homePage(Model model , HttpSession session,Principal principal) {
		
//		if(session.getAttribute("username") == null) {
//			return "redirect:" + "./";
//		}
		
		System.out.println("home controller");
		try {
			List<Employee> listEmployee = employeeService.getAllEmployees();
			model.addAttribute("employeeList", listEmployee.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService.findTodayAttendenceList();
			if(listAttendenceRegister != null) {
				model.addAttribute("listAttendenceRegister", listAttendenceRegister.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<AttendenceRegister> findTodayLeave = attendenceRegisterService.findTodayLeaveEmployee();
			if(findTodayLeave != null) {
				model.addAttribute("findTodayLeave", findTodayLeave.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<InterviewMaster> listInterviewMaster = interviewMasterService.getFinalSelection();
			if(listInterviewMaster != null) {
				model.addAttribute("finalSelection", listInterviewMaster.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<OrderIssueTracking> orderService = orderIssueTrackingService.getAllOrderIssueTracking();
			if(orderService != null) {
				model.addAttribute("orderTracking", orderService.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			List<TrackallEnquiries> trackList =  trackallEnquiriesService.getAllTrackallEnquiries();
			if(trackList != null) {
				model.addAttribute("trackList", trackList.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("chart", new Object[][] {
		    {"Airline",     "Price $"},
		    {"Delta",       100},
		    {"Southwest",   500},
		    {"Jet Blue",    300},
		    {"Canada Air",  300},
		    {"Average month price", 300}
		});
		
		String id = principal.getName();
		System.out.println("principal is : "+ id);
		
		
		UserEntity userRecord = userService.findDataById(id);
		session.setAttribute("uuuuu", userRecord.getUserName());
		session.setAttribute("USER_NAME", userRecord.getUserName());
		
		String profilePic = userRecord.getEmpCode().getProfilePic();
		
		session.setAttribute("profilePic", profilePic);
		
		System.out.println("==user image====>"+userRecord.getEmpCode().getProfilePic());

		//session.setAttribute("User_Profile_Pic", userRecord.getEmpCode().getImageProfile());
		//session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", principal.getName());
		String userCode = (String) session.getAttribute("username");
		
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		model.addAttribute("modules", modules);
		
		
		
		

		System.out.println("home dashbaord controller");
		
		return "dashboard";
	}
	
	
	
	
	@ResponseBody
	@GetMapping("getCategoryData")
	public Map<String, Long> getCategory() {
		
		System.out.println("================calling dashboad controller");
		
		Map<String, Long> countCategory = employeeService.countRecordByCategory();
		
		
		
		
		return countCategory;
	}
	
	@ResponseBody
	@GetMapping("demoChart")
	public Map<String, Long> getCategory1() {
		
		System.out.println("================calling dashboad controller========");
		
		List<PaiChart> paiChart = new ArrayList<>();
		
		Map<String, Integer> countCategory = new HashMap<>();
		countCategory.put("vt1st", 12);
		countCategory.put("vt2nd", 15);
        
		paiChart.add(new PaiChart("Chrome","18.55D"));
        paiChart.add(new PaiChart("Firefoc","19.99D"));
        paiChart.add(new PaiChart("IE","54.13D"));
        paiChart.add(new PaiChart("Oher","0.49D"));
		
        
        Map<String, Long> result = budgetProvisionService.findBudgetTrackDepartment();
		
		return result;
	}
	

}
