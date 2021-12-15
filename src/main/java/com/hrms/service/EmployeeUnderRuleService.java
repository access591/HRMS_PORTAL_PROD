package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeeUnderRule;

public interface EmployeeUnderRuleService {
	
	public EmployeeUnderRule addEmployeeUnderRule(EmployeeUnderRule employeeUnderRule);
	public List<EmployeeUnderRule> getAllEmployeeUnderRule();
	public EmployeeUnderRule getEmployeeUnderRuleFindById(Long eod);
	public void deleteEmployeeUnderRule(Long eod);
	public void updateEmployeeUnderRule(EmployeeUnderRule employeeUnderRule);
	public List<EmployeeUnderRule> getUniqueEmployeeUnderRule();
	public List<EmployeeUnderRule> findEmployeeUnderRuleByEmpCodeAndEmpType(String empCode , String empType);
	

}
