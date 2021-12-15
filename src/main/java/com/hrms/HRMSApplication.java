package com.hrms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hrms.model.UserEntity;
import com.hrms.service.UserService;
import com.hrms.service.UserServiceImpl;

@SpringBootApplication
public class HRMSApplication extends SpringBootServletInitializer {

	 @Autowired UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(HRMSApplication.class, args);

		System.out.println("Starting Application....");

	}
	
	@Autowired PasswordEncoder encoder;

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		//UserService u = new UserServiceImpl();
//
//		//UserEntity user = u.findByUserCode("102");
//		
//		UserEntity user = userService.findDataById("102");
//		
//		System.out.println("user : "+ user.getUserName());
//
//		String pass = encoder.encode("admin");
//		
//		System.out.println("password :"+ pass);
//		
//		//user.setUserPass(encoder.encode("admin"));
//
//		//user.updateUser(user);
//		//userService.updateUser(user);
//		
//		return null;
//	}

}
