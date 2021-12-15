package com.hrms.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hrms.model.ArmsLicenses;
import com.hrms.model.Award;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.EmployeeUnderRule;
import com.hrms.repository.ArmsLicenseDao;
import com.hrms.service.ArmsLicenseService;

@Service
public class ArmsLicenseServiceImpl implements ArmsLicenseService {
	@Autowired
	ArmsLicenseDao armsLicenseDao;

	@Autowired
	SessionFactory sessionFactory;

	@Modifying
	@Override
	public void addArmsLicenseDetails(ArmsLicenses armsLicense) {
		
//		armsLicense.setArmsCode(armsLicenseDao.getMaxId("ARM"));

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(armsLicense);
			tx.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public ArmsLicenses findArmsLicensesByEmployee(String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArmsLicenses result = null;

		try {
			tx = session.beginTransaction();
			Query<ArmsLicenses> query = session.createQuery("from ArmsLicenses e where " 
						+ "e.employee.empCode=:empCode",
					ArmsLicenses.class);
			query.setParameter("empCode", empCode);
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
	public List<ArmsLicenses> getAllArmsLicenses() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<ArmsLicenses> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<ArmsLicenses> query = session.createQuery("from ArmsLicenses a", ArmsLicenses.class);
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
	public void updateArmsLicenseService(ArmsLicenses armsLicense) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			ArmsLicenses emp = session.find(ArmsLicenses.class, armsLicense.getArmsCode());
			emp.getArmsLicensesDetail().clear();
			
			emp.getArmsLicensesDetail().addAll(armsLicense.getArmsLicensesDetail());
			session.merge(armsLicense);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public void removeArmsLicenseService(String id2) {
		this.armsLicenseDao.delete(id2);

	}

	@Override 
	public ArmsLicenses findArmsLicenseById(String id) {
//		try {
//			return armsLicenseDao.findArmsByEmpEmpCode(id);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public List<ArmsLicenses> allArmsLicenseDetails() {

		Session session = sessionFactory.openSession();
		
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ArmsLicenses> criteria = builder.createQuery(ArmsLicenses.class);
		criteria.from(ArmsLicenses.class);
		List<ArmsLicenses> data = session.createQuery(criteria).getResultList();
		
		session.close();
		return data;
	}

	@Override
	public ArmsLicenses findArmsByEmpEmpCode(String empCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArmsLicenses result = null;
		
		try {
			tx = session.beginTransaction();
			Query<ArmsLicenses> query = session.createQuery("from ArmsLicenses a", ArmsLicenses.class);
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
