package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Travel;
import com.hrms.repository.TravelDao;

@Service
public class TravelServiceImpl implements TravelService {
@Autowired
TravelDao  travelDao;
	@Override
	public void addTravel(Travel travel) {
		travel.setTravelCode(travelDao.getMaxId("TVL"));
		this.travelDao.saveOrUpdate(travel);
		
	}

	@Override
	public List<Travel> getAllTravels() {
	
		return travelDao.findAll();
		
	}

	@Override
	public Travel findTravelById(String id) {
	
		return travelDao.findById(id);
	}

	@Override
	public void updateTravel(Travel t) {
	
	this.travelDao.saveOrUpdate(t);	
	}

	@Override
	public void removeTravel(String id) {
		this.travelDao.delete(id);
		
	}

}
