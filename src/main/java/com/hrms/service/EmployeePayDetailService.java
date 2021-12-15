package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeePayDetail;

public interface EmployeePayDetailService {
	public void addEmployeePayDetail(EmployeePayDetail employeePayDetail);

	List<EmployeePayDetail> getAllEmployeePayDetail();

	EmployeePayDetail findEmployeePayDetaildById(String empCode);

	public void updateEmployeePayDetail(EmployeePayDetail c);

	public void removeEmployeePayDetail(long id);
	
	public boolean isEmployeePayExists(String empCode);

}
