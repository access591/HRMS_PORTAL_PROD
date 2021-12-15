package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveGrant;
import org.hibernate.query.Query;

@Repository
public class LeaveGrantRegisterDaoImpl extends AbstractGenericDao<LeaveGrant> implements LeaveGrantRegisterDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<LeaveGrant> findLeaveGrantByEmp(String empCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveGrant> result = null;
		try {
			tx = session.beginTransaction();
			Query<LeaveGrant> query = session.createQuery("from LeaveGrant l where l.empCode.empCode=:empCode", LeaveGrant.class);

			query.setParameter("empCode", empCode);
			result = query.getResultList();

			tx.commit();
		} catch (HibernateException e) {

			e.printStackTrace();

		} finally {
			session.close();
		}
		
		return result;

	}

}
