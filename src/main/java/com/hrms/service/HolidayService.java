package com.hrms.service;

import java.util.List;

import com.hrms.model.Holiday;

public interface HolidayService {
	
	public void addHoliday(Holiday holiday);
	   List<Holiday>getAllHolidays();
	   Holiday findHolidayById(String id);
	   public void updateHoliday(Holiday h); 
	   public void removeHoliday(String id);
	

}
