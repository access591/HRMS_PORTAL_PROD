package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "M_LEAVE_GRANT")
public class LeaveGrant  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180255080078306637L;

	@Id
	@Column(name="LEAVEGRANT_CODE")
	private String leaveGrantCode;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@ManyToOne
	@JoinColumn(name = "LEAVE_CODE", nullable = false)
	private Leave levCode;

	@Column(name = "NO_OF_LEAVES_GRANTED")
	private int noOfLeavesGranted;

	@Column(name = " PREVIOUS_YR_BALANCE")
	private int previousYrBalance;

	@Column(name = "YEAR")
	private String year;
	@Column(name = "LEAVE_AVAILED")
	private int leaveAvailed;

	@Column(name = "CLOSING_BAL")
	private int closingBal;
	
	@Column(name = "TAKEN_LEAVE")
	private String takenLeave = "0";


	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

	public String getLeaveGrantCode() {
		return leaveGrantCode;
	}

	public void setLeaveGrantCode(String leaveGrantCode) {
		this.leaveGrantCode = leaveGrantCode;
	}

	public int getNoOfLeavesGranted() {
		return noOfLeavesGranted;
	}

	public void setNoOfLeavesGranted(int noOfLeavesGranted) {
		this.noOfLeavesGranted = noOfLeavesGranted;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Leave getLevCode() {
		return levCode;
	}

	public void setLevCode(Leave levCode) {
		this.levCode = levCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(int closingBal) {
		this.closingBal = closingBal;
	}

	public int getPreviousYrBalance() {
		return previousYrBalance;
	}

	public void setPreviousYrBalance(int previousYrBalance) {
		this.previousYrBalance = previousYrBalance;
	}

	public int getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(int leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
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

	public String getTakenLeave() {
		return takenLeave;
	}

	public void setTakenLeave(String takenLeave) {
		this.takenLeave = takenLeave;
	}
	



	

}
