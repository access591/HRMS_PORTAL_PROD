package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Information;
import com.hrms.repository.InformationDao;

@Service
public class InformationserviceImpl implements Informationservice {

	@Autowired InformationDao  informationDao;
	
	
	@Override
	public List<Information> getAllInformation() {
		
		try {
			return informationDao.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addInformation(Information information) {
		try {
			this.informationDao.save(information);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public Information findInformationById(long id) {
		
		try {
			return informationDao.findById(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateInformation(Information i) {
	try {
		this.informationDao.saveOrUpdate(i);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		
	}

	@Override
	public void removeInformation(long id) {
	
		try {
			this.informationDao.delete(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
