package com.hrms.service;

import java.util.List;

import com.hrms.model.InterviewMaster;

public interface InterviewMasterService {
	
	public void addInterviewMaster(InterviewMaster interviewMaster );
	public List<InterviewMaster> getAllInterviewMaster();
	public void interviewFinalapproval(String applicantCode,String interviewCode,String finalApprovalStatus );
	
	public List<InterviewMaster> getFinalSelection();
	
	public InterviewMaster findinterviewMasterById(String interviewCode);
}
