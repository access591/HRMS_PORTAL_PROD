package com.hrms.model;


import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
@Table(name="TOUR_PLAN_MAST")
public class TourPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7748536710350573569L;
	@Id
	@Column(name = "TOUR_PLAN_ID")
	private String tourPlanId;
	@Temporal(TemporalType.DATE)
	@Column(name = "TOUR_PLAN_DT")
	private Date tourPlanDate;
	 @OneToMany(mappedBy="tourPlanId",cascade = CascadeType.ALL,
		        orphanRemoval = true)
	List<TourPlanDetails> tourPlanDetail;


	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;

	
	@ManyToOne
	@JoinColumn(name ="DEPT_CODE")
	private Department departmentCode ;
	@ManyToOne
	@JoinColumn(name ="DESG_CODE")
	private Designation desgCode;
	
	@Column(name = "TOUR_START_DT")
	private String tourStartDate;
	
    @Column(name = "MOB_NUMBER")
	private String  mobNumber;
    @Column(name = "TOUR_END_DT")
	private String  tourEndDate;
	
    @Column(name = "APPROVED_BY")
	private String  approvedBy ;   
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate ;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getTourPlanId() {
		return tourPlanId;
	}

	public void setTourPlanId(String tourPlanId) {
		this.tourPlanId = tourPlanId;
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


	public String getTourStartDate() {
		return tourStartDate;
	}

	public void setTourStartDate(String tourStartDate) {
		this.tourStartDate = tourStartDate;
	}

	
	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getTourEndDate() {
		return tourEndDate;
	}

	public void setTourEndDate(String tourEndDate) {
		this.tourEndDate = tourEndDate;
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

	public Department getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(Department departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Designation getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(Designation desgCode) {
		this.desgCode = desgCode;
	}

	

	public List<TourPlanDetails> getTourPlanDetail() {
		return tourPlanDetail;
	}

	public void setTourPlanDetail(List<TourPlanDetails> tourPlanDetail) {
		this.tourPlanDetail = tourPlanDetail;
	}

	public TourPlan(String tourPlanId, Date tourPlanDate, List<TourPlanDetails> tourPlanDetail,
			Employee empCode, Department departmentCode, Designation desgCode, String tourStartDate, String mobNumber,
			String tourEndDate, String approvedBy, String approvalStatus, Date approvalDate, String insBy,
			Date insDate) {
		super();
		this.tourPlanId = tourPlanId;
		this.tourPlanDate = tourPlanDate;
		this.tourPlanDetail = tourPlanDetail;
		this.empCode = empCode;
		this.departmentCode = departmentCode;
		this.desgCode = desgCode;
		this.tourStartDate = tourStartDate;
		this.mobNumber = mobNumber;
		this.tourEndDate = tourEndDate;
		this.approvedBy = approvedBy;
		this.approvalStatus = approvalStatus;
		this.approvalDate = approvalDate;
		this.insBy = insBy;
		this.insDate = insDate;
	}

	public TourPlan() {
		super();
	
	}
	
}
