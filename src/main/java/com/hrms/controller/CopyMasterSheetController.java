package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class CopyMasterSheetController {
	
	int pageno = 50;
	String reqPage = "/copyMasterSheet";
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	

	@GetMapping("/copyMasterSheet")
	public String copyMasterSheet(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
}
