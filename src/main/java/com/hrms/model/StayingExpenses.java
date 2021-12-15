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

@Entity
@Table(name = "EMP_STAY")
public class StayingExpenses implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8328613755775210403L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "TOUR_CLAIM_ID")
	private TourClaim tourClaimId;
	@ManyToOne
	@JoinColumn(name = "TOUR_CLAIM_DATE")
	private TourClaim tourClaimDate;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Column(name = "STAY_DATE")
	private Date stayDate;
	
	@Column(name = "NO_DAYS")
	private String noDays;
	
	@Column(name = "ATA_AMOUNT")
	private String ataAmt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TourClaim getTourClaimId() {
		return tourClaimId;
	}

	public void setTourClaimId(TourClaim tourClaimId) {
		this.tourClaimId = tourClaimId;
	}

	public TourClaim getTourClaimDate() {
		return tourClaimDate;
	}

	public void setTourClaimDate(TourClaim tourClaimDate) {
		this.tourClaimDate = tourClaimDate;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Date getStayDate() {
		return stayDate;
	}

	public void setStayDate(Date stayDate) {
		this.stayDate = stayDate;
	}

	public String getNoDays() {
		return noDays;
	}

	public void setNoDays(String noDays) {
		this.noDays = noDays;
	}

	public String getAtaAmt() {
		return ataAmt;
	}

	public void setAtaAmt(String ataAmt) {
		this.ataAmt = ataAmt;
	}

	
	
}
