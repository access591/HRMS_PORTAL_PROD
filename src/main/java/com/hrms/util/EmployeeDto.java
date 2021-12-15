package com.hrms.util;

import java.util.List;

import com.hrms.model.ArmsLicenses;
import com.hrms.model.ArmsLicensesDetail;
import com.hrms.model.Employee;

public class EmployeeDto {
	
	private Employee employee;
	private ArmsLicenses armsLicenses;
	private List<ArmsLicensesDetail> armsLicensesDetail;
	
	public EmployeeDto() {
		super();
		
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
	
	

}
