package com.hrms.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeePromotion;
import com.hrms.repository.EmployeePromotionDao;

@Service
public class EmployeePromotionServiceImpl implements EmployeePromotionService {
@Autowired EmployeePromotionDao  employeePromotionDao;
@Autowired SessionFactory sessionFactory;


	@Override
	public boolean addEmployeePromotion(EmployeePromotion employeePromotion) {
		Session session = sessionFactory.openSession();
		
		try {
		
			session.beginTransaction();
			session.save(employeePromotion);
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
	public List<EmployeePromotion> getAllEmployeePromotion() {
		return employeePromotionDao.findAll();
	}
	@Override
	public void removeEmployeePromotion(long id) {
	this.employeePromotionDao.delete(id);
		
	}
	@Override
	public EmployeePromotion findByIdEmployeePromotion(long id) {
		
		return employeePromotionDao.findById(id);
	}
	@Override
	public void updateEmployeePromotion(EmployeePromotion employeePromotion) {
	this.employeePromotionDao.saveOrUpdate(employeePromotion);
		
	}
	
	
	//written by rahul 
	
	@Override
	public EmployeePromotion findemployeePromotionByEmpCode(String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		EmployeePromotion result = null;
		try {
			tx = session.beginTransaction();
			Query<EmployeePromotion> query = session.createQuery("from EmployeePromotion e "
					+ "where e.empCode.empCode=:empCode",EmployeePromotion.class);
			query.setParameter("empCode", empCode);
			result = query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return result;
	}
}
