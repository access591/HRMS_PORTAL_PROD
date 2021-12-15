package com.hrms.service;

import java.util.List;

import com.hrms.model.Travel;

public interface TravelService {
	public void addTravel(Travel travel);
	List<Travel>getAllTravels();
	Travel findTravelById(String id);
	public void updateTravel(Travel c);
	public void removeTravel(String id);

}
