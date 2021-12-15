package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MiscAllowance;
import com.hrms.repository.MiscAllowanceDeductionDao;

@Service
public class MiscAllowanceDeductionServiceImpl implements MiscAllowanceDeductionService {
	@Autowired
	MiscAllowanceDeductionDao miscAllowanceDeductionDao;

	@Override
	public void addMiscAllowanceDeduction(MiscAllowance miscAllowance) {
		miscAllowance.setInsDate(new Date());
		miscAllowance.setAllowanceCode(miscAllowanceDeductionDao.getMaxId("MAD"));
		this.miscAllowanceDeductionDao.saveOrUpdate(miscAllowance);

	}

	@Override
	public List<MiscAllowance> getAllMiscAllowanceDeduction() {

		return miscAllowanceDeductionDao.findAll();

	}

	@Override
	public MiscAllowance findMiscAllowanceDeductionById(String id) {
		return miscAllowanceDeductionDao.findById(id);

	}

	@Override
	public void updateMiscAllowanceDeduction(MiscAllowance miscAllowance) {
		miscAllowance.setDescription(miscAllowance.getDescription());
		miscAllowance.setHead(miscAllowance.getHead());
		miscAllowance.setType(miscAllowance.getType());
		miscAllowance.setActCode(miscAllowance.getActCode());
		miscAllowance.setAccountName(miscAllowance.getAccountName());
		miscAllowance.setSubGroupCode(miscAllowance.getSubGroupCode());
		miscAllowance.setSubGroupName(miscAllowance.getSubGroupName());
		miscAllowance.setUpdDate(miscAllowance.getUpdDate());
		miscAllowance.setActive(miscAllowance.getActive());
		this.miscAllowanceDeductionDao.saveOrUpdate(miscAllowance);

	}

	@Override
	public void removeMiscAllowanceDeduction(String id) {
		this.miscAllowanceDeductionDao.delete(id);

	}

}
