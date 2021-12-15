package com.hrms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LoanSchedule;
import com.hrms.repository.LoanTrackingDao;

@Service
public class LoanTrackingServiceImpl implements LoanTrackingService {
@Autowired
LoanTrackingDao loanTrackingDao;
@Autowired
SessionFactory sessionFactory;

	@Override
	public boolean addLoanSchhedule(LoanSchedule ls) {
		
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		
		s.save(ls);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
