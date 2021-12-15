package com.hrms.repository;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Loan;

@Repository
public class LoanMaterDaoImpl extends AbstractGenericDao<Loan> implements LoanMaterDao {
	private Logger logger = LoggerFactory.getLogger(LoanMaterDaoImpl.class.getName());
	@Override
	public Loan checkLoanExists(Loan loan) {
		Loan gradeName = null;
		try {

			Criteria criteria = getSession().createCriteria(Loan.class);
			gradeName = (Loan) criteria.setFetchMode("LOAN_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("loanName", loan.getLoanName())).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return gradeName;
	
	}

	

}
