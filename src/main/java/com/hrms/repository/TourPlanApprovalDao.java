package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.TourPlan;

public interface TourPlanApprovalDao extends GenericDao<TourPlan>{

	List<TourPlan> getAllTourPlan();

	void approvedByTourPlanId(String id);

}
