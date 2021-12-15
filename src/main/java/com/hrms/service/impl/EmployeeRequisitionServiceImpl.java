package com.hrms.service.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisition;
import com.hrms.repository.EmployeeRequisitionDao;
import com.hrms.service.EmployeeRequisitionService;

@Service
public class EmployeeRequisitionServiceImpl implements EmployeeRequisitionService{

	@Autowired EmployeeRequisitionDao employeRequisitionDao;
	@Autowired SessionFactory sessionFactory;
	
	
	@Override
	public void addEmployeeRequisition(EmployeeRequisition employeReq) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			employeReq.setReqCode(employeRequisitionDao.getMaxId("REQ"));
		    session.save(employeReq);
		    tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<EmployeeRequisition> getAllEmployeeRequisition() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<EmployeeRequisition> result = null;
		
		try {
			tx = session.beginTransaction();
			TypedQuery<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition", EmployeeRequisition.class);
			
		
			
			tx.commit();
			result = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public EmployeeRequisition findEmployeeRequisitiondById(String reqCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx= session.beginTransaction();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e where e.reqCode = :reqCode", EmployeeRequisition.class);
			query.setParameter("reqCode", reqCode);
			EmployeeRequisition er = query.getSingleResult();
			tx.commit();
			return er;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
		
	}

	@Override
	public void updateEmployeeRequisition(EmployeeRequisition c) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			EmployeeRequisition emp = session.find(EmployeeRequisition.class, c.getReqCode());
			emp.getEmployeRequisitionDetail().clear();
			
			emp.getEmployeRequisitionDetail().addAll(c.getEmployeRequisitionDetail());
			session.merge(c);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

	}

	@Override
	public void removeEmployeeRequisition(String reqCode) {
		
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Object o = session.get(EmployeeRequisition.class, reqCode);
			EmployeeRequisition e = (EmployeeRequisition) o;
			
			session.delete(e);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public boolean isEmployeeRequisitionExists(String empCode) {
		
		return false;
	}

	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusN() {
		
		return this.employeRequisitionDao.findEmployeeReqByStatusN();
	}

	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusY() {
		
		return this.employeRequisitionDao.findEmployeeReqByStatusY();
	}

	@Override
	public void approvedByReqCode(String reqCode) {
		this.employeRequisitionDao.approvedStatusByReqCode(reqCode);
		
		
	}

	@Override
	public void approvedByReqCodeAndStatus(String reqCode, String requisitionApproval) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	
		try {
			tx = session.beginTransaction();
			EmployeeRequisition em = session.find(EmployeeRequisition.class, reqCode);
			em.setStatus(requisitionApproval);
			session.merge(em);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<EmployeeRequisition> getAllPendingEmployeeRequisition() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e where e.status = :status", EmployeeRequisition.class);
			query.setParameter("status", "N");
			tx.commit();
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}
