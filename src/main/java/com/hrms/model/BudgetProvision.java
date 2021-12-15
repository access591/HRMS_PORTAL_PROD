package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUDGET")
public class BudgetProvision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUDGET_ID")
	private Long budgetProvisionId;

	@Column(name = "BUDGET_HEAD",length = 50)
	private String budgetHead;

	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Department department;

	@Column(name = "EXPEN_PURPOSE",length = 200)
	private String expenditurePurpose;

	@Column(name = "FILE_NO",length = 100)
	private String fileNo;

	@Column(name = "SANCTION_DATE")
	private Date dateOfSanction;

	@Column(name = "EXPEND_AMOUNT",length = 20)
	private Long expenditureAmount;

	@Column(name = "BAL_AMOUNT",length = 20)
	private Long balanceAmount;

	@Column(name = "YR_FIX_AMOUNT",length = 20)
	private int yearlyFixAmount;

	@Column(name = "FIN_YEAR")
	private Date budgetYear = new Date();


	@Column(name = "INS_BY", updatable = false,length = 50)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false,length = 6)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false,length = 50)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false,length = 6)
	private Date updDate = new Date();

	public BudgetProvision() {
		super();
		
	}

	public Long getBudgetProvisionId() {
		return budgetProvisionId;
	}

	public void setBudgetProvisionId(Long budgetProvisionId) {
		this.budgetProvisionId = budgetProvisionId;
	}

	public String getBudgetHead() {
		return budgetHead;
	}

	public void setBudgetHead(String budgetHead) {
		this.budgetHead = budgetHead;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getExpenditurePurpose() {
		return expenditurePurpose;
	}

	public void setExpenditurePurpose(String expenditurePurpose) {
		this.expenditurePurpose = expenditurePurpose;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public Date getDateOfSanction() {
		return dateOfSanction;
	}

	public void setDateOfSanction(Date dateOfSanction) {
		this.dateOfSanction = dateOfSanction;
	}

	public Long getExpenditureAmount() {
		return expenditureAmount;
	}

	public void setExpenditureAmount(Long expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}

	public Long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public int getYearlyFixAmount() {
		return yearlyFixAmount;
	}

	public void setYearlyFixAmount(int yearlyFixAmount) {
		this.yearlyFixAmount = yearlyFixAmount;
	}

	public Date getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Date budgetYear) {
		this.budgetYear = budgetYear;
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
