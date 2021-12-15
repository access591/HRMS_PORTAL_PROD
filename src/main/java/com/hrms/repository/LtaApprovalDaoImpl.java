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
import com.hrms.model.LtaRequest;
@Repository
public class LtaApprovalDaoImpl extends AbstractGenericDao<LtaRequest> implements LtaApprovalDao{
	@Autowired SessionFactory sessionFactory;

	Session session=null;

	@Override
	public List<LtaRequest> getAllLtaApproval() {
		try {
		 session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<LtaRequest>query = session.createQuery("from LtaRequest  e where e.approvalStatus ='N'",LtaRequest.class);
			
			List<LtaRequest> listLtaApproval = query.getResultList();
			session.getTransaction().commit();
			
			return listLtaApproval;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;

}



	@Override
	public void approvedLtaRequestById(String id) {
		session = this.sessionFactory.openSession();
		Query query = session.createQuery("UPDATE LtaRequest e set e.approvalStatus =:approvalStatus WHERE e.ltaCode= :id" );
		query.setParameter("approvalStatus", "Y");
		query.setParameter("id", id);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		
	}
}

