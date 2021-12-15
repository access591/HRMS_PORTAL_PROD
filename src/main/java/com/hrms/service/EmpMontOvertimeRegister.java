package com.hrms.service;

import java.util.List;

import com.hrms.model.EmpMonOvertime;

public interface EmpMontOvertimeRegister {
	
	public List<EmpMonOvertime> findEmpMontOvertimeByEmpCode(String empCode);
	public List<EmpMonOvertime> findAllEmpMontOvertime();
	public List<EmpMonOvertime> findAllByDeptCode(String deptCode);
	public List<EmpMonOvertime> findAllDeptByMonth(int month);
	public List<EmpMonOvertime> findOvertimeMonthByDeptCodeAndMonth(String deptCode,int month);
	public boolean addMontOvertimeRegister(EmpMonOvertime overtimeEval);
	public List<EmpMonOvertime> getAllMontOvertimeRegister();
	public void removeMonthOverTimeRegister(long id);
	public boolean checkMonthOverTimeExists(String empCode);

}
