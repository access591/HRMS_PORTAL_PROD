package com.hrms.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.repository.TourPlanDetailDao;
import com.hrms.model.TourPlanDetails;

@Service
public class TourPlanDetailServiceImpl implements TourPlanDetailService{
	@Autowired
	TourPlanDetailDao tourPlanDetailDao;
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addTourPlanDetail(TourPlanDetails tourPlanDetail) {
		
		
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		
		s.save(tourPlanDetail);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
