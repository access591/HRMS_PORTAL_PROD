package com.hrms.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ApplicantExpDetail;
import com.hrms.service.ApplicantExpDetailService;

@Service
public class ApplicantExpDetailServiceImpl implements  ApplicantExpDetailService{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void addApplicantExpDetail(ApplicantExpDetail applicantExpDetail) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(applicantExpDetail);
		session.getTransaction().commit();
		session.close();
	}

}
