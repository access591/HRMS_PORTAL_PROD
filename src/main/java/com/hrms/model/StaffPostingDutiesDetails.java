package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMP_DUTIES_DETAILS")
public class StaffPostingDutiesDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3514876685537113895L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JOB_ID")
	private Long jdId;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_CODE",updatable = false)
	private StaffPostingDuties jobCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FROM_DT")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  fromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TO_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  toDate;
	
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String status;
	@Size(max =12)
	@Column(name = "JOB_DUTIE")
	private String jobDutie;
	
	
	
	public Long getJdId() {
		return jdId;
	}

	public void setJdId(Long jdId) {
		this.jdId = jdId;
	}

	public String getJobDutie() {
		return jobDutie;
	}

	public void setJobDutie(String jobDutie) {
		this.jobDutie = jobDutie;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public StaffPostingDuties getJobCode() {
		return jobCode;
	}

	public void setJobCode(StaffPostingDuties jobCode) {
		this.jobCode = jobCode;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
