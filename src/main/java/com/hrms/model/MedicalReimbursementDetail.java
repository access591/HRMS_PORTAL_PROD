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



@Entity
@Table(name = "MEDICLAIM_DETAILS")
public class MedicalReimbursementDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8770003946525614736L;
	/**
	 * 
	 */
	
	@Id
	@Column(name = "SR_NO")
	private String  srNo;  
	@Column(name = "MED_ST_DR_NAME")
	private String medStDr ;  
	@Column(name = "CASHMEMO_NO")
	private String caseMemoNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "CASHMEMO_DATE", updatable = false)
	private Date caseMemoDate;
	
	@Column(name = "AMOUNT")
	private String calmlAmount;
	
	@Column(name = "PASSED_AMT")
	private String passedAmount;
	@Column(name = "GOVT_EXMPT_AMT")
	private String govtexemptionAmount;
	@Column(name = "REMARKS")
	private String remark;
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>3 Step Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	@ManyToOne
	@JoinColumn(name ="SLIP_NO",updatable = false)
	private MedicalReimbursement slipNo;
	
	
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getMedStDr() {
		return medStDr;
	}
	public void setMedStDr(String medStDr) {
		this.medStDr = medStDr;
	}
	public String getCaseMemoNo() {
		return caseMemoNo;
	}
	public void setCaseMemoNo(String caseMemoNo) {
		this.caseMemoNo = caseMemoNo;
	}
	public Date getCaseMemoDate() {
		return caseMemoDate;
	}
	public void setCaseMemoDate(Date caseMemoDate) {
		this.caseMemoDate = caseMemoDate;
	}
	public String getCalmlAmount() {
		return calmlAmount;
	}
	public void setCalmlAmount(String calmlAmount) {
		this.calmlAmount = calmlAmount;
	}
	public String getPassedAmount() {
		return passedAmount;
	}
	public void setPassedAmount(String passedAmount) {
		this.passedAmount = passedAmount;
	}
	public String getGovtexemptionAmount() {
		return govtexemptionAmount;
	}
	public void setGovtexemptionAmount(String govtexemptionAmount) {
		this.govtexemptionAmount = govtexemptionAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public MedicalReimbursement getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(MedicalReimbursement slipNo) {
		this.slipNo = slipNo;
	}
	
	
}
