package com.hrms.controller;

import java.util.List;

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

import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;


@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	EmployeeService employeeService;
	/**
	 * Method to get Department Result 	
	 * @param model
	 * @param session
	 * @return
	 */	
@GetMapping("/departmentMaster")
public String departmentMaster(Model model,HttpSession session) {
	if(session.getAttribute("username")==null) {
		return "redirect:" + "./";
	}
	List<Department> listDepartment = departmentService.getAllDepartments();
	model.addAttribute("listDepartment", listDepartment);
	String userCode= (String)session.getAttribute("username");
	session.setAttribute("imgUtil", new ImageUtil());
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
	
	  return "departmentMaster";
	}
/**
 * Method to Save Department Details	
 * @param model
 * @param session
 * @return
 */	
@PostMapping("/saveDepartment")
	public String saveDepartment(@ModelAttribute("department") Department department, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
	
	boolean isModuleExist = departmentService.checkDepartmentExists(department);	
	
	
	if (isModuleExist) {
		
	   redirectAttributes.addFlashAttribute("message", "Department Name Already exists !  ");
	   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	 
		return"redirect:/departmentMaster";
}
else 
{    department.setInsBy((String) session.getAttribute("USER_NAME"));
	departmentService.addDepartment(department);
	  redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!! ");
    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	List<Department> listDepartment = departmentService.getAllDepartments();
	model.addAttribute("listDepartment", listDepartment);
	
}
	return"redirect:/departmentMaster";
}	
/**
 * Method to Edit Department Details	
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/editDepartment/{id}"})
public String editdepartment(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	List<Employee> listEmployee = employeeService.findByDepartmentCode(id);
	model.addAttribute("listEmployee", listEmployee);
	
	
	Department departmentEdit = departmentService.findDepartmentById(id);
	  model.addAttribute("departmentEdit", departmentEdit);

   
    return "editDepartment";
}
/**
 * Method to Update Department Details	
 * @param model
 * @param session
 * @return
 */	
@PostMapping("/updateDepartment")
public String updateDepartment(@ModelAttribute("deptupdate")  Department department, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
 
	   try {
		
		   	  department.setUpdBy((String) session.getAttribute("USER_NAME"));
			  this.departmentService.updateDepartment(department);   
			   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   
	} catch (Exception e) {
	System.out.println("exception "+e);
	}
	
  	  
	   return"redirect:/departmentMaster";
}

/**
 * Method to Delete  Department Details	
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/deleteDepartment/{id}"})
public String deletedepartment(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { 
	  this.departmentService.removeDepartment(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
	    return"redirect:/departmentMaster";
}

@ResponseBody
@GetMapping(value = {"getdepartmentd/{deptCode}"})
public Department getDepartmentByDeptId(@PathVariable("deptCode") String deptCode) {
	
	System.out.println("get department by department id");
	return departmentService.findDepartmentById(deptCode);
	
}

}