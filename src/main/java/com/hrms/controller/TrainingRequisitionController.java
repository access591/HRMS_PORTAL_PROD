package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.hrms.helper.Message1;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TrainingReqEmployeeDetail;
import com.hrms.model.TrainingRequisition;
import com.hrms.model.TrainingRequisitionDetail;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TrainingRequistionService;

@Controller
public class TrainingRequisitionController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	TrainingRequistionService trainingRequistionService;

	@InitBinder("trainingRequisition")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "trDate", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "trReqDate", new CustomDateEditor(dateFormatter, true));

	}

	@GetMapping("/trainingRequisition")
	public String trainingRequisitionPage(
			@ModelAttribute("trainingRequisition") TrainingRequisition trainingRequisition, Model model,
			HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Department> listDepartment = departmentService.getAllDepartments();
		if (listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}

		List<TrainingRequisition> listTrainingReqisition = trainingRequistionService.getAllTrainingRequisition();
		if (listTrainingReqisition != null) {
			model.addAttribute("listTrainingReqisition", listTrainingReqisition);
		}

		return "trainingRequisition";

	}

	@PostMapping("saveTrainingRequisition")
	public String saveTrainingRequisition(
			@ModelAttribute("trainingRequisition") TrainingRequisition trainingRequisition, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {
			for (TrainingRequisitionDetail t : trainingRequisition.getListTransactionRequisitionDetail()) {
				t.setTrainingRequisition(trainingRequisition);
			}
			for (TrainingReqEmployeeDetail t : trainingRequisition.getListTransactionReqEmployeeDetail()) {
				t.setTrainingRequisition(trainingRequisition);
			}
			
			
			trainingRequisition.setInsBy((String) session.getAttribute("USER_NAME"));
			trainingRequistionService.addTrainingRequisition(trainingRequisition);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		}catch(Exception e) {
			e.printStackTrace();

			redirectAttributes.addFlashAttribute("message", "Something went wrong!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		
		}
		

		return "redirect:trainingRequisition";
	}

	@GetMapping("editTrainingRequisition/{id}")
	public String editTrainingRequisition(@PathVariable("id") String trReqCode,
			@ModelAttribute("trainingRequisition") TrainingRequisition trainingRequisition, Model model,
			HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<Department> listDepartment = departmentService.getAllDepartments();
		if (listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}

		TrainingRequisition tr = trainingRequistionService.findById(trReqCode);
		if (tr != null) {
			model.addAttribute("trainingRequisition", tr);
		}
		return "editTrainingRequisition";
	}

	@PostMapping(value = { "updateTrainingRequisition" })
	public String updateTrainingRequisition(
			@ModelAttribute("trainingRequisition") TrainingRequisition trainingRequisition, Model model,RedirectAttributes redirectAttributes
			,HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("=====================>update employee Requisition");

		for (TrainingRequisitionDetail t : trainingRequisition.getListTransactionRequisitionDetail()) {
			t.setTrainingRequisition(trainingRequisition);

		}
		for (TrainingReqEmployeeDetail t : trainingRequisition.getListTransactionReqEmployeeDetail()) {
			t.setTrainingRequisition(trainingRequisition);
		}
		//trainingRequistionService.addTrainingRequisition(trainingRequisition);

		trainingRequisition.setUpdBy((String) session.getAttribute("USER_NAME"));
		
		
		
		this.trainingRequistionService.updateTrainingRequisition(trainingRequisition);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
		return "redirect:trainingRequisition";
	}

	@GetMapping("deleteTrainingRequisition/{id}")
	public String deleteTrainingRequisition(@PathVariable("id") String trainingRequisitionId, HttpSession session,RedirectAttributes redirectAttributes) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		trainingRequistionService.removeTrainingRequisition(trainingRequisitionId);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/trainingRequisition";
	}

	@ResponseBody
	@GetMapping("getTrainingInfoBytrCode/{id}")
	public TrainingRequisition getTrainingInfo(@PathVariable("id") String trReqCode) {

		System.out.println("hi ..");
		TrainingRequisition tr = trainingRequistionService.findById(trReqCode);
		System.out.println("traininfg requisition : " + tr.getTrReqDate());
		return tr;
	}

}
