package com.hrms.repository;


import java.util.Date;
import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.LeaveRequest;

public interface LeaveRequestDao  extends GenericDao<LeaveRequest>{
	
	List<LeaveRequest> findAllByEmpCode(String empCode);
	List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode,String applyDate);
	List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode);
	List<LeaveRequest> getEmployeeByStatusY();
	List<LeaveRequest> findByEmpBetweenDate(String empCode,Date toDate,Date fromDate);
	LeaveRequest findByToDate(Date date);
	List<LeaveRequest> getEmployeeByStatusN();
	

}
