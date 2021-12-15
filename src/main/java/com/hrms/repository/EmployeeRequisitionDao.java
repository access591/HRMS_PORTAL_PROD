package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.EmployeeRequisition;

public interface EmployeeRequisitionDao extends GenericDao<EmployeeRequisition> {
	
	public List<EmployeeRequisition> findEmployeeReqByStatusN();
	
	public List<EmployeeRequisition> findEmployeeReqByStatusY();
	
	public void approvedStatusByReqCode(String reqCode);
	

}
