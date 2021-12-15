package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.dao.CaptchaUtil;
import com.hrms.model.Login;

import cn.apiclub.captcha.Captcha;

@Controller
public class SigninController {

	@GetMapping("/signin")
	public String signInPage(Model model) {
		
		Login user = new Login();
		getCaptcha(user);
		
		System.out.println("retrun captcha");
		model.addAttribute("user", user);
		return "sign-in";
		
	}
	
	
	private void getCaptcha(Login user) {
		Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
		user.setHiddenCaptcha(captcha.getAnswer());
		
		user.setCaptcha(""); // value entered by the User
		user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
		
	}

}
