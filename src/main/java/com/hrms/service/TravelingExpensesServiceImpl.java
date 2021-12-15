package com.hrms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TravelingExpenses;
import com.hrms.repository.TravelingExpensesDao;

@Service
public class TravelingExpensesServiceImpl implements TravelingExpensesService{
@Autowired
TravelingExpensesDao travelingExpensesDao;
@Autowired
SessionFactory sessionFactory;
	@Override
	public boolean addTravelingExpenses(TravelingExpenses travExp) {

		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		// TODO Auto-generated method stub
		s.save(travExp);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
