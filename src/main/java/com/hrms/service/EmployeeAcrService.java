package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeeAcr;

public interface EmployeeAcrService {

	List<EmployeeAcr> getAllEmployeeAcr();

	boolean addEmployeeAcr(EmployeeAcr employeeAcr);

	void removeEmployeeAcr(long id);

	EmployeeAcr findByIdEmployeeAcr(long id);

	void updateEmployeePromotion(EmployeeAcr employeeAcr);
	
	public EmployeeAcr findEmployeeAcrByEmpCode(String empCode);

}
