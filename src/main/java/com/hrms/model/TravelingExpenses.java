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
@Table(name = "EMP_TRAVEL")
public class TravelingExpenses  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1638252512779972502L;
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
	@Column(name = "FROM_DATE")
	private Date fromDate;
	
	@Column(name = "TO_DATE")
	private Date toDate;
	
	@Column(name = "START_PLACE")
	private String startPlace;
	
	@Column(name = "VISIT_PLACE")
	private String visitPlace;
	
	@Column(name = "MODE_OF_TRAVEL")
	private String  modeOfTravel; 
	
	
	@Column(name = "TICKET_NO")
	private int  ticketNo; 
	
	@Column(name = "PAID_COMP")
	private int  paidCompany; 
	
	@Column(name = "PAID_SELF")
	private int  paidSelf;

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

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getVisitPlace() {
		return visitPlace;
	}

	public void setVisitPlace(String visitPlace) {
		this.visitPlace = visitPlace;
	}

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public int getPaidCompany() {
		return paidCompany;
	}

	public void setPaidCompany(int paidCompany) {
		this.paidCompany = paidCompany;
	}

	public int getPaidSelf() {
		return paidSelf;
	}

	public void setPaidSelf(int paidSelf) {
		this.paidSelf = paidSelf;
	} 
	      	
}
