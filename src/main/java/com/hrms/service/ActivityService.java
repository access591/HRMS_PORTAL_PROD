package com.hrms.service;

import java.util.List;

import com.hrms.model.Activities;

public interface ActivityService {

	public void addActivity(Activities act);
	List<Activities>getAllActivitys();
	Activities findActivityById(String id);
	public void updateActivity(Activities a);
	public void removeActivity(String id);
}
