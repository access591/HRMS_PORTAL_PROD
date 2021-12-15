package com.hrms.service;

import com.hrms.model.Role;

public interface RoleRepository {
	
	public void addRole(Role role);
	public Role isRoleExistOrNot(String roleName);
	
	
	
	

}
