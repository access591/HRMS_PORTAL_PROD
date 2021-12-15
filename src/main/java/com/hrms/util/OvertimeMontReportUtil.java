package com.hrms.util;

import java.util.Date;

public class OvertimeMontReportUtil {
	
	private String srNo;
	private Date oTimeMonth;
	private String empName;
	private String deptName;
	private Date dateOfJoining;
	private String basic;
	private Date aTimeIn;
	private Date aTimeOut;
	private String overFlowHrs;
	private String amt;
	private String remarks;
	
	
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public Date getoTimeMonth() {
		return oTimeMonth;
	}
	public void setoTimeMonth(Date oTimeMonth) {
		this.oTimeMonth = oTimeMonth;
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
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public Date getaTimeIn() {
		return aTimeIn;
	}
	public void setaTimeIn(Date aTimeIn) {
		this.aTimeIn = aTimeIn;
	}
	public Date getaTimeOut() {
		return aTimeOut;
	}
	public void setaTimeOut(Date aTimeOut) {
		this.aTimeOut = aTimeOut;
	}
	public String getOverFlowHrs() {
		return overFlowHrs;
	}
	public void setOverFlowHrs(String overFlowHrs) {
		this.overFlowHrs = overFlowHrs;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public OvertimeMontReportUtil() {
		super();
		
	}
	public OvertimeMontReportUtil(String srNo,Date oTimeMonth, String empName, String deptName, Date dateOfJoining, String basic,
			Date aTimeIn, Date aTimeOut, String overFlowHrs, String amt, String remarks) {
		super();
		this.srNo = srNo;
		this.oTimeMonth = oTimeMonth;
		this.empName = empName;
		this.deptName = deptName;
		this.dateOfJoining = dateOfJoining;
		this.basic = basic;
		this.aTimeIn = aTimeIn;
		this.aTimeOut = aTimeOut;
		this.overFlowHrs = overFlowHrs;
		this.amt = amt;
		this.remarks = remarks;
	}
	
	

}
