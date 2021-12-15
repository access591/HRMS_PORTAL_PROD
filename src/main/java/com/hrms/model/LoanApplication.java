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
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "LOAN_REQUEST")
public class LoanApplication  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6794795665038552177L;
	
	@Id
	@Column(name = "APP_NO")
	private String appNo;
	@Temporal(TemporalType.DATE)
	@Column(name="APP_DATE",updatable = false)
	private Date appDate;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Size(max =1)
	@Column(name="LOAN_TYPE",updatable = false)
	private String loanType;
	
	@ManyToOne
	@JoinColumn(name ="LOAN_CODE",updatable = false)
	private Loan loanCode;
	
	@Size(max =1)
	@Column(name="LOAN_STATUS",updatable = false)
	private String loanStatus;
	
	@Column(name="AMOUNT_REQUIRED",updatable = false)
	private String amountRequired;
	@Temporal(TemporalType.DATE)
	@Column(name="EFF_SCHEDULE_DATE",updatable = false)
	private Date effScheduleDate;
	
	 
	@Column(name="AMOUNT_SANCTIONED",updatable = false)
	private String amountSanctioned;
	
	//*****************************
	@Column(name="CHEQUE_NO")
	private String chequeNo;
	
	@Column(name="CHEQUE_DATE")
	private Date chequeDate;
	
	@Column(name="CHEQUE_AMT")
	private int chequeAmount;
	
	@Column(name="BANK_NAME")
	private String bankName;
	@Column(name="INTEREST_RATE")
	private int interestRate;
	
	@Column(name="NO_OF_INSTALLMENTS")
	private int noOfInstallments;
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	@Column(name = "APPROVED_BY")
	private String  approvedBy ;   
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Loan getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(Loan loanCode) {
		this.loanCode = loanCode;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}



	public String getAmountRequired() {
		return amountRequired;
	}

	public void setAmountRequired(String amountRequired) {
		this.amountRequired = amountRequired;
	}

	public Date getEffScheduleDate() {
		return effScheduleDate;
	}

	public void setEffScheduleDate(Date effScheduleDate) {
		this.effScheduleDate = effScheduleDate;
	}

	public String getAmountSanctioned() {
		return amountSanctioned;
	}

	public void setAmountSanctioned(String amountSanctioned) {
		this.amountSanctioned = amountSanctioned;
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

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public int getChequeAmount() {
		return chequeAmount;
	}

	public void setChequeAmount(int chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public int getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	
}
