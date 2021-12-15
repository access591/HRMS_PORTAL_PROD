package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.Insurance;
import com.hrms.repository.InsuranceDao;

@Service
public class InsuranceServiceImpl implements InsuranceService{
@Autowired
InsuranceDao insuranceDao;
	@Override
	public void addInsurance(Insurance insurance) {
		insurance.setInsCode(insuranceDao.getMaxId("INS"));
		this.insuranceDao.save(insurance);
		
	}

	@Override
	public Insurance findInsuranceById(String id) {
	
		return insuranceDao.findById(id);
	}

	@Override
	public void updateInsurance(Insurance ins) {
	
		this.insuranceDao.saveOrUpdate(ins);
	}

	@Override
	public void removeInsurance(String id) {
		this.insuranceDao.delete(id);
		
	}

	@Override
	public List<Insurance> getAllInsurances() {
		
		return insuranceDao.findAll();
		 
	}

}
