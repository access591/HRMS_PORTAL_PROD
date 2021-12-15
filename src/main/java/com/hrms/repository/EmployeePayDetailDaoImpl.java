package com.hrms.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.EmployeePayDetail;

@Repository
public class EmployeePayDetailDaoImpl extends AbstractGenericDao<EmployeePayDetail> implements EmployeePayDetailDao {

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public boolean isEmployeePayExists(String empCode) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery( "select count(*) from Emp_Pay_detail e where e.empCode=:empCode");
		query.setParameter("empCode", empCode);
		if(query.list().size()>1) {
			return true;
		}
		return false;
	}

	

}
