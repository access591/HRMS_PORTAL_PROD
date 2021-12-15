package com.hrms.service;

import java.util.List;

import com.hrms.model.ApplicantInfo;

public interface ApplicantInfoService {
	
	public void addApplicantInfo(ApplicantInfo applicantInfo);
	public List<ApplicantInfo> getAllApplicantInfo();
	public ApplicantInfo getApplicantInfoByApplicantCode(String applicantCode);
	public void updateApplicantInfoInterviewStatus(String applicant,String interviewStatus);
	public List<ApplicantInfo> findApplicantInfoStatusHoldAndPending();
	public List<ApplicantInfo> findApplicantInfoStatusForward();
	public List<ApplicantInfo> findAllApplicantInfo();
}
