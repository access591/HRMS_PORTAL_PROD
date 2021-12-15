package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LoanApplication;
import com.hrms.repository.LoanRequestDao;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {
@Autowired
LoanRequestDao  loanRequestDao;
	@Override
	public void addLoanRequest(LoanApplication loanRequest) {
		loanRequest.setAppNo(loanRequestDao.getMaxId("LAR"));
		
		this.loanRequestDao.saveOrUpdate(loanRequest);
	}
	@Override
	public List<LoanApplication> getAllRequest() {
		return  loanRequestDao.findAll() ;
		
		
	}
	@Override
	public void removeLoanRequest(String id) {
	try {
		this.loanRequestDao.delete(id);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
		
	}
	@Override
	public LoanApplication findByIdLoanReq(String id) {
	
		return loanRequestDao.findById(id);
	}
	@Override
	public void updateLoanRequest(LoanApplication loanRequest) {
	String ui=loanRequest.getAppNo();
	
	loanRequest.setApprovalStatus("N");
		this.loanRequestDao.saveOrUpdate(loanRequest);
	}
	@Override
	public List<LoanApplication> findByApprovalLoan(String id) {
		return loanRequestDao.findByApprovalLoan(id);
		 
	}

}
