package com.hrms.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.io.Serializable;
@Entity
@Table(name = "M_LEAVEDETAILS")
public class LeaveDetail implements Serializable {

	/**
	 * ACCESS SURENDRA 
	 */
	private static final long serialVersionUID = 1821750528624723826L;
	@Id
	@Size(max =15)
	@Column(name = "LEAVE_CODE")
	private String lvCode;
	@Size(max =80)
	@Column(name = "LEAVE_TYPE_NAME")
	private String levTypeName;
	@Size(max =2)
	@Column(name = "TOTALLEAVE")
	private String totalLeave;
	
	@Size(max =1)
	@Column(name = "LWP")
	private String lwp;
	@Size(max =2)
	@Column(name = "MAXLIMIT")
	private String maxLimit;
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;
	@Size(max =50)
	@Column(name = "LEAVETYPE")
	private String leaveType;
	@Size(max =2)
	@Column(name = "LEAVESTATUS")
	private String leaveStatus;
	@Size(max =2)
	@Column(name = "ENTDAY")
	private String entDay;
	@Size(max =2)
	@Column(name = "ABBREV")
	private String abbrev;
	@Size(max =50)
	@Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	@Size(max =50)
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();

	public String getLvCode() {
		return lvCode;
	}

	public void setLvCode(String lvCode) {
		this.lvCode = lvCode;
	}

	public String getLevTypeName() {
		return levTypeName;
	}

	public void setLevTypeName(String levTypeName) {
		this.levTypeName = levTypeName;
	}

	public String getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(String totalLeave) {
		this.totalLeave = totalLeave;
	}

	public String getLwp() {
		return lwp;
	}

	public void setLwp(String lwp) {
		this.lwp = lwp;
	}

	public String getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getEntDay() {
		return entDay;
	}

	public void setEntDay(String entDay) {
		this.entDay = entDay;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
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

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
	
	
}
