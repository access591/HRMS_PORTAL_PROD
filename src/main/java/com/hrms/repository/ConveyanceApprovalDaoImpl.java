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
import com.hrms.model.LocalConvyence;
@Repository
public class ConveyanceApprovalDaoImpl extends AbstractGenericDao<LocalConvyence> implements ConveyanceApprovalDao {
	@Autowired SessionFactory sessionFactory;
	
	Session session=null;
	@Override
	public List<LocalConvyence> getAllLocalConveyance() {
	
		try {
			 session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<LocalConvyence> query = session.createQuery("from LocalConvyence e where e.approvalStatus ='N'",LocalConvyence.class);
			
			List<LocalConvyence> localConvyenceList = query.getResultList();
			session.getTransaction().commit();
			
			return localConvyenceList;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void approvedByLocalConvyenceId(String id) {
		 session = this.sessionFactory.openSession();
		Query query = session.createQuery("UPDATE LocalConvyence e set e.approvalStatus =:approvalStatus WHERE e.localConvId= :id" );
		query.setParameter("approvalStatus", "Y");
		query.setParameter("id", id);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		
	}

}
