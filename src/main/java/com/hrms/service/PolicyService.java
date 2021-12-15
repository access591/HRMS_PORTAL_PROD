package com.hrms.service;

import java.util.List;

import com.hrms.model.Policy;

public interface PolicyService {

	void addPolicy(Policy policy);

	List<Policy> getAllPolicy();

	Policy findPolicyById(long id);

	public void updatePolicy(Policy p);

	public void removePolicy(long id);

}
