package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Eservices;
import com.hrms.repository.EservicesDao;
@Service
public class EservicesServiceImpl implements EservicesService {
@Autowired EservicesDao  eservicesDao;
	
	
	@Override
	public List<Eservices> getAllEservices() {

		try {
			return eservicesDao.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void addEservices(Eservices eServices) {
	
		try {
			this.eservicesDao.saveOrUpdate(eServices);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}

	@Override
	public Eservices findEservicesById(long id) {

		try {
			return eservicesDao.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateEservices(Eservices e) {
		try {
			this.eservicesDao.saveOrUpdate(e);
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public void removeEservices(long id) {
		try {
			this.eservicesDao.delete(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
