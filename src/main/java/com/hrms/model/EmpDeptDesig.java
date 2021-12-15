package com.hrms.model;

public class EmpDeptDesig {
	
	private String empName;
	private String deptName;
	private String desgName;
	
	public EmpDeptDesig() {
		super();
		
	}
	public EmpDeptDesig(String empName, String deptName, String desgName) {
		super();
		this.empName = empName;
		this.deptName = deptName;
		this.desgName = desgName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	
	

}
