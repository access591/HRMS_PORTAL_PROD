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

import com.hrms.ImageUtil;
import com.hrms.helper.Message1;
import com.hrms.model.ApplicantInfo;
import com.hrms.model.InterviewMaster;
import com.hrms.model.MenuModule;
import com.hrms.service.ApplicantInfoService;
import com.hrms.service.DesignationService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;
import com.hrms.util.InterViewForm;

@Controller
public class InterviewController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	ApplicantInfoService applicantInfoService;
	@Autowired
	InterviewMasterService interviewMasterService;
	@Autowired
	DesignationService designationService;

	@InitBinder("form")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");//
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "interviewDate", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "applicantDate", new CustomDateEditor(dateFormatter, true));

	}
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", userCode);
	}

	// INTERVIEW DETAILS

	@GetMapping("interviewDetails")
	public String interviewDetails(Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}

		List<ApplicantInfo> listApplicantInfo = applicantInfoService.findApplicantInfoStatusForward();

		
		if (listApplicantInfo != null) {
			model.addAttribute("listApplicantInfo", listApplicantInfo);
		}
		
		List<InterviewMaster> im = new ArrayList<>();
		InterViewForm form = new InterViewForm();
		form.setInterviewForm(im);
		model.addAttribute("interviewMaster", form);

		
		return "interviewDetails"; 

	}

	@PostMapping("addInterviewDetail")
	public String addInterviewDetails(@ModelAttribute("interviewMaster") InterViewForm form, HttpSession session,
			RedirectAttributes redirectAttributes) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		System.out.println(form.getInterviewForm() != null ? form.getInterviewForm() : "null list");
		if (form.getInterviewForm() != null) {
			for (int i = 0; i < form.getInterviewForm().size(); i++) {

				InterviewMaster interviewMaster = form.getInterviewForm().get(i);
				interviewMasterService.addInterviewMaster(interviewMaster);
			}
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	

		} else {
			System.out.println("else block ");
			redirectAttributes.addFlashAttribute("message", " Something went Wrong !!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		return "redirect:interviewDetails";
	}

	@ResponseBody
	@GetMapping("getApplicantDate/{applicantCode}")
	public Date getRequisitionDateByAdvtCode(@PathVariable("applicantCode") String applicantCode) {
		
		
		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		
		return applicantInfo.getApplicantDate();
	}

	// INTERVIEW APPROVAL DETAILS

	@GetMapping("interviewApproval")
	public String interviewApprovalPage(Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		try {
			
			
			redirectAttributes.addFlashAttribute("message", " Detail has been Added !!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			e.printStackTrace();
			
			redirectAttributes.addFlashAttribute("message", " Something went Wrong !!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		List<ApplicantInfo> listApplicantInfo = applicantInfoService.findApplicantInfoStatusHoldAndPending();

		model.addAttribute("listInterviewApprovalUtil", listApplicantInfo);

		
		return "interviewApproval";
	}

	@GetMapping("approveInterview/{applicantCode}/{interviewStatus}")
	public String approveInterview(@PathVariable("interviewStatus") String interviewStatus,
			@PathVariable("applicantCode") String applicantCode,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		try {
			applicantInfoService.updateApplicantInfoInterviewStatus(applicantCode, interviewStatus);
			
			if(interviewStatus.equals("Forward")) {
				redirectAttributes.addFlashAttribute("message", "Forwarded ..  ");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}else if(interviewStatus.equals("Reject")) {
				redirectAttributes.addFlashAttribute("message", "Rejected !!  ");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}
			else if(interviewStatus.equals("Hold")) {
				//session.setAttribute("message",new Message1("","alert-primary"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		

		return "redirect:/interviewApproval";
	}

	// INTERVIEW FINAL SELECTION

	@GetMapping("interviewFinalSelection")
	public String interviewFinalSelection(Model model, HttpSession session) throws Exception {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		List<InterviewMaster> listInterviewMaster = interviewMasterService.getAllInterviewMaster();

		model.addAttribute("interviewFinalSelection", listInterviewMaster);
		
		return "interviewFinalSelection"; // interviewFinalSelection.html
	}

	@GetMapping("interviewFileApproval/{applicantCode}/{interviewCode}/{finalApprovalStatus}")
	public String interviewFinalApproval(@PathVariable("finalApprovalStatus") String finalApprovalStatus,
			@PathVariable("applicantCode") String applicantCode, @PathVariable("interviewCode") String interviewCode,
			HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		try {
			interviewMasterService.interviewFinalapproval(applicantCode, interviewCode, finalApprovalStatus);
			
			if(finalApprovalStatus.equals("Selected")) {
				redirectAttributes.addFlashAttribute("message", "Selected ..  ");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}else if(finalApprovalStatus.equals("Rejected")) {
				session.setAttribute("message",new Message1("Request has been Rejected","alert-primary"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/interviewFinalSelection";
	}

	@GetMapping("viewInterviewDetail/{id}")
	public String viewInterviewDetail(@ModelAttribute("interviewMaster") InterviewMaster interviewMaster,
			@PathVariable("id") String inteviewCode, Model model,
			HttpSession session) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		InterviewMaster interviewMaster1 = interviewMasterService.findinterviewMasterById(inteviewCode);

		if (interviewMaster1 != null) {
			model.addAttribute("interviewMaster", interviewMaster1);
		}

		return "viewInterviewDetail";
	}

}
