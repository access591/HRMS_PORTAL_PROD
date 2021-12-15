package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LoanApplication;
@Repository
public class LoanRequestDaoImpl extends AbstractGenericDao<LoanApplication> implements LoanRequestDao{
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	@Override
	public List<LoanApplication> findByApprovalLoan(String id) {
		
		try {
			session= this.sessionFactory.openSession();
			session.beginTransaction();
			Query<LoanApplication>query = session.createQuery("from LoanApplication  e where e.approvalStatus ='Y'",LoanApplication.class);
			
			List<LoanApplication> listLoanApproval = query.getResultList();
			session.getTransaction().commit();
			
			return listLoanApproval;
			
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
