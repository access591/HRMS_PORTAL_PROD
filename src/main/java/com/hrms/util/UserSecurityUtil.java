package com.hrms.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.model.UserEntity;
import com.hrms.model.UserRole;

@Component
public class UserSecurityUtil {
	
	private UserEntity userEntity;
	private List<UserRole> userRoles;
	
	
	public UserSecurityUtil() {
		super();
		
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}


	public List<UserRole> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
	

}
