package com.hrms.util;

import java.util.Date;



public class InductionTrainingUtil {
	
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;
	private String  empPayCode;
	private long id;
	


	private String contPerson;
	private Date trainingDate;
	private String duration;
	public String getContPerson() {
		return contPerson;
	}
	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}
	public Date getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public InductionTrainingUtil(String empCode, String empName, String departmentCode, String deptName,
			String desgCode, String desgName, String empPayCode) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.departmentCode = departmentCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.empPayCode = empPayCode;
	}
	public InductionTrainingUtil() {
		super();
	}
	

}
