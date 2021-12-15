package com.hrms.util;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class LocalConvyenceUtil {
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;

	private String  empPayCode;
	private String  fatherHusband;
	private String localConvId;
	@Temporal(TemporalType.DATE)
	private Date localConvDate;
	private int totalClaim;
	private int totalPas;
	private String  approvalStatus ;
	// DETAIL ========================================
	private Date  locDate;
	
	private String  startPlace;
	
	private String  vistPlace;
	
	private String  purposeOfVist;
	
	private String  modeOfTravel;
	
	private String  distanceKm;
	
	private String  ltaRate;
	
	private String  actualAmount;
	
	private String  claimedAmount;
	
	
	
	
	
	
	public Date getLocDate() {
		return locDate;
	}
	public void setLocDate(Date locDate) {
		this.locDate = locDate;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getVistPlace() {
		return vistPlace;
	}
	public void setVistPlace(String vistPlace) {
		this.vistPlace = vistPlace;
	}
	public String getPurposeOfVist() {
		return purposeOfVist;
	}
	public void setPurposeOfVist(String purposeOfVist) {
		this.purposeOfVist = purposeOfVist;
	}
	public String getModeOfTravel() {
		return modeOfTravel;
	}
	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}
	public String getDistanceKm() {
		return distanceKm;
	}
	public void setDistanceKm(String distanceKm) {
		this.distanceKm = distanceKm;
	}
	public String getLtaRate() {
		return ltaRate;
	}
	public void setLtaRate(String ltaRate) {
		this.ltaRate = ltaRate;
	}
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getClaimedAmount() {
		return claimedAmount;
	}
	public void setClaimedAmount(String claimedAmount) {
		this.claimedAmount = claimedAmount;
	}
	public Date getLocalConvDate() {
		return localConvDate;
	}
	public void setLocalConvDate(Date localConvDate) {
		this.localConvDate = localConvDate;
	}
	public int getTotalClaim() {
		return totalClaim;
	}
	public void setTotalClaim(int totalClaim) {
		this.totalClaim = totalClaim;
	}
	public int getTotalPas() {
		return totalPas;
	}
	public void setTotalPas(int totalPas) {
		this.totalPas = totalPas;
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
	public String getFatherHusband() {
		return fatherHusband;
	}
	public void setFatherHusband(String fatherHusband) {
		this.fatherHusband = fatherHusband;
	}
	public String getLocalConvId() {
		return localConvId;
	}
	public void setLocalConvId(String localConvId) {
		this.localConvId = localConvId;
	}
	
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public LocalConvyenceUtil(String empCode, String empName, String departmentCode, String deptName, String desgCode,
			String desgName, String empPayCode, String localConvId) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.departmentCode = departmentCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.empPayCode = empPayCode;
	
		this.localConvId = localConvId;
	}
	public LocalConvyenceUtil() {
		super();
	}
	
	
	public LocalConvyenceUtil(String empName2, String deptName2, String desgName2, String employeePayeeCode) {
		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
		this.empPayCode = employeePayeeCode;
	}
	
	
	
	
}
