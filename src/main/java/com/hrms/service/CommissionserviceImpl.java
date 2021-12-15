package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Commission;
import com.hrms.repository.CommissionDao;

@Service
public class CommissionserviceImpl implements Commissionservice {
	@Autowired
	CommissionDao commissionDao;

	@Override
	public List<Commission> getAllCommissions() {

		try {
			return commissionDao.findAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addCommission(Commission commission) {
		try {
			this.commissionDao.save(commission);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public Commission findCommissionById(long id) {

		try {
			return commissionDao.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateCommission(Commission c) {
		try {
			this.commissionDao.saveOrUpdate(c);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void removeCommission(long id) {
		try {
			this.commissionDao.delete(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
