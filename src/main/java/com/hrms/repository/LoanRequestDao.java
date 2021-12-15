package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.LoanApplication;

public interface LoanRequestDao extends GenericDao<LoanApplication> {

	List<LoanApplication> findByApprovalLoan(String id);

}
