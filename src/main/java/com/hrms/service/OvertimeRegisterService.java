package com.hrms.service;

import java.sql.Date;
import java.util.List;

import com.hrms.model.OvertimeRegister;

public interface OvertimeRegisterService {
	
	public List<OvertimeRegister> findOverTimeRegisterByEmpCodeBetweenDate(String empCode,Date fromDate,Date toDate);

	public boolean addOvertimeRegister(OvertimeRegister overReg);

	public List<OvertimeRegister> getAllOvertimeRegister();

	public void removeOverTimeRegister(long id);
	
	

}
