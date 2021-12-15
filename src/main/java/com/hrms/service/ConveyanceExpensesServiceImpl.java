package com.hrms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ConveyanceExpenses;
import com.hrms.repository.ConveyanceExpensesDao;

@Service
public class ConveyanceExpensesServiceImpl implements ConveyanceExpensesService {
	@Autowired
	ConveyanceExpensesDao conveyanceExpensesDao;
	
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addConveyanceExpenses(ConveyanceExpenses convExp) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(convExp);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
