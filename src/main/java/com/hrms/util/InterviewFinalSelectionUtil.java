package com.hrms.util;

import java.util.Date;

public class InterviewFinalSelectionUtil {
	
	private String interviewCode;
	private Date interviewDate;
	private String applicantCode;
	private String applicantName;
	private Date applicantDate;
	private String currentCtc;
	private String expectedCtc;
	private String ovarAllRating;
	private String selectionStatus = "Action";
	
	public InterviewFinalSelectionUtil() {
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

	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Date getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	public String getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}

	public String getExpectedCtc() {
		return expectedCtc;
	}

	public void setExpectedCtc(String expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	public String getOvarAllRating() {
		return ovarAllRating;
	}

	public void setOvarAllRating(String ovarAllRating) {
		this.ovarAllRating = ovarAllRating;
	}

	public String getSelectionStatus() {
		return selectionStatus;
	}

	public void setSelectionStatus(String selectionStatus) {
		this.selectionStatus = selectionStatus;
	}
	
	
	
	
	

}
