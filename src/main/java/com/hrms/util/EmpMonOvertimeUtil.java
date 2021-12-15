package com.hrms.util;

import java.util.Date;

import javax.persistence.Column;



public class EmpMonOvertimeUtil {
	
	
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;
	
	private Date oTimeMonth;
	private String oTimeRate;
	private String payableAmt;
	private String status;
	private long id;
	
	
	
	public Date getoTimeMonth() {
		return oTimeMonth;
	}
	public void setoTimeMonth(Date oTimeMonth) {
		this.oTimeMonth = oTimeMonth;
	}
	public String getoTimeRate() {
		return oTimeRate;
	}
	public void setoTimeRate(String oTimeRate) {
		this.oTimeRate = oTimeRate;
	}
	public String getPayableAmt() {
		return payableAmt;
	}
	public void setPayableAmt(String payableAmt) {
		this.payableAmt = payableAmt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
