package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LocalConvyence;

import com.hrms.repository.LocalConvyenceDao;

@Service
public class LocalConvyenceServiceImpl implements LocalConvyenceService{
@Autowired
LocalConvyenceDao localConvyenceDao;

@Autowired
SessionFactory sessionfactory;

	@Override
	public void addlocalConvyence(LocalConvyence localConv) {
		localConv.setLocalConvId(localConvyenceDao.getMaxId("LCC"));
	
		this.localConvyenceDao.saveOrUpdate(localConv);
	}
	
	@Override
	public List<LocalConvyence> getAlllocalConvyence() {
		
		return this.localConvyenceDao.findAll();
	}
	
	
	@Override
	public void removelocalConvyence(String id) {
		
		Session session = sessionfactory.openSession();
		Object o = session.get(LocalConvyence.class, id);
		LocalConvyence e = (LocalConvyence) o;
		Transaction tx = session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();
		this.localConvyenceDao.delete(id);
		
	}
	

	@Override
	public LocalConvyence findByIdLocalConvyence(String id) {
		return this.localConvyenceDao.findById(id);
	}

	@Override
	public void updateLocalConvyence(LocalConvyence localConvyence) {

		this.localConvyenceDao.saveOrUpdate(localConvyence);
	}

}
