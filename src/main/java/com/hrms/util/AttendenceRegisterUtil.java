package com.hrms.util;

import java.util.Date;





public class AttendenceRegisterUtil {
	
	private Date attendenceDate;

	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;
	private String  empPayCode;
	private long  id;
	
	
	


	private String shiftCode;

	
	private String status;

	
	
	private String timeIn;


	
	private String timeOut;

	
	private String insBy;


	private Date insDate = new Date();

	
	private String compunsantaryL;

	
	private String aTimeIn;

	
	private String aTimeOut;

	
	private String overTime;

	
	private String status2;


	private String overTimeStatus;

	
	private String overTime2;

	
	private String regCode;


	private String employeePayeCode;

	
	private String overFlowHrs;

	
	private String statusTemp;


	public Date getAttendenceDate() {
		return attendenceDate;
	}


	public void setAttendenceDate(Date attendenceDate) {
		this.attendenceDate = attendenceDate;
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


	public String getShiftCode() {
		return shiftCode;
	}


	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	public String getCompunsantaryL() {
		return compunsantaryL;
	}


	public void setCompunsantaryL(String compunsantaryL) {
		this.compunsantaryL = compunsantaryL;
	}



	public String getOverTime() {
		return overTime;
	}


	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}


	public String getStatus2() {
		return status2;
	}


	public void setStatus2(String status2) {
		this.status2 = status2;
	}


	public String getOverTimeStatus() {
		return overTimeStatus;
	}


	public void setOverTimeStatus(String overTimeStatus) {
		this.overTimeStatus = overTimeStatus;
	}


	public String getOverTime2() {
		return overTime2;
	}


	public void setOverTime2(String overTime2) {
		this.overTime2 = overTime2;
	}


	public String getRegCode() {
		return regCode;
	}


	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}


	public String getEmployeePayeCode() {
		return employeePayeCode;
	}


	public void setEmployeePayeCode(String employeePayeCode) {
		this.employeePayeCode = employeePayeCode;
	}


	public String getOverFlowHrs() {
		return overFlowHrs;
	}


	public void setOverFlowHrs(String overFlowHrs) {
		this.overFlowHrs = overFlowHrs;
	}


	public String getStatusTemp() {
		return statusTemp;
	}


	public void setStatusTemp(String statusTemp) {
		this.statusTemp = statusTemp;
	}





	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTimeIn() {
		return timeIn;
	}


	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}


	public String getTimeOut() {
		return timeOut;
	}


	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}


	public String getaTimeIn() {
		return aTimeIn;
	}


	public void setaTimeIn(String aTimeIn) {
		this.aTimeIn = aTimeIn;
	}


	public String getaTimeOut() {
		return aTimeOut;
	}


	public void setaTimeOut(String aTimeOut) {
		this.aTimeOut = aTimeOut;
	}


	public AttendenceRegisterUtil() {
		super();
	}


	public AttendenceRegisterUtil(Date attendenceDate, String empCode, String empName, String departmentCode,
			String deptName, String desgCode, String desgName, String empPayCode, int id, String shiftCode,
			String status, String timeIn, String timeOut, String insBy, Date insDate, String compunsantaryL,
			String aTimeIn, String aTimeOut, String overTime, String status2, String overTimeStatus, String overTime2,
			String regCode, String employeePayeCode, String overFlowHrs, String statusTemp) {
		super();
		this.attendenceDate = attendenceDate;
		this.empCode = empCode;
		this.empName = empName;
		this.departmentCode = departmentCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.empPayCode = empPayCode;
		this.id = id;
		this.shiftCode = shiftCode;
		this.status = status;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.insBy = insBy;
		this.insDate = insDate;
		this.compunsantaryL = compunsantaryL;
		this.aTimeIn = aTimeIn;
		this.aTimeOut = aTimeOut;
		this.overTime = overTime;
		this.status2 = status2;
		this.overTimeStatus = overTimeStatus;
		this.overTime2 = overTime2;
		this.regCode = regCode;
		this.employeePayeCode = employeePayeCode;
		this.overFlowHrs = overFlowHrs;
		this.statusTemp = statusTemp;
	}


	public AttendenceRegisterUtil(String empCode2, String empName2, String deptName2, String departmentCode2,
			String desgCode2, String desgName2, String employeePayeeCode) {
		this.empCode = empCode2;
		this.empName = empName2;
		this.departmentCode = departmentCode2;
		this.deptName = deptName2;
		this.desgCode = desgCode2;
		this.desgName = desgName2;
		this.empPayCode = employeePayeeCode;
	}
	
}
