package com.hrms.service;

import java.util.List;

import com.hrms.model.Insurance;

public interface InsuranceService {
	public void addInsurance(Insurance insurance);
	Insurance findInsuranceById(String id);
	public void updateInsurance(Insurance i);
	public void removeInsurance(String id);
	List<Insurance>getAllInsurances();

}
