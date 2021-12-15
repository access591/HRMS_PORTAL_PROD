package com.hrms.util;

import java.util.Date;


public class LtaRequestUtil {
	private String ltaCode;
	private String  empCode;
	private Date appDate;
	private String empName;
	private String deptName;
	//------------------------------------
	
	public String getEmpName() {
		return empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	private Date eligibilityDate;
	
	
	private Date whenDue;
	
	
	private Date whenAvailed;
	
	//---------------------------------
	
	private Date leaveFrom;
	
	
	private Date leaveTo;
	
	
	private String  adavance;
	
	private String  remarks;

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public Date getEligibilityDate() {
		return eligibilityDate;
	}

	public void setEligibilityDate(Date eligibilityDate) {
		this.eligibilityDate = eligibilityDate;
	}

	public Date getWhenDue() {
		return whenDue;
	}

	public void setWhenDue(Date whenDue) {
		this.whenDue = whenDue;
	}

	public Date getWhenAvailed() {
		return whenAvailed;
	}

	public void setWhenAvailed(Date whenAvailed) {
		this.whenAvailed = whenAvailed;
	}

	public Date getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public Date getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getAdavance() {
		return adavance;
	}

	public void setAdavance(String adavance) {
		this.adavance = adavance;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLtaCode() {
		return ltaCode;
	}

	public void setLtaCode(String ltaCode) {
		this.ltaCode = ltaCode;
	}
	
	
	
}