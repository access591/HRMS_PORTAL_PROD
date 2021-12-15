package com.hrms.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.OrderIssueTracking;
import com.hrms.service.OrderIssueTrackingService;

@Service
public class OrderIssueTrackingServiceImpl implements OrderIssueTrackingService{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveOrderIssueTracking(OrderIssueTracking orderIssueTracking) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			
			tx = session.beginTransaction();

			session.save(orderIssueTracking);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<OrderIssueTracking> getAllOrderIssueTracking() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<OrderIssueTracking> result = null;
		try  {

			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OrderIssueTracking> criteria = builder.createQuery(OrderIssueTracking.class);
			criteria.from(OrderIssueTracking.class);
			result = session.createQuery(criteria).getResultList();

			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public OrderIssueTracking findOrderIssueTrackingById(Long orderIssueTrackingId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			
			tx = session.beginTransaction();
			OrderIssueTracking orderIssueTracking = session.find(OrderIssueTracking.class, orderIssueTrackingId);
			tx.commit();
			
			return orderIssueTracking;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public void updateOrderIssueTracking(OrderIssueTracking orderIssueTracking) {
		
		Session session = sessionFactory.openSession(); 
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			session.find(OrderIssueTracking.class, orderIssueTracking.getOrderTrackingId());

			session.merge(orderIssueTracking);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			tx.commit();
		}
		
	}

	@Override
	public void removeOrderIssueTracking(Long orderTrackinId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			Object o = session.get(OrderIssueTracking.class, orderTrackinId);
			OrderIssueTracking e = (OrderIssueTracking) o;
			
			session.delete(e);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
	}

	@Override
	public OrderIssueTracking findOrderIssueTrackingByIssuedby(String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			
			
			tx = session.beginTransaction();
			Query<OrderIssueTracking> query = session.createQuery("from OrderIssueTracking o "
					+ "left join fetch o.employee e where e.empCode = :empCode", OrderIssueTracking.class);
			
			query.setParameter("empCode", empCode);
			OrderIssueTracking result = query.getSingleResult();
			tx.commit();
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}
