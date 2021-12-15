package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "M_EMP_DUTIES")
public class StaffPostingDuties implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3150550385557418137L;

	@Id
	@Column(name = "JOB_CODE")
	private String jobCode;
	
	@Column(name = "JOB_DESC")
	private String jobDesc;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Column(name = "POSITION_CODE")
	private String positionCode;

	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	
	@OneToMany(mappedBy="jobCode",cascade = CascadeType.ALL,orphanRemoval = true)
	List<StaffPostingDutiesDetails> staffPostingDutiesDetails;
	
	

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
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

	public List<StaffPostingDutiesDetails> getStaffPostingDutiesDetails() {
		return staffPostingDutiesDetails;
	}

	public void setStaffPostingDutiesDetails(List<StaffPostingDutiesDetails> staffPostingDutiesDetails) {
		this.staffPostingDutiesDetails = staffPostingDutiesDetails;
	}
}
