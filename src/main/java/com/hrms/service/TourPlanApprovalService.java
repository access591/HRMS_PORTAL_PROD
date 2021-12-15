package com.hrms.service;

import java.util.List;

import com.hrms.model.TourPlan;

public interface TourPlanApprovalService {

	List<TourPlan> getAllTourPlan();

	void approvedByTourPlanId(String id);

}
