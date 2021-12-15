package com.hrms.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.model.LeaveRequest;

import com.hrms.service.LeaveRequestService;

@Controller
public class CommonEditViewController {
	
	@Autowired LeaveRequestService leaveRequestService;

	
//LEAVE REQUEST VIEW PAGE 
	@GetMapping(value = { "/viewLeaveRequest/{id}" })
	public String viewLeaveRequestByEmpId(@PathVariable("id")String leaveRequestId,
						Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		LeaveRequest leaveRequest = this.leaveRequestService.findLeaveRequestById(Long.parseLong(leaveRequestId));
		
		if(leaveRequest != null) {
			model.addAttribute("leaveRequest", leaveRequest);
		}
		
		model.addAttribute("header", "View Leave Request");
		model.addAttribute("myhref", "leaveRequest");
		return "viewLeaveRequest";
	}
	
	//LEAVE REQUEST APPROVAL VIEW PAGE 
	@GetMapping(value = { "/viewLeaveApproval/{id}" })
	public String viewLeaveRequestApprovalByEmpId(@PathVariable("id")String leaveRequestId,
						Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		LeaveRequest leaveRequest = this.leaveRequestService.findLeaveRequestById(Long.parseLong(leaveRequestId));
		
		if(leaveRequest != null) {
			model.addAttribute("leaveRequest", leaveRequest);
		}
		
		model.addAttribute("header", "View Leave Approval");
		model.addAttribute("myhref", "leaveApproval");
		return "viewLeaveRequest";
	}
	
}
