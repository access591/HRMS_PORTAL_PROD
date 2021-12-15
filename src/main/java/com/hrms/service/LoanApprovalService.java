package com.hrms.service;

import java.util.List;

import com.hrms.model.LoanApplication;

public interface LoanApprovalService {

	List<LoanApplication> getAllLoanApproval();

	void approvedLoanRequestById(String id);

}
