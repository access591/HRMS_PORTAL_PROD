package com.hrms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hrms.model.BudgetProvision;
import com.hrms.model.Department;
import com.hrms.model.MenuModule;
import com.hrms.reports.BudgetReport;
import com.hrms.service.BudgetProvisionService;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class BudgetProvisionController {
	
	@Autowired private ModuleService moduleService;
	@Autowired DepartmentService departmentService;
	@Autowired BudgetProvisionService budgetProvisionService;
	@Autowired EmployeeService employeeService;
	
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		//budget provision page
		//edit budget provision 
		//budget report 
		List<Department> departmentList = departmentService.getAllDepartments();
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		String userCode = (String) session.getAttribute("username");
		session.setAttribute("username", userCode);
	}
	
	
	@InitBinder("budgetProvision")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "advtDate",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "dateOfSanction",
                new CustomDateEditor(dateFormatter, true));

       
    }
	
	@GetMapping("budgetprovisionpage")
	public String budgetProvisionPage(@ModelAttribute("budgetProvision")BudgetProvision budgetProvision,
			Model model,HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<BudgetProvision> listBudgetProvision = budgetProvisionService.getAllBudgetProvision();
		if (listBudgetProvision != null) {
			model.addAttribute("listBudgetProvision", listBudgetProvision);
		}
		
		return "budgetProvision";
		
	}
	
	@PostMapping("saveBudgetProvision")
	public String saveBudgetProvision(@ModelAttribute("budgetProvision")BudgetProvision budgetProvision,
			Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		try {
			
			
			budgetProvision.setInsBy((String) session.getAttribute("USER_NAME"));
			budgetProvisionService.saveBudgetProvision(budgetProvision);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}catch(Exception e ) {
			e.printStackTrace();
			
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		
		return "redirect:budgetprovisionpage";
	}
	
	@GetMapping("editBudgetProvision/{id}")
	public String editBudgetProvision(@PathVariable("id")String budgetProvisionCode,
			@ModelAttribute("budgetProvision")
							BudgetProvision budgetProvision,Model model,HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		BudgetProvision b = budgetProvisionService.findByBudgetProvisionId(
				Long.parseLong(budgetProvisionCode));
		
		
		
		if(b != null) {
			model.addAttribute("budgetProvision", b);
		}
		return "editBudgetProvision"; 
	}
	
	@PostMapping("updateBudgetProvision")
	public String updateBudgetProvision(@ModelAttribute("budgetProvision")BudgetProvision budgetProvision,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		try {
			
			
			budgetProvision.setUpdBy((String) session.getAttribute("USER_NAME"));
			budgetProvisionService.updateBudgetProvision(budgetProvision);
			
			redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		
		return "redirect:budgetprovisionpage";
	}
	
	@GetMapping(value = {"deleteBudgetProvision/{id}"})
	public String deleteBudgetProvision(@PathVariable("id") String orderTrackingId, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		try {
			budgetProvisionService.removeBudgetProvision(Long.parseLong(orderTrackingId));
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		
		
		return "redirect:/budgetprovisionpage";
	}
	
	
	
	
	@GetMapping("budgetReport")
	public String viewBudgetReport(Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		return "budgetReport";
	}
	
	@Autowired BudgetReport budgetReport;
	@PostMapping("createbudgetreport")
	public String createBudgetReport(@RequestParam("deptCode")String deptCode,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		
	
		if(deptCode.equals("ALL")) {
			List<BudgetProvision> budgetProvision = budgetProvisionService.getAllBudgetProvision();
			budgetReport.createBudgetReport(response, request, budgetProvision, "All");
		}
		else {
			List<BudgetProvision> budgetProvision = budgetProvisionService.findBudgetProvisionByDepartment(deptCode);
			
			String deptName = null;
			if(budgetProvision != null) {
				deptName = budgetProvision.get(0).getDepartment().getDeptName();
			}
			
			budgetReport.createBudgetReport(response, request, budgetProvision,deptName);
		}
		
		return null;
	}
	
	
	
	

}
