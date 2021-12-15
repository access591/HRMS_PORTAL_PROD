package com.hrms.service;

import java.util.List;

import com.hrms.model.Leave;

public interface LeaveService 
{
	public void addLeave(Leave leave);
	List<Leave>getAllLeaves();
	Leave findLeaveById(String id);
	public void updateLeave(Leave d);
	public void removeLeave(String id);
}
