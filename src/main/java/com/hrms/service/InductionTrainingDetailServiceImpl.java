package com.hrms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.InductionTrainingDetail;

@Service
public class InductionTrainingDetailServiceImpl implements InductionTrainingDetailService {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addInductionTrainingDetail(InductionTrainingDetail inductDetails) {

		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(inductDetails);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
