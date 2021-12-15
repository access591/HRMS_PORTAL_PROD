package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeeRequisition;

public interface EmployeeRequisitionService {
	
	
	
	public void addEmployeeRequisition(EmployeeRequisition employeReq);

	List<EmployeeRequisition> getAllEmployeeRequisition();

	EmployeeRequisition findEmployeeRequisitiondById(String empCode);

	public void updateEmployeeRequisition(EmployeeRequisition c);

	public List<EmployeeRequisition> findEmployeeReqByStatusN();
	
	List<EmployeeRequisition> findEmployeeReqByStatusY();
	
	public boolean isEmployeeRequisitionExists(String empCode);

	void removeEmployeeRequisition(String id);
	
	void approvedByReqCode(String reqCode);
	
	void approvedByReqCodeAndStatus(String reqCode,String requisitionApproval);
	
	public List<EmployeeRequisition> getAllPendingEmployeeRequisition();


}
