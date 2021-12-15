package com.hrms.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ReqAdvertisementDetail;
import com.hrms.repository.ReqAdvertisementDetailDao;
import com.hrms.service.ReqAdvertisementDetailService;

@Service
public class ReqAdvertisementDetailServiceIpml implements ReqAdvertisementDetailService{

	@Autowired ReqAdvertisementDetailDao reqAdvertisementDetailDao;
	@Autowired SessionFactory sessionFactory;
	@Override
	public void addActivity(ReqAdvertisementDetail reqAdvertisementDetail) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			 tx = session.beginTransaction();
			 session.save(reqAdvertisementDetail);
			 tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	   
		
		
	}

	@Override
	public List<ReqAdvertisementDetail> getAllReqAdvertisement() {
		
		return this.reqAdvertisementDetailDao.findAll();
	}

	@Override
	public ReqAdvertisementDetail findReqAdvertisementById(String id) {
		
		return this.reqAdvertisementDetailDao.findById(id);
	}

	@Override
	public void updateReqAdvertisement(ReqAdvertisementDetail a) {
		this.reqAdvertisementDetailDao.saveOrUpdate(a);
		
	}

	@Override
	public void removeReqAdvertisement(Long id) {
		this.reqAdvertisementDetailDao.delete(id);
		
	}

	

}
