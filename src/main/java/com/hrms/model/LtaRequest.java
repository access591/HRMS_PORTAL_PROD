package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LTA_INFO")
public class LtaRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1848517235088983142L;
	@Id
	@Column(name = "LTA_CODE")
	private String ltaCode;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE")
	private Date appDate;
	//------------------------------------
	@Temporal(TemporalType.DATE)
	@Column(name = "ELIGIBILITY_DATE")
	private Date eligibilityDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "WHEN_DUE")
	private Date whenDue;
	@Temporal(TemporalType.DATE)
	@Column(name = "WHEN_AVAILED")
	private Date whenAvailed;
	
	//---------------------------------
	@Temporal(TemporalType.DATE)
	@Column(name = "LEAVE_FROM")
	private Date leaveFrom;
	@Temporal(TemporalType.DATE)
	@Column(name = "LEAVE_TO")
	private Date leaveTo;
	@Column(name = "ADVANCE")
	private String  adavance;
	
	//-------------------------------
	
	@Column(name = "LEAVE_AVAILED")
	private Date leaveAvailed= new Date();
	
	@Column(name = "FROM_DATE")
	private Date fromDate= new Date();
	

	
	@Column(name = "APPROVED_BY")
	private String  approvedBy ;   
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate ;

	@Column(name = "REMARKS")
	private String  remarks;
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getLtaCode() {
		return ltaCode;
	}

	public void setLtaCode(String ltaCode) {
		this.ltaCode = ltaCode;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
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

	public Date getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(Date leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
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
	
	
	
	
}
