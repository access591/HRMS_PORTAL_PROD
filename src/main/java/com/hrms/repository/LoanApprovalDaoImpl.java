package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LoanApplication;

@Repository
public class LoanApprovalDaoImpl extends AbstractGenericDao<LoanApplication> implements LoanApprovalDao {
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	@Override
	public List<LoanApplication> getAllLoanApproval() {
		try {
		session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<LoanApplication>query = session.createQuery("from LoanApplication  e where e.approvalStatus ='N'",LoanApplication.class);
			
			List<LoanApplication> listLoanApproval = query.getResultList();
			session.getTransaction().commit();
			
			return listLoanApproval;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void approvedLoanRequestById(String id) {
	 session = this.sessionFactory.openSession();
		Query query = session.createQuery("UPDATE LoanApplication e set e.approvalStatus =:approvalStatus WHERE e.appNo= :id" );
		query.setParameter("approvalStatus", "Y");
		query.setParameter("id", id);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		
	}
		
	}


