package com.hrms.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hrms.model.LeaveRequest;
import com.hrms.repository.LeaveRequestDao;
import com.hrms.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	LeaveRequestDao leaveRequestDao;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<LeaveRequest> findAllByEmpCode(String empCode) {

		return this.leaveRequestDao.findAllByEmpCode(empCode);

	}

	
	@Modifying
	@Override
	public void addLeave(LeaveRequest leaveRequest) {

		
		if (leaveRequest.getRequestType().equals("single")) {
			leaveRequest.setToDate(leaveRequest.getFromDate());
		}
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(leaveRequest);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

	}

	@Override
	public List<LeaveRequest> getAllLeaves() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			
			Query<LeaveRequest> query = session.createQuery(
					"from LeaveRequest lr inner join fetch lr.employee e" + " inner join fetch lr.leave lv ",
					LeaveRequest.class);

			result = query.getResultList();
			tx.commit();

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
		
	}

	@Override
	public LeaveRequest findLeaveRequestById(long id) {

		return this.leaveRequestDao.findById(id);
	}

	@Override
	public void updateLeaveRequest(LeaveRequest d) {
		this.leaveRequestDao.saveOrUpdate(d);

	}

	@Override
	public void removeLeaveRequest(Long id) {
		this.leaveRequestDao.delete(id);

	}

	@Override
	public List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate) {

		return this.leaveRequestDao.findByEmpCodeAndApplyDate(empCode, applyDate);
	}

	@Override
	public List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode) {
		return this.leaveRequestDao.findAllByDeptCodeAndStatusN(deptCode);
	}

	@Override
	public List<LeaveRequest> getEmployeeByStatusY() {

		return this.leaveRequestDao.getEmployeeByStatusY();
	}

	@Override
	public List<LeaveRequest> findByEmpBetweenDate(String empCode, Date toDate, Date fromDate) {

		return this.leaveRequestDao.findByEmpBetweenDate(empCode, toDate, fromDate);
	}

	@Override
	public LeaveRequest findByToDate(Date date) {
		
		return this.leaveRequestDao.findByToDate(date);
	}

	@Override
	public List<LeaveRequest> getEmployeeByStatusN() {
		return this.leaveRequestDao.getEmployeeByStatusN();
	}

	@Override
	public List<LeaveRequest> findAllApproveLeaveRequestBetweenDate(Date fromDate, Date toDate) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " l.status=:status", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("status", "Y");
			
			result = query.getResultList();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<LeaveRequest> findApproveLeaveRequestByEmpBetweenDate(Date fromDate, Date toDate, String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l left join fetch "
					+ "l.employee e "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " l.status=:status and e.empCode = :empCode", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("status", "Y");
			query.setParameter("empCode", empCode);
			
			result = query.getResultList();
			
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<LeaveRequest> findAllApproveLeaveRequestByDeptBetweenDate(Date fromDate, Date toDate, String deptCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l left join fetch "
					+ "l.department d "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " l.status=:status and d.departmentCode = :deptCode", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("status", "Y");
			query.setParameter("deptCode", deptCode);
			
			result = query.getResultList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<LeaveRequest> findAllLeaveRequestBetweenDate(Date fromDate, Date toDate) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<LeaveRequest> query = session.createQuery(
					"from LeaveRequest lr inner join fetch lr.employee e" + " inner join fetch lr.leave lv "
							+ "where lr.fromDate>=:fromDate and lr.toDate<:toDate",
					LeaveRequest.class);
			
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);

			result = query.getResultList();
			tx.commit();

		} catch (Exception e) {
			System.out.println("error occured in gt All leaves ");
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<LeaveRequest> findAllLeaveRequestByDeptBetweenDate(Date fromDate, Date toDate, String deptCode) {
		

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<LeaveRequest> query = session.createQuery(
					"from LeaveRequest lr inner join fetch lr.employee e" + " inner join fetch lr.department d "
							+ "where lr.fromDate>=:fromDate and lr.toDate<:toDate and d.departmentCode = :deptCode",
					LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("deptCode", deptCode);

			result = query.getResultList();
			tx.commit();

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;

	}

	@Override
	public List<LeaveRequest> findAllLeaveRequestbyEmpBetweenDate(Date fromDate, Date toDate, String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<LeaveRequest> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l left join fetch "
					+ "l.employee e "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " e.empCode = :empCode", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("empCode", empCode);
			
			result = query.getResultList();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
