package com.hrms.service;

import java.util.List;

import com.hrms.model.Department;


public interface DepartmentService 
{
	public void addDepartment(Department department);
	boolean checkDepartmentExists(Department department);
	List<Department>getAllDepartments();
	Department findDepartmentById(String id);
	public void updateDepartment(Department d);
	public void removeDepartment(String id);
	public List<Department> findDepartmentByEmpCode(String empCode);

}
