package com.hrms.service;

import java.sql.Date;
import java.util.List;

import com.hrms.model.AttendenceRegister;

public interface AttendenceRegisterService {
	
	public List<AttendenceRegister> findAttendenceByEmpCodeBetweenDate(String empCode,
			Date fromDate,Date toDate);
	
	public List<AttendenceRegister> findAttendenceByDeptBetweenDate(String deptCode,
			Date fromDate,Date toDate);	
	
	public List<AttendenceRegister> findAllAttendenceBetweenDate(Date fromDate,Date toDate);
	public boolean addAttendenceRegister(AttendenceRegister attn);
	public List<AttendenceRegister> getAllAttendenceRegister();
	public AttendenceRegister findAttendenceRegisterByEmpCode(String empCode);	
	public List<AttendenceRegister> findAttendenceByEmpStatusAbsent(String empCode);
	public void removeAttendanceRegister(long id);	
	public List<AttendenceRegister> findAttendenceStatusByDeptCode(String deptCode,
			Date fromDate,Date toDate);
	
	public List<AttendenceRegister> findTodayAttendenceList();
	public List<AttendenceRegister> findTodayLeaveEmployee();

	public AttendenceRegister findByIdAttendenceRegister(long id);
	
	

}
