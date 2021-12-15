package com.hrms.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ArmsLicensesDetail;
import com.hrms.repository.ArmsLicenseDao;
import com.hrms.service.ArmsLicensesDetailService;

@Service
public class ArmsLicenseDetailServiceImpl implements ArmsLicensesDetailService{

	@Autowired
	SessionFactory sessionFactory;
	@Autowired ArmsLicenseDao armsLicenseDao;
	@Override
	public void addArmsLicensesDetail(ArmsLicensesDetail armsLicensesDetail) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(armsLicensesDetail);
			tx.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
	}

}
