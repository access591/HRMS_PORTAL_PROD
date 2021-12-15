package com.hrms.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Employee;

import com.hrms.model.MedicalReimbursement;
import com.hrms.model.MedicalReimbursementDetail;
import com.hrms.util.MedicalReimbursementUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.MedicalReimbursementDetailsService;
import com.hrms.service.MedicalReimbursementService;
import com.hrms.service.ModuleService;

@Controller
public class MedicalReimbursementController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	MedicalReimbursementService medicalReimbursementService;
	@Autowired
	MedicalReimbursementDetailsService medicalReimbursementDetailsService;
	
	@InitBinder("medicalReimbursement")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dateOfSlip",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "claimingDate", new CustomDateEditor(dateFormatter, true));
				
       
    }

	@GetMapping("/medicalReimbursement")
	public String medicalReimbursement(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		List<MedicalReimbursement> listMedicalReimbursement =  medicalReimbursementService.getAllMedicalReimbursement();
		model.addAttribute("listMedicalReimbursement", listMedicalReimbursement);
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		List<MedicalReimbursementDetail> listMedicalReimbursementDetail=medicalReimbursementDetailsService.getAllMedicalReimbursementDetails();
		model.addAttribute("listMedicalReimbursementDetail", listMedicalReimbursementDetail);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "medicalReimbursement";
	}
	
	@PostMapping("/saveMedicalReimbursement")
	String saveMedicalReimbursement(@ModelAttribute("medicalReimbursement") MedicalReimbursementUtil medicalReimbursement, Model model,HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes){
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		 MedicalReimbursement m2=new MedicalReimbursement();
		 MedicalReimbursementDetail m4=new MedicalReimbursementDetail();
		 Employee emp=new Employee();
		 emp.setEmpCode(medicalReimbursement.getEmpCode());
		 m2.setEmpCode(emp);
		 m2.setDateOfSlip(medicalReimbursement.getDateOfSlip());
		 m2.setNameOfPerson(medicalReimbursement.getNameOfPerson());
		 m2.setEmpRelation(medicalReimbursement.getEmpRelation());
		 m2.setMedIndOut(medicalReimbursement.getMedIndOut());
		 m2.setTreatmentType(medicalReimbursement.getTreatmentType());
		 m2.setTreatDescription(medicalReimbursement.getTreatDescription());
		 m2.setClaimingDate(medicalReimbursement.getClaimingDate());
		 m2.setApprovalStatus("N");
		 medicalReimbursementService.addMedicalReimbursement(m2);
		
           String slipNo=m2.getSlipNo();
           session.setAttribute("slipNo",slipNo);
         
           
           System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+slipNo);
        
		
        int flag = 0;
   		int counter = 1;
		try {
			
			
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr"));
			System.out.println("counter::::::::::::::::::::" + counter);
			
		
			for (int i =0; i < counter; i++) 
			{
				System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				if (request.getParameter("medStDr" + i) != null) {
					m4.setMedStDr(request.getParameter("medStDr" + i));
				} else {
					m4.setMedStDr("" + i);
				}
				
				if(request.getParameter("caseMemoNo" + i) != null) {
					m4.setCaseMemoNo(request.getParameter("caseMemoNo" + i));
				} else {
					m4.setCaseMemoNo("" + i);
				}
				
			
				if(request.getParameter("caseMemoDate" + i) != null) {
					String sDate1 = request.getParameter("caseMemoDate" + i);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					m4.setCaseMemoDate(date1);
				} 
				
				
			
					
				if(request.getParameter("calmlAmount" + i) != null) {
					m4.setCalmlAmount(request.getParameter("calmlAmount" + i));
				} else {
					m4.setCalmlAmount("" + i);
				}
				
				if(request.getParameter("passedAmount" + i) != null) {
					m4.setPassedAmount(request.getParameter("passedAmount" + i));
				} else {
					m4.setPassedAmount("" + i);
				}
				
				if(request.getParameter("govtexemptionAmount" + i) != null) {
					m4.setGovtexemptionAmount(request.getParameter("govtexemptionAmount" + i));
				} else {
					m4.setGovtexemptionAmount("" + i);
				}
				
				if(request.getParameter("remark" + i) != null) {
					m4.setRemark(request.getParameter("remark" + i));
				} else {
					m4.setRemark("" + i);
				}
				
				
			
		       m2.setSlipNo(slipNo);
				m4.setSlipNo(m2);
				insertStatusMR= medicalReimbursementDetailsService.addMedicalReimbursementDetails(m4);
				
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
		return "redirect:/medicalReimbursement";

		
	}
	@GetMapping(value = {"/editMedicalReimbursement/{id}"})
	public String editMedicalReimbursement(@PathVariable("id")String id,  Model model,HttpSession session)
	 { session.setAttribute("imgUtil", new ImageUtil());
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		MedicalReimbursement medicalReimbursementEdit =	medicalReimbursementService.findByIdMedicalReimbursementMaster(id);
		  model.addAttribute("medicalReimbursementEdit", medicalReimbursementEdit);

	   
	    return "editMedicalReimbursement";
	}

	 @PostMapping("/updateMedicalReimbursement")
	  public String updateLeave(@ModelAttribute("medicalReimbursement") MedicalReimbursement medicalReimbursement, Model model,RedirectAttributes redirectAttributes) {
		  medicalReimbursement.setApprovalStatus("N");
		  
		  this.medicalReimbursementService.updateMedicalReimbursement(medicalReimbursement);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");   
			return "redirect:/medicalReimbursement";
	  }

	
	@GetMapping(value = { "/deleteMedicalReimbursement/{id}" })
	public String deleteEmployee(@PathVariable("id") String id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		try {
			
			medicalReimbursementService.removeMedicalReimbursement(id);
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "redirect:/medicalReimbursement";
	}
	
	 @CrossOrigin
	    @GetMapping("/medicalReimbursementViewDetails/{id}")
	    public ResponseEntity<MedicalReimbursementUtil> getMedicalReimbursementById(@PathVariable(value = "id") String id) {
		
		 MedicalReimbursementUtil medicalReimbursement=new MedicalReimbursementUtil();
		 MedicalReimbursement m1 = medicalReimbursementService.findByIdMedicalReimbursementMaster(id);
		
		 SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		 Date abc=m1.getDateOfSlip();
		  String formatedDate = format2.format(abc);
		 
		 System.out.println("date of slip >>>>>>>>>>>>>>>"+formatedDate);
		 
		 medicalReimbursement.setDateOfSlip(m1.getDateOfSlip());
		 
		 medicalReimbursement.setNameOfPerson(m1.getNameOfPerson());
		
		 medicalReimbursement.setEmpCode(m1.getEmpCode().getEmpCode());
		
	        return ResponseEntity.ok().body(medicalReimbursement);
	    }
	
	 
	 
	}


