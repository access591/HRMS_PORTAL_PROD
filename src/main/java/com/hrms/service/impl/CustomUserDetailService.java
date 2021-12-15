package com.hrms.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.hrms.model.CustomUserDetail;
import com.hrms.model.UserEntity;
import com.hrms.model.UserRole;
import com.hrms.service.UserProgramRightService;
import com.hrms.service.UserRoleService;
import com.hrms.service.UserService;
import com.hrms.util.UserSecurityUtil;

@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired UserProgramRightService userProgramRightService;
	@Autowired UserService userService; 
	@Autowired UserRoleService userRoleService;
	 @Resource
	    private UserDetailsService userDetailsService;
	

	@Override
	public UserDetails loadUserByUsername(String usercode) throws UsernameNotFoundException {
		
		System.out.println("=======user====== "+usercode);

		
		UserEntity u = userService.findByUserCode(usercode);
		
		
		if(u == null) {
			throw new UsernameNotFoundException("No User is available in this User Code ");
		}
		

		
		
		UserSecurityUtil user = new UserSecurityUtil();
		
		List<UserRole> userRole = userRoleService.findUserRoleBuUser(usercode);
		
		user.setUserEntity(u);
		
		if(userRole != null) {
			user.setUserRoles(userRole);
		}
		
		
		return new CustomUserDetail(user);
	}
	
	

}
