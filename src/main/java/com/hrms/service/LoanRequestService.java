package com.hrms.service;

import java.util.List;

import com.hrms.model.LoanApplication;


public interface LoanRequestService {

	void addLoanRequest(LoanApplication loanRequest);

	List<LoanApplication> getAllRequest();
	public void removeLoanRequest(String id);
	LoanApplication findByIdLoanReq(String id);
	void updateLoanRequest(LoanApplication ltaRequest);

	List<LoanApplication> findByApprovalLoan(String id);
}
