package com.hrms.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisitionDetail;
import com.hrms.repository.EmployeeRequisitionDetailDao;
import com.hrms.service.EmployeeRequisitionDetailService;

@Service
public class EmployeeRequisitionDetailServiceImpl implements EmployeeRequisitionDetailService{

	@Autowired EmployeeRequisitionDetailDao  employeeRequisitionDetailDao;
	@Autowired SessionFactory sessionFactory;
	@Override
	public void addEmployeeRequisitionDetail(EmployeeRequisitionDetail employeReq) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
		    
		    session.save(employeReq);
		
		    session.getTransaction().commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	    
	}

	@Override
	public List<EmployeeRequisitionDetail> getAllEmployeeRequisitionDetail() {
		
		return this.employeeRequisitionDetailDao.findAll();
	}

	@Override
	public EmployeeRequisitionDetail findEmployeeRequisitiondById(String empCode) {
		
		return this.employeeRequisitionDetailDao.findById(empCode);
	}

	@Override
	public void updateEmployeeRequisition(EmployeeRequisitionDetail c) {
		this.employeeRequisitionDetailDao.saveOrUpdate(c);
	}

	@Override
	public void removeEmployeeRequisitionDetail(String id) {
		this.employeeRequisitionDetailDao.delete(id);
		
	}

	@Override
	public boolean isEmployeeRequisitionDetailExists(String empCode) {
		
		return false;
	}

	@Override
	public List<EmployeeRequisitionDetail> findUniqueDesignation() {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		
		List<EmployeeRequisitionDetail> result = null;
		try {
			
			tx = session.beginTransaction();
			Query<EmployeeRequisitionDetail> query = session.createQuery("from EmployeeRequisitionDetail e "
					+ "inner join fetch e.designation d "
					+ "where e.employeeRequisition.status=:status"
					+ " group by d.desgCode",
					EmployeeRequisitionDetail.class);
			
			query.setParameter("status", "Y");
			tx.commit();
			result = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
		
	}

	
	

}
