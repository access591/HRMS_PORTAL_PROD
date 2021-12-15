package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.Loan;

public interface LoanMaterDao extends GenericDao<Loan> {

	Loan checkLoanExists(Loan loan);

}
