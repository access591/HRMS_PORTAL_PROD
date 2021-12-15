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
@Table(name = "EMP_TOUR_CLAIM")
public class TourClaim implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647681939917697344L;
	
	
	@Id
	@Column(name = "TOUR_CLAIM_ID")
	private String tourClaimId;
	
	@Column(name = "TOUR_CLAIM_DATE")
	private Date tourClaimDate;
	
	
	@Column(name = "TOUR_PLAN_DATE")
	private Date tourPlanDate;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Column(name = "VISIT_PURPOSE")
	private String visitPurpose;
	
	@Column(name = "START_PLACE")
	private String startPlace;
	
	@Column(name = "VISIT_PLACE")
	private String visitPlace;
	@ManyToOne
	@JoinColumn(name = "TOUR_PLAN_ID",updatable = false)
	private TourPlan tourPlanId;
	
	@Column(name = "ADVANCE_PAID")
	private int advancePaid;
	//--------------------------------
	@Column(name = "TOTAL_TRAVEL")
	private int totalTravel;
	
	
	
	@Column(name = "TOTAL_CONV")
	private int totalConv;
	
	@Column(name = "TOTAL_STAY")
	private int totalStay; 
	//.........................
	@Column(name = "PASS_TRAVEL")
	private int passTravel;    
	@Column(name = "PASS_CONV")
	private int passConv;    
	@Column(name = "PASS_STAY")
	private int passStay;   
	//..............................
	
	@Column(name = "APPROVED_BY")
	private String  approvedBy ; 
	
    @Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
    
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate= new Date(); 
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getTourClaimId() {
		return tourClaimId;
	}

	public void setTourClaimId(String tourClaimId) {
		this.tourClaimId = tourClaimId;
	}

	public Date getTourClaimDate() {
		return tourClaimDate;
	}

	public void setTourClaimDate(Date tourClaimDate) {
		this.tourClaimDate = tourClaimDate;
	}

	public Date getTourPlanDate() {
		return tourPlanDate;
	}

	public void setTourPlanDate(Date tourPlanDate) {
		this.tourPlanDate = tourPlanDate;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public String getVisitPurpose() {
		return visitPurpose;
	}

	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
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

	public TourPlan getTourPlanId() {
		return tourPlanId;
	}

	public void setTourPlanId(TourPlan tourPlanId) {
		this.tourPlanId = tourPlanId;
	}

	public int getAdvancePaid() {
		return advancePaid;
	}

	public void setAdvancePaid(int advancePaid) {
		this.advancePaid = advancePaid;
	}

	public int getTotalTravel() {
		return totalTravel;
	}

	public void setTotalTravel(int totalTravel) {
		this.totalTravel = totalTravel;
	}

	public int getTotalConv() {
		return totalConv;
	}

	public void setTotalConv(int totalConv) {
		this.totalConv = totalConv;
	}

	public int getTotalStay() {
		return totalStay;
	}

	public void setTotalStay(int totalStay) {
		this.totalStay = totalStay;
	}

	public int getPassTravel() {
		return passTravel;
	}

	public void setPassTravel(int passTravel) {
		this.passTravel = passTravel;
	}

	public int getPassConv() {
		return passConv;
	}

	public void setPassConv(int passConv) {
		this.passConv = passConv;
	}

	public int getPassStay() {
		return passStay;
	}

	public void setPassStay(int passStay) {
		this.passStay = passStay;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
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
