package com.hrms.service;

import java.util.Date;
import java.util.List;


import com.hrms.model.LeaveRequest;

public interface LeaveRequestService {

	public List<LeaveRequest> findAllByEmpCode(String empCode);
	
	public void addLeave(LeaveRequest leaveRequest);
	public List<LeaveRequest> getAllLeaves();
	
	public void updateLeaveRequest(LeaveRequest d);
	public void removeLeaveRequest(Long id);
	public List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate);
	public List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode);

	public LeaveRequest findLeaveRequestById(long id);
	public List<LeaveRequest> getEmployeeByStatusY();
	public List<LeaveRequest> getEmployeeByStatusN();
	public List<LeaveRequest> findByEmpBetweenDate(String empCode, Date toDate, Date fromDate);
	public LeaveRequest findByToDate(Date date);
	public List<LeaveRequest> findAllApproveLeaveRequestBetweenDate(Date fromDate,Date toDate);
	public List<LeaveRequest> findApproveLeaveRequestByEmpBetweenDate(Date fromDate,Date toDate,
			String empCode);
	public List<LeaveRequest> findAllApproveLeaveRequestByDeptBetweenDate(Date fromDate,Date toDate,
			String deptCode);
	
	
	public List<LeaveRequest> findAllLeaveRequestBetweenDate(Date fromDate,Date toDate);
	public List<LeaveRequest> findAllLeaveRequestByDeptBetweenDate(Date fromDate,Date toDate,String deptCode);
	public List<LeaveRequest> findAllLeaveRequestbyEmpBetweenDate(Date fromDate,Date toDate,String empCode);
	
}
