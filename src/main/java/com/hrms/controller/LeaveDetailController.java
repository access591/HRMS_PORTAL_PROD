package com.hrms.controller;

import java.io.IOException;
import java.text.ParseException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Leave;
import com.hrms.model.LeaveDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;


@Controller
public class LeaveDetailController {
	
	@Autowired
	LeaveService leaveService;

	@Autowired
	private ModuleService moduleService;
	@Autowired
	LeaveDetailService leaveDetailService;

	/**
	 * Get All Leave Details list
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/leaveDetailMaster")
	public String leaveDetailMaster(Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		model.addAttribute("listLeaveDetail", listLeaveDetail);
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "leaveDetailMaster";
	}

	/**
	 * save leave Details
	 * 
	 * @param leaveDetail
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveLeaveDetail")
	public String saveLeaveDetail(@ModelAttribute("leaveDetail") LeaveDetail leaveDetail, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		String insertedBY = (String) session.getAttribute("USER_NAME");
		leaveDetail.setInsBy(insertedBY);
		leaveDetailService.addLeaveDetail(leaveDetail);
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		 return "redirect:/leaveDetailMaster"; 

	}

	/**
	 * Find Record And Set update form leave page
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editLeaveDetail/{id}" })
	public String editLeaveDetail(@PathVariable("id") String id, Model model, HttpSession session) {
		
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		LeaveDetail leaveDetailEdit = leaveDetailService.findLeaveDetailById(id);
		model.addAttribute("leaveDetailEdit", leaveDetailEdit);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));

		return "editLeaveDetail";
	}

	/**
	 * 
	 * @param update  leave details
	 * @param model
	 * @param session
	 * @return
	 */

	@PostMapping("/updateLeaveDetail")
	public String updateLeaveDetail(@ModelAttribute("leaveDetail") LeaveDetail c, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
	
		c.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.leaveDetailService.updateLeaveDetail(c);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/leaveDetailMaster";
	}

	/**
	 * delete leave Details
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteLeaveDetail/{id}" })
	public String deleteLeaveDetail(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		this.leaveDetailService.removeLeaveDetail(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		session.setAttribute("username", session.getAttribute("username"));
		 return "redirect:/leaveDetailMaster"; 
	}
	@GetMapping(value = { "/reportLeaveDetail" })
	public String reportLeaveDetail(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response)  throws ParseException, IOException {
		List<LeaveDetail> dataList = leaveDetailService.getAllLeaveDetails();

		String reportFileName = "";

		String val = null;
		if (request.getParameter("_ex") != null) {
			val = request.getParameter("_ex");
		}
		if (val.equals("P")) {
			System.out.println("heloo0000000000" + val);

			reportFileName = "leavedetail_pdf2";
			leaveDetailService.leaveReportGenratepdf(request, response, reportFileName, dataList);
		} else if (val.equals("E")) {
			reportFileName = "leavedetail_XLS";
			String filename = "leavedetail";
			leaveDetailService.generateEXCELleaveDetailService(request, response, reportFileName, filename,dataList);

		}
		session.setAttribute("username", session.getAttribute("username"));
		return null;

	}
}
