package com.hrms.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ReqAdvertisement;
import com.hrms.repository.RequisitionAdvertisementDao;
import com.hrms.service.RequisitionAdvertisementService;

@Service
public class RequisitionAdvertisementServiceImpl implements  RequisitionAdvertisementService{

	@Autowired RequisitionAdvertisementDao requisitionAdvertisementDao;
	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void addActivity(ReqAdvertisement reqAdvertisement) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			reqAdvertisement.setAdvtCode(requisitionAdvertisementDao.getMaxId("ADT"));
		    session.save(reqAdvertisement);
		    
		    tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	    
	}

	@Override
	public List<ReqAdvertisement> getAllReqAdvertisement() {
		
		return this.requisitionAdvertisementDao.findAll();
	}

	@Override
	public ReqAdvertisement findReqAdvertisementById(String id) {
		
		return this.requisitionAdvertisementDao.findById(id);
	}

	@Override
	public void updateReqAdvertisement(ReqAdvertisement a) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ReqAdvertisement r = session.find(ReqAdvertisement.class, a.getAdvtCode());
			r.getListReqAdvertisementDetail().clear();
			r.getListReqAdvertisementDetail().addAll(a.getListReqAdvertisementDetail());
			
			session.merge(a);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void removeReqAdvertisement(String id) {
		this.requisitionAdvertisementDao.delete(id);
		
	}

	
	
}
