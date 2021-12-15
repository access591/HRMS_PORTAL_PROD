package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Activities;
import com.hrms.repository.ActivityDao;

@Service
public class ActivityServiceImpl implements ActivityService {
@Autowired
ActivityDao activityDao;
	@Override
	public void addActivity(Activities act) {
		act.setActCode(activityDao.getMaxId("ACT"));
		this.activityDao.saveOrUpdate(act);
	}

	@Override
	public List<Activities> getAllActivitys() {
		List<Activities> listActivity=null;
		listActivity= activityDao.findAll();
		return listActivity;
	}

	@Override
	public Activities findActivityById(String id) {
		return activityDao.findById(id);
	}

	@Override
	public void updateActivity(Activities a) {
		this.activityDao.saveOrUpdate(a);
		
	}

	@Override
	public void removeActivity(String id) {
		
		this.activityDao.delete(id);
	}

}
