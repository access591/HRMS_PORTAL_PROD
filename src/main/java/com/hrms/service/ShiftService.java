package com.hrms.service;

import java.util.List;

import com.hrms.model.Shift;

public interface ShiftService {
	public void addShift(Shift shift);
	List<Shift>getAllShifts();
	Shift findShiftById(String id);
	public void updateShift(Shift s);
	public void removeShift(String id);

}
