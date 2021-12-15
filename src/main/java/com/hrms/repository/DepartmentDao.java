package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Department;



public interface DepartmentDao extends GenericDao<Department>
{

	Department checkDepartmentExists(Department department);
	public List<Department> findByDepartmentByDeptCode(String empCode);

	
}
