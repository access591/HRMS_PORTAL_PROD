package com.hrms.service;

import java.util.List;

import com.hrms.model.LocalConvyenceDetail;

public interface LocalConvyenceDetailService {

	boolean addTourPlanDetail(LocalConvyenceDetail localConvyenceDetail);
	
	public List<LocalConvyenceDetail> findLocalConvyenceDetailByEmpCode(String empCode);
	
	public List<LocalConvyenceDetail> findAllLocalConvyenceDetail();

}
