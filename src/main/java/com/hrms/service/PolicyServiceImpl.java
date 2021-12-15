package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Policy;
import com.hrms.repository.PolicyDao;

@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	PolicyDao policyDao;

	@Override
	public void addPolicy(Policy policy) {
		try {
			this.policyDao.save(policy);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Policy> getAllPolicy() {
		try {
			return policyDao.findAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Policy findPolicyById(long id) {
		try {
			return policyDao.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePolicy(Policy p) {
		try {
			this.policyDao.saveOrUpdate(p);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void removePolicy(long id) {
		try {
			this.policyDao.delete(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
