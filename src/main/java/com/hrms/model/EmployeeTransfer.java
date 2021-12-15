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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "T_EMP_TRANS_DTLS")
public class EmployeeTransfer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3279462664821388430L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ETD")
	private long id;
	
	
	
	@Column(name = "ETD_MEMO_NO")
	private String etdMemoNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TRANSFER_DATE")
	private Date transferDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FROM_DATE")
	private Date fromDate;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TO_DATE")
	private Date toDate;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	@Column(name = "EMP_TRANSFER__DOC", nullable = true)
	private String empTransferDoc;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEtdMemoNo() {
		return etdMemoNo;
	}

	public void setEtdMemoNo(String etdMemoNo) {
		this.etdMemoNo = etdMemoNo;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
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

	public String getEmpTransferDoc() {
		return empTransferDoc;
	}

	public void setEmpTransferDoc(String empTransferDoc) {
		this.empTransferDoc = empTransferDoc;
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

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}
	
	
	
	
}
