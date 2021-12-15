package com.hrms.model;

import java.io.Serializable;
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
@Table(name = "LOAN_SCHEDULE")

public class LoanSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7986595644818206372L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "APP_NO",updatable = false)
	private LoanApplication appNo;
	
	@ManyToOne
	@JoinColumn(name = "APP_DATE",updatable = false)
	private LoanApplication appDate;
	

	@Column(name = "INST_NO")
	private int instNo;
	

	@Column(name = "INST_DATE")
	private Date instDate;
	

	@Column(name = "INST_PAID")
	private int instPaid;
	

	@Column(name = "INST_AMT")
	private int instAmt;
	

	@Column(name = "INST_PAID_DATE")
	private Date instPaidDate;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public LoanApplication getAppNo() {
		return appNo;
	}


	public void setAppNo(LoanApplication appNo) {
		this.appNo = appNo;
	}


	public LoanApplication getAppDate() {
		return appDate;
	}


	public void setAppDate(LoanApplication appDate) {
		this.appDate = appDate;
	}


	public int getInstNo() {
		return instNo;
	}


	public void setInstNo(int instNo) {
		this.instNo = instNo;
	}


	public Date getInstDate() {
		return instDate;
	}


	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}


	public int getInstPaid() {
		return instPaid;
	}


	public void setInstPaid(int instPaid) {
		this.instPaid = instPaid;
	}


	public int getInstAmt() {
		return instAmt;
	}


	public void setInstAmt(int instAmt) {
		this.instAmt = instAmt;
	}


	public Date getInstPaidDate() {
		return instPaidDate;
	}


	public void setInstPaidDate(Date instPaidDate) {
		this.instPaidDate = instPaidDate;
	}
	
	
}
