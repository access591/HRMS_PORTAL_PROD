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

import com.hrms.model.TourPlan;
@Repository
public class TourPlanApprovalDaoImpl extends  AbstractGenericDao<TourPlan> implements TourPlanApprovalDao {
	@Autowired SessionFactory sessionFactory;
	Session session =null;
	@Override
	public List<TourPlan> getAllTourPlan() {
		
		try {
			 session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<TourPlan> query = session.createQuery("from TourPlan e where e.approvalStatus ='N' ",TourPlan.class);
			
			List<TourPlan> tourPlanList = query.getResultList();
			session.getTransaction().commit();
			
			return tourPlanList;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void approvedByTourPlanId(String id) {
		 session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE TourPlan e set e.approvalStatus =:approvalStatus WHERE e.tourPlanId= :id" );
		query.setParameter("approvalStatus", "Y");
		query.setParameter("id", id);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		
	}

}
