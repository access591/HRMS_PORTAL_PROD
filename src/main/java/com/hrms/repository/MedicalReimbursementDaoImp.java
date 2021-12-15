package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.MedicalReimbursement;

@Repository
public class MedicalReimbursementDaoImp  extends AbstractGenericDao<MedicalReimbursement> implements MedicalReimbursementDao{
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	@Override
	public void approvedByMrId(String slipNo) {
		 session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE MedicalReimbursement e set e.approvalStatus =:approvalStatus WHERE e.slipNo = :slipNo" );
		query.setParameter("approvalStatus", "Y");
		query.setParameter("slipNo", slipNo);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		
	}
	@Override
	public List<MedicalReimbursement> getAllMedicalReimbursementApproval() {
		try {
			 session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<MedicalReimbursement>query = session.createQuery("from MedicalReimbursement  e where e.approvalStatus ='N'",MedicalReimbursement.class);
			
			List<MedicalReimbursement> listMdApproval = query.getResultList();
			session.getTransaction().commit();
			
			return listMdApproval;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
