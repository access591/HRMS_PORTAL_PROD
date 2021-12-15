package com.hrms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.MenuModule;
import com.hrms.model.UserEntity;
import com.hrms.service.ModuleService;
import com.hrms.service.UserService;

@Controller
public class AdminController {
	
	@Autowired ModuleService moduleService;
	@Autowired UserService userService;
	@Autowired PasswordEncoder encode;
	
	
	@GetMapping("/admin")
	public String getAdminPannel(@ModelAttribute("user") UserEntity user, Model model,Principal principal) {
		
		System.out.println("admin controller");
		List<MenuModule> modules = moduleService.getAllModulesList(principal.getName());
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<UserEntity> userList = userService.getActiveUsers();
		if(userList != null) {
			model.addAttribute("userList", userList);
		}
		
		return "updatePassword"; //updatePassword.html 
		
	}
	
	@PostMapping("/updatePassword")
	public String saveOrUpdateUserPassword(@ModelAttribute("user") UserEntity user,RedirectAttributes redirectAttributes) {
		
		boolean result = userService.updatePassword(user.getUserCode(), encode.encode(user.getUserPass()));
		if(result) {
			System.out.println("true");
			redirectAttributes.addFlashAttribute("message", "User Password has Updated  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}else {
			System.out.println("false");
			redirectAttributes.addFlashAttribute("message", "Something went Wrong !!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		return "redirect:/admin";
	}
	
	
	
	@ResponseBody
	@GetMapping("/getuserdetail/{userCode}")
	public UserEntity getUserDetail(@PathVariable("userCode") String userCode) {
		
		System.out.println("get user details : "+ userCode);
		
		
		return userService.findByUserCode(userCode);
	}
	
	

}
