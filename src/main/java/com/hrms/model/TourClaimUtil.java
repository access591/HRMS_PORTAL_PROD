package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;

public class TourClaimUtil {
	private String tourPlanId;
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;
	private int totalTravel;
	private int totalConv;
	private int totalStay; 
	//...............................
	private Date tourClaimDate;
	private Date tourPlanDate;
	private String visitPurpose;
	private String startPlace;
	private String visitPlace;
	private int advancePaid;
	public TourClaimUtil(String empName2, String deptName2, String desgName2) {
		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
		
				
	}
	//master end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	public String getTourPlanId() {
		return tourPlanId;
	}
	public void setTourPlanId(String tourPlanId) {
		this.tourPlanId = tourPlanId;
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
	public int getTotalTravel() {
		return totalTravel;
	}
	public void setTotalTravel(int totalTravel) {
		this.totalTravel = totalTravel;
	}
	public int getTotalConv() {
		return totalConv;
	}
	public void setTotalConv(int totalConv) {
		this.totalConv = totalConv;
	}
	public int getTotalStay() {
		return totalStay;
	}
	public void setTotalStay(int totalStay) {
		this.totalStay = totalStay;
	}
	public Date getTourClaimDate() {
		return tourClaimDate;
	}
	public void setTourClaimDate(Date tourClaimDate) {
		this.tourClaimDate = tourClaimDate;
	}
	public Date getTourPlanDate() {
		return tourPlanDate;
	}
	public void setTourPlanDate(Date tourPlanDate) {
		this.tourPlanDate = tourPlanDate;
	}
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getVisitPlace() {
		return visitPlace;
	}
	public void setVisitPlace(String visitPlace) {
		this.visitPlace = visitPlace;
	}
	public int getAdvancePaid() {
		return advancePaid;
	}
	public void setAdvancePaid(int advancePaid) {
		this.advancePaid = advancePaid;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

//master end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	
}
