package com.hrms.service;

import java.util.List;

import com.hrms.model.Role;
import com.hrms.model.UserEntity;
import com.hrms.model.UserRole;

public interface UserRoleService {
	
	public void addUserRole(UserRole userRole);
	List<UserRole> findUserRoleBuUser(String userCode);
	
	public boolean roleExistForUser(String userCode , String roleName);
	
	public void deleteUserRole(UserEntity u, Role role);

}
