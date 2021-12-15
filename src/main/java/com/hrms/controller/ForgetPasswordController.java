package com.hrms.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.EncryptionUtil;
import com.hrms.model.UserEntity;
import com.hrms.service.UserService;
@Controller
public class ForgetPasswordController {
	
	@Autowired
	UserService userService;
	@Autowired PasswordEncoder passwordEncoder;
	@GetMapping("/forgetPassword")
	public String showForgotPasswordForm()
	{
	    return "forgotPasswordForm";
	}
	
	@PostMapping("/forgotMsgPassword")
	public String forgotMsgPassword(@RequestParam("password")String password, @ModelAttribute("user") UserEntity userEntity, Model model, HttpSession session,RedirectAttributes redirectAttributes) throws IOException {
		
		boolean isUserExist = userService.checkUserExistsOrNotRest(userEntity);
		if (isUserExist) {
			boolean result = userService.updatePassword(userEntity.getUserCode(), passwordEncoder.encode(password));
			 //this.userService.updateResetPasssword(userEntity);
			
			
			if(result) {
				System.out.println("true");
				redirectAttributes.addFlashAttribute("message", "User Password has Updated  ");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}else {
				System.out.println("false");
				redirectAttributes.addFlashAttribute("message", "Something went Wrong !!");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}
			
			redirectAttributes.addFlashAttribute("message", "Password Change Successfully  !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
			return"redirect:/forgetPassword";
		} else {
			
			redirectAttributes.addFlashAttribute("message", "please Contact To Administrator !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
			
		}
		
		
		
	
		return"redirect:/forgetPassword";
	}
	
	
}
