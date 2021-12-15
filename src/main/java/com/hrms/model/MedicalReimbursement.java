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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "MEDICLAIM_MASTER")
public class MedicalReimbursement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2627010190406966654L;

	/**
	 * 
	 */
	

	/**
	 * 
	 */

	

	@Id
	@Column(name = "SLIP_NO")
	private String slipNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "SLIP_DATE")
	private Date dateOfSlip;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "CLAIMING_DATE")
	private Date claimingDate;

	@Column(name = "NAME_OF_PERSON")
	private String nameOfPerson;

	@Column(name = "EMP_RELATION")
	private String empRelation;

	@Column(name = "TREATMENT_TYPE")
	private String treatmentType;

	@Column(name = "TREAT_DESCRIPTION")
	private String treatDescription;
	@Column(name = "MED_IND_OUT")
	private String medIndOut;
	
	@Column(name = "APPROVED_BY")
	private String  approvedBy ;   
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate ;
	
	 @OneToMany(mappedBy="slipNo",cascade = CascadeType.ALL,
		        orphanRemoval = true)
	List<MedicalReimbursementDetail> medicalReimbursementDetail;
	     @Transient
	    private String calmlAmount;
	     @Transient
		private String passedAmount;
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>second Step Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public String getSlipNo() {
		return slipNo;
	}

	public String getCalmlAmount() {
		return calmlAmount;
	}

	public void setCalmlAmount(String calmlAmount) {
		this.calmlAmount = calmlAmount;
	}

	public String getPassedAmount() {
		return passedAmount;
	}

	public void setPassedAmount(String passedAmount) {
		this.passedAmount = passedAmount;
	}

	public List<MedicalReimbursementDetail> getMedicalReimbursementDetail() {
		return medicalReimbursementDetail;
	}

	public void setMedicalReimbursementDetail(List<MedicalReimbursementDetail> medicalReimbursementDetail) {
		this.medicalReimbursementDetail = medicalReimbursementDetail;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public Date getDateOfSlip() {
		return dateOfSlip;
	}

	public void setDateOfSlip(Date dateOfSlip) {
		this.dateOfSlip = dateOfSlip;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Date getClaimingDate() {
		return claimingDate;
	}

	public void setClaimingDate(Date claimingDate) {
		this.claimingDate = claimingDate;
	}

	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}

	public String getEmpRelation() {
		return empRelation;
	}

	public void setEmpRelation(String empRelation) {
		this.empRelation = empRelation;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public String getTreatDescription() {
		return treatDescription;
	}

	public void setTreatDescription(String treatDescription) {
		this.treatDescription = treatDescription;
	}

	public String getMedIndOut() {
		return medIndOut;
	}

	public void setMedIndOut(String medIndOut) {
		this.medIndOut = medIndOut;
	}
//...............................................

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

	public MedicalReimbursement(Date dateOfSlip, Employee empCode, Date claimingDate, String nameOfPerson,
			String empRelation, String treatmentType, String treatDescription, String medIndOut, String approvedBy,
			String approvalStatus, Date approvalDate, List<MedicalReimbursementDetail> medicalReimbursementDetail) {
		super();
		this.dateOfSlip = dateOfSlip;
		this.empCode = empCode;
		this.claimingDate = claimingDate;
		this.nameOfPerson = nameOfPerson;
		this.empRelation = empRelation;
		this.treatmentType = treatmentType;
		this.treatDescription = treatDescription;
		this.medIndOut = medIndOut;
		this.approvedBy = approvedBy;
		this.approvalStatus = approvalStatus;
		this.approvalDate = approvalDate;
		this.medicalReimbursementDetail = medicalReimbursementDetail;
	}

	public MedicalReimbursement() {
		super();
	}

	
	
	
}
