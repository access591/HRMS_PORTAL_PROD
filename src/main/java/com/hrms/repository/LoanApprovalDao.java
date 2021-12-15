package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.LoanApplication;

public interface LoanApprovalDao extends GenericDao<LoanApplication>{

	List<LoanApplication> getAllLoanApproval();

	void approvedLoanRequestById(String id);

}
