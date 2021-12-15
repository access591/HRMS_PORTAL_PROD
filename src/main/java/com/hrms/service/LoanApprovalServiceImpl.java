package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LoanApplication;
import com.hrms.repository.LoanApprovalDao;

@Service
public class LoanApprovalServiceImpl  implements LoanApprovalService  {
@Autowired
LoanApprovalDao loanApprovalDao;
	@Override
	public List<LoanApplication> getAllLoanApproval() {
		return loanApprovalDao.getAllLoanApproval();
		 
	}
	@Override
	public void approvedLoanRequestById(String id) {
		this.loanApprovalDao.approvedLoanRequestById(id);
		
	}

}
