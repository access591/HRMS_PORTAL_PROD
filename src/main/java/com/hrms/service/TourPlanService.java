package com.hrms.service;

import java.util.List;


import com.hrms.model.TourPlan;


public interface TourPlanService {

	public void addTourPlan(TourPlan tourPlan);
	List<TourPlan>getAllTourPlan();
	public void updateTourPlan(TourPlan c);
	public void removeTourPlan(String id);
	public TourPlan findByIdTourPlan(String id);
	public List<TourPlan> findTourPlanByEmpCode(String  employee);
}
