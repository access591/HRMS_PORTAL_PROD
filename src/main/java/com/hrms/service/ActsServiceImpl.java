package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Acts;
import com.hrms.repository.ActsDao;
@Service
public class ActsServiceImpl implements ActsService {
	@Autowired  ActsDao actsDao;
	@Override
	public void addActs(Acts acts) {
		try {
			this.actsDao.save(acts);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Acts> getAllActs() {

		try {
			return actsDao.findAll();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Acts findActsById(long id) {
	
		try {
			return actsDao.findById(id);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateActs(Acts n) {
	try {
		this.actsDao.saveOrUpdate(n);
	} catch (Exception e) {
	
		e.printStackTrace();
	}	
		
	}

	@Override
	public void removeActs(long id) {
		try {
			this.actsDao.delete(id);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}

}
