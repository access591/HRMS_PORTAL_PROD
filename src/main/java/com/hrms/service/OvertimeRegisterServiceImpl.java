package com.hrms.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.OvertimeRegister;
import com.hrms.repository.OvertimeRegisterDao;

@Service
public class OvertimeRegisterServiceImpl implements OvertimeRegisterService{

	@Autowired SessionFactory sessionFactory;
	@Autowired OvertimeRegisterDao overtimeRegisterDao;
	Session session=null;
	
	@Override
	public List<OvertimeRegister> findOverTimeRegisterByEmpCodeBetweenDate(String empCode, Date fromDate, Date toDate) {
		
		try {
			 session = sessionFactory.openSession();
			Query<OvertimeRegister> query = session.createQuery("from OvertimeRegister o "
					+ "inner join fetch o.employee e where e.empCode = :empCode and "
					+ "o.timeIN = :fromDate and o.timeOut = :toDate", OvertimeRegister.class);
			query.setParameter("empCode", empCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<OvertimeRegister> result = query.getResultList();
			System.out.println("esult lenth : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addOvertimeRegister(OvertimeRegister overReg) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
	
		s.save(overReg);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

	@Override
	public List<OvertimeRegister> getAllOvertimeRegister() {

		return overtimeRegisterDao.findAll();
	}

	@Override
	public void removeOverTimeRegister(long id) {
	this.overtimeRegisterDao.delete(id);
		
	}

}
