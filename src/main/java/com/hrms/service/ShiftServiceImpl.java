package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.Shift;
import com.hrms.repository.ShiftDao;
@Service
public class ShiftServiceImpl implements ShiftService {
@Autowired
ShiftDao shiftDao;
	@Override
	public void addShift(Shift shift) {
		shift.setShiftCode(shiftDao.getMaxId("SFC"));
		this.shiftDao.saveOrUpdate(shift);
		
	}

	@Override
	public List<Shift> getAllShifts() {
	
		return shiftDao.findAll();
		
	}

	@Override
	public Shift findShiftById(String id) {
	
		return shiftDao.findById(id);
	}

	@Override
	public void updateShift(Shift s) {
		this.shiftDao.saveOrUpdate(s);
		
	}

	@Override
	public void removeShift(String id) {
		this.shiftDao.delete(id);

		
	}

}
