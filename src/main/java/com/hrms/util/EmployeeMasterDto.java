package com.hrms.util;

import java.util.List;

import com.hrms.model.ArmsLicenses;
import com.hrms.model.ArmsLicensesDetail;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeAcr;
import com.hrms.model.EmployeePromotion;

public class EmployeeMasterDto {
	
	private Employee employee;
	private ArmsLicenses armsLicenses;
	private List<ArmsLicensesDetail> armsLicensesDetail;
	private EmployeePromotion employeePromotion;
	private EmployeeAcr employeeAcr;
	
	public EmployeeMasterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ArmsLicenses getArmsLicenses() {
		return armsLicenses;
	}

	public void setArmsLicenses(ArmsLicenses armsLicenses) {
		this.armsLicenses = armsLicenses;
	}

	public List<ArmsLicensesDetail> getArmsLicensesDetail() {
		return armsLicensesDetail;
	}

	public void setArmsLicensesDetail(List<ArmsLicensesDetail> armsLicensesDetail) {
		this.armsLicensesDetail = armsLicensesDetail;
	}

	public EmployeePromotion getEmployeePromotion() {
		return employeePromotion;
	}

	public void setEmployeePromotion(EmployeePromotion employeePromotion) {
		this.employeePromotion = employeePromotion;
	}

	public EmployeeAcr getEmployeeAcr() {
		return employeeAcr;
	}

	public void setEmployeeAcr(EmployeeAcr employeeAcr) {
		this.employeeAcr = employeeAcr;
	}
	
	
	

}
