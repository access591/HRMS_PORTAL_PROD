package com.hrms.service;

import java.sql.Date;
import java.util.List;

import com.hrms.model.TourClaim;

public interface TourClaimService {

	void AddTourClaim(TourClaim tourClaim);
	
	public List<TourClaim> getAllTourClaimBetweenDate(Date fromDate,Date toDate);
	public List<TourClaim> findTourClaimByEmpCodeBetweenDate(String empCode,Date fromDate,Date toDate);

	List<TourClaim> getAllTourClaim();

}
