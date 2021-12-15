package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TourPlan;
import com.hrms.repository.TourPlanApprovalDao;

@Service
public class TourPlanApprovalServiceImpl implements TourPlanApprovalService{

	@Autowired TourPlanApprovalDao tourPlanApprovalDao;
	
	
	@Override
	public List<TourPlan> getAllTourPlan() {
		return  tourPlanApprovalDao.getAllTourPlan();
		
		
	}


	@Override
	public void approvedByTourPlanId(String id) {
	this.tourPlanApprovalDao.approvedByTourPlanId(id);
		
	}
	

}
