package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.EmployeePayDetail;

public interface EmployeePayDetailDao extends GenericDao<EmployeePayDetail>{
	
	public boolean isEmployeePayExists(String empCode);

	

}
