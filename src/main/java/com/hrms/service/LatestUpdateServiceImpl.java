package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LatestUpdate;
import com.hrms.repository.LatestUpdateDao;

@Service
public class LatestUpdateServiceImpl implements LatestUpdateService {
@Autowired LatestUpdateDao latestUpdateDao;

	@Override
	public List<LatestUpdate> getAllLatestUpdate() {
	
		try {
			return latestUpdateDao.findAll();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addLatestUpdate(LatestUpdate latestUpdate) {

		try {
			this.latestUpdateDao.save(latestUpdate);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
	}

	@Override
	public LatestUpdate findLatestUpdateById(long id) {

		try {
			return latestUpdateDao.findById(id);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateLatestUpdate(LatestUpdate l) {
	try {
		this.latestUpdateDao.saveOrUpdate(l);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
		
	}

	@Override
	public void removeLatestUpdate(long id) {
		try {
			this.latestUpdateDao.delete(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

}
