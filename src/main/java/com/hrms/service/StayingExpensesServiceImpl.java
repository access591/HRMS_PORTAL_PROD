package com.hrms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.StayingExpenses;

@Service
public class StayingExpensesServiceImpl implements StayingExpensesService{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addStayingExpenses(StayingExpenses stayingExpenses) {
	
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		
		s.save(stayingExpenses);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
