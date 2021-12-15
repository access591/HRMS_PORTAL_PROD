package com.hrms.util;

import java.util.Date;

import javax.persistence.Transient;

public class SaffPostingDutiesUtil {
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;
	private String jobDesc;
	private String  empPayCode;
	private String positionCode;
	private String dutiesDesc;
	private String jobCode;
	private String email;
	private String address;
	private String mobileNumber;
	private String jobDuties;
	
	@Transient
	private String timePeriod;
	
	public SaffPostingDutiesUtil(String empName2, String deptName2, String desgName2, String employeePayeeCode) {

		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
		this.empPayCode = employeePayeeCode;
	}
	


	public String getJobDuties() {
		return jobDuties;
	}



	public void setJobDuties(String jobDuties) {
		this.jobDuties = jobDuties;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getJobCode() {
		return jobCode;
	}


	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
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

	public String getEmpPayCode() {
		return empPayCode;
	}

	public void setEmpPayCode(String empPayCode) {
		this.empPayCode = empPayCode;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getDutiesDesc() {
		return dutiesDesc;
	}

	public void setDutiesDesc(String dutiesDesc) {
		this.dutiesDesc = dutiesDesc;
	}




	
	public SaffPostingDutiesUtil(String empCode, String empName, String departmentCode, String deptName,
			String desgCode, String desgName, String jobDesc, String empPayCode, String positionCode, String dutiesDesc,
			String jobCode, String email, String address, String mobileNumber) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.departmentCode = departmentCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.jobDesc = jobDesc;
		this.empPayCode = empPayCode;
		this.positionCode = positionCode;
		this.dutiesDesc = dutiesDesc;
		this.jobCode = jobCode;
		this.email = email;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}



	public SaffPostingDutiesUtil() {
		super();
	}






	public String getTimePeriod() {
		return timePeriod;
	}



	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	
}
