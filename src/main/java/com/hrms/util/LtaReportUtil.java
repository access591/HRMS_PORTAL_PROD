package com.hrms.util;

import java.util.Date;

public class LtaReportUtil {
	
	private String empCode;
	private String empName;
	private Date dateOfAppointment;
	private Date dateOfEligibility;
	private Date leaveAvailed;
	private Date leaveFrom;
	private Date leaveTo;
	
	public LtaReportUtil() {
		super();
	}
	
	

	public LtaReportUtil(String empCode, String empName, Date dateOfAppointment, Date dateOfEligibility,
			Date leaveAvailed, Date leaveFrom, Date leaveTo) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.dateOfAppointment = dateOfAppointment;
		this.dateOfEligibility = dateOfEligibility;
		this.leaveAvailed = leaveAvailed;
		this.leaveFrom = leaveFrom;
		this.leaveTo = leaveTo;
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

	public Date getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(Date dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public Date getDateOfEligibility() {
		return dateOfEligibility;
	}

	public void setDateOfEligibility(Date dateOfEligibility) {
		this.dateOfEligibility = dateOfEligibility;
	}

	public Date getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(Date leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
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
	
	
	
	

}
