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
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="M_EMP_UNDER_RULE")
public class EmployeeUnderRule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EOD",length = 100)
	private Long eod;
	
	@Column(name="EOD_MEMO_NO",length = 100)
	private String memoNo;
	
	@Column(name="EOD_MEMO_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date memoDate;
	
	@Column(name="REMARKS",length = 500)
	private String remarks;
	
	@Column(name="UPLOAD_FILE_NAME",length = 200)
	private String underRuleFile;
	
	@Column(name="EOD_TYPE",length = 200)
	private String underRuleType;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employee;
	
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	@Size(max = 50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE", insertable = false)
	private Date  updDate = new Date();
	
	

	public EmployeeUnderRule() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getEod() {
		return eod;
	}



	public void setEod(Long eod) {
		this.eod = eod;
	}



	public String getMemoNo() {
		return memoNo;
	}



	public void setMemoNo(String memoNo) {
		this.memoNo = memoNo;
	}



	public Date getMemoDate() {
		return memoDate;
	}



	public void setMemoDate(Date memoDate) {
		this.memoDate = memoDate;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getUnderRuleFile() {
		return underRuleFile;
	}



	public void setUnderRuleFile(String underRuleFile) {
		this.underRuleFile = underRuleFile;
	}



	


	public String getUnderRuleType() {
		return underRuleType;
	}



	public void setUnderRuleType(String underRuleType) {
		this.underRuleType = underRuleType;
	}



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
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
