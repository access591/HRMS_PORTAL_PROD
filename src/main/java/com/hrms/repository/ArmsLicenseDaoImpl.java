package com.hrms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.ArmsLicenses;
import com.hrms.model.Employee;
@Repository
public class ArmsLicenseDaoImpl  extends AbstractGenericDao<ArmsLicenses> implements ArmsLicenseDao {
	@Autowired SessionFactory sessionFactory;
	@Override
	public  ArmsLicenses findArmsByEmpEmpCode(String empCode) {
		
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query<ArmsLicenses> query = session.createQuery("from ArmsLicenseDetails e where e.empCode=:empCode",ArmsLicenses.class);
			query.setParameter("empCode",empCode);
			ArmsLicenses employeeList = query.getSingleResult();
			return employeeList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

