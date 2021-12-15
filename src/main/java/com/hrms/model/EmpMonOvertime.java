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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="EMP_MON_OVERTIME")
public class EmpMonOvertime implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6303266266695918699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employee;
	
	@Column(name="OTIME_MONTH")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date oTimeMonth;

	@Column(name="OTIME_RATE")
	private String oTimeRate;

	@Column(name="PAYABLE_AMT")
	private String payableAmt;
	
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getoTimeMonth() {
		return oTimeMonth;
	}

	public void setoTimeMonth(Date oTimeMonth) {
		this.oTimeMonth = oTimeMonth;
	}

	public String getoTimeRate() {
		return oTimeRate;
	}

	public void setoTimeRate(String oTimeRate) {
		this.oTimeRate = oTimeRate;
	}

	public String getPayableAmt() {
		return payableAmt;
	}

	public void setPayableAmt(String payableAmt) {
		this.payableAmt = payableAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
