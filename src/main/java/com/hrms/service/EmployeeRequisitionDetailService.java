package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeeRequisitionDetail;

public interface EmployeeRequisitionDetailService {

	public void addEmployeeRequisitionDetail(EmployeeRequisitionDetail employeReq);

	List<EmployeeRequisitionDetail > getAllEmployeeRequisitionDetail ();

	EmployeeRequisitionDetail  findEmployeeRequisitiondById(String empCode);

	public void updateEmployeeRequisition(EmployeeRequisitionDetail  c);

	
	
	public boolean isEmployeeRequisitionDetailExists(String empCode);

	void removeEmployeeRequisitionDetail(String id);
	
	public List<EmployeeRequisitionDetail> findUniqueDesignation();
	
}
