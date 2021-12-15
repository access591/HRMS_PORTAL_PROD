package com.hrms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="INTERVIEW_MASTER")
public class InterviewMaster {
	
	@Id
	@Column(name="INTERVIEW_CODE")
	private String interviewCode;
	
	@Column(name="INTERVIEW_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date interviewDate;
	
	@OneToOne
	@JoinColumn(name="APPLICANT_CODE")
	private ApplicantInfo applicantCode;
	
	@Column(name="APPLICANT_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applicantDate;
	
	@Size(max=10)
	@Column(name="SELECTION_STATUS")
	private String selectionStatus = "N";
	
	@Size(max=250)
	@Column(name="REMARKS")
	private String remarks;
	
	@Size(max=10)
	@Column(name="ROUND1")
	private String round1;
	
	@Size(max=10)
	@Column(name="RATING1")
	private String rating1;
	
	@Size(max=20)
	@Column(name="ROUND2")
	private String round2;
	
	@Size(max=10)
	@Column(name="RATING2")
	private String rating2;
	
	@Size(max=10)
	@Column(name="ROUND3")
	private String round3;
	
	
	@Size(max=10)
	@Column(name="RATING3")
	private String rating3;
	
	@Size(max=10)
	@Column(name="ROUND4")
	private String round4;
	
	@Size(max=10)
	@Column(name="RATING4")
	private String rating4;
	
	@Size(max=10)
	@Column(name="ROUND5")
	private String round5;
	
	@Size(max=10)
	@Column(name="RATING5")
	private String rating5;
	
	@Size(max=10)
	@Column(name="ROUND6")
	private String round6;
	
	@Size(max=10)
	@Column(name="RATING6")
	private String rating6;
	
	@Column(name="INS_DATE")
	private Date insDate;
	
	@Column(name="INS_BY")
	private String insBy;
	
	@Size(max=10)
	@Column(name="OVER_ALL_RATING")
	private String overAllRating;
	
	@Column(name="SELECTED_BY")
	private String selectedBy;
	
	@Column(name="SELECTED_ON")
	private String selectedOn;
	
	@Column(name="OFFER_LETTER_NO")
	private String offerLetterNo;
	
	@Column(name="OFFER_LETTER_DATE")
	private Date offerLetterDate;
	
	@Column(name="SALARY_OFFER")
	private String salaryOffer;
	
	@Column(name="APPOINTMENT_DATE")
	private Date appointmentDate;

	
	
	public InterviewMaster() {
		super();
		
	}
	

	public String getInterviewCode() {
		return interviewCode;
	}

	public void setInterviewCode(String interviewCode) {
		this.interviewCode = interviewCode;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	

	public ApplicantInfo getApplicantCode() {
		return applicantCode;
	}





	public void setApplicantCode(ApplicantInfo applicantCode) {
		this.applicantCode = applicantCode;
	}





	public Date getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	public String getSelectionStatus() {
		return selectionStatus;
	}

	public void setSelectionStatus(String selectionStatus) {
		this.selectionStatus = selectionStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRound1() {
		return round1;
	}

	public void setRound1(String round1) {
		this.round1 = round1;
	}

	public String getRating1() {
		return rating1;
	}

	public void setRating1(String rating1) {
		this.rating1 = rating1;
	}

	public String getRound2() {
		return round2;
	}

	public void setRound2(String round2) {
		this.round2 = round2;
	}

	public String getRating2() {
		return rating2;
	}

	public void setRating2(String rating2) {
		this.rating2 = rating2;
	}

	public String getRound3() {
		return round3;
	}

	public void setRound3(String round3) {
		this.round3 = round3;
	}

	public String getRating3() {
		return rating3;
	}

	public void setRating3(String rating3) {
		this.rating3 = rating3;
	}

	public String getRound4() {
		return round4;
	}

	public void setRound4(String round4) {
		this.round4 = round4;
	}

	public String getRating4() {
		return rating4;
	}

	public void setRating4(String rating4) {
		this.rating4 = rating4;
	}

	public String getRound5() {
		return round5;
	}

	public void setRound5(String round5) {
		this.round5 = round5;
	}

	public String getRating5() {
		return rating5;
	}

	public void setRating5(String rating5) {
		this.rating5 = rating5;
	}

	public String getRound6() {
		return round6;
	}

	public void setRound6(String round6) {
		this.round6 = round6;
	}

	public String getRating6() {
		return rating6;
	}

	public void setRating6(String rating6) {
		this.rating6 = rating6;
	}

	public Date getInsDate() {
		return insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	public String getInsBy() {
		return insBy;
	}

	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	public String getOverAllRating() {
		return overAllRating;
	}

	public void setOverAllRating(String overAllRating) {
		this.overAllRating = overAllRating;
	}

	public String getSelectedBy() {
		return selectedBy;
	}

	public void setSelectedBy(String selectedBy) {
		this.selectedBy = selectedBy;
	}

	public String getSelectedOn() {
		return selectedOn;
	}

	public void setSelectedOn(String selectedOn) {
		this.selectedOn = selectedOn;
	}

	public String getOfferLetterNo() {
		return offerLetterNo;
	}

	public void setOfferLetterNo(String offerLetterNo) {
		this.offerLetterNo = offerLetterNo;
	}

	public Date getOfferLetterDate() {
		return offerLetterDate;
	}

	public void setOfferLetterDate(Date offerLetterDate) {
		this.offerLetterDate = offerLetterDate;
	}

	public String getSalaryOffer() {
		return salaryOffer;
	}

	public void setSalaryOffer(String salaryOffer) {
		this.salaryOffer = salaryOffer;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	
}
