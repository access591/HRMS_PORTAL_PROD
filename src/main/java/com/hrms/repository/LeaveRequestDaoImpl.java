package com.hrms.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveRequest;

@Repository
public class LeaveRequestDaoImpl extends AbstractGenericDao<LeaveRequest> implements LeaveRequestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LeaveRequest> findAllByEmpCode(String empCode) {

		Session session = this.sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			String hql = "FROM LeaveRequest l left join fetch l.employee E WHERE E.empCode = :empCode";
			Query<LeaveRequest> query = session.createQuery(hql, LeaveRequest.class);
			query.setParameter("empCode", empCode);

			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("exception block in leaveRequestDaoImpl model ");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;

	}

	@Override
	public List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;

		try {
			tx = session.beginTransaction();

			String hql = "FROM LeaveRequest e WHERE e.empCode=:empCode and e.applyDate=:applyDate";
			Query<LeaveRequest> query = session.createQuery(hql, LeaveRequest.class);
			query.setParameter("empCode", empCode);
			query.setParameter("applyDate", applyDate);
			tx.commit();

			result = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;

		List<LeaveRequest> result = null;

		try {
			tx = session.beginTransaction();
			String hql = "from LeaveRequest e where e.deptCode = :deptCode and e.status = 'N' ";
			Query<LeaveRequest> query = session.createQuery(hql, LeaveRequest.class);
			query.setParameter("deptCode", deptCode);
			result = query.list();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<LeaveRequest> getEmployeeByStatusY() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		try {
			tx = session.beginTransaction();
			String hql = "from LeaveRequest e where e.status = 'Y'";
			Query<LeaveRequest> query = session.createQuery(hql, LeaveRequest.class);
			result = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<LeaveRequest> findByEmpBetweenDate(String empCode, Date toDate, Date fromDate) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		List<LeaveRequest> listLeaveRequest = null;
		try {

			tx = session.beginTransaction();

			String qu = "FROM LeaveRequest l inner join fetch l.employee e "
					+ "WHERE l.fromDate >=:fromDate and l.toDate <=:toDate and  e.empCode =:empCode";
			Query<LeaveRequest> query = session.createQuery(qu, LeaveRequest.class);

			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("empCode", empCode);

			tx.commit();

			listLeaveRequest = query.list();

		} catch (Exception e) {
			System.out.println("findByEmpBetweenDate error ");
			e.printStackTrace();

		} finally {
			session.close();
		}

		return listLeaveRequest;
	}

	@Override
	public LeaveRequest findByToDate(Date date) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		LeaveRequest result = null;
		try {
			tx = session.beginTransaction();
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l where l.toDate =:date",
					LeaveRequest.class);
			query.setParameter("date", date);
			result = query.getSingleResult();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<LeaveRequest> getEmployeeByStatusN() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			
			String hql = "from LeaveRequest e where e.status = 'N'";
			Query<LeaveRequest> query = session.createQuery(hql,LeaveRequest.class);
			
			result = query.list();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return result;
	}

}
