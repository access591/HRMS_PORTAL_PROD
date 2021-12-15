package com.hrms.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeAcr;
import com.hrms.repository.EmployeeAcrDao;
@Service
public class EmployeeAcrServiceImpl implements EmployeeAcrService {
@Autowired  EmployeeAcrDao  employeeAcrDao;
@Autowired SessionFactory sessionFactory;
@Override
	public List<EmployeeAcr> getAllEmployeeAcr() {
	
		return employeeAcrDao.findAll();
	}
	@Override
	public boolean addEmployeeAcr(EmployeeAcr employeeAcr) {
	Session session = sessionFactory.openSession();
		
		try {
		
			session.beginTransaction();
			session.save(employeeAcr);
			session.getTransaction().commit();
		
		return true;
	
	} catch (HibernateException e) {
		
		e.printStackTrace();
	}
	 finally {
			session.clear();
			session.close();
	}
		
	return false;
	}
	@Override
	public void removeEmployeeAcr(long id) {
	try {
		this.employeeAcrDao.delete(id);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		
	}
	@Override
	public EmployeeAcr findByIdEmployeeAcr(long id) {
		
		try {
			return employeeAcrDao.findById(id);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		finally {
			 System.out.println("finally : i execute always.");
	}
		return null;
	}
	@Override
	public void updateEmployeePromotion(EmployeeAcr employeeAcr) {
	this.employeeAcrDao.saveOrUpdate(employeeAcr);
		
	}
	@Override
	public EmployeeAcr findEmployeeAcrByEmpCode(String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		EmployeeAcr result = null;
		
		try {
			tx = session.beginTransaction();
			Query<EmployeeAcr> query = session.createQuery("from EmployeeAcr e where"
					+ " e.empCode.empCode=:empCode", EmployeeAcr.class);
			query.setParameter("empCode", empCode);
			result = query.getSingleResult();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}
}
