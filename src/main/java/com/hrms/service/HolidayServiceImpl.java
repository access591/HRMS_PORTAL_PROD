package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.Holiday;
import com.hrms.repository.HolidayDao;
@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	HolidayDao holidayDao;
	@Override
	public void addHoliday(Holiday holiday) {
		holiday.setHolidayCode(holidayDao.getMaxId("HLD"));
		holiday.setInsDate(new Date());
		  this.holidayDao.saveOrUpdate(holiday);
		
	}
	
	@Override
	public List<Holiday> getAllHolidays() {
		return holidayDao.findAll();
		 
	}
	@Override
	public Holiday findHolidayById(String id) {
		
		return holidayDao.findById(id);
	}

	@Override
	public void updateHoliday(Holiday h)
	{	
		h.setDescription(h.getDescription());
		h.setDateOfHoliday(h.getDateOfHoliday());
		h.setHolidayType(h.getHolidayType());
		h.setUpdDate(h.getUpdDate());
		h.setActive(h.getActive());
		this.holidayDao.saveOrUpdate(h);
		
		
	}

	@Override
	public void removeHoliday(String id) {
		
		this.holidayDao.delete(id);
	}




}
