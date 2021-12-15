package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDenieContoller {
	
	
	@RequestMapping("/UnauthorizePage")
	public String accessDenie() {
		return "UnauthorizePage";
	}

}
