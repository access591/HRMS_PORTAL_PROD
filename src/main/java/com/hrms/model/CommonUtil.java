package com.hrms.model;

import java.util.Date;

import org.springframework.stereotype.Component;


//EmployeeLeaveRequest
@Component
public class CommonUtil {

	private String empCode;
	private String maritalStatus;
	private Date empDateOfJoning;
	private String empName;
	private String deptName;
	private String desgName;
	private String desigCode;
	private String leaveCode;
	private String toDate;
	  
	private String fromDate;

	private String fromDateType;
	private String toDateType;
	
	private String applyDate;
	
	private String approvedDate;
	private String approevedBy;
	private String shiftCode;
	private String reason;
	private String leaveFor;

	private String cancelBy;
	
	private String cancelDate;
	
	private String reqCode;
	
	 private Date reqDate;
	
	 private String deptCode;
	
	 private String reqPriority;
	
	 private String reqApprover;
	 
	 private String remarks;
	 
	 private String insBy;
	 
	 
	 private Date insDate;
	 
	 private Date reqTill;

	 private Date approveDate;
	 
	
	 private String status;

	public CommonUtil() {
		super();
	
	}

	
	public String getReqCode() {
		return reqCode;
	}


	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}


	public Date getReqDate() {
		return reqDate;
	}


	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}


	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public String getReqPriority() {
		return reqPriority;
	}


	public void setReqPriority(String reqPriority) {
		this.reqPriority = reqPriority;
	}


	public String getReqApprover() {
		return reqApprover;
	}


	public void setReqApprover(String reqApprover) {
		this.reqApprover = reqApprover;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getInsBy() {
		return insBy;
	}


	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}


	public Date getInsDate() {
		return insDate;
	}


	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}


	public Date getReqTill() {
		return reqTill;
	}


	public void setReqTill(Date reqTill) {
		this.reqTill = reqTill;
	}


	public Date getApproveDate() {
		return approveDate;
	}


	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
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


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public Date getEmpDateOfJoning() {
		return empDateOfJoning;
	}


	public void setEmpDateOfJoning(Date empDateOfJoning) {
		this.empDateOfJoning = empDateOfJoning;
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

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromDateType() {
		return fromDateType;
	}

	public String getDesigCode() {
		return desigCode;
	}


	public void setDesigCode(String desigCode) {
		this.desigCode = desigCode;
	}


	public void setFromDateType(String fromDateType) {
		this.fromDateType = fromDateType;
	}

	public String getToDateType() {
		return toDateType;
	}

	public void setToDateType(String toDateType) {
		this.toDateType = toDateType;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApproevedBy() {
		return approevedBy;
	}

	public void setApproevedBy(String approevedBy) {
		this.approevedBy = approevedBy;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLeaveFor() {
		return leaveFor;
	}

	public void setLeaveFor(String leaveFor) {
		this.leaveFor = leaveFor;
	}

	public String getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(String cancelBy) {
		this.cancelBy = cancelBy;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public CommonUtil(String empName, String deptName,  String leaveCode, String toDate,
			String fromDate, String applyDate, 
			String approevedBy, String reason, String leaveFor) {
		super();
		this.empName = empName;
		this.deptName = deptName;
	
		this.leaveCode = leaveCode;
		this.toDate = toDate;
		this.fromDate = fromDate;
		
		this.applyDate = applyDate;
		
		this.approevedBy = approevedBy;
		
		this.reason = reason;
		this.leaveFor = leaveFor;
		
	}

	public CommonUtil(String empName2, String deptName2, String desgName2, String fromDate, String toDate) {
		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
		this.fromDate = fromDate;
		this.toDate = toDate;
	
	}


	public CommonUtil(String empCode2, String empName2, String deptName2, String martialStatus,
			Date dateOfJoining) {
		this.empCode = empCode2;
		this.empName = empName2;
		this.deptName = deptName2;
		this.maritalStatus = martialStatus;
		this.empDateOfJoning = dateOfJoining;
	}


	public CommonUtil(String departmentCode, String designationCode, Date dateOfJoining) {
		//this.de
	}


	public CommonUtil(String empName2, String deptName2, String desgName2) {
		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
	}


	public CommonUtil(String deptName, String reqCode, Date reqDate, String reqPriority, String reqApprover,
			String remarks, String insBy, Date insDate, Date reqTill, Date approveDate, String status) {
		super();
		this.deptName = deptName;
		this.reqCode = reqCode;
		this.reqDate = reqDate;
		this.reqPriority = reqPriority;
		this.reqApprover = reqApprover;
		this.remarks = remarks;
		this.insBy = insBy;
		this.insDate = insDate;
		this.reqTill = reqTill;
		this.approveDate = approveDate;
		this.status = status;
	}
	
	
	


	
	
	
	
}
